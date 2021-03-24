package com.example.demo.repository;

import com.example.demo.entity.BookEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.model.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements CustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<BookEntity> searchBook(SearchCondition condition) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        Metamodel m = em.getMetamodel();
        EntityType<BookEntity> bookEntityEntityType = m.entity(BookEntity.class);

        CriteriaQuery cq = cb.createQuery(BookEntity.class);

        Root<BookEntity> book = cq.from(bookEntityEntityType);

        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(condition.getISBN())) {
            predicates.add(cb.like(book.get("ISBN"), "%" +condition.getISBN()+"%"));
        }
        if(!StringUtils.isEmpty(condition.getName())){
            predicates.add(cb.like(book.get("name"), "%" + condition.getName() + "%"));
        }
        if(!StringUtils.isEmpty(condition.getAuthor())){
            predicates.add(cb.like(book.get("author"), "%" + condition.getAuthor() + "%"));
        }
        if(!StringUtils.isEmpty(condition.getShortDescription())){
            predicates.add(cb.like(book.get("shortDescription"), "%" + condition.getShortDescription() + "%"));
        }
        if(condition.getPublishYear()>0) {
            predicates.add(cb.equal(book.get("publishYear"), condition.getPublishYear()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        if(condition.getTotalNumberTicket()>0){
            ListJoin<BookEntity, ?> tickets = book.join(bookEntityEntityType.getList("tickets"));
            cq.groupBy(book.get("id")).having(cb.equal(cb.count(tickets),condition.getTotalNumberTicket()));
        }
        return em.createQuery(cq).getResultList();
    }
}

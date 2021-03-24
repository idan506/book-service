package com.example.demo.repository;

import com.example.demo.entity.BookEntity;
import com.example.demo.model.Book;
import com.example.demo.model.SearchCondition;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long>, CustomRepository{

    @Modifying
    @Query(value="UPDATE BookEntity b SET b.flag = false where b.id = :id")
    int deleteBook(@Param("id") long id);

    //update status sau khi user muon sach, quantity = 0 -> stauts false
    @Modifying
    @Query(value = "UPDATE BookEntity b SET b.status = false where b.id=:id")
    void updateStatus(@Param("id") long id);

    //update status sau khi user tra sach, status true, tang so luong còn của sách
    @Modifying
    @Query(value = "UPDATE BookEntity b SET b.status = true where b.id=:id")
    void updateStatusBook(@Param("id") long id);

    @Modifying
    @Query(value = "Select b from BookEntity b where b.flag = true")
    List<BookEntity> getBookByFlag();

    @Modifying
    @Query(value = "Select b from BookEntity b where b.flag = true and b.quantity > 0")
    List<BookEntity> getBookByStatus();

    List<BookEntity> searchBook(SearchCondition condition);
}

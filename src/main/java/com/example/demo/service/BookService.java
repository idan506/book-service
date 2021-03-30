package com.example.demo.service;

import com.example.demo.convert.BookConvert;
import com.example.demo.entity.BookEntity;
import com.example.demo.model.Book;
import com.example.demo.model.SearchCondition;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookConvert bookConvert;

    //tất cả sách còn hoạt động, chưa bị xóa
    @Transactional
	public List<Book> getAllBook() {
        List<Book> result = repository.getBookByFlag().stream()
                .map(x -> bookConvert.toModel(x))
                .collect(Collectors.toList());
        return result;
    }

    //tất cả book còn User có thể mượn
    @Transactional
    public List<Book> getAllBookAvailable() {
        List<Book> result = repository.getBookByStatus().stream()
                .map(x -> bookConvert.toModel(x))
                .collect(Collectors.toList());
        return result;
    }
	
    public Page retrieveBooks(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page result = repository.findAll(pageRequest).map(x -> bookConvert.toModel(x));
        return result;
    }

    //find book by id
    public Book findByid(long id){
        BookEntity entity = repository.findById(id).get();
        return bookConvert.toModel(entity);
    }

    //delete book by id
    @Transactional
    public int deleteBook(long id) {
        return repository.deleteBook(id);
    }

    //create new book
    public Book createBook(Book book) {
        BookEntity b = bookConvert.toEntity(book);
        b = repository.save(b);
        return bookConvert.toModel(b);
    }

    public Book updateBook(Book book){
	    BookEntity b = bookConvert.toEntity(book);
	    b = repository.save(b);
        return bookConvert.toModel(b);
    }

    //list search
    public List<Book> searchResult(SearchCondition condition){
        List<Book> list = repository.searchBook(condition).stream().map(x->bookConvert.toModel(x)).collect(Collectors.toList());
        return list;
    }

}

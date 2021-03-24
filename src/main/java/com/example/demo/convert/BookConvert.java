package com.example.demo.convert;

import com.example.demo.entity.BookEntity;
import com.example.demo.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConvert {

    @Autowired
    private ModelMapper modelMapper;

    public Book toModel(BookEntity entity){
        return modelMapper.map(entity, Book.class);
    }
    public BookEntity toEntity(Book book){
        return modelMapper.map(book, BookEntity.class);
    }
}

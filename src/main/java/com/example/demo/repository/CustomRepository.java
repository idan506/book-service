package com.example.demo.repository;

import com.example.demo.entity.BookEntity;
import com.example.demo.model.SearchCondition;

import java.util.List;

public interface CustomRepository {
    List<BookEntity> searchBook(SearchCondition condition);
}

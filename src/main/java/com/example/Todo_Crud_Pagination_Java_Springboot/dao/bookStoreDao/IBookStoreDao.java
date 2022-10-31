package com.example.Todo_Crud_Pagination_Java_Springboot.dao.bookStoreDao;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Book;

import java.util.List;

public interface IBookStoreDao {
    List<Book> getBooks();
    Book getBook(int bookId);
    Book createBook(Book book);
    Book updateBook(int bookId,Book book);
    boolean deleteBook(int bookId);
}

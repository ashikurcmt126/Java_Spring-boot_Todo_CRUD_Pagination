package com.example.Todo_Crud_Pagination_Java_Springboot.services.bookService;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Book;

import java.util.List;

public interface IBookStoreService {
    List<Book> getBooks();
    Book createBook(Book book);
    Book updateBook(int bookId, Book book);
    Book getBook(int bookId);
    boolean deleteBook(int bookId);
}

package com.example.Todo_Crud_Pagination_Java_Springboot.services.bookService;

import com.example.Todo_Crud_Pagination_Java_Springboot.dao.bookStoreDao.IBookStoreDao;
import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreService implements IBookStoreService{

    @Autowired
    private IBookStoreDao dao;

    @Override
    public List<Book> getBooks() {
        return dao.getBooks();
    }

    @Override
    public Book createBook(Book book) {
        return dao.createBook(book);
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        return dao.updateBook(bookId, book);
    }

    @Override
    public Book getBook(int bookId) {
        return dao.getBook(bookId);
    }

    @Override
    public boolean deleteBook(int bookId) {
        return dao.deleteBook(bookId);
    }
}

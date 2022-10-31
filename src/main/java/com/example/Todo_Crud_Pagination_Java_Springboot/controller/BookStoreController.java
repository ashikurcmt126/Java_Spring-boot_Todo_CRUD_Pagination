package com.example.Todo_Crud_Pagination_Java_Springboot.controller;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Book;
import com.example.Todo_Crud_Pagination_Java_Springboot.services.bookService.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/")
public class BookStoreController {

    @Autowired
    private IBookStoreService service;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getBooks(){

        List<Book> books = service.getBooks();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Integer id){
        Book book = service.getBook(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping("books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book b = service.createBook(book);
        return new ResponseEntity<Book>(b, HttpStatus.OK);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        Book b = service.updateBook(id, book);
        return new ResponseEntity<Book>(b, HttpStatus.OK);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id){
        boolean isDeleted = service.deleteBook(id);
        if(isDeleted){
            String responseContent = "Book has been deleted successfully";
            return new ResponseEntity<String>(responseContent,HttpStatus.OK);
        }
        String error = "Error while deleting book from database";
        return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

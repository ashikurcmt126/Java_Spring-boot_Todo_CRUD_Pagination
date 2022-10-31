package com.example.Todo_Crud_Pagination_Java_Springboot.dao.bookStoreDao;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class BookStoreDao implements IBookStoreDao{

    @PersistenceContext
    private EntityManager entityManager;


    // https://www.bezkoder.com/jpa-entitymanager-spring-boot/
    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getBooks() {
        //String hql = "FROM Book as atcl ORDER BY atcl.id";
        String sql = "Select b from Book as b order by b.id";
        return (List<Book>) entityManager.createQuery(sql).getResultList();
    }

    @Override
    public Book getBook(int bookId) {
        return entityManager.find(Book.class, bookId);
    }

    @Override
    public Book createBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book bookFromDB = getBook(bookId);
        bookFromDB.setName(book.getName());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setCategory(book.getCategory());
        bookFromDB.setPublication(book.getPublication());
        bookFromDB.setPages(book.getPages());
        bookFromDB.setPrice(book.getPrice());

        entityManager.flush();

        //again i am taking updated result of book and returning the book object
        Book updatedBook = getBook(bookId);

        return updatedBook;
    }

    @Override
    public boolean deleteBook(int bookId) {
        Book book = getBook(bookId);
        entityManager.remove(book);

        //we are checking here that whether entityManager contains earlier deleted book or not
        // if contains then book is not deleted from DB that's why returning false;
        boolean status = entityManager.contains(book);
        if(status){
            return false;
        }
        return true;
    }

    private Book getLastInsertedBook(){
        String hql = "from Book order by id DESC";
        Query query = entityManager.createQuery(hql);
        query.setMaxResults(1);
        Book book = (Book)query.getSingleResult();
        return book;
    }
}

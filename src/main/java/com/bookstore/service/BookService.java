package com.bookstore.service;

import com.bookstore.dao.BookDao;
import com.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public Book createBook(Book book){
        book.setStockDate(new Date());
        return bookDao.save(book);
    }

    public List<Book> getAllBooks(){
        List<Book> bookList = bookDao.findAll();
        return bookList;
    }

    public void updateBookStock(String id, int stockCount){
        Optional<Book> book = bookDao.findById(id);
        if(book.isPresent()){
            Book book1 = book.get();
            book1.setStockCount(stockCount);
            bookDao.save(book1);
        }
    }

    public void deleteBooks(String id){
        bookDao.deleteById(id);
    }
}

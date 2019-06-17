package com.bookstore.demo.service;

import com.bookstore.demo.mapper.BookMapper;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.model.BookExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;



    public void addBook(Book book){

        UUID uuid = UUID.randomUUID();
        book.setBookNumber(uuid.toString());
        bookMapper.insert(book);
    }

    public void delBook(String bookNumber){
        bookMapper.deleteByPrimaryKey(bookNumber);
    }

    public void updateBook(Book book){
        bookMapper.updateByPrimaryKeySelective(book);
    }

    public List<Book> findAllBook(){
        return bookMapper.selectByExample(new BookExample());
    }
}

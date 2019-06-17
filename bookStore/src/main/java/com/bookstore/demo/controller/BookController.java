package com.bookstore.demo.controller;

import com.bookstore.demo.model.Book;
import com.bookstore.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/add")
    public void addBook(@RequestBody  Book book){
        bookService.addBook(book);
    }

    @RequestMapping("delete")
    public void delBook(@RequestBody Map map){
        String bookId = (String) map.get("bookNumber");
        bookService.delBook(bookId);
    }

    @RequestMapping("update")
    public void updateBook(@RequestBody Book book){
        bookService.updateBook(book);
    }

    @RequestMapping("find")
    public List<Book> find(@RequestBody Book book){
        return bookService.findAllBook();
    }

}

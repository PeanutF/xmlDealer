package com.bookstore.demo.controller;

import com.bookstore.demo.model.Book;
import com.bookstore.demo.model.Order;
import com.bookstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.UUID;

@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/commit")
    public void commitOrder(@RequestBody List<Book> books, @RequestBody int userId){
        orderService.commitOrder(books,userId);
    }

    @RequestMapping("/addToCart")
    public void addCart(@RequestBody Book book, @RequestBody int userId){
        orderService.addCart(book,userId);
    }

    @RequestMapping("/showCart")
    public List<Book> showCart(@RequestBody int userId){
        return orderService.showCart(userId);
    }

    @RequestMapping("/showOrder")
    public List<Order> showOrder(@RequestBody int userId){
        return orderService.showOrder(userId);
    }

}

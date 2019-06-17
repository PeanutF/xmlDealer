package com.bookstore.demo.service;

import com.bookstore.demo.mapper.BookMapper;
import com.bookstore.demo.mapper.BookOrderMapper;
import com.bookstore.demo.mapper.OrderMapper;
import com.bookstore.demo.model.*;
import jdk.internal.loader.BootLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    BookOrderMapper bookOrderMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BookMapper bookMapper;

    public void commitOrder(List<Book> books, int userId){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        float price = 0;
        int totalQuantity = 0;
        for (Book book : books){
            price += book.getPrice() * book.getQuantity();
            totalQuantity += book.getQuantity();
            BookOrder bookOrder = new BookOrder();
            bookOrder.setBookNumber(book.getBookNumber());
            bookOrder.setOrderNumber(order.getOrderNumber());
            bookOrderMapper.insert(bookOrder);
        }

        order.setUserNumber(userId);
        order.setTotalPrice(price);
        order.setTotalQuantity(totalQuantity);
        order.setPaymentStatus(1);

    }


    public void addCart(Book book, int userId){

        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserNumberEqualTo(userId);
        criteria.andPaymentStatusEqualTo(0);

        Order order;

        if (orderMapper.selectByExample(orderExample) == null){
            order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPaymentStatus(0);
            order.setUserNumber(userId);
        }else {
            order = orderMapper.selectByExample(orderExample).get(0);

        }

        BookOrder bookOrder = new BookOrder();
        bookOrder.setOrderNumber(order.getOrderNumber());
        bookOrder.setBookNumber(book.getBookNumber());
        bookOrderMapper.insert(bookOrder);
    }

    public List<Book> showCart(int userId){
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserNumberEqualTo(userId);
        criteria.andPaymentStatusEqualTo(0);

        Order order;

        if (orderMapper.selectByExample(orderExample) == null){
            return null;
        }else {
            List<Book> returnBook = new ArrayList<>();

            order = orderMapper.selectByExample(orderExample).get(0);
            String orderId = order.getOrderNumber();
            BookOrderExample bookOrderExample = new BookOrderExample();
            BookOrderExample.Criteria criteria1 = bookOrderExample.createCriteria();
            criteria1.andOrderNumberEqualTo(orderId);
            List<BookOrder> bookOrders = bookOrderMapper.selectByExample(bookOrderExample);

            for (BookOrder bookOrder : bookOrders){
                returnBook.add(bookMapper.selectByPrimaryKey(bookOrder.getBookNumber()));
            }

            return returnBook;


        }
    }
}

package com.bookstore.demo.service;

import com.bookstore.demo.mapper.BookMapper;
import com.bookstore.demo.mapper.BookOrderMapper;
import com.bookstore.demo.mapper.OrderlistMapper;
import com.bookstore.demo.model.*;
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
    OrderlistMapper orderMapper;

    @Autowired
    BookMapper bookMapper;

    public void commitOrder(List<Book> books, int userId){
        Orderlist order = new Orderlist();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setUserNumber(111);
        order.setPaymentStatus(1);
        order.setTotalQuantity(0);
        order.setTotalPrice((float) 0.0);
        float price = 0;
        orderMapper.insertSelective(order);

        int totalQuantity = 0;
        for (Book book : books){
            price += book.getPrice() * book.getQuantity();
            totalQuantity += book.getQuantity();
            BookOrder bookOrder = new BookOrder();
            bookOrder.setBookNumber(book.getBookNumber());
            bookOrder.setOrderNumber(order.getOrderNumber());
            bookOrderMapper.insert(bookOrder);
            clearCart(book,userId);
            updateBookNumber(book);

        }

        order.setUserNumber(userId);
        order.setTotalPrice(price);
        order.setTotalQuantity(totalQuantity);
        order.setPaymentStatus(1);
        orderMapper.updateByPrimaryKeySelective(order);

    }

    public void updateBookNumber(Book book){
        Book newBook = bookMapper.selectByPrimaryKey(book.getBookNumber());
        newBook.setQuantity(newBook.getQuantity() - book.getQuantity());
        bookMapper.updateByPrimaryKey(newBook);
    }

    public void clearCart(Book book,int userId){
        OrderlistExample orderlistExample = new OrderlistExample();
        OrderlistExample.Criteria criteria = orderlistExample.createCriteria();
        criteria.andUserNumberEqualTo(userId);
        criteria.andPaymentStatusEqualTo(0);
        String orderId = orderMapper.selectByExample(orderlistExample).get(0).getOrderNumber();

        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria1 = bookOrderExample.createCriteria();
        criteria1.andOrderNumberEqualTo(orderId);
        criteria1.andBookNumberEqualTo(book.getBookNumber());


        bookOrderMapper.deleteByExample(bookOrderExample);

    }


    public void addCart(Book book, int userId){

        OrderlistExample orderExample = new OrderlistExample();
        OrderlistExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserNumberEqualTo(userId);
        criteria.andPaymentStatusEqualTo(0);

        Orderlist order;

        if (orderMapper.selectByExample(orderExample).size() == 0){
            order = new Orderlist();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPaymentStatus(0);
            order.setUserNumber(userId);
            order.setTotalQuantity(0);
            order.setTotalPrice((float)0);
            orderMapper.insert(order);
        }else {
            order = orderMapper.selectByExample(orderExample).get(0);

        }

        BookOrder bookOrder = new BookOrder();
        bookOrder.setOrderNumber(order.getOrderNumber());
        bookOrder.setBookNumber(book.getBookNumber());
        bookOrderMapper.insert(bookOrder);
    }

    public List<Book> showCart(int userId){
        OrderlistExample orderExample = new OrderlistExample();
        OrderlistExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserNumberEqualTo(userId);
        criteria.andPaymentStatusEqualTo(0);

        Orderlist order;

        if (orderMapper.selectByExample(orderExample).size() == 0){
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


    //显示订单时没有显示书籍
    public List<Orderlist> showOrder(int userId){
        OrderlistExample orderExample = new OrderlistExample();
        OrderlistExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserNumberEqualTo(userId);
        criteria.andPaymentStatusEqualTo(1);

        return orderMapper.selectByExample(orderExample);
    }
}

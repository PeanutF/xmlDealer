package com.bookstore.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.model.Orderlist;
import com.bookstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/commit")
    public void commitOrder(@RequestBody Map map){
        String jsonStr = JSONArray.toJSONString(map.get("books"));
        List<Book> books = JSONObject.parseArray(jsonStr,Book.class);
        int userId = (int) map.get("userId");



        orderService.commitOrder(books,userId);
    }

    @RequestMapping("/addToCart")
    public void addCart(@RequestBody Map map){
        int userId = (int) map.get("userId");
        String jsonStr = JSONArray.toJSONString(map.get("book"));
        Book book =  JSONArray.parseObject(jsonStr,Book.class);

        orderService.addCart(book,userId);
    }

    @RequestMapping("/showCart")
    public List<Book> showCart(@RequestBody Map map){

        int userId = (int) map.get("userId");
        return orderService.showCart(userId);
    }

    @RequestMapping("/showOrder")
    public List<Orderlist> showOrder(@RequestBody Map map){

        int userId = (int) map.get("userId");
        return orderService.showOrder(userId);
    }

}

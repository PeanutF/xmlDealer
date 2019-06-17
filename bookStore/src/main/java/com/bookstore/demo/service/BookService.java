package com.bookstore.demo.service;

import com.bookstore.demo.mapper.BookMapper;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.model.BookExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map statistics(){
        Map data = new HashMap();
        data.put("total",bookMapper.countByExample(new BookExample()));
        int totalStock = 0;
        List<Book> books = bookMapper.selectByExample(new BookExample());
        int low = 0, middle = 0, high = 0;


        for (int i = 0 ; i < books.size(); i++){
            totalStock += books.get(i).getQuantity();
            if (books.get(i).getPrice() > 100)
                high += 1;
            else if (books.get(i).getPrice() > 50)
                middle += 1;
            else if (books.get(i).getPrice() > 0)
                low += 1;
        }

        data.put("totalStock",totalStock);
        data.put("100+",high);
        data.put("50-100",middle);
        data.put("0-50",low);
        //todo 订单量
        return data;

    }
}

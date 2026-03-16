package com.example.demo.Mapper;


import com.example.demo.Dto.Request.BookRequest;
import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Entity.Book;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BookMapper {

    public Book RepoMapperToEntity(BookResponse bookResponse) {
        return  null;
    }

    public List<BookResponse> EntityMapperToResponse(List<Book> listbook){
          return listbook.stream()
                  .map(b -> {
                      BookResponse bookResponse = new BookResponse();
                      bookResponse.setId(b.getId());
                      bookResponse.setName(b.getName());
                      bookResponse.setAuthor(b.getAuthor());
                      bookResponse.setPrice(b.getPrice());
                      bookResponse.setDescription(b.getDescription());
                      bookResponse.setImageUrl(b.getImageUrl());
                      bookResponse.setSummary(b.getSummary());
                      bookResponse.setQuantity(b.getQuantity());
                      return  bookResponse;
                  })
                  .toList();
    }

    public Book RequestToEntity(BookRequest bookRequest){
        Book newbook = new Book();
        newbook.setAuthor(bookRequest.getAuthor());
        newbook.setDescription(bookRequest.getDescription());
        newbook.setName(bookRequest.getName());
        newbook.setPrice(bookRequest.getPrice());
        newbook.setQuantity(bookRequest.getQuantity());
        newbook.setSummary(bookRequest.getSummary());
        newbook.setImageUrl(bookRequest.getImageUrl());
        newbook.setCategoryId(bookRequest.getCategoryId());
        newbook.setCreatedAt(LocalDateTime.now());
        return newbook;
    }

    public BookResponse MapperOne(Optional<Book> b){

        Book book = b.get();   // lấy Book ra

        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setImageUrl(book.getImageUrl());
        bookResponse.setSummary(book.getSummary());
        bookResponse.setQuantity(book.getQuantity());
        return bookResponse;
    }





}
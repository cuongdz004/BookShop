package com.example.demo.Mapper;


import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Entity.Book;

import java.util.ArrayList;
import java.util.List;

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
                      return  bookResponse;
                  })
                  .toList();
    }




}

package com.example.demo.Repository;


import com.example.demo.Entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.createdAt DESC")
    List<Book> getTop10NewArrivals(Pageable pageable);

    @Query("SELECT b FROM Book b ORDER BY b.price DESC ")
    List<Book> getPriceHighToLow();

    @Query("SELECT b FROM Book b ORDER BY b.price DESC ")
    List<Book> getPriceLowToHigh();


}
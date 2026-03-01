package com.example.demo.Repository;


import com.example.demo.Entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.createdAt DESC")
    List<Book> getTop10NewArrivals(Pageable pageable);

    @Query("SELECT b FROM Book b ORDER BY b.price DESC ")
    List<Book> getPriceHighToLow();

    @Query("SELECT b FROM Book b ORDER BY b.price ASC ")
    List<Book> getPriceLowToHigh();

    @Query("""
SELECT b FROM Book b
WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Book> searchBooks(@Param("keyword") String keyword);

    @Query("""
SELECT b FROM Book b
WHERE b.price BETWEEN :minPrice AND :maxPrice
""")
    List<Book> filterByPrice(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );

    List<Book> findByCategories_Name(String categoryName);

}
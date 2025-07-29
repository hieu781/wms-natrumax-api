package com.natrumax.repository;

import com.natrumax.models.Report;
import com.natrumax.models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r " +
            "WHERE r.userProduct.user.id = :userId " +
            "AND r.userProduct.product.productId = :productId " +
            "AND r.month = :month " +
            "AND r.year = :year")
    Optional<Report> findByUserProductAndMonthAndYear(
            @Param("userId") Long userId,
            @Param("productId") Long productId,
            @Param("month") int month,
            @Param("year") int year
    );

    Optional<Report> findByUserProduct_User_IdAndUserProduct_Product_ProductIdAndMonthAndYear(
            Long userId, Long productId, int month, int year);

    Optional<Report> findByUserProductAndMonthAndYear(UserProduct userProduct, int month, int year);

    List<Report> findByMonthAndYear(int month, int year);
}

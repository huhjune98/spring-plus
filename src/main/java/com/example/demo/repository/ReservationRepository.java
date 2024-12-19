package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserIdAndItemId(Long userId, Long itemId);

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByItemId(Long itemId);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.item.id = :id " +
            "AND NOT (r.endAt <= :startAt OR r.startAt >= :endAt) " +
            "AND r.status = 'APPROVED'")
    List<Reservation> findConflictingReservations(
            @Param("id") Long id,
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt
    );

    @Query("SELECT r From Reservation r Join Fetch r.user u Join Fetch r.item i")
    List<Reservation> findAllWithUserAndItem();

    @Query("SELECT r FROM Reservation r LEFT JOIN FETCH r.user u LEFT JOIN FETCH r.item i WHERE (:userId IS NULL OR u.id = :userId) AND (:itemId IS NULL OR i.id = :itemId)")
    List<Reservation> findReservations(@Param("userId") Long userId, @Param("itemId") Long itemId);

    default Reservation findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(()-> new IllegalArgumentException("해당 ID에 맞는 데이터가 존재하지 않습니다."));
    }
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Reserve;


@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
    List<Reserve> findAllByUserUserId(Long userId);
}




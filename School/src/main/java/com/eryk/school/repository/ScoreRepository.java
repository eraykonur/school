package com.eryk.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eryk.school.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>  {

}

package com.eryk.school.service;

import java.util.List;
import java.util.Optional;

import com.eryk.school.model.Score;

public interface IScoreService {
    List<Score> getScoreList();
    Optional<Score> getScore(Long id);
    Score saveScore(Score score);
    void deleteScore(Long id);
}

package com.eryk.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eryk.school.model.Score;
import com.eryk.school.repository.ScoreRepository;

@Service
public class ScoreService implements IScoreService {

	@Autowired
    private ScoreRepository scoreRepository;
    
    public ScoreService(ScoreRepository scoreRepository) {
        super();
    	this.scoreRepository = scoreRepository;
    }

    @Override
    public List<Score> getScoreList() {
        return scoreRepository.findAll();
    }

    @Override
    public Optional<Score> getScore(Long id) {
        return scoreRepository.findById(id);
    }

    @Override
    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }
}

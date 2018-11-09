package ru.tandser.finesnew.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tandser.finesnew.model.Fine;
import ru.tandser.finesnew.model.FinesCount;
import ru.tandser.finesnew.repository.FineRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Repository
class FineRepositoryImpl implements FineRepository {

    @Autowired
    private JpaFineRepository jpaFineRepository;

    @Autowired
    private JpaFinesCountRepository jpaFinesCountRepository;

    @Override
    public List<Fine> findByCarNumber(String carNumber) {
        return jpaFineRepository.findByCarNumber(requireNonNull(carNumber));
    }

    @Override
    public List<FinesCount> findAllTops() {
        return jpaFinesCountRepository.findAllTops();
    }

    @Override
    public List<FinesCount> findTopsByCarNumber(String carNumber) {
        return jpaFinesCountRepository.findTopsByCarNumber(requireNonNull(carNumber));
    }
}
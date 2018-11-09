package ru.tandser.finesnew.repository;

import ru.tandser.finesnew.model.Fine;
import ru.tandser.finesnew.model.FinesCount;

import java.util.List;

public interface FineRepository {

    List<Fine> findByCarNumber(String carNumber);

    List<FinesCount> findAllTops();

    List<FinesCount> findTopsByCarNumber(String carNumber);
}
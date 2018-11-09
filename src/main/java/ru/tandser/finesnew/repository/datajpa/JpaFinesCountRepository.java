package ru.tandser.finesnew.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.finesnew.model.FinesCount;

import java.util.List;

@Transactional(readOnly = true)
interface JpaFinesCountRepository extends JpaRepository<FinesCount, Long> {

    @Query("SELECT new ru.tandser.finesnew.model.FinesCount(fc.duty.name, COUNT(fc))" +
           "  FROM FinesCount AS fc" +
           "    GROUP BY fc.duty.name" +
           "    ORDER BY COUNT(fc) DESC")
    List<FinesCount> findAllTops();

    @Query("SELECT new ru.tandser.finesnew.model.FinesCount(fc.duty.name, COUNT(fc))" +
           "  FROM FinesCount AS fc" +
           "    WHERE fc.car.number = ?1" +
           "      GROUP BY fc.duty.name" +
           "      ORDER BY COUNT(fc) DESC")
    List<FinesCount> findTopsByCarNumber(String carNumber);
}
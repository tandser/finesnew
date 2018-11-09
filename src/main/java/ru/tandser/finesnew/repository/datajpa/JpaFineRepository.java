package ru.tandser.finesnew.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.finesnew.model.Fine;

import java.util.List;

@Transactional(readOnly = true)
interface JpaFineRepository extends JpaRepository<Fine, Long> {

    @EntityGraph(Fine.WITH_CAR_AND_DUTY)
    @Query("SELECT f FROM Fine AS f WHERE f.car.number = ?1")
    List<Fine> findByCarNumber(String carNumber);
}
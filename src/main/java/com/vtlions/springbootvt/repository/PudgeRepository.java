package com.vtlions.springbootvt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vtlions.springbootvt.model.Pudge;

@Repository
public interface PudgeRepository extends JpaRepository<Pudge, Integer> {

}

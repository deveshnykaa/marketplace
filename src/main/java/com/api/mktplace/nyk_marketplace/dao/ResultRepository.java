package com.api.mktplace.nyk_marketplace.dao;

import com.api.mktplace.nyk_marketplace.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    public List<Result> findByCorrId(String corrId);
}

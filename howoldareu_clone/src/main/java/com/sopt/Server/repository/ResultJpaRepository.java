package com.sopt.Server.repository;

import com.sopt.Server.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultJpaRepository extends JpaRepository<Result, Long>{
    List<Result> findAllByMemberIdOrderByIdDesc(Long memberId);
}

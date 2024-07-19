package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.JSONModelEntity;

public interface JSONRepository extends JpaRepository<JSONModelEntity, Long> {

}

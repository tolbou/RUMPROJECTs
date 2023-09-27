package com.example.rum.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rum.domain.entity.MyEntity;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface MyRepository extends JpaRepository<MyEntity, Integer> {
	
}
package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_sub_group_master;

public interface Acc_sub_groupRepository extends JpaRepository<Acc_sub_group_master, Long> {
	@Query("select COUNT(id) from Acc_sub_group_master")
	String countId();
}
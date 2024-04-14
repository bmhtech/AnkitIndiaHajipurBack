package com.AnkitIndia.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Post_office_master;

public interface Post_office_masterRepository extends JpaRepository<Post_office_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(postid , 3 , length(postid))) FROM Post_office_master where postid like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("SELECT DISTINCT i.dist_code FROM Post_office_master i where i.dist_code =:dist_code and i.modified_type !='DELETED'")
    Optional<String> getPostDtlsThruDist(@Param("dist_code") String dist_code);
	
	@Query("select p from Post_office_master p where p.postid =:postid and p.modified_type !='DELETED'")
	Optional<Post_office_master> getPincodeThruPO(@Param("postid") String postid);
	
}

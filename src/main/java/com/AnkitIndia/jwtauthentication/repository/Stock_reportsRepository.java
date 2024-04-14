package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_reports;

public interface Stock_reportsRepository extends JpaRepository<Stock_reports, Long>{

	@Modifying(clearAutomatically = true)
	@Query(value = "{call multipleStockReports(:reportname)}", nativeQuery = true)
	List<Stock_reports> getStockReports(@Param("reportname") String reportname);
}

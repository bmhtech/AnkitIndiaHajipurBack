package com.AnkitIndia.jwtauthentication.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.AnkitIndia.jwtauthentication.model.Sales_register_details;


public interface Sales_register_detailsRepository extends JpaRepository<Sales_register_details, Long>{

	@Query("select b from Sales_register_details b where b.sales_report =:sales_report")
	List<Sales_register_details> getSalesRegister1(@Param("sales_report") String sales_report);
	
	
	//@Query( "select b from Sales_register_details b where b.sales_report =:sales_report")
	//Sales_register_details getSalesRegister(@Param("sales_report") String sales_report);
	
	//@Query( "select b.reportfields from Sales_register_details b where b.sales_report=:sales_report")
	//List<Sales_register_details> getSalesRegister1(@Param("sales_report") String sales_report);
	
	
}

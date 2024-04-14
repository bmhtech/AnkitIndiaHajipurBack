package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Purchase_reg_dynamic;

public interface Purchase_reg_dynamicRepository extends JpaRepository<Purchase_reg_dynamic, Long>{

/*	@Query("select b from Sales_reg_dynamic b ")
	List<Sales_reg_dynamic> getSalesReportNameList();
	
	@Query(value = "{call GetCustomerDetail1(:reportname)}", nativeQuery = true)
	List getRows(@Param("reportname") String reportname);
	
	@Query(value = "select a.static_report from Sales_reg_dynamic a where  a.reportname=:reportname", nativeQuery = true)
	List getColumn(@Param("reportname") String reportname);

	@Query(value = "select b.sales_report from Sales_reg_dynamic b where b.id =:reportname", nativeQuery = true)
	List salesDynamicSortList(@Param("reportname") String reportname);
	
	@Query("select b from Sales_dynamic_sort b where b.backend =:backend")
	List<Sales_dynamic_sort> salesDynamicMultiList(@Param("backend") String backend);
	
	@Query(value = "select b.sales_report from Sales_reg_dynamic b where b.id =:reportname", nativeQuery = true)
	List salesDynamicSortListById(@Param("reportname") Long id);*/
}

package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_dynamic_sort;

public interface Sales_dynamic_sortRepository extends JpaRepository<Sales_dynamic_sort, Long>{

	@Query(value = "select b.data_column from Sales_dynamic_sort b where b.backend =:backend", nativeQuery = true)
	List salesDynamicsortListByName(@Param("backend") String backend);
	
	@Query(value = "select b.backend from Sales_dynamic_sort b where b.dynamic =:dynamic", nativeQuery = true)
	List salesBackendsortListByName(@Param("dynamic") String dynamic);
	
	@Query("select b.data_column from Sales_dynamic_sort b where b.backend =:backend and b.reportlist =:reporttype and b.reporttype=:reportlist")
	List salesreportsorting_staticfield(@Param("backend") String backend,@Param("reporttype") String reporttype,@Param("reportlist") String reportlist);
	
	@Query("select b.dynamic from Sales_dynamic_sort b where b.backend =:static ")
	List getColName(@Param("static") String staticflds);
	
}

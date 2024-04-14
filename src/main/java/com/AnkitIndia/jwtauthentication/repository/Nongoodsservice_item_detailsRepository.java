package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;

public interface Nongoodsservice_item_detailsRepository extends JpaRepository<Nongoodsservice_item_details, Long>{
	
	@Query( "select n from Nongoodsservice_item_details n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid order by n.slno")
	List<Nongoodsservice_item_details> getItemDetails(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nonservice_desc_details n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid AND n.serviceno = :serviceno")
	List<Nonservice_desc_details> getItemServiceDetails(@Param("nongoodsid") String nongoodsid,@Param("serviceno") String serviceno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_item_details n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateItemDetails(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nonservice_desc_details n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid AND n.serviceno = :serviceno")
    int updateItemServiceDetails(@Param("nongoodsid") String nongoodsid,@Param("serviceno") String serviceno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_item_details n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertItem(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nonservice_desc_details n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertService(@Param("nongoodsid") String nongoodsid);
	
	@Query(value= "SELECT \r\n" + 
			"s.desc_name AS desc_name,\r\n" + 
			"s.bill_period AS bill_period,\r\n" + 
			"s.bill_on AS bill_on,\r\n" + 
			"s.amount_change AS amount_change,\r\n" + 
			"CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_from\r\n" + 
			"END\r\n" + 
			"AS billing_from1\r\n" + 
			",\r\n" + 
			"CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_to\r\n" + 
			"END\r\n" + 
			"AS billing_to1,\r\n" + 
			"\r\n" + 
			"CASE \r\n" + 
			"WHEN  s.bill_period ='Daily' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_from\r\n" + 
			"END, INTERVAL 1 DAY)  \r\n" + 
			"WHEN  s.bill_period ='Monthly' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_from\r\n" + 
			"END, INTERVAL 1 MONTH)\r\n" + 
			"WHEN  s.bill_period ='Quaterly' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_from\r\n" + 
			"END, INTERVAL 3 MONTH)\r\n" + 
			"WHEN  s.bill_period ='Yearly' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_from\r\n" + 
			"END, INTERVAL 1 YEAR)\r\n" + 
			"ELSE 'NA' END AS nextbillingfrom,\r\n" + 
			"\r\n" + 
			"CASE \r\n" + 
			"WHEN  s.bill_period ='Daily' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_to\r\n" + 
			"END, INTERVAL 1 DAY)  \r\n" + 
			"WHEN  s.bill_period ='Monthly' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_to\r\n" + 
			"END, INTERVAL 1 MONTH)\r\n" + 
			"WHEN  s.bill_period ='Quaterly' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_to\r\n" + 
			"END, INTERVAL 3 MONTH)\r\n" + 
			"WHEN  s.bill_period ='Yearly' THEN DATE_ADD(CASE \r\n" + 
			"WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 \r\n" + 
			"THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)\r\n" + 
			"ELSE s.billing_to\r\n" + 
			"END, INTERVAL 1 YEAR)\r\n" + 
			"ELSE 'NA' END AS nextbillingto ,\r\n" + 
			"s.desc_price AS desc_price,\r\n" + 
			"\r\n" + 
			"s.desc_uom AS desc_uom\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"FROM `nonservice_desc_details` s \r\n" + 
			"\r\n" + 
			"WHERE s.nongoodsserviceid=:nongoodsserviceid AND s.serviceno=:serviceno AND s.modified_type='INSERTED'",nativeQuery=true)
	List<Map<String, Object>> getDescAccordingBillPeriodList(@Param("nongoodsserviceid") String nongoodsserviceid,@Param("serviceno") String serviceno);
	
	
}

/*  //getDescAccordingBillPeriodList
SELECT 
s.desc_name AS desc_name,
s.bill_period AS bill_period,
s.bill_on AS bill_on,
s.amount_change AS amount_change,
CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_from
END
AS billing_from1
,
CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_to
END
AS billing_to1,

CASE 
WHEN  s.bill_period ='Daily' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_from
END, INTERVAL 1 DAY)  
WHEN  s.bill_period ='Monthly' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_from
END, INTERVAL 1 MONTH)
WHEN  s.bill_period ='Quaterly' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_from
END, INTERVAL 3 MONTH)
WHEN  s.bill_period ='Yearly' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid  AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_from FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_from
END, INTERVAL 1 YEAR)
ELSE 'NA' END AS nextbillingfrom,

CASE 
WHEN  s.bill_period ='Daily' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_to
END, INTERVAL 1 DAY)  
WHEN  s.bill_period ='Monthly' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_to
END, INTERVAL 1 MONTH)
WHEN  s.bill_period ='Quaterly' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_to
END, INTERVAL 3 MONTH)
WHEN  s.bill_period ='Yearly' THEN DATE_ADD(CASE 
WHEN (SELECT COUNT(id) FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name)>0 
THEN (SELECT billing_to FROM nonservicesrn_desc_details WHERE modified_type='INSERTED' AND nongoodsserviceid=s.nongoodsserviceid AND serviceno=s.serviceno AND desc_name=s.desc_name ORDER BY srn_date DESC LIMIT 1)
ELSE s.billing_to
END, INTERVAL 1 YEAR)
ELSE 'NA' END AS nextbillingto ,
s.desc_price AS desc_price,

s.desc_uom AS desc_uom


FROM `nonservice_desc_details` s 

WHERE s.nongoodsserviceid='NGS00001' AND s.serviceno='JSM-2223-0000001' AND s.modified_type='INSERTED'
*/

package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Tag_advice_with_po;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;

public interface Tag_advice_with_poRepository extends JpaRepository<Tag_advice_with_po, Long>{
	
	@Query("select COUNT(id) from Tag_advice_with_po")
	String countId();
	
	@Query("select COUNT(id) from Tag_advice_with_po where modified_type = 'INSERTED' ")
	String countTagAdvOrder();
	
	@Query( "select s from Tag_advice_with_po s where s.modified_type = 'INSERTED' and s.company_id =:company ORDER BY s.adv_po_tag_id DESC")
	List<Tag_advice_with_po> getTagAdvWithPO(@Param("company") String company);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Tag_advice_withpo_broker_dtls w SET w.modified_type ='DELETED' WHERE w.adv_po_tag_id = :adv_po_tag_id")
    int tag_advice_withpo_broker_dtlsupdate(@Param("adv_po_tag_id") String adv_po_tag_id);
	
	@Query(value = "{call checkTagAdvicePoUsage(:adviceno)}", nativeQuery = true)
	String checkTagAdvicePoUsage(@Param("adviceno") String adviceno);


	@Query(value = "SELECT CASE WHEN COUNT(id)>0 THEN 'Yes' ELSE 'No' END AS res FROM pur_good_receipt WHERE modified_type='INSERTED' AND  referance_id =(SELECT unadviceid FROM wm_unload_advice WHERE  modified_type='INSERTED' AND unadviceno=:adviceno)", nativeQuery = true)
	String checkTagAdvicePoUsagresponse(@Param("adviceno") String adviceno);
	
	
	
	@Query(value = "SELECT CASE  WHEN count(pl.po_id)> 0 THEN true ELSE false END FROM tag_advice_delete pl where  pl.po_id=:code", nativeQuery = true)
	int checkTagAdvicePoUsageingrn(@Param("code") String code);
	
	
	@Query(value = "{call getSearchtaggedadviceData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#po_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#pono},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Tag_advice_with_po> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("po_no") String po_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("pono") String pono,
			@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);

	@Query(value = "{call getSearchtaggedadviceData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#po_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#pono},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Map<String,Object>> getsearchdataFast(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("po_no") String po_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("pono") String pono,
			@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);

	
	@Query(value= "select adv_po_tag_no,advice_no,ula_date,item_subtype_name,busi_partnername,business_unitname,pur_orderno,veh_no,id from tag_advice_with_po where modified_type ='INSERTED' and fin_year =:finyear and ula_date =:currDate", nativeQuery=true)
	List<Map<String, Object>> getTagAdviceWithPoList(String currDate,String finyear);
}

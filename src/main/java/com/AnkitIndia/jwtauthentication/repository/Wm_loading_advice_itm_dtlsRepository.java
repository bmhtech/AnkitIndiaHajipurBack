package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;

public interface Wm_loading_advice_itm_dtlsRepository extends JpaRepository<Wm_loading_advice_itm_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_itm_dtls w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_itm_dtlsupdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);

	@Query( "select w from Wm_loading_advice_itm_dtls w where w.advice_id =:advice_id and w.modified_type = 'INSERTED'")
	List<Wm_loading_advice_itm_dtls> getLoadingItemDetails(@Param("advice_id") String advice_id);
	
	@Query( "select w from Wm_loading_advice_itm_dtls w where w.advice_id =:advice_id and w.modified_type = 'INSERTED'")
	Wm_loading_advice_itm_dtls getLoadingItemDtls(@Param("advice_id") String advice_id);
	
	@Query( "select w from Wm_loading_advice_itm_dtls w where w.modified_type = 'INSERTED' and w.advice_id =:advice_id and w.item_code =:itemid and w.packing =:packingid")
	Wm_loading_advice_itm_dtls getLoadingItemDtls(@Param("advice_id") String advice_id,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	
	@Query( "select w from Wm_loading_advice_itm_dtls w where w.modified_type = 'INSERTED' and w.advice_id =:advice_id and w.item_code =:itemid ")
	Wm_loading_advice_itm_dtls getLoadingItemDtlsupdatedloadingadvice(@Param("advice_id") String advice_id,@Param("itemid") String itemid);
	
	@Query( "select w from Wm_loading_advice_itm_dtls w where w.modified_type = 'INSERTED' and w.advice_id =:advice_id and w.alter_item_code =:itemid and w.alter_packing =:packingid")
	Wm_loading_advice_itm_dtls getLoadingItemDtlsupdatedloadingadvicealternative(@Param("advice_id") String advice_id,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	@Query( value="select count(id) as id from wm_loading_advice_itm_dtls w where w.modified_type = 'INSERTED' and w.advice_id =:advice_id and w.item_code =:itemid ",nativeQuery=true)
	int checkloadingitems(@Param("advice_id") String advice_id,@Param("itemid") String itemid);
	
	@Query( value="select count(id) as id from wm_loading_advice_itm_dtls w where w.modified_type = 'INSERTED' and w.advice_id =:advice_id and w.alter_item_code =:itemid  and w.alter_packing =:packingid",nativeQuery=true)
	int checkloadingitemsalternative(@Param("advice_id") String advice_id,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	@Query( "select w from Wm_loading_advice_itm_dtls w where w.advice_id =:code and w.modified_type = 'INSERTED' ORDER BY w.sl_no")
	List<Wm_loading_advice_itm_dtls> loadingItemRetriveList(@Param("code") String code);
	
	@Query( "select SUM(w.mat_wt) from Wm_loading_advice_itm_dtls w where w.advice_id =:code and w.modified_type = 'INSERTED' ")
	double gettotalmat_weight(@Param("code") String code);
	
	@Query( value="SELECT CASE WHEN jobwork = '0' THEN (SELECT SUM(w.mat_wt) FROM wm_loading_advice_itm_dtls w WHERE w.advice_id =:code AND w.modified_type = 'INSERTED') ELSE (SELECT SUM(j.item_qty) FROM wm_loading_advice_item_dtls_for_jobwork j WHERE j.advice_id =:code AND j.modified_type = 'INSERTED') END AS mat_wt FROM wm_loading_advice WHERE advice_id =:code AND modified_type = 'INSERTED' ",nativeQuery=true)
	double gettotalmat_weightjobwork(@Param("code") String code);
	
	@Query( "select sum(w.quantity) from Wm_loading_advice_itm_dtls w where w.advice_id =:code and w.modified_type = 'INSERTED' ORDER BY w.sl_no")
	double getsumitemqty(@Param("code") String code);
	
	@Query( "select sum(w.s_quantity) from Wm_loading_advice_itm_dtls w where w.advice_id =:code and w.modified_type = 'INSERTED' ORDER BY w.sl_no")
	double getsumpackingqty(@Param("code") String code);

	@Query( "select w.advice_id from Wm_loading_advice w where w.weighment_id =:code and w.modified_type = 'INSERTED' ")
	List<String> getadviceidthrughweighmentid(@Param("code") String code);
	
	
	@Query( value="select sum(w.total_amt) from wm_loading_advice_itm_dtls w where w.advice_id =:code and w.modified_type = 'INSERTED' ",nativeQuery=true)
	double getpaymentmodepreviousamount(@Param("code") String code);
	
	
	//@Query( value="SELECT (CASE WHEN w.weighment_status < 2 THEN (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)+wd.mat_wt)>=:packing_qty THEN \"Yes\" ELSE \"No\" END) ELSE (CASE WHEN (SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)>=:packing_qty THEN \"Yes\" ELSE \"No\" END)END FROM wm_loading_advice_itm_dtls wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.alter_item_code =:itemid  AND wd.alter_packing =:packingid) ELSE \"No\" END) AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'",nativeQuery=true)
	//+wd.mat_wt removed bcz plus was not needed 
	@Query( value="SELECT (CASE WHEN w.weighment_status < 2 THEN (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid))>=:packing_qty THEN \"Yes\" ELSE \"No\" END) ELSE (CASE WHEN (SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)>=:packing_qty THEN \"Yes\" ELSE \"No\" END)END FROM wm_loading_advice_itm_dtls wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.alter_item_code =:alter_item_code  AND wd.alter_packing =:alter_packing) ELSE \"No\" END) AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'",nativeQuery=true)
	String getloadingitemdetailwtalternate(@Param("advice_id") String advice_id,@Param("itemid") String itemid,
			@Param("packing_qty") double packing_qty,@Param("orderid") String orderid,
			@Param("alter_item_code") String alter_item_code,@Param("alter_packing") String alter_packing);
	
	//SELECT (CASE WHEN w.weighment_status < 2 THEN (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_item_qty FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = :orderid AND s.job_item =:itemid)+wd.item_qty)>=:pack_qty THEN "Yes" ELSE "No" END) ELSE (CASE WHEN (SELECT s.rest_item_qty FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = :orderid AND s.job_item =:itemid)>=:pack_qty THEN "Yes" ELSE "No" END)END FROM wm_loading_advice_item_dtls_for_jobwork wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.job_item =:itemid  AND wd.job_packing =:packingid) ELSE "No" END) AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'
	//+wd.item_qty  removed bcz plus was not needed 
	
	//@Query(value="SELECT (CASE WHEN w.weighment_status < 2 THEN (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_item_qty FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = :orderid AND s.job_item =:itemid))>=:pack_qty THEN \"Yes\" ELSE \"No\" END) ELSE (CASE WHEN (SELECT s.rest_item_qty FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = :orderid AND s.job_item =:itemid)>=:pack_qty THEN \"Yes\" ELSE \"No\" END)END FROM wm_loading_advice_item_dtls_for_jobwork wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.job_item =:itemid  AND wd.job_packing =:packingid) ELSE \"No\" END) AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'",nativeQuery=true)
	@Query(value="SELECT (CASE WHEN w.weighment_status < 2 THEN (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT (s.rest_item_qty+wd.item_qty) FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = :orderid AND s.job_item =:itemid))>=:pack_qty THEN \"Yes\" ELSE \"No\" END) ELSE (CASE WHEN (SELECT (s.rest_item_qty+wd.item_qty) FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = :orderid AND s.job_item =:itemid)>=:pack_qty THEN \"Yes\" ELSE \"No\" END)END FROM wm_loading_advice_item_dtls_for_jobwork wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.job_item =:itemid  AND wd.job_packing =:packingid) ELSE \"No\" END) AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'",nativeQuery=true)
	String getLoadingRestWeightJobworkupdate(@Param("advice_id") String advice_id,@Param("itemid") String itemid,@Param("packingid") String packingid,@Param("pack_qty") double pack_qty,@Param("orderid") String orderid );
	
	
	//@Query( value="SELECT (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)+wd.mat_wt)>=:packing_qty THEN \"Yes\" ELSE \"No\" END) ELSE (CASE WHEN (SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)>=:packing_qty THEN \"Yes\" ELSE \"No\" END)END FROM wm_loading_advice_itm_dtls wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.alter_item_code =:itemid  AND wd.alter_packing =:packingid)  AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'",nativeQuery=true)
	//+wd.mat_wt  removed bcz plus was not needed 
	@Query( value="SELECT (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid))>=:packing_qty THEN \"Yes\" ELSE \"No\" END) ELSE (CASE WHEN (SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)>=:packing_qty THEN \"Yes\" ELSE \"No\" END)END FROM wm_loading_advice_itm_dtls wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.alter_item_code =:itemid  AND wd.alter_packing =:packingid)  AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'",nativeQuery=true)
	String getLoadingRestWeightupdatelooseitem(@Param("advice_id") String advice_id,@Param("itemid") String itemid,@Param("packingid") String packingid,@Param("packing_qty") double packing_qty,@Param("orderid") String orderid );
	
	
	@Query( value="SELECT ROUND(:s_quantity*((SELECT capacity  FROM item_master_pack_mat_tag WHERE modified_type='INSERTED' AND item_id=:alter_item_code  AND  item_code=:alter_packing)/(capacity))) AS altsqty FROM item_master_pack_mat_tag WHERE modified_type='INSERTED' AND  item_code=:packing AND item_id=:item_code",nativeQuery=true)
	double bagsforaltconvert(@Param("item_code") String item_code,@Param("packing") String packing,@Param("alter_item_code") String alter_item_code,@Param("alter_packing") String alter_packing,@Param("s_quantity") double s_quantity );
	
	
	
}

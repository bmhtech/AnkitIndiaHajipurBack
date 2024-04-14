package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_transport;

public interface Sales_transportRepository extends JpaRepository<Sales_transport, Long>{

	@Query("select COUNT(id) from Sales_transport")
	String countId();
	
	//@Query("select COUNT(id) from Sales_transport where loadingdate =:currDate")
	//@Query("select COUNT(id) from Sales_transport where currentdate =:currDate")
	@Query("select COUNT(id) from Sales_transport where jvdate =:currDate")
	String countTransJVCode(@Param("currDate") String currDate);
	
	//@Query(value = "SELECT id,jvdate,trans_jv_no,sales_tranport_id,challandate,challanno,buname,transname,partyname,vehicleno,own_slip_no,own_slip_nonew,grosswt,grosswtnew,tarewt,tarewtnew,netwt,netwtnew,balancewt,bags,qtls,paymentson,price,adv_pay,actual_amt,balance_amt,sales_transport_date,adv_voucher_no,referance_no FROM sales_transport WHERE modified_type='INSERTED' AND currentdate>=:fromdate AND currentdate<=:todate ORDER BY id", nativeQuery=true)
	//List<Map<String, Object>> getSalesTransactionReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call getSearchTransport(:#{#tablename},:#{#invtype},:#{#challanno1},:#{#currentdate},:#{#salestype},:#{#challanno},:#{#fromdate},:#{#todate},:#{#finyear},:#{#jvno},:#{#jvnum})}", nativeQuery = true)
	List<Map<String,Object>> getSearchTransport(@Param("tablename") String tablename,@Param("invtype") String invtype,
			@Param("challanno1") String challanno1,@Param("currentdate") String currentdate, 
			@Param("salestype") String salestype,@Param("challanno") String challanno,@Param("fromdate") String fromdate,@Param("todate") String todate,
			@Param("finyear") String finyear,@Param("jvno") String jvno,@Param("jvnum") String jvnum);
	
	
	@Query(value = "SELECT d.challan_date,d.partyname,dt.transporter_name,d.challan_no,dt.vehicle_no,dt.freight_amt,dt.adv_paid,(dt.freight_amt-dt.adv_paid) AS balanceamt,CASE WHEN (SELECT COUNT(id) FROM sales_transport WHERE modified_type='INSERTED' AND challanno=d.challan_no)>0 THEN \"PAID\" ELSE \"DUE\" END AS paidstatus FROM delivery_challan d,delivery_challan_trans_info dt WHERE d.delivery_cid=dt.delivery_cid AND d.modified_type=dt.modified_type AND d.modified_type='INSERTED' AND dt.trans_borne_by='FOR' AND d.challan_date>=:fromdate AND d.challan_date<=:todate and d.inv_type=:inv_typenew", nativeQuery=true)
	List<Map<String, Object>> getSalesTransactionReportCheckbalance(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("inv_typenew") String inv_typenew);
	
	@Query(value = "SELECT d.challan_date,d.partyname,dt.transporter_name,d.challan_no,dt.vehicle_no,dt.freight_amt,dt.adv_paid,(dt.freight_amt-dt.adv_paid) AS balanceamt,CASE WHEN (SELECT COUNT(id) FROM sales_transport WHERE modified_type='INSERTED' AND challanno=d.challan_no)>0 THEN \"PAID\" ELSE \"DUE\" END AS paidstatus FROM delivery_challan d,delivery_challan_trans_info dt WHERE d.delivery_cid=dt.delivery_cid AND d.modified_type=dt.modified_type AND d.modified_type='INSERTED' AND dt.trans_borne_by='FOR' AND d.challan_date>=:fromdate AND d.challan_date<=:todate and d.inv_type=:inv_typenew AND dt.trans_code=:trans_code", nativeQuery=true)
	List<Map<String, Object>> getSalesTransactionReportCheckbalancewithTrans(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("inv_typenew") String inv_typenew,@Param("trans_code") String trans_code);
	
	@Query(value = "SELECT id,sales_tranport_id,trans_jv_no,challandate,challanno,buname,transname,partyname,vehicleno,own_slip_no,own_slip_nonew,grosswt,grosswtnew,tarewt,tarewtnew,netwt,netwtnew,balancewt,bags,qtls,paymentson,price,adv_pay,actual_amt,balance_amt,sales_transport_date,adv_voucher_no,referance_no FROM sales_transport WHERE modified_type='INSERTED' AND currentdate=:currentdate ORDER BY id", nativeQuery=true)
	List<Map<String, Object>> getSalesTransactionReportList(@Param("currentdate") String currentdate);
	
	@Query(value = "SELECT * FROM sales_transport WHERE modified_type='INSERTED' AND id=:id", nativeQuery=true)
	Map<String, Object> getSalesTransport(@Param("id") String id);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_transport p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_transport p SET p.export=0  WHERE p.id = :id" )
	int exportuomtallyundo(@Param("id") long id);
	
	@Query(value = "SELECT p.referance_no as referance_no,p.challanno as challanno FROM sales_transport p WHERE p.modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getDuplicateRefTransport();
	
	@Query(value = "SELECT s.jvdate AS jvdate,d.challan_date AS challandate,d.challan_no AS challanno,t.vehicle_no AS truck,d.partyname AS partyname,t.transporter_name AS transportername,(SELECT SUM(squantity) FROM  delivery_challan_item_dtls WHERE modified_type='INSERTED' AND delivery_cid = d.delivery_cid) AS totalbags,((SELECT SUM(squantity) FROM  delivery_challan_item_dtls WHERE modified_type='INSERTED' AND delivery_cid = d.delivery_cid)/10) AS tons,s.netwt AS ownwt,s.netwtnew AS partywt,s.balancewt AS shortage,s.mat_amt AS shortageamt,s.mat_price,s.price,s.actual_amt,s.detaintiontodate AS unloaddate,s.detaintionfromdate AS truckpalced,s.balance_amt,s.adv_pay ,s.final_pay AS balance,s.referance_no,s.trans_jv_no,s.remarks,s.chgs_dedu AS chargesdeduct FROM sales_transport s,delivery_challan d,delivery_challan_weight_dtl w,delivery_challan_item_dtls i,delivery_challan_trans_info t WHERE d.challan_no=s.challanno AND d.delivery_cid=w.delivery_cid AND d.delivery_cid = i.delivery_cid AND d.delivery_cid = t.delivery_cid AND d.modified_type='INSERTED' AND d.modified_type=s.modified_type AND d.modified_type=w.modified_type AND d.modified_type=i.modified_type AND d.modified_type=t.modified_type AND d.challan_date>=:fromdate AND d.challandate<=:todate AND d.inv_type=:invoicetype", nativeQuery=true)
	List<Map<String, Object>> searchTransportStatement(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("invoicetype") String invoicetype);

	
}

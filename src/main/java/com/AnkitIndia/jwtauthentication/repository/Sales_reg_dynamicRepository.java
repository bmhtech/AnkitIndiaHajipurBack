package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_dynamic_sort;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;
import com.AnkitIndia.jwtauthentication.responseDTO.sales_reg_dynamicDTO;



public interface Sales_reg_dynamicRepository extends JpaRepository<Sales_reg_dynamic, Long>{
	
	//@Query(value="select reportname from sales_reg_dynamic",nativeQuery=true)
	//List<Sales_reg_dynamic> getSalesReportNameList();
	
	@Query("select b from Sales_reg_dynamic b ")
	List<Sales_reg_dynamic> getSalesReportNameList();
	
	@Query(value = "{call GetCustomerDetail1(:reportname)}", nativeQuery = true)
	List getRows(@Param("reportname") String reportname);
	
	@Query("select a.sales_report from Sales_reg_dynamic a where  a.reportname=:reportname")
	List getColumn(@Param("reportname") String reportname);

	//@Query(value = "select b.sales_report from Sales_reg_dynamic b where b.id =:reportname", nativeQuery = true)
	@Query("select b from Sales_reg_dynamic b where b.id =:reportname")
	Sales_reg_dynamic salesDynamicSortList(@Param("reportname") Long reportname);
	
	
	
	@Query("select b from Sales_dynamic_sort b where b.backend =:backend")
	List<Sales_dynamic_sort> salesDynamicMultiList(@Param("backend") String backend);
	
	@Query(value = "select b.sales_report from Sales_reg_dynamic b where b.id =:reportname", nativeQuery = true)
	List salesDynamicSortListById(@Param("reportname") Long id);
	
	@Query("select b.reportname from Sales_reg_dynamic b where b.modified_type !='DELETED'")
	List getSalesRegDuplicateCheck();
	
	@Query("select b from Sales_reg_dynamic b where b.reporttype =:reporttype and b.modified_type !='DELETED'")
	List<Sales_reg_dynamic> reportTypeDropdownList(@Param("reporttype") String reporttype);
	
	
	@Query("select a.reportlist,a.reporttype from Sales_reg_dynamic a where  a.reportname=:reportname")
	String getReportNameList(@Param("reportname") String reportname);
	
	@Query(value = "SELECT\r\n"
			+ "			    si.invoice_type_name,\r\n"
			+ "			    si.businessunitname,\r\n"
			+ "			    si.invoice_no,\r\n"
			+ "			    si.adviceno,\r\n"
			+ "			    si.invoice_date,\r\n"
			+ "			    si.state,\r\n"
			+ "			    si.partyname,\r\n"
			+ "			    sit.gstno,\r\n"
			+ "			    sib.broker_name,\r\n"
			+ "			    si.challan,\r\n"
			+ "			    si.e_invoice_no,\r\n"
			+ "			    si.salesorderno,\r\n"
			+ "			    si.salesorderdate,\r\n"
			+ "			    si.refchallanno,\r\n"
			+ "			    si.refchallandate,\r\n"
			+ "			    siid.item_name,\r\n"
			+ "			    siid.item_groupname,\r\n"
			+ "			    siid.packing_name,\r\n"
			+ "			    siid.hsn_code,\r\n"
			+ "			    siid.squantity,\r\n"
			+ "			    siid.suom,\r\n"
			+ "			    siid.quantity,\r\n"
			+ "			    siid.uom,\r\n"
			+ "			    siid.mat_wt,\r\n"
			+ "			    siid.price,\r\n"
			+ "			    siid.price_based_on,\r\n"
			+ "			    siid.amount,\r\n"
			+ "			    siid.discount_rate,\r\n"
			+ "			    siid.discount_type,\r\n"
			+ "			    siid.discount_amt,\r\n"
			+ "			    siid.tax_codename,\r\n"
			+ "			    siid.tax_rate,\r\n"
			+ "			    sts.Cgst,\r\n"
			+ "			    sts.Sgst,\r\n"
			+ "			    sts.Igst,\r\n"
			+ "			    siid.tax_amt,\r\n"
			+ "			    siid.total_amt,\r\n"
			+ "			    sitd.vehicleno,\r\n"
			+ "			    si.roundoff_amt\r\n"
			+ "			FROM \r\n"
			+ "			    sales_invoice si\r\n"
			+ "			INNER JOIN \r\n"
			+ "			    sales_invoice_item_dtls siid ON siid.invoice_id = si.invoice_id\r\n"
			+ "			INNER JOIN \r\n"
			+ "			    sales_invoice_tax_info sit ON sit.invoice_id = si.invoice_id\r\n"
			+ "			INNER JOIN \r\n"
			+ "			    saletaxsum sts ON sts.invoice_id = sit.invoice_id\r\n"
			+ "			INNER JOIN \r\n"
			+ "			    sales_invoice_broker_dtls sib ON sib.invoice_id = sts.invoice_id\r\n"
			+ "			INNER JOIN \r\n"
			+ "			    sales_invoice_trans_dtls sitd ON sitd.invoice_id = sib.invoice_id\r\n"
			+ "			WHERE \r\n"
			+ "			    si.invoice_date BETWEEN :fromdate AND :todate\r\n"
			+ "			    AND si.modified_type = 'INSERTED'\r\n"
			+ "			    AND siid.modified_type = 'INSERTED'\r\n"
			+ "			    AND sit.modified_type = 'INSERTED'\r\n"
			+ "			    AND sib.modified_type = 'INSERTED'\r\n"
			+ "			    AND sitd.modified_type = 'INSERTED'\r\n"
			+ "			    AND siid.item_code = sts.item_code;", nativeQuery = true)
	List getSalesInvoiceReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_inv_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesInvoiceProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_enquiry_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesEnquiryProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_quotation_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesQuotationProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_order_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesOrderProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_deliverychallan_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesDeliverychallanProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_returnapprovalnote_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesReturnapprovalnoteProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_returnnote_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesReturnnoteProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_creditnote_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesCreditnoteProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call sales_gatepass_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsSalesgatepassProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_indentorder_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurIndentorderProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_enquiry_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurEnquiryProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_quotation_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurQuotationProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_order_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurOrderProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_qualitycheck_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurQualitycheckProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_peripheral_qualitycheck_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurPeripheralqualitycheckProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_goodsreceipt_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurGoodsreceiptProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_l1selection_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurL1selectionProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_bill_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurchasebillProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_returnapprove_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurReturnappovalnoteProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_returnnote_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurReturnnoteProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call purchese_debitnote_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsPurDebitnoteProcedure(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "{call weighment_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsweighment(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);

	//	UNLOAD
	@Query(value = "{call unload_advice_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsUnloadadvice(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	// LOAD 
//	@Query(value = "{call load_advice_procedure(:reportname)}", nativeQuery = true)
	@Query(value = "{call load_advice_procedure(:reportname,:fromdate,:todate)}", nativeQuery = true)
	List getRowsLoadingadvice(@Param("reportname") String reportname,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_reg_dynamic w SET w.modified_type ='DELETED' WHERE w.id = :id")
    int deleteSalesRegDynamic(@Param("id") Long id);
}

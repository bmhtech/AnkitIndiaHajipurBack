package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Issuestock_Item_Dtls;

public interface IssuestockItemDtlsRepository extends JpaRepository<Issuestock_Item_Dtls, Long>{
	
	@Query( "select r from Issuestock_Item_Dtls r where r.issueno = :issueno and  r.modified_type = 'INSERTED' order by slno" )
	List<Issuestock_Item_Dtls> getitemdetails(@Param("issueno") String issueno);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE from Issuestock_Item_Dtls w set  w.modified_type ='UPDATED'  WHERE w.issueno = :issueno and  w.modified_type ='INSERTED' ")
    int updateIssueDetails(@Param("issueno") String issueno);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE from Issuestock_Item_Dtls w set  w.modified_type ='DELETED'  WHERE w.issueno = :issueno and  w.modified_type ='INSERTED' ")
    int deleteIssueDetails(@Param("issueno") String issueno);
	
	
	
	
	@Query( value="select itemname,item_code,openingstock,openingstock_packing,pur_return,pur_return_packing,stocktransfer_pur,stocktransfer_pur_packing,production,production_packing,sale,sale_packing,sale_return,sale_return_packing,stocktransfer_sale,stocktransfer_sale_packing,closingstock,closingstock_packing   from stocktrac r where r.item_code = :itemcode",nativeQuery=true )
	List<Object []> stockrecordsall(@Param("itemcode") String itemcode);
	
	
	@Query(value="select CASE WHEN  sum(packingqty)>0 then sum(packingqty) else 0 end as packingqty,CASE WHEN sum(itemqty) >0 then sum(itemqty) else 0 end  as itemqty from issuestock_Item_Dtls WHERE requisitionno = :requisitionno and item_code=:itemcode and packing=:packingcode  and   modified_type ='INSERTED'",nativeQuery=true)
	 List<Object []> getrequistionstockrecordbyitem(@Param("itemcode") String itemcode,@Param("packingcode") String packingcode,@Param("requisitionno") String requisitionno);
				
	
}

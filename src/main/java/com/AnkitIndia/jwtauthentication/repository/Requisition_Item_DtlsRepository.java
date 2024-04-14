package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Requisition_Item_Dtls;

public interface Requisition_Item_DtlsRepository extends JpaRepository<Requisition_Item_Dtls, Long>{

	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE from Requisition_Item_Dtls w set  w.modified_type ='UPDATED'  WHERE w.requisitionno = :requisitionno and  w.modified_type ='INSERTED' ")
    int update(@Param("requisitionno") String requisitionno);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE from Requisition_Item_Dtls w set  w.modified_type ='DELETED'  WHERE w.requisitionno = :requisitionno and  w.modified_type ='INSERTED' ")
    int deleterequisition(@Param("requisitionno") String requisitionno);

	@Query( "select r from Requisition_Item_Dtls r where r.requisitionno = :requisitionno and  r.modified_type = 'INSERTED' order by slno" )
	List<Requisition_Item_Dtls> getitemdetails(@Param("requisitionno") String requisitionno);
	
	
    @Query(value="select sum(packingqty) as packingqty,sum(itemqty) as itemqty from requisition_Item_Dtls WHERE requisitionno = :requisitionno and item_code=:itemcode and packing=:packingcode  and   modified_type ='INSERTED'",nativeQuery=true)
    List<Object []> getrequistionstockrecordbyitem(@Param("itemcode") String itemcode,@Param("packingcode") String packingcode,@Param("requisitionno") String requisitionno);
	
}

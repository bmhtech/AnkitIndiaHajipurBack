package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Party_Dtls;

public interface Sales_Order_Party_DtlsRepository extends JpaRepository<Sales_Order_Party_Dtls, Long>{
	
	@Query( "select p from Sales_Order_Party_Dtls p where p.modified_type = 'INSERTED' and p.p_code =:custid ORDER BY p.sl_no")
	List<Sales_Order_Party_Dtls> salesOrderPartyList(@Param("custid") String custid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Party_Dtls w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int sales_Order_Party_Dtlsupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Query("select s from Sales_Order_Party_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ORDER BY s.sl_no")
	List<Sales_Order_Party_Dtls> getSalesOrdPartyDtls(@Param("order_id") String order_id);
	
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Party_Dtls w SET w.modified_type ='UPDATED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int updatepreviouschannelpartynames(@Param("order_id") String order_id);

}

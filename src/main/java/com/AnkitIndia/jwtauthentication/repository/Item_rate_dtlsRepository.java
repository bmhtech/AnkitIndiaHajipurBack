package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;

public interface Item_rate_dtlsRepository extends JpaRepository<Item_rate_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Item_rate_dtls u SET u.modified_type =:mstatus WHERE u.rate_code = :rate_code and u.modified_type ='INSERTED'")
	  int updateItem_ratedtls(@Param("rate_code") String rate_code, @Param("mstatus") String mstatus);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_rate_dtls d SET d.modified_type ='DELETED' WHERE d.rate_code = :rate_code")
    int item_rate_dtlsUpdate(@Param("rate_code") String rate_code);
	
	@Query(value="SELECT * FROM item_rate_dtls WHERE rate_code = :code AND modified_type = 'INSERTED' ORDER BY sl_no ", nativeQuery = true)
	List<Map<String, Object>> getRatedtlsList(@Param("code") String code);
	
	@Query("select b from Item_rate_dtls b where b.modified_type ='INSERTED' AND b.date<=:order_date AND b.valid_date>=:order_date and b.item_code=:itemid and b.packing=:packingcode AND b.price_based_on=:pricebasedon")
	Item_rate_dtls getRateChartItemSOList(@Param("itemid") String itemid,@Param("packingcode") String packingcode,@Param("order_date") String order_date,@Param("pricebasedon") String pricebasedon);
	
	@Query("select b from Item_rate_dtls b where b.modified_type ='INSERTED' AND b.date<=:order_date AND b.valid_date>=:order_date and b.item_code=:itemid and b.packing=:packingcode")
	List<Item_rate_dtls> getRateChartItemSOforItem(@Param("itemid") String itemid,@Param("packingcode") String packingcode,@Param("order_date") String order_date);

}

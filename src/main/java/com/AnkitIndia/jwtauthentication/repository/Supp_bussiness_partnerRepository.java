package com.AnkitIndia.jwtauthentication.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_delv_from;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_statutory;

public interface Supp_bussiness_partnerRepository extends JpaRepository<Supp_bussiness_partner, Long> {

	@Query("SELECT MAX(SUBSTRING(bp_Id , 4 , length(bp_Id))) FROM Supp_bussiness_partner where bp_Id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(bp_code , 11, length(bp_code))) from Supp_bussiness_partner where bp_code like %:code% and company_id =:company and bp_type =:wtype")
	String getSuppSequenceId(@Param("code") String code,@Param("company") String company,@Param("wtype") String wtype);
	
	@Query("SELECT i FROM Supp_bussiness_partner i where i.modified_type = 'INSERTED' ")
    public List<Supp_bussiness_partner> findAllItems();
    
    @Query("SELECT i FROM Supp_bussiness_partner i where i.modified_type = 'INSERTED' ORDER BY i.bp_Id DESC")
    public List<Supp_bussiness_partner> findAllSuppliers();
    
    @Query("SELECT s FROM Supp_bussiness_partner s WHERE CONCAT(s.bp_code, s.bp_name, s.bp_type, s.sub_group_type, s.group_type_name, s.trans_currency) LIKE %?1% and s.modified_type = 'INSERTED' ORDER BY s.bp_Id DESC")
    public List<Supp_bussiness_partner> findSuppliers(String keyword);
	
	@Query( "select s from Supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_active =:status and s.company_id =:company")
	List<Supp_bussiness_partner> SupplierMasterName(@Param("status") boolean status,@Param("company") String company);
	
	@Query( value="select s.bp_id  as bp_Id ,s.bp_name as bp_name FROM supp_bussiness_partner s WHERE s.modified_type = 'INSERTED' AND s.bp_active =1 and s.company_id =:company",nativeQuery=true)
	 List<Map<String, Object>>allsupplier(@Param("company") String company);
	
	@Query(value= "select  s.bp_id  as bp_Id ,s.bp_name as bp_name,s.bp_code as bp_code from supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_active =:status and s.company_id =:company", nativeQuery=true)
	List<Map<String, Object>> SupplierMasterNameNew(@Param("status") boolean status,@Param("company") String company);
	
	@Query(value= "select  s.bp_id  as bp_Id ,s.bp_name as bp_name,s.bp_code as bp_code from supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.fin_year =:fin_year and s.company_id =:company", nativeQuery=true)
	List<Map<String, Object>> SupplierMasterNameWithTotalAmt(@Param("company") String company, @Param("fin_year") String fin_year);
	
	@Query(value= "select  s.bp_id  as bp_Id ,s.bp_name as bp_name,s.bp_code as bp_code from supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_active =:status and s.group_type =:group_type", nativeQuery=true)
	List<Map<String, Object>> SupplierMasterChannelList(@Param("status") boolean status,@Param("group_type") String group_type);
	
	@Query( "select s.bp_Id,UPPER(s.bp_name) from Supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_active =:status and s.company_id =:company")
	List<Object[]> SupplierMasterName_new(@Param("status") boolean status,@Param("company") String company);

	
	//Delete
	@Query( "select s from Supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_active =:status")
	List<Supp_bussiness_partner> SupplierMasterName(@Param("status") boolean status);
	
	@Query( "select s from Supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_active =:status and s.group_type =:group")
	List<Supp_bussiness_partner> supplierListByGroup(@Param("status") boolean status,@Param("group") String group);
	
	
	
	@Query( "select s from Supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_Id =:Code")
	Supp_bussiness_partner getSupplierName(@Param("Code") String Code);
	
	@Query( value="select *, s.bp_id as bp_Id from supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.bp_Id =:Code", nativeQuery = true)
	Map<String,Object> getSupplierNameFast(@Param("Code") String Code);
	/*
	
	
	*/
	@Query( "select a.add1 from Supp_bussiness_partner s,Supp_bussiness_partner_address a where s.modified_type = 'INSERTED' and s.bp_Id=a.bp_Id and s.bp_Id =:suppid")
	String getSupplierAddress(@Param("suppid") String suppid);
	
	@Query( "select s from Supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.id =:id")
	Supp_bussiness_partner SupplierNameById(@Param("id") Long id);
	
	/*
	
	
	*/
	@Query("SELECT s FROM Supp_bussiness_partner s where s.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner> getSupplierDtls();
	
	/*
	
	
	
	@Query( "select b from Supp_bussiness_partner_delv_from b where b.bp_Id = :Id and b.modified_type = 'INSERTED'")
	Supp_bussiness_partner_delv_from getSuppBPNameList(@Param("Id") String Id);
	

	
	
	*/
	@Query("select b from Supp_bussiness_partner b where b.business_unit like %:bunit% and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner> getSupplierThruBU(@Param("bunit") String bunit);
	
	@Query("select b.bp_name from Supp_bussiness_partner b where b.modified_type = 'INSERTED' and b.bp_name =:sname")
	String chkSuppNameStatus(@Param("sname") String sname);
	
	@Query("select i from Supp_bussiness_partner i where i.modified_type = 'INSERTED' and i.bp_code =:code")
	Optional<Supp_bussiness_partner> chkSuppBpCodeStatus(@Param("code") String code);

	@Query("SELECT DISTINCT s.group_type FROM Supp_bussiness_partner s where s.group_type =:group_id and s.modified_type = 'INSERTED'")
    Optional<String> getSuppDtlsThruGroup(@Param("group_id") String group_id);
	
	@Query("select b.bp_name from Supp_bussiness_partner b where b.bp_Id like %:bunit% and b.modified_type = 'INSERTED'")
	String getSupplierThruBUstring(@Param("bunit") String bunit);
	
	@Query(value = "{call checkSupplierMasterUsage(:code)}", nativeQuery = true)
	String checkSupplierMasterUsage(@Param("code") String code);
	
	@Query("select b from Supp_bussiness_partner_address b where b.bp_Id=:bp_Id and b.modified_type = 'INSERTED'")
	Supp_bussiness_partner_address getaddress(@Param("bp_Id") String bp_Id);
	
	
	@Query("select b from Supp_bussiness_partner_statutory b where b.bp_Id=:bp_Id and b.modified_type = 'INSERTED'")
	Supp_bussiness_partner_statutory getstatutary(@Param("bp_Id") String bp_Id);
	
	@Query("select b from Supp_bussiness_partner_accont b where b.bp_Id=:bp_Id and b.modified_type = 'INSERTED'")
	Supp_bussiness_partner_accont getaccounts(@Param("bp_Id") String bp_Id);
	
	
	@Query("select b from Supp_bussiness_partner_addr_dtls b where b.bp_Id=:bp_Id and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_addr_dtls> getcontact(@Param("bp_Id") String bp_Id);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Supp_bussiness_partner p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Supp_bussiness_partner p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int exportuomtallyReverse(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
	@Query(value = "{call getsearchItemdata(:#{#tablename},:#{#item_id},:#{#item_group},:#{#item_category},:#{#item_type},:#{#itemname1},:#{#itemgroup},:#{#itemcategory},:#{#itemtype1},:#{#finyear})}", nativeQuery = true)
	List<Supp_bussiness_partner> getsearchItemdata(@Param("tablename") String tablename,@Param("item_id") String item_id,
			@Param("item_group") String item_group,@Param("item_category") String item_category, 
			@Param("item_type") String item_type,@Param("itemname1") String itemname1,
			@Param("itemgroup") String itemgroup,@Param("itemcategory") String itemcategory,
			@Param("itemtype1") String itemtype1,@Param("finyear") String finyear);
	
	@Query(value= "select s.bp_name as bp_name,s.bp_id as bp_Id from supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.business_unit =:bunit",nativeQuery=true)
	List<Map<String, Object>> getSupplierThruBUNew(@Param("bunit") String bunit);
	
	@Query(value= "select s.bp_name as party_name,s.bp_Id as party_id from supp_bussiness_partner s where s.modified_type = 'INSERTED' and s.business_unit =:bunit",nativeQuery=true)
	List<Map<String, Object>> getPartyList(@Param("bunit") String bunit);
	
	
	@Query(value= "select bp_id as bp_Id,bp_name as bp_name,bp_code as bp_code from supp_bussiness_partner  where modified_type = 'INSERTED' and group_type =:code",nativeQuery=true)
	List<Map<String, Object>> getgroupbysupplier(@Param("code") String code);
	
	@Query(value="select s.gst_no as gst_no from supp_bussiness_partner_statutory s where s.modified_type = 'INSERTED' and s.bp_Id =:code",nativeQuery=true)
	Map<String, Object> getSuppBPStat(@Param("code") String code);
	
	@Query( value="select id as id,bp_id as bp_Id,bp_name as bp_name,bp_code as bp_code,bp_type as bp_type,group_type_name as group_type_name,trans_currency as trans_currency,username as username,export as export from supp_bussiness_partner where modified_type = 'INSERTED' and bp_active =1 and company_id =:company_id ",nativeQuery=true)
	List<Map<String,Object>> getSupplierBPartnersFastApi(@Param("company_id") String company_id);
	
	@Query( value="SELECT\r\n"
			+ "  `soi`.`bp_type`           AS `bp_type`,\r\n"
			+ "  `soi`.`bp_code`           AS `bp_code`,\r\n"
			+ "  `soi`.`bp_name`           AS `bp_name`,\r\n"
			+ "  `soi`.`alt_name`          AS `alt_name`,\r\n"
			+ "  `soi`.`group_type_name`   AS `group_type_name`,\r\n"
			+ "  `soi`.`sub_group_type`    AS `sub_group_type`,\r\n"
			+ "  `soi`.`constitution`      AS `constitution`,\r\n"
			+ "  \r\n"
			+ "  `x`.`state`         AS `state`,\r\n"
			+ "  `x`.`district`      AS `district`,\r\n"
			+ "  x.city AS city,\r\n"
			+ "  `x`.`pincode`       AS `pincode`,\r\n"
			+ "  `x`.`add1`          AS `add1`,\r\n"
			+ "  `x`.`add2`          AS `add2`,\r\n"
			+ "  `x`.`add3`          AS `add3`,\r\n"
			+ "  \r\n"
			+ "  `y`.`pan_no`          AS `pan_no`,\r\n"
			+ "  `y`.`gst_no`          AS `gst_no`,\r\n"
			+ "  y.supplier_type AS supplier_type,\r\n"
			+ "  \r\n"
			+ "  `z`.`ven_name`        AS `ven_name`,\r\n"
			+ "  \r\n"
			+ "  `a`.`accountholder` AS `accountholder`,\r\n"
			+ "  `a`.`acc_no`        AS `acc_no`,\r\n"
			+ "  `a`.`bankname`      AS `bankname`,\r\n"
			+ "  `a`.`branch`        AS `branch`,\r\n"
			+ "  `a`.`ifsc`          AS `ifsc`,\r\n"
			+ "   \r\n"
			+ "  `b`.`mobile`        AS `mobile`,\r\n"
			+ "  `j`.`businessunit_name`        AS `businessunit_name`\r\n"
			+ "  \r\n"
			+ "FROM (`supp_bussiness_partner` `soi`\r\n"
			+ "   LEFT JOIN (SELECT\r\n"
			+ "                `si`.`bp_id`         AS `bp_id`,\r\n"
			+ "                `si`.`state`         AS `state`,\r\n"
			+ "                `si`.`district`      AS `district`,\r\n"
			+ "                si.`city` AS city,\r\n"
			+ "                `si`.`pincode`       AS `pincode`,\r\n"
			+ "                 IFNULL(`si`.`add1`,'')          AS `add1`,\r\n"
			+ "                 IFNULL(`si`.`add2`,'')         AS `add2`,\r\n"
			+ "                 IFNULL(`si`.`add3`,'')         AS `add3`\r\n"
			+ "              FROM (`supp_bussiness_partner_address` `si`)\r\n"
			+ "              WHERE (`si`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `si`.`bp_id`) `x`\r\n"
			+ "     ON (((`soi`.`bp_id` = `x`.`bp_id`)))\r\n"
			+ "      LEFT JOIN (SELECT\r\n"
			+ "                `v`.`bp_id`           AS `bp_id`,\r\n"
			+ "                `v`.`pan_no`          AS `pan_no`,\r\n"
			+ "                `v`.`gst_no`          AS `gst_no`,\r\n"
			+ "                v.`supplier_type` AS supplier_type\r\n"
			+ "                \r\n"
			+ "              FROM (`supp_bussiness_partner_statutory` `v`)\r\n"
			+ "              WHERE (`v`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `v`.`bp_id`) `y`\r\n"
			+ "     ON (((`soi`.`bp_id` = `y`.`bp_id`)))\r\n"
			+ "      LEFT JOIN (SELECT\r\n"
			+ "                `c`.`bp_id`           AS `bp_id`,\r\n"
			+ "                `c`.`ven_code_name` AS `ven_code_name`,\r\n"
			+ "                `c`.`ven_name`        AS `ven_name`\r\n"
			+ "              FROM (`supp_bussiness_partner_broker` `c`)\r\n"
			+ "              WHERE (`c`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `c`.`bp_id`) `z`\r\n"
			+ "     ON (((`soi`.`bp_id` = `z`.`bp_id`)))\r\n"
			+ "     \r\n"
			+ "     LEFT JOIN (SELECT\r\n"
			+ "                `d`.`bp_id`         AS `bp_id`,\r\n"
			+ "                IFNULL(`d`.`accountholder`,'') AS `accountholder`,\r\n"
			+ "                IFNULL(`d`.`acc_no`,'')         AS `acc_no`,\r\n"
			+ "                IFNULL(`d`.`bankname`,'')      AS `bankname`,\r\n"
			+ "                IFNULL(`d`.`branch`,'')        AS `branch`,\r\n"
			+ "                IFNULL(`d`.`ifsc`,'')          AS `ifsc`\r\n"
			+ "              FROM (`supp_bussiness_partner_accont` `d`)\r\n"
			+ "              WHERE (`d`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `d`.`bp_id`) `a`\r\n"
			+ "     ON (((`soi`.`bp_id` = `a`.`bp_id`)))\r\n"
			+ "     \r\n"
			+ "       LEFT JOIN (SELECT\r\n"
			+ "                `f`.`bp_id`           AS `bp_id`,\r\n"
			+ "                IFNULL(`f`.`mobile`,'')        AS `mobile`\r\n"
			+ "              FROM (`supp_bussiness_partner_addr_dtls` `f`)\r\n"
			+ "              WHERE (`f`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `f`.`mobile`) `b`\r\n"
			+ "     ON (((`soi`.`bp_id` = `b`.`bp_id`)))\r\n"
			+ "     \r\n"
			+ "     LEFT JOIN (SELECT\r\n"
			+ "                `i`.`businessunit_id`           AS `businessunit_id`,\r\n"
			+ "                `i`.`businessunit_name`        AS `businessunit_name`\r\n"
			+ "              FROM (`company_business_unit_master` `i`)\r\n"
			+ "              WHERE (`i`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `i`.`businessunit_id`) `j`\r\n"
			+ "     ON (((`soi`.`business_unit` = `j`.`businessunit_id`)))\r\n"
			+ "     \r\n"
			+ "     )\r\n"
			+ "  WHERE (`soi`.`modified_type` = 'INSERTED' AND `soi`.`bp_code` !='NA')  ",nativeQuery=true)
	List<Map<String,Object>> getSupplierMasterReport();
}

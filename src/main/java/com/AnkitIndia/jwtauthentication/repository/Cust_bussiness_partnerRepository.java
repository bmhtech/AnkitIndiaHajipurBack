package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTOC;

public interface Cust_bussiness_partnerRepository extends JpaRepository<Cust_bussiness_partner, Long>{
	@Query("SELECT MAX(SUBSTRING(cp_Id , 4 , length(cp_Id))) FROM Cust_bussiness_partner where cp_Id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(cp_code , 11, length(cp_code))) from Cust_bussiness_partner where cp_code like %:code% and company_id =:company and cp_type =:wtype")
	String getCustSequenceId(@Param("code") String code,@Param("company") String company,@Param("wtype") String wtype);
	
	@Query( "select c from Cust_bussiness_partner c where c.modified_type = 'INSERTED' and c.cp_active =:status ")
	List<Cust_bussiness_partner> getCustomerNameCodeList(@Param("status") boolean status);
	
	@Query( "select c from Cust_bussiness_partner c where c.modified_type = 'INSERTED' ORDER BY c.cp_Id DESC")
	List<Cust_bussiness_partner> findAllCustomers();
	
	@Query("SELECT i FROM Cust_bussiness_partner i WHERE CONCAT(i.cp_code, i.cp_name, i.cp_type, i.alt_name, i.group_type, i.group_name, i.sub_group_type, i.trans_currency) LIKE %?1% and i.modified_type = 'INSERTED' ORDER BY i.cp_Id DESC")
    public List<Cust_bussiness_partner> findCustomers(String keyword);
	
	@Query( "select c from Cust_bussiness_partner c where c.modified_type = 'INSERTED' and c.cp_Id =:code ")
	Cust_bussiness_partner getCustomerName(@Param("code") String code);

	@Query( "select c from Cust_bussiness_partner c where c.cp_Id = :code and c.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner> getCustomerDetails(@Param("code") String code);
	
	@Query( value="select *,c.cp_id as cp_Id from cust_bussiness_partner c where c.cp_Id = :code and c.modified_type = 'INSERTED'", nativeQuery = true)
	List<Map<String,Object>> getCustomerDetailsFast(@Param("code") String code);
	
	@Query( "select c from Cust_bussiness_partner c where c.group_type = :group and c.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner> getCustomerByGroup(@Param("group") String group);
	
	@Query( "select c from Cust_bussiness_partner c where c.modified_type = 'INSERTED' and c.cp_Id =:cp_Id ")
	Cust_bussiness_partner getCustomer(@Param("cp_Id") String cp_Id);
	
	@Query("select b from Cust_bussiness_partner b where b.business_unit like %:bunit% and b.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner> getCustomerThruBU(@Param("bunit") String bunit);
	
	@Query("select b.cp_name from Cust_bussiness_partner b where b.modified_type = 'INSERTED' and b.cp_name =:cname")
	String chkCustNameStatus(@Param("cname") String cname);
	
	@Query("select i from Cust_bussiness_partner i where i.modified_type = 'INSERTED' and i.cp_code =:code")
	Optional<Cust_bussiness_partner> chkCustCodeStatus(@Param("code") String code);
	
	@Query("SELECT DISTINCT c.group_type FROM Cust_bussiness_partner c where c.group_type =:group_id and c.modified_type = 'INSERTED'")
    Optional<String> getCustDtlsThruGroup(@Param("group_id") String group_id);
	
	@Query("select b.cp_name from Cust_bussiness_partner b where b.modified_type = 'INSERTED' and b.cp_Id like %:cp_id%")
	String getCustomerThruBUstring(@Param("cp_id") String cp_id);
	
	@Query("select b from Cust_bussiness_partner b where b.modified_type = 'INSERTED' and b.cp_Id =:cp_id")
	Cust_bussiness_partner getCustomerThruBUstringnew(@Param("cp_id") String cp_id);
	
	@Query(value = "{call checkCustomerMasterUsage(:code)}", nativeQuery = true)
	String checkCustomerMasterUsage(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Cust_bussiness_partner p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Query("select b from Cust_bussiness_partner_address b where b.modified_type = 'INSERTED' and b.cp_Id =:cp_id")
	Cust_bussiness_partner_address gettallycreditnotestate (@Param("cp_id") String cp_id);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Cust_bussiness_partner p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int exportuomtallyReverse(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
	@Query( "select c from Cust_bussiness_partner c where c.modified_type = 'INSERTED' and c.cp_active =1 and c.company_id =:company_id ")
	List<Cust_bussiness_partner> findallcustomer(@Param("company_id") String company_id);
	
	@Query( value="select c.cp_id as bp_Id,c.cp_name as bp_name FROM cust_bussiness_partner c WHERE c.modified_type = 'INSERTED' AND c.cp_active =1 and c.company_id =:company_id ",nativeQuery=true)
	List<Map<String, Object>>allcustomer(@Param("company_id") String company_id);
	
	@Query( "select c.cp_Id,c.cp_code,c.cp_name from Cust_bussiness_partner c where c.modified_type = 'INSERTED' and c.cp_active =1 and c.company_id =:company_id ")
	List<Object[]> findallnewcustomer(@Param("company_id") String company_id);
	
	@Query( value="select id,cp_Id,cp_code,cp_name,alt_name,group_name,cp_type,username,export from cust_bussiness_partner where modified_type = 'INSERTED' and cp_active =1 and company_id =:company_id ",nativeQuery=true)
	List<Map<String,Object>> findallnewcustomerfast(@Param("company_id") String company_id);
	
	
	@Query(value = "{call getsearchItemdata(:#{#tablename},:#{#item_id},:#{#item_group},:#{#item_category},:#{#item_type},:#{#itemname1},:#{#itemgroup},:#{#itemcategory},:#{#itemtype1},:#{#finyear})}", nativeQuery = true)
	List<Cust_bussiness_partner> getsearchItemdata(@Param("tablename") String tablename,@Param("item_id") String item_id,
			@Param("item_group") String item_group,@Param("item_category") String item_category, 
			@Param("item_type") String item_type,@Param("itemname1") String itemname1,
			@Param("itemgroup") String itemgroup,@Param("itemcategory") String itemcategory,
			@Param("itemtype1") String itemtype1,@Param("finyear") String finyear);
	
	@Query(value="select c.cp_Id,c.cp_code,c.cp_name from cust_bussiness_partner c where c.modified_type='INSERTED' and c.cp_active='1' and c.company_id=:company", nativeQuery=true)
	List<Map<String,Object>> getCustList(@Param("company") String company);
	
	@Query(value="select c.cp_Id,c.cp_code,c.cp_name from cust_bussiness_partner c where c.modified_type='INSERTED' and c.cp_active='1' and c.group_type=:group_type", nativeQuery=true)
	List<Map<String,Object>> getCustChannelList(@Param("group_type") String group_type);
	
	@Query(value="select b.cp_id as cp_Id,b.cp_code as cp_code,b.cp_name as cp_name from cust_bussiness_partner b where b.business_unit=:bunit and b.modified_type='INSERTED'", nativeQuery=true)
	List<Map<String,Object>> getCustBList(@Param("bunit") String bunit);
	
	@Query(value= "select s.cp_name as party_name,s.cp_Id as party_id from cust_bussiness_partner s where s.modified_type = 'INSERTED' and s.business_unit =:bunit",nativeQuery=true)
	List<Map<String, Object>> getPartyList(@Param("bunit") String bunit);
	
	@Query(value="select s.gst_no as gst_no from cust_bussiness_partner_statutory s where s.modified_type = 'INSERTED' and s.cp_Id =:code",nativeQuery=true)
	Map<String, Object> getCustBPStat(@Param("code") String code);
	
	@Query(value="select s.gst_no as gst_no from cust_bussiness_partner_statutory s where s.modified_type = 'INSERTED' and s.cp_Id =:code",nativeQuery=true)
	String getInvCustBPGstStatus(@Param("code") String code);
	
	@Query( value="SELECT\r\n"
			+ "  `soi`.`cp_type`           AS `cp_type`,\r\n"
			+ "  `soi`.`cp_code`           AS `cp_code`,\r\n"
			+ "  `soi`.`cp_name`           AS `cp_name`,\r\n"
			+ "  `soi`.`alt_name`          AS `alt_name`,\r\n"
			+ "  `soi`.`print_to_name`          AS `print_to_name`,\r\n"
			+ "  `soi`.`group_name`   AS `group_name`,\r\n"
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
			+ "  y.customer_type AS customer_type,\r\n"
			+ "  \r\n"
			+ "  `z`.`ven_name`        AS `ven_name`,\r\n"
			+ "  \r\n"
			+ "  `a`.`contact_person` AS `contact_person`,\r\n"
			+ "  `a`.`mobile`        AS `mobile`,\r\n"
			+ "  \r\n"
			+ "  `j`.`businessunit_name`        AS `businessunit_name`\r\n"
			+ "  \r\n"
			+ "FROM (`cust_bussiness_partner` `soi`\r\n"
			+ "   LEFT JOIN (SELECT\r\n"
			+ "                `si`.`cp_id`         AS `cp_id`,\r\n"
			+ "                `si`.`state`         AS `state`,\r\n"
			+ "                `si`.`district`      AS `district`,\r\n"
			+ "                si.`city` AS city,\r\n"
			+ "                `si`.`pincode`       AS `pincode`,\r\n"
			+ "                 IFNULL(`si`.`add1`,'')          AS `add1`,\r\n"
			+ "                 IFNULL(`si`.`add2`,'')         AS `add2`,\r\n"
			+ "                 IFNULL(`si`.`add3`,'')         AS `add3`\r\n"
			+ "              FROM (`cust_bussiness_partner_address` `si`)\r\n"
			+ "              WHERE (`si`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `si`.`cp_id`) `x`\r\n"
			+ "     ON (((`soi`.`cp_id` = `x`.`cp_id`)))\r\n"
			+ "      LEFT JOIN (SELECT\r\n"
			+ "                `v`.`cp_id`           AS `cp_id`,\r\n"
			+ "                `v`.`pan_no`          AS `pan_no`,\r\n"
			+ "                `v`.`gst_no`          AS `gst_no`,\r\n"
			+ "                v.`customer_type` AS customer_type\r\n"
			+ "                \r\n"
			+ "              FROM (`cust_bussiness_partner_statutory` `v`)\r\n"
			+ "              WHERE (`v`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `v`.`cp_id`) `y`\r\n"
			+ "     ON (((`soi`.`cp_id` = `y`.`cp_id`)))\r\n"
			+ "      LEFT JOIN (SELECT\r\n"
			+ "                `c`.`cp_id`           AS `cp_id`,\r\n"
			+ "                `c`.`ven_code_name` AS `ven_code_name`,\r\n"
			+ "                `c`.`ven_name`        AS `ven_name`\r\n"
			+ "              FROM (`cust_bussiness_partner_broker` `c`)\r\n"
			+ "              WHERE (`c`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `c`.`cp_id`) `z`\r\n"
			+ "     ON (((`soi`.`cp_id` = `z`.`cp_id`)))\r\n"
			+ "     \r\n"
			+ "     LEFT JOIN (SELECT\r\n"
			+ "                `d`.`cp_id`         AS `cp_id`,\r\n"
			+ "                IFNULL(`d`.`contact_person`,'') AS `contact_person`,\r\n"
			+ "                IFNULL(`d`.`mobile`,'')         AS `mobile`\r\n"
			+ "                \r\n"
			+ "              FROM (`cust_bussiness_partner_address_dtls` `d`)\r\n"
			+ "              WHERE (`d`.`modified_type` = 'INSERTED')\r\n"
			+ "              GROUP BY `d`.`cp_id`) `a`\r\n"
			+ "     ON (((`soi`.`cp_id` = `a`.`cp_id`)))\r\n"
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
			+ "  WHERE (`soi`.`modified_type` = 'INSERTED' AND `soi`.`cp_code` !='NA')  ",nativeQuery=true)
	List<Map<String,Object>> getCustomerMasterReport();
}

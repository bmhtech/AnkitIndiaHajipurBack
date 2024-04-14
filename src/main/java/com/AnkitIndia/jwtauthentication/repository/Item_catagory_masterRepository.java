package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_catagory_master;

public interface Item_catagory_masterRepository extends JpaRepository<Item_catagory_master, Long> {
	
	
	@Query("SELECT MAX(SUBSTRING(catagory_id , 3 , length(catagory_id))) FROM Item_catagory_master where catagory_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(catagory_code , 8, length(catagory_code))) from Item_catagory_master where catagory_code like %:code% and company_id =:company ")
	String getIcatSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select c from Item_catagory_master c where c.id =:id")
	Item_catagory_master findCategoryDtls(@Param("id") Long id);
	
	@Query("SELECT e FROM Item_catagory_master e where e.modified_type != 'DELETED' and e.company_id =:company ORDER BY e.catagory_id DESC")
    public List<Item_catagory_master> findAllItemCategories(@Param("company") String company);
	
	@Query("SELECT e FROM Item_catagory_master e where e.modified_type != 'DELETED'")
    public List<Item_catagory_master> itemCategoriesList(@Param("pageable") Pageable pageable);
    
    @Query("SELECT p FROM Item_catagory_master p WHERE p.company_id =:company and CONCAT(p.catagory_code, p.catagory_name, p.item_type) LIKE %:keyword% ORDER BY p.catagory_id DESC")
    public List<Item_catagory_master> findItemCategories(String keyword,@Param("company") String company);
	
	@Query( "select c from Item_catagory_master c where c.modified_type != 'DELETED' and c.item_active =:status and c.company_id =:company")
	List<Item_catagory_master> getItemCategotyList(@Param("status") boolean status,@Param("company") String company);
	
	@Query( "select c from Item_catagory_master c where c.catagory_id = :code and c.modified_type != 'DELETED'")
	Item_catagory_master catagoryNameByCode(@Param("code") String code);

	@Query("SELECT ic FROM Item_catagory_master ic where ic.company_id =:company and ic.fin_year =:fin_year and ic.modified_type != 'DELETED'")
    public List<Item_catagory_master> findICategories(@Param("company") String company,@Param("fin_year") String fin_year);
    
    @Query("select i.catagory_name from Item_catagory_master i where i.modified_type != 'DELETED' and i.catagory_name =:cname ")
	String chkCatNameStatus(@Param("cname") String cname);
    
    @Query("select i from Item_catagory_master i where i.modified_type != 'DELETED' and i.catagory_code =:code")
	Optional<Item_catagory_master> chkItemCatagoryCodeStatus(@Param("code") String code);
}

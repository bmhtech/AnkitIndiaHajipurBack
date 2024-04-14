package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.User_Role_Access;

public interface User_Role_AccessRepository extends JpaRepository<User_Role_Access, Long>{

	@Query("select a.item_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleItemMaster(@Param("role_access") String role_access);
	
	@Query("select a.supplier_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleSupplierMaster(@Param("role_access") String role_access);
	
	@Query("select a.cusromer_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleCustomerMaster(@Param("role_access") String role_access);
	
	@Query("select a.broker_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleBrokerMaster(@Param("role_access") String role_access);
	
	@Query("select a.other_partner_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleOtherPartnerMaster(@Param("role_access") String role_access);
	
	@Query("select a.other_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleOtherMaster(@Param("role_access") String role_access);
	
	@Query("select a.mislenious_master from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleMisleniousMaster(@Param("role_access") String role_access);
	
	@Query("select a.purchase_inventory from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRolePurchaseInventory(@Param("role_access") String role_access);
	
	@Query("select a.weighment from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleWeighment(@Param("role_access") String role_access);
	
	@Query("select a.sales_transaction from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleSalesTransaction(@Param("role_access") String role_access);
	
	@Query("select a.production_module from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleProductionModule(@Param("role_access") String role_access);
	
	@Query("select a.stock_transfer from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleStockTransfer(@Param("role_access") String role_access);
	
	@Query("select a.sales_pur_report from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getRoleSalesPurReport(@Param("role_access") String role_access);
	
	@Query("select a.stock_transaction from User_Role_Access a where a.role_id=:role_access")
	List<User_Role_Access> getStockTransaction(@Param("role_access") String role_access);
	
	@Query("select a from User_Role_Access a where a.role_id=:role")
	User_Role_Access searchrole(@Param("role") String role);
}

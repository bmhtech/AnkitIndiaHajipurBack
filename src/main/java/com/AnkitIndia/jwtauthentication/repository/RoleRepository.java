package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AnkitIndia.jwtauthentication.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	//Optional<Role> findByName(@Param("roleName") RoleName roleName);
	
	@Query("select MAX(id) from Role")
	String countId();
	
	@Query("Select b from Role b  where b.role_id =:role_id")
	Optional<Role> getRoleNamePId(@Param("role_id") String role_id);
	
	@Query("Select b.name from Role b  where b.role_id =:role_id")
	String getRoleNamePIdS(@Param("role_id") String role_id);
	
	@Query("Select b.name from Role b  where b.role_id =:role_id")
	String getRoleNameById(@Param("role_id") String role_id);
	
	@Query("Select b.id from Role b  where b.role_id =:role_id")
	String getRoleId(@Param("role_id") String role_id);
	
	@Query("Select b.name from Role b  where b.role_id =:role_id")
	boolean getRoleById(@Param("role_id") String role_id);
	
	@Query("Select b from Role b  where b.role_id =:role_id")
	Role getRoleDtls(@Param("role_id") String role_id);
	
	@Query("Select b from Role b  where b.id =:id")
	Optional<Role> getRoleIdById(@Param("id") long id);
	
	@Query("Select b from Role b  where b.id =:id")
	Role getRoleIdByIdWOOP(@Param("id") long id);
	
	@Query("Select b.name from Role b  where b.id =:id")
	String getRoleNmById(@Param("id") long id);
	
	@Query( "select s from Role s")
	List<Role> findAllRole();
	
	@Query( "select s from Role s where s.id =:id")
	List<Role> getRolesThruUserId(@Param("id") long id);
	  
	@Query("Select b.role_id from Role b  where b.name =:name")
	String getRoleIdByName(@Param("name") String name);
	 
	@Query("Select b.parent_role from Role b  where b.parent_role_id =:parent_role_id and b.parent_role =b.name and b.parent_role_id =b.role_id")
	String getParentName(@Param("parent_role_id") String parent_role_id);
	
	@Query("Select b.id from Role b  where b.role_id =:name")
	String getidByRoleName(@Param("name") String name);
	
	
}
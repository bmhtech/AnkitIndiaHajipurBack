package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.User_roles;

public interface User_rolesRepository extends JpaRepository<User_roles, Long>{

	 @Query( "select s from User_roles s where role_id = :role_id")
	 List<User_roles> getUserRoleList(@Param("role_id") long role_id);
	
	 @Query("Select b.role_id from User_roles b  where b.user_id =:user_id")
	 long getRoleIdThUId(@Param("user_id") long user_id);
	 
	 @Query("Select b from User_roles b  where b.user_id =:user_id")
	 List<User_roles> getRoleIdThUIdList(@Param("user_id") long user_id);
	 
	 @Query("Select b.user_id from User_roles b  where b.role_id =:role_id")
	 long getUserIdThRid(@Param("role_id") long role_id);
	 
	 @Modifying(clearAutomatically = true)
     @Query("UPDATE User_roles u SET u.roleaccessjson =:accjson where u.user_id =:user and u.role_id =:role")
     int updateUserRoles(@Param("user") long user,@Param("role") long role,@Param("accjson") String accjson);
	 
	 @Query("Select u from User_roles u where u.user_id =:user and u.role_id =:role")
	 User_roles getUserRoles(@Param("user") long user,@Param("role") long role);
	 
	 @Query("Select b.user_id from User_roles b  where b.role_id =:role_id")
	 List<Long> getUserRolesIdById(@Param("role_id") long role_id);
	 
}

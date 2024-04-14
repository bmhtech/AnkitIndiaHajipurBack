package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AnkitIndia.jwtauthentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(@Param("username") String username);
    
    Boolean existsByEmail(@Param("email") String email);
    
    @Query("select u from User u where u.username =:username")
    User getUserDetails(@Param("username") String username);
    
    @Query("select u from User u where u.email =:email")
    User getUserDetailsThruEmail(@Param("email") String email);
    
    @Query( "select s from User s ")
	List<User> getUser();

    @Query("SELECT a FROM User a where a.id = :id") 
    List<User> getRoleThroughUser(@Param("id") long id);
  
    @Query("SELECT a.id FROM User a where a.name = :name") 
    long getTIdThroughUser(@Param("name") String name);
  
    @Query("SELECT a.id FROM User a where a.username = :name") 
    long getTIdThroughUserName(@Param("name") String name);
  
    @Query("SELECT a.username FROM User a where a.id = :id") 
    String getUserNameThTId(@Param("id") long id);
  
    @Query("SELECT a FROM User a where a.username = :name") 
    Optional<User> checkuser(@Param("name") String name);
    
    @Query("SELECT a FROM User a where a.id = :id") 
    List<User> getUserRolesNameList(@Param("id") long id);
    
    @Query("select a.email from User a ")
	List getUserEmailDuplicateCheck();
    
    @Query("select a.password from User a where a.username=:username ")
	List getUserEmailPswdDuplicateCheck(@Param("username") String username);
    
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User w SET w.password =:pswd WHERE w.username = :empusername ")
    int updateUserPswd(@Param("pswd") String pswd,@Param("empusername") String empusername);
	
    
    
}
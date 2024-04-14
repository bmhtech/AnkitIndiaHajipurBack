package com.AnkitIndia.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.System_settings;

public interface System_settingsRepository extends JpaRepository<System_settings, Long>{
	
	@Query("select s from System_settings s where s.modified_type !='DELETED' and s.company_id =:comp")
	Optional<System_settings> getSystemSettingsByComp(@Param("comp") String comp);
}

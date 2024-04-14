package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="System_settings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "company_id"
            })
    })
public class System_settings extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean code_generator;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String generator_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean vehicle_doc;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String vehicle_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_name;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String fin_year;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String username;
	
    @Column(columnDefinition="varchar(50) default 'INSERTED'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String deleted_by;
	
	@Transient
	private String status;
	
}

package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Department_master")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Department_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String department_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String department_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean department_active;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String department_remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
}

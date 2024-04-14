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
@Table(name="VehicleTypeMaster")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

public class VehicleTypeMaster extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehtype_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehtype_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String vehtype_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean vehtype_active;
	
	private int noofwheels;

	@Column(columnDefinition="varchar(100) default '0'")
	private String vehtype_remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
}

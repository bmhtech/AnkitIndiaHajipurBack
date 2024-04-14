package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ShopFloorAccess")
public class ShopFloorAccess extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String accessid;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String shop_floor;
	
	/*@Column(columnDefinition="varchar(100) default '0'")
	private String shop_floorname;*/
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String operator_name;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String operatorname;
	
}

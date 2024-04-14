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

@Entity
@Table(name="Loading_point")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Loading_point extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(50) default '0'")
	private String loading_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String loading_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String loading_name;

	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;

}

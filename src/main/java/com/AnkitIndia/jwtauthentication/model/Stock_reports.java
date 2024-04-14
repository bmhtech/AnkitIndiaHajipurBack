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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stock_reports")
public class Stock_reports {                           

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String item;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String item_uom;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double opening;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double issued;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double current_stock;
}

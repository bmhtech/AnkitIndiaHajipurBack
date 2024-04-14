package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table(name="Dailyproduction_Dtls")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dailyproduction_Dtls extends CommonProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dailyproductionid;

	private int slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String six_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String six_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String six_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eight_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eight_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eight_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ten_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ten_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ten_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twelve_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twelve_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twelve_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String forteen_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String forteen_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String forteen_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixteen_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixteen_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixteen_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eighteen_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eighteen_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eighteen_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twenty_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twenty_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twenty_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twentytwo_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twentytwo_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twentytwo_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String zero_bag;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String zero_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String zero_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String two_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String two_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String two_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String four_bag;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String four_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String four_percen;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total_percen;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "d_id")
    private Dailyproduction dailyproduction;
}

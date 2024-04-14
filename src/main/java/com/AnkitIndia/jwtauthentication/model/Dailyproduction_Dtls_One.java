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
@Table(name="Dailyproduction_Dtls_One")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dailyproduction_Dtls_One extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dailyproductionid;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String six_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String six_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eight_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eight_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ten_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ten_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twelve_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twelve_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String forteen_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String forteen_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixteen_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixteen_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eighteen_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eighteen_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twenty_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twenty_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twentytwo_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twentytwo_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String zero_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String zero_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String two_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String two_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String four_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String four_quantity_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total_bag_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total_quantity_total;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "d_id")
    private Dailyproduction dailyproduction;
}

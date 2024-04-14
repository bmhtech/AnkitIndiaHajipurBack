package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Production_planning_shift_details")
public class Production_planning_shift_details {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppd_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppds_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pp_shiftid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shop_floor;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fromdate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String todate;
	
	private int shift;
	
	//New ********************************************************
	@Column(columnDefinition="varchar(20) default '0'")
	private String shift_startdate;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String shift_starttime;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String shift_enddate;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String shift_endtime;
	//New ********************************************************
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
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
	
	@Column(columnDefinition="varchar(20) default 'No'")
	private String shift_used;
	
	@JsonIgnore
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "shiftid")
    private Production_planning_periodic_date_details pDate_details;

	

}

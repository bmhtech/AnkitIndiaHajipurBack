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
public class Production_planning_shift_details_static {

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
	
	@JsonIgnore
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "shiftid")
    private Production_planning_periodic_date_details_static pDate_details;
/*
	public Production_planning_shift_details_static(String prod_plan_id, String prod_plan_code, String ppd_id,
			String ppds_id, String pp_shiftid, String business_unit, String shop_floor, String process,
			String production, String fromdate, String todate, int shift, String shift_startdate,
			String shift_starttime, String shift_enddate, String shift_endtime, String company_id, String fin_year,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on,
			String updated_by, LocalDateTime deleted_on, String deleted_by,
			Production_planning_periodic_date_details_static pDate_details) {
		super();
		this.prod_plan_id = prod_plan_id;
		this.prod_plan_code = prod_plan_code;
		this.ppd_id = ppd_id;
		this.ppds_id = ppds_id;
		this.pp_shiftid = pp_shiftid;
		this.business_unit = business_unit;
		this.shop_floor = shop_floor;
		this.process = process;
		this.production = production;
		this.fromdate = fromdate;
		this.todate = todate;
		this.shift = shift;
		this.shift_startdate = shift_startdate;
		this.shift_starttime = shift_starttime;
		this.shift_enddate = shift_enddate;
		this.shift_endtime = shift_endtime;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.pDate_details = pDate_details;
	}
*/

}

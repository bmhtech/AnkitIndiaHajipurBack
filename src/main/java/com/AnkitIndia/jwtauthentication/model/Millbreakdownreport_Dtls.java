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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Millbreakdownreport_Dtls")
public class Millbreakdownreport_Dtls extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String millbreakdownid;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String breakdowncount;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String startdate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String starttime;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enddate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String endtime;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String timediff;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String shift;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date_diff;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "m_id")
    private Millbreakdownreport millbreakdownreport;
	
}

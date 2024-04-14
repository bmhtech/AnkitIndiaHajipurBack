package com.AnkitIndia.jwtauthentication.model;

import java.util.Date;

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
@Table(name="Broker_master_pty")
public class Broker_master_pty extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_code;
	
	private Long srl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_code_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pty_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String basis;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String based_on;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double rate;
	
	/* New */
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	private Date eff_date;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "b_Id")
	private Broker_master broker_master;

}

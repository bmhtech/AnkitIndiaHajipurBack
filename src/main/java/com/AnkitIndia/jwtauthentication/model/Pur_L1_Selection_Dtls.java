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
import javax.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pur_L1_Selection_Dtls")
public class Pur_L1_Selection_Dtls extends CommonProperties {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String lsel_id;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pq_doc_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vendor_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String vendor_name;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(10,2)")
	private double price;
	
	private Date req_date;
	
	private Date qout_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String req_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qout_qty;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(10,2)")
	private double amount;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String status;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "PL_Id")
    private Pur_L1_Selection pur_L1_Selection;

}

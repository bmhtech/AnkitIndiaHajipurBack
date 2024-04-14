package com.AnkitIndia.jwtauthentication.model;

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
@Table(name="Sales_credit_note_trans_dtls")
public class Sales_credit_note_trans_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String creditnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String creditnoteno;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transname; 	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicletype;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicleno; 
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ewaybillno;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String ewaybilldate; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String transporter; 
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "scn_id")
	private Sales_credit_note sales_credit_note;
}

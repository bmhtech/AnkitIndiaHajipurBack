package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
//@Embeddable
@Table(name="Sale_credit_note_einvoice_gen")
public class Sale_credit_note_einvoice_gen extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String creditnoteid;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean f_api_status;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String auth_token;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean s_api_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ack_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ack_date;
	
	@Column(columnDefinition="varchar(300) default 'NA'")
	private String irn_no;
	
	@Column(columnDefinition="TEXT")
	private String sighned_invoice;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean cancel_status;
	
	@Column(columnDefinition="TEXT")
	private String cancel_message;
	
	private LocalDateTime cancel_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cancel_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eway_bill_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eway_bill_date;
	
	private LocalDateTime waybill_create_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String waybill_create_by;
	
	@Column(columnDefinition="TEXT")
	private String waybill_cancel_message;
	
	private LocalDateTime waybill_cancel_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String waybill_cancel_by;

}

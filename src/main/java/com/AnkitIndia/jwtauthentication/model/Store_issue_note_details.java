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
@Table(name="Store_issue_note_details")
public class Store_issue_note_details extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String store_issue_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item;

	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String classified_item;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double avail_qty;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double issue_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String return_qty;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String issue_for;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String store_issue_date;
	
	@Column(columnDefinition = "varchar(70) default 'NA'")
	private String warehouse;	
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String warehouse_name;	
	
	@Column(columnDefinition = "varchar(20) default 'NA'")
	private String transferable;
	
	@Column(columnDefinition = "varchar(70) default 'NA'")
	private String transfer_warehouse;	
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String transfer_warehouse_name;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "s_id")
    private Store_issue_note storeissuenote;
}

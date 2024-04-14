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
@Table(name="Credit_item_groupwise_hsnsumm")
public class Credit_item_groupwise_hsnsumm extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String creditnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String hsn_code;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double amount; 
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "scn_id")
	private Sales_credit_note sales_credit_note;
	
}

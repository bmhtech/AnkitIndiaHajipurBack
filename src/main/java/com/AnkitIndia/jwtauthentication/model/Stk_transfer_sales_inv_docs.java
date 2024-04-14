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
@Table(name="Stk_transfer_sales_inv_docs")
public class Stk_transfer_sales_inv_docs extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String stk_sales_inv_id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String stk_sales_inv_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String doc_name;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stsi_id")
	private Stk_transfer_sales_inv sTransfer_sales_inv;
}

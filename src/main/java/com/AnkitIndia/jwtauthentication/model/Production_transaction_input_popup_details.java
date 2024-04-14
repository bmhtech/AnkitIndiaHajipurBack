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
@Table(name="Production_transaction_input_popup_details")
public class Production_transaction_input_popup_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_popupid;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String shifttime;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String itemqty;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String packingqty;
    
    private int sl_no;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ptinid")
    private Production_transaction_input_details pTransaction_input_details;
}

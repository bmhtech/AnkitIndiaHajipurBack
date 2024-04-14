package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Item_master_inv_data2")
public class Item_master_inv_data2 extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean neg_inv_allow;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean manage_inv_wh;
	
	private int sales_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_uom;
	
	private int pur_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_uom;
	
	private int eoq_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eoq_uom;
	
	private int reorder_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reorder_uom;
	
	private boolean insert_status;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Im_Id")
    private Item_master item_master;

}

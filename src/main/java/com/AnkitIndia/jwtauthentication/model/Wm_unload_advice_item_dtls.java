package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Wm_unload_advice_item_dtls")
public class Wm_unload_advice_item_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	private int sl_no;  
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String packing_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_item_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_size;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_weight;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_type;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double quantity;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	//private int s_qty;  
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double s_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String s_uom;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double mat_wt;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String qc_norms;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wearhouse;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rack;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String base_uom;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double base_qty;

	private int pur_dyn_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String con_factor;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;  /* new 14-0-2022 */
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String classified_item_name;
	 
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String price_based_on;
	 
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice wm_unload_advice;
	
	
	
	//Static
//	@OneToOne(fetch = FetchType.EAGER,mappedBy="Wm_unload_advice_item_dtls",cascade =CascadeType.ALL)
//	private Pur_ord_item_dtls_track pur_ord_item_dtls_track;
}

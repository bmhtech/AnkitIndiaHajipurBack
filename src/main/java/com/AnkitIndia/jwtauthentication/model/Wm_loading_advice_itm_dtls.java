package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
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
@Table(name="Wm_loading_advice_itm_dtls")

/*@NamedStoredProcedureQuery(name = "Wm_loading_advice_itm_dtls.updateSalesorderwithLoadingAdvice", 
procedureName = "updateSalesorderwithLoadingAdvice", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class)})
*/


public class Wm_loading_advice_itm_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String advice_no;
	
	private int sl_no;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String item_code;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition = "varchar(200) default 'NA'")
	private String packing_name;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String alter_item_code;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String alter_item_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String alter_packing;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String alter_packing_name;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String hsn_code;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double quantity;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double tolerance_qty;
	
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double s_quantity;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double alt_s_quantity;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String s_uom;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double mat_wt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String warehouse;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String warehouse_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String stack_rack;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String stack_rack_name;
	
	@Column(columnDefinition = "Double(12,2)")
	private double base_qty;
	
	@Column(columnDefinition = "Double(12,2)")
	private double amount;
	
	@Column(columnDefinition = "Double(12,2)")
	private double price;
	
	@Column(columnDefinition = "Double(12,2)")
	private double pricecal;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String price_based_on;
    
    @Column(columnDefinition = "Double(12,2)")
	private double discount_rate;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String discount_type;
    
    @Column(columnDefinition = "Double(12,2)")
	private double discount_amt;
    
    @Column(columnDefinition = "Double(12,2)")
	private double tolerance;
    
    @Column(columnDefinition = "varchar(50) default 0")
	private String tax_code;
    
    @Column(columnDefinition = "Double(12,2)")
	private double tax_rate;
    
    @Column(columnDefinition = "Double(12,2)")
	private double tax_amt;
    
    @Column(columnDefinition = "Double(12,2)")
	private double total_amt;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String acc_norms;
    
    @Column(columnDefinition = "varchar(20) default 'NA'")
  	private String order_id;
           
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wl_id")
    private Wm_loading_advice wm_loading_advice;
	
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "so_id")
    private Sales_Order_Item_Dtls sales_Order_Item_Dtls;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wld_id")
    private Wm_unload_wgmnt wm_unload_wgmnt;
}

package com.AnkitIndia.jwtauthentication.model;

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
@Table(name="Sales_Order_Item_Dtls")
public class Sales_Order_Item_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String order_no;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String packing_name;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String hsn_code;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition = "Double(10,0)")
	private double squantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String suom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String con_factor;
	
	@Column(columnDefinition = "Double(15,3) default 0.000")
	private double mat_wt;
	
	@Column(columnDefinition = "Double(15,2)")
	private double price;

	@Column(columnDefinition="varchar(50) default 0")
	private String price_based_on;
	
	@Column(columnDefinition = "Double(15,2)")
	private double amount;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String discount_type;
	
	@Column(columnDefinition = "Double(10,2)")
	private double discount_rate;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tolerance;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tax_code;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;
	
	/*@Column(columnDefinition = "Double(10,2)")
	private double tax_amt;
	
	@Column(columnDefinition = "Double(10,2)")
	private double total_amt;*/
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_norms;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packing_list_req;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_list;

	@Column(columnDefinition = "Double(10,2)")
	private double discount_amt;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_amt;
	
	@Column(columnDefinition = "Double(15,2)")
	private double total_amt;
	
	@Column(columnDefinition = "Double(15,2)")
	private double cgst_amt;
	
	@Column(columnDefinition = "Double(15,2)")
	private double sgst_amt;
	
	@Column(columnDefinition = "Double(15,2)")
	private double igst_amt;
	
	//@Column(columnDefinition = "Double(15,2) default 0.00")
	//private double ratechartamt;
	
	//@Column(columnDefinition = "Double(15,2) default 0.00")
	//private double ratecharttol;
	
	//@Column(columnDefinition = "varchar(50) default 0")
//	private String rateres;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "so_id")
    private Sales_Order sales_order;
	
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_Order_Item_Dtls",cascade = CascadeType.ALL)
	private Wm_loading_advice_itm_dtls wm_loading_advice_itm_dtls;
}

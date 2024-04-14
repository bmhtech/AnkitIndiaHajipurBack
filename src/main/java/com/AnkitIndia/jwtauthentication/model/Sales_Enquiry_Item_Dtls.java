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
@Table(name="Sales_Enquiry_Item_Dtls")
public class Sales_Enquiry_Item_Dtls extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enquiry_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String enquiry_no;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_item;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String packing_item_name;
	
	@Column(columnDefinition = "Double(10,0)")
	private double packing_quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_uom;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String qc_norms;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double mat_wt;
	
	@Column(columnDefinition = "Double(10,2)")
	private double price;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tax_code;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;

	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "se_id")
    private Sales_Enquiry sales_enquiry;
	
}

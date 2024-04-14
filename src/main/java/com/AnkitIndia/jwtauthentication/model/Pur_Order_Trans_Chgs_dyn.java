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
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pur_Order_Trans_Chgs_dyn")
public class Pur_Order_Trans_Chgs_dyn extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_order_no;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_trans;
	
	@Size(max = 20)
	private String transport_from;
	
	@Size(max = 20)
	private String transport_to;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transport_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_code;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double chgs_rate_value;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String chgs_remarks;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double distance_in_km;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double tax_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transportation_acc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_codename;

	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_accname;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double allowed_shortage;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String deduction_basedon;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_id;
     
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;

}

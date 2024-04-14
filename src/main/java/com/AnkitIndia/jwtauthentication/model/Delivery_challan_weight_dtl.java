package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
@Table(name="Delivery_challan_weight_dtl")
public class Delivery_challan_weight_dtl extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_cid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_uom_name;
    
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_gross;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_tare;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_net; 
    
	@Column(columnDefinition="varchar(50) default 0")
	private String own_permit_no;
    
    private Date own_date;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String own_slip_no;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String party_uom;
    
    @Column(columnDefinition = "Double(10,3) default 0.000")
	private double party_gross;
    
    @Column(columnDefinition = "Double(10,3) default 0.000")
	private double party_tare;
    
    @Column(columnDefinition = "Double(10,3) default 0.000")
	private double party_net;
    
    private Date party_date;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String party_slip_no;
    
    
    
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "dc_id")
    private Delivery_challan delivery_challan;
}

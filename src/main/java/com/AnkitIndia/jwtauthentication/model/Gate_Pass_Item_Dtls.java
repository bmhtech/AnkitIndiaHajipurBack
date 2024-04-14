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
@Table(name="Gate_Pass_Item_Dtls")
public class Gate_Pass_Item_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gatepass_id;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pack_mat;
	
	@Column(columnDefinition = "Double(10,2)")
	private double pack_qty;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pack_uom;
    
    @Column(columnDefinition = "Double(10,2)")
	private double item_qty;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String item_uom;
    
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "gp_id")
    private Gate_Pass gate_pass;
}

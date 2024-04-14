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
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Item_opening_stk_pack_dtls", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            	"business_unit","item_id","packing_id"
            })
        })
public class Item_opening_stk_pack_dtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transe_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tdate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packing_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_id;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double open_packing_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double curr_packing_qty;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String packing_uom;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double open_item_qty;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double curr_item_qty;

    @Column(columnDefinition="Double(20,2) default 0.00")
	private double sold_packing_qty;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double sold_item_qty;
    
    @Column(columnDefinition="Double(20,2) default 0.00")
	private double pur_packing_qty;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double pur_item_qty;
    
    @Column(columnDefinition="Double(20,2) default 0.00")
	private double prod_packing_qty;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double prod_item_qty;
    
    @Column(columnDefinition="Double(20,2) default 0.00")
	private double salertn_packing_qty;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double salertn_item_qty;
    
    @Column(columnDefinition="Double(20,2) default 0.00")
	private double purrtn_packing_qty;
    
    @Column(columnDefinition="Double(20,3) default 0.000")
	private double purrtn_item_qty;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String item_uom;
    
    @Column(columnDefinition="Double(20,2) default 0.00")
	private double tolerance;
    
    @Column(columnDefinition="Double(20,2) default 0.00")
	private double capacity;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String fin_year;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ptrans_id")
    private Item_opening_stk_dtls opening_stk_dtls;
	
	
}

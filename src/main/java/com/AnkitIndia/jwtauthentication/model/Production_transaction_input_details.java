package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="Production_transaction_input_details")
public class Production_transaction_input_details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_popupid;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String packing_name;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double packing_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double item_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double production_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double con_factor;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String uom_basedon;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double ratio;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double deviation;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String scrap_packing;
    
    @Lob
	private String input_qty;
    
    private int bags;
	
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pTransaction_input_details",cascade = CascadeType.ALL)
	private Set<Production_transaction_input_popup_details> production_transaction_input_popup_details;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ptid")
    private Production_transaction production_transaction;

}

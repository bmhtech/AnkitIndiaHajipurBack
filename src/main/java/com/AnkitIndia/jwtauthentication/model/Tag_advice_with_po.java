package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="Tag_advice_with_po")
public class Tag_advice_with_po extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String adv_po_tag_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String adv_po_tag_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String po_number;
	
	@Column(columnDefinition="varchar(50) default 0")
    private String pur_orderno;
	
	@Column(columnDefinition="varchar(50) default 0")
    private String purorderno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String advice_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String adviceno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String veh_no; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_subtype;
	
	@Column(columnDefinition="varchar(50) default 0")
    private String item_subtype_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String busi_partner;
	
	@Column(columnDefinition="varchar(100) default 0")
    private String busi_partnername;
	
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean we_req;
    
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean we_chg_app;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String supp_ref_doc;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String supp_ref_docno;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
    private String ula_date;  
    
    @Column(columnDefinition="varchar(50) default 'NA'")
    private String uladate;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean grn_req;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
    private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;  
	
	private int total_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean return_type;  
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String return_remarks;  
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String remarks; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transporter_code; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String weighment_status;
	
	
	
	//Dynamic
		@OneToMany(fetch = FetchType.EAGER,mappedBy="tag_advice_with_po",cascade =CascadeType.ALL)
		private Set<Tag_advice_withpo_broker_dtls> tag_advice_withpo_broker_dtls;
	
}


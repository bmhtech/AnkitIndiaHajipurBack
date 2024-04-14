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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
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
@Table(name="Delivery_challan")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Delivery_challan.callSoldItemStockDelivery", 
	  procedureName = "callSoldItemStockDelivery", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advitemqty", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advpackqty", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advitemcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advpackcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "bunit", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "finyear", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Delivery_challan.callSoldItemStockDeliveryReverse", 
	  procedureName = "callSoldItemStockDeliveryReverse", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "radvitemqty", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "radvpackqty", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "radvitemcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "radvpackcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "bunit", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "finyear", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
})
public class Delivery_challan extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_cid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String challan_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String challanno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String challan_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String challandate;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bro_info_req;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String price_term;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String cust_ref_doc_no;
	
    private Date date2;
    
    @Column(columnDefinition="varchar(250) default 'NA'")
	private String remark;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String approval;
    
    @Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String ref_type;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String party;
    
    @Column(columnDefinition="varchar(100) default 'NA'")
	private String partyname;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String grand_total;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String inv_type;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String inv_type_name;
	
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_id;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String adviceno;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String invoicestatus;
	
	 @Column(columnDefinition="varchar(50) default 'NA'")
	 private String salereturn_id;
	 
	 @Column(columnDefinition="varchar(10) default 'NO'")
	 private String sales_returnstatus;
	 
	 @Column(columnDefinition="varchar(10) default 'NA'")
	 private String challan_status;
	 
	 @Column(columnDefinition="varchar(10) default 'NA'")
	 private String sales_invoice_id;
	 
	 
	 @Column(columnDefinition="tinyint(1) default 0")
	 private boolean jobwork;

	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade = CascadeType.ALL)
	private Set<Delivery_challan_Item_Dtls> delivery_challan_Item_Dtls;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade = CascadeType.ALL)
	private Set<Delivery_challan_Item_Dtls_for_jobwork> delivery_challan_Item_Dtls_for_jobwork;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade = CascadeType.ALL)
	private Set<Delivery_challan_Broker_Dtls> delivery_challan_Broker_Dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade = CascadeType.ALL)
	private Set<Delivery_challan_Party_Dtls> delivery_challan_Party_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade =CascadeType.ALL)
	private Delivery_challan_Shipment_Dtls delivery_challan_Shipment_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade =CascadeType.ALL)
	private Delivery_challan_weight_dtl delivery_challan_weight_dtl;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade =CascadeType.ALL)
	private Delivery_challan_Trans_Info delivery_challan_Trans_Info;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade = CascadeType.ALL)
	private Set<Delivery_challan_Docs> delivery_challan_Docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="delivery_challan",cascade = CascadeType.ALL)
	private Set<Delivery_challan_Chgs_dyn> delivery_challan_Chgs_dyn;
	//delivery_challan_Item_Dtls_for_jobwork
}

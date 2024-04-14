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
import javax.persistence.OneToMany;
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
@Table(name="Sales_return_note")
public class Sales_return_note extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnnoteno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inv_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String inv_typename;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String party; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String partyname; 
	
	@Column(columnDefinition="varchar(30) default 0")
	private String salesreturnnotedate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String businessunit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String businessunitname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnnoterefno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String price_term;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String cust_ref_doc_no;
	
    private Date date2;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remark; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmedby;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approval;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String reason; 
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double  grandtotal;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String referance_id;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String credit_note_id;
	
	@Column(columnDefinition="varchar(10) default 'NO'")
	private String credit_note_status;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Set<Sales_return_note_item_dtls> sales_return_note_item_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Sales_return_note_trans_info sales_return_note_trans_info;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Set<Sales_return_note_broker_dtls> sales_return_note_broker_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Set<Sales_return_note_party_dtls> sales_return_note_party_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Sales_return_note_shipment_dtls sales_return_note_shipment_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Sales_return_note_weight_dtl sales_return_note_weight_dtl;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Set<Sales_return_note_docs> sales_return_note_docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_return_note",cascade = CascadeType.ALL)
	private Set<Sales_return_note_item_dtls_for_jobwork> sales_return_note_item_dtls_for_jobwork;

}

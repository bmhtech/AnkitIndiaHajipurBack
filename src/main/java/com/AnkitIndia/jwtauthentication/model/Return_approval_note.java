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
@Table(name="Return_approval_note")
public class Return_approval_note extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturntype;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String inv_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String partyname; 
	
	@Column(columnDefinition="varchar(30) default 0")
	private String salesreturndate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String businessunit;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String businessunitname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String returncriteria;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String returnbasedon;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnrefno;
	
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
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String weighment_id;
	
	private int reapp_note_status;
	
	private int weighment_status;
	
	@Column(columnDefinition = "varchar(50) default 'Not Done'")
	private String srn_status;
	
	@Column(columnDefinition = "varchar(50) default 'Not Done'")
	private String unload_status;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String credit_note_id;
	
	@Column(columnDefinition="varchar(10) default 'NO'")
	private String credit_note_status;
	
	@Column(columnDefinition = "varchar(200) default 'Not Done'")
	private String diliverylist;
	
	
	@Column(columnDefinition="varchar(30) default 0")
	private String order_id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean jobwork;
	
	
	 
	 	@OneToMany(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Set<Return_approval_item_dtls> return_approval_item_dtls;
		
		@OneToOne(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Return_approval_trans_info return_approval_trans_info;
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Set<Return_approval_broker_dtls> return_approval_broker_dtls;
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Set<Return_approval_party_dtls> return_approval_party_dtls;
		
		@OneToOne(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Return_approval_shipment_dtls return_approval_shipment_dtls;
		
		@OneToOne(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Return_approval_weight_dtl return_approval_weight_dtl;
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Set<Return_approval_docs> return_approval_docs;
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Set<Return_approval_item_dtls_for_jobwork> return_approval_item_dtls_for_jobwork;
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="return_approval_note",cascade = CascadeType.ALL)
		private Set<Return_approval_item_dtls_for_jobwork_price> return_approval_item_dtls_for_jobwork_price;

}

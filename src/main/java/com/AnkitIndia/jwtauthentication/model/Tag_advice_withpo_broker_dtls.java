package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="tag_advice_withpo_broker_dtls")
public class Tag_advice_withpo_broker_dtls extends CommonProperties {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String adv_po_tag_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String adv_po_tag_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String ven_code_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String ven_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String basis;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double rate;
	

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "unload_id")
    private Tag_advice_with_po tag_advice_with_po;

	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="tag_advice_withpo_broker_dtls",cascade = CascadeType.ALL)
	private Wm_unload_advice_broker_dtls wm_unload_advice_broker_dtls;
	

	
	
}

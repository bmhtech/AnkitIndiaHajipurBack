package com.AnkitIndia.jwtauthentication.model;

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
@Table(name="Sales_return_note_shipment_dtls")
public class Sales_return_note_shipment_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnnoteno;

	@Column(columnDefinition="varchar(200) default 'NA'")
	private String shipaddr;
	
	@Column(columnDefinition="varchar(500) default 'NA'")
	private String shipdetails;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String payaddr;
	
	@Column(columnDefinition="varchar(500) default 'NA'")
	private String paydetails;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "sr_id")
	private Sales_return_note sales_return_note;

}

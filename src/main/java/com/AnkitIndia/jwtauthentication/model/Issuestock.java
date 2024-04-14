package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
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
@Table(name="Issuestock")
public class Issuestock extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String issueno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String issuedatefrom;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String issueto;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String issuetoname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String issuetype;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requesteddate;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unitname;
	

	
	@Column(columnDefinition="varchar(50) default 0")
	private String requisitionno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requestedby;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requestedbyname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String approvedby;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String approvedbyname;
 
	private LocalDateTime approvedon;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="issuestock",cascade = CascadeType.ALL)
	private Set<Issuestock_Item_Dtls> issuestock_Item_Dtls;
}

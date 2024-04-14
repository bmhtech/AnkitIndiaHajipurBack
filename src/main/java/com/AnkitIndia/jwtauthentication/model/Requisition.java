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
@Table(name="Requisition")
public class Requisition extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requisitionno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requestedby;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String requestedbyname;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String shop_floor;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String shop_floorname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requesteddate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String approvedby;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String approvedbyname;
	
	@Column(columnDefinition="varchar(100) default NA")
	private String reject;
	
	private LocalDateTime approvedon;
	
	private LocalDateTime rejecton;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="requisition",cascade = CascadeType.ALL)
	private Set<Requisition_Item_Dtls> requisition_Item_Dtls;
   
}

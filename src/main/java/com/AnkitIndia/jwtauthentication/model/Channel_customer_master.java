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

@Entity
@Table(name="Channel_customer_master")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Channel_customer_master extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String channel_custid;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String channel_desc;
	
	@Column(columnDefinition="varchar(1000) default '0'")
	private String custid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String channeltype;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String group_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String group_type_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean terminate;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="channel_customer_master",cascade =CascadeType.ALL)
	private Set<Sales_Order_Party_Dtls> sales_Order_Party_Dtls;

}

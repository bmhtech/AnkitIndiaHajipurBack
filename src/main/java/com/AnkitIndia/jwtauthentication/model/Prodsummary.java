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
@Table(name="Prodsummary")
public class Prodsummary extends CommonProperties
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String prod_sum_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String date;
	
	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double totalrate;
	
	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double totalamount;

	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double totalbags;
	
	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double totalqty;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	//Dynamic
    @OneToMany(fetch = FetchType.EAGER,mappedBy="prodsummary",cascade =CascadeType.ALL) 
    private Set<Prod_summary_dtls> prod_summary_dtls;
}

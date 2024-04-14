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
@Table(name="Dailyweigher")
public class Dailyweigher extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String dwg_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String b_unit_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String machine;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String weigherdate;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double oacumwt;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double cacumwt;
	
	private int oacumpcs;
	
	private int cacumpcs;
	
	private int totalbags;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double totalkgs;
	
	private int differencebags;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double differencekgs;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="dailyweigher",cascade = CascadeType.ALL)
	private Set<Dailyweigher_Dtls> dailyweigher_Dtls;
}

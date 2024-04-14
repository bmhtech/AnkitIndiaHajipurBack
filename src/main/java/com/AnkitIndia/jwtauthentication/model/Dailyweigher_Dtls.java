package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Dailyweigher_Dtls")
public class Dailyweigher_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String dwg_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(60) default 'NA'")
	private String packing_item;
	
	@Column(columnDefinition="varchar(60) default 'NA'")
	private String packing_item_name;
	
	private int bags;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double kgs;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "dw_id")
    private Dailyweigher dailyweigher;
}

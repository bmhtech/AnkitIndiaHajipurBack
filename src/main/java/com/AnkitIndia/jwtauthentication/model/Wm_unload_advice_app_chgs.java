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
@Table(name="Wm_unload_advice_app_chgs")
public class Wm_unload_advice_app_chgs extends CommonProperties{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String charges_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate_cal_method;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(10,2)")
	private double amount;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice wm_unload_advice;
}

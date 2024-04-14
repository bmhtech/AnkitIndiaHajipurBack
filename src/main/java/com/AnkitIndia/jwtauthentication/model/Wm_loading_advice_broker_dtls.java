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
@Table(name="Wm_loading_advice_broker_dtls")
public class Wm_loading_advice_broker_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String advice_no;
	
	private int slno;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String broker_code;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String broker_name;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String basis;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String based_on;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double rate;
           
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wl_id")
    private Wm_loading_advice wm_loading_advice;
}

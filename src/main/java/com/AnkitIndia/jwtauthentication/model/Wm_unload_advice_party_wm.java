package com.AnkitIndia.jwtauthentication.model;

import java.util.Date;
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
@Table(name="Wm_unload_advice_party_wm")
public class Wm_unload_advice_party_wm extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gross_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom1;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tare_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom2;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String net_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom3;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String slip_no;
	
	private Date pw_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String wb_name;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice wm_unload_advice;
}

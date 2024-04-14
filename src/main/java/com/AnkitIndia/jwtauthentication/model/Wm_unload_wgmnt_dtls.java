package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
@Table(name="Wm_unload_wgmnt_dtls")
public class Wm_unload_wgmnt_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wgment_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String wgment_no;
	
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String customer;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String supplier;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String advice;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String advice_no;
	
	private Date wgment_date;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmulw_id")
    private Wm_unload_wgmnt wm_unload_wgmnt;
}

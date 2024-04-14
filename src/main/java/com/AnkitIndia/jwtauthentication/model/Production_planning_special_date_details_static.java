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
@Table(name="Production_planning_special_date_details_static")
public class Production_planning_special_date_details_static extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppsplid;
	
	private int sl_no;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fromdate;
	

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ppsid")
    private Production_planning_special_dtls_static pSpecial_dtls;

}

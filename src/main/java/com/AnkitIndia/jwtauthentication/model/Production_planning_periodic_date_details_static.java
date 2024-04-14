package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name="Production_planning_periodic_date_details")
public class Production_planning_periodic_date_details_static extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppd_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppds_id;
	
	private int sl_no;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fromdate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String todate;
	
	/*@Column(name = "shift",columnDefinition="varchar(200) default '0'") 
	private	String shift;*/
	
	@Lob
	private String shift1;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift2;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pDate_details",cascade = CascadeType.ALL)
	private Set<Production_planning_shift_details_static> production_planning_shift_details;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "pppid")
    private Production_planning_shop_floor_dtls_static pShop_floor_dtls;
}

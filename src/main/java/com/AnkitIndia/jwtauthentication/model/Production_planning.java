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
@Table(name="Production_planning")
public class Production_planning extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_code;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String pred_from;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String pred_to;
    
	@Column(columnDefinition="varchar(100) default '0'")
	private String prod_plan_desc;
	

	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="production_planning",cascade = CascadeType.ALL)
	private Set<Production_planning_shop_floor_dtls> production_planning_shop_floor_dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="production_planning",cascade = CascadeType.ALL)
	private Set<Production_planning_special_dtls> production_planning_special_dtls;

}

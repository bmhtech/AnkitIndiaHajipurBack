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
@Table(name="Production_planning_shop_floor_dtls")
public class Production_planning_shop_floor_dtls extends CommonProperties{
	
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
	private String business_unit;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shop_floor;
    
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean active;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String process;
    
    @Column(columnDefinition="varchar(100) default '0'")
	private String process_name;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String production;
    
    @Column(columnDefinition="varchar(100) default '0'")
	private String production_name;
    
    @Lob
	private String process_date;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pShop_floor_dtls",cascade = CascadeType.ALL)
	private Set<Production_planning_periodic_date_details> periodic_date_details;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ppid")
    private Production_planning production_planning;
}

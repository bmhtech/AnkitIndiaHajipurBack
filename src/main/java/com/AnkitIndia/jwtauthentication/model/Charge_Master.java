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

@Entity
@Table(name="Charge_Master")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Charge_Master extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String screen_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String company_id;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String charge_desc;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String screen_name;
	
    //Dynamic
    @OneToMany(fetch = FetchType.EAGER,mappedBy="charge_master",cascade =CascadeType.ALL) 
    private Set<Charge_Masterdtls> charge_masterdtls;

}

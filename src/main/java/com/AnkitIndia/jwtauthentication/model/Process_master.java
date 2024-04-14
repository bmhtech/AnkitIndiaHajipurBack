package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="Process_master")

public class Process_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shop_floor;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String shop_floorname;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String process_desc;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_freq;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String perd_day;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_mntnce;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_type1;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift_mntnce;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift_start_time;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift_end_time;
    
	@Column(columnDefinition="tinyint(50) default 0")
	private boolean process_active;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tot_shift_hrs;
	
	@Column(columnDefinition="TEXT")
	private String [] item_group;
	
	@Lob
	private String itemgroup_array;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="process_master",cascade = CascadeType.ALL)
	private Set<Process_master_doc> process_master_doc;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="process_master",cascade = CascadeType.ALL)
	private Set<Process_master_shift_details> process_master_shift_details;

}

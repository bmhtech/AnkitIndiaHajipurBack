package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
@Table(name="Gate_Pass")
public class Gate_Pass extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gatepass_id;
	
	private Date challan_date;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String advive_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String narration;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String driver_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String truck_no;
    
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="gate_pass",cascade = CascadeType.ALL)
	private Set<Gate_Pass_Item_Dtls> gate_Pass_Item_Dtls;
}

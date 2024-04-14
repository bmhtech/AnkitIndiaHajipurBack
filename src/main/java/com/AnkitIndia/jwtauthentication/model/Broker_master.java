package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Broker_master", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Broker_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String broker_type;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String broker_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String alt_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String group_type;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String group_type_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sub_group_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_curr;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String constitution;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_app;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_regno;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean broker_active;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean broker_block;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Broker_master_add broker_master_add;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Set<Broker_master_add_dtls> broker_master_add_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Set<Broker_master_pty> broker_master_pty;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Set<Broker_master_vdr> broker_master_vdr;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Set<Broker_master_transporter> broker_master_transporter;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Broker_master_account broker_master_account;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Set<Broker_master_oth> broker_master_oth;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Broker_master_statutory broker_master_statutory;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="broker_master",cascade = CascadeType.ALL)
	private Set<Broker_master_doc> broker_master_doc;
	
	
	

}

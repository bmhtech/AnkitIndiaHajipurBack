package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import java.time.LocalDateTime;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Company_licence_details")
@EqualsAndHashCode(callSuper=false)
public class Company_licence_details extends CommonProperties{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long sln_no;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String licence_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String licence_name;
	
	private Date expiry_date;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(50) default 'INSERTED'")
	private String modified_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String updated_by;
	
	private LocalDateTime updated_on;

	@Column(columnDefinition="varchar(50) default '0'")
	private String deleted_by;
	
	private LocalDateTime deleted_on;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="C_Id")
	private Company_master company_master;
}

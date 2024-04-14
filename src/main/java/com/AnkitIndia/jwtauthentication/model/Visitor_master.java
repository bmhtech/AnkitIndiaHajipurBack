package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Visitor_master")
public class Visitor_master extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50)
	private String visitor_id;
	
	@Size(max = 100)
	private String visitor_name;
	
	private Long phone_no;
	
	@Size(max = 250)
	private String address;
	
	@Size(max = 50)
	private String identity;
	
	@Size(max = 50)
	private String doc_type;
	
	@Column(columnDefinition="TEXT")
	private String doc_img;
	
	@Size(max = 50)
	private String doc_no;
	
	@Size(max = 50)
	private String veh_no;
	
	@Size(max = 20)
	private String catagory;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String in_time;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String out_time;
	
}

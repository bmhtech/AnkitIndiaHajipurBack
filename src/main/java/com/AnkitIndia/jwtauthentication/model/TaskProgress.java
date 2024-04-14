package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="TaskProgress")
public class TaskProgress extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String task_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String task_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String task;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String date;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String valid_upto;
	
	@Column(columnDefinition="varchar(400) default 'NA'")
	private String description;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String priority;
	
	@Column(columnDefinition="varchar(400) default 'NA'")
	private String user_remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String progress;

}

package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="TaskAllocation")
public class TaskAllocation extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String allocation_id;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String task_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String task_date;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String priority;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String valid_upto;
	
	@Column(columnDefinition="varchar(400) default 'NA'")
	private String description;
	
	@Column(columnDefinition="TEXT")
	private String [] alloted_to;
	
	@Lob
	private String alloted_to_array;
	
}

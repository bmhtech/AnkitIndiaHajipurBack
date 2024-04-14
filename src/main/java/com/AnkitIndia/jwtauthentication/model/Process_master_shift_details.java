package com.AnkitIndia.jwtauthentication.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Process_master_shift_details")
public class Process_master_shift_details{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_no;
	
	private int shiftno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift_start_time;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift_end_time;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tot_shift_hrs;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String deleted_by;
	
	@JsonIgnore
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "pid")
	private Process_master process_master;
	
	public Process_master_shift_details(String process_id, String process_no, int shiftno, String shift_start_time,
			String shift_end_time, String tot_shift_hrs, String company_id, String fin_year, String modified_type,
			LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on, String updated_by,
			LocalDateTime deleted_on, String deleted_by, Process_master process_master) {
		super();
		this.process_id = process_id;
		this.process_no = process_no;
		this.shiftno = shiftno;
		this.shift_start_time = shift_start_time;
		this.shift_end_time = shift_end_time;
		this.tot_shift_hrs = tot_shift_hrs;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.process_master = process_master;
	}

}

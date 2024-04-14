package com.AnkitIndia.jwtauthentication.model;

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

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Process_master_doc")
public class Process_master_doc extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String process_no;
	
	@Column(columnDefinition = "varchar(100) default '0'")
	private String  doc_name;
	
	@Column(columnDefinition = "varchar(200) default '0'")
	private String  doc_pdf;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "pid")
	private Process_master process_master;
}

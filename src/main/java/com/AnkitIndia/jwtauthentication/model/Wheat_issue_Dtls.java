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
@Table(name="Wheat_issue_Dtls")
public class Wheat_issue_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheatreceiveid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	 
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String issue_grade;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String issue_grade_name;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_no;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String issue_bags;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String issue_qty;
    
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "w_id")
    private Wheatreceiving wheatreceiving;
    
}

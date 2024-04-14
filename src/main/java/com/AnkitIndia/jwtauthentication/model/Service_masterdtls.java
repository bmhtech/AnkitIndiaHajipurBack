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
@Table(name="Service_masterdtls")

public class Service_masterdtls extends CommonProperties {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String service_name; 
	
	@Column(columnDefinition="TEXT")
	private String remarks_a; 
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "sservice_no")
    private Servicemaster servicemaster;
}

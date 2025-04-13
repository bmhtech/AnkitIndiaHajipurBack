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
@Table(name="Delivery_challan_Docs")
public class Delivery_challan_Docs extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_cid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String doc_name;
	
	@Column(columnDefinition = "varchar(200) default '0'")
	private String doc_pdf;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String doc_file_name;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "dc_id")
    private Delivery_challan delivery_challan;
}

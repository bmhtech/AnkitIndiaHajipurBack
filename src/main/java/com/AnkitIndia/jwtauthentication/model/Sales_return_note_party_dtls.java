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
@Table(name="Sales_return_note_party_dtls")
public class Sales_return_note_party_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnnoteno;
	
	 private Long slno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String pcode;
	 
	 @Column(columnDefinition="varchar(100) default 'NA'")
	 private String cpname;
	 
	 private Long cpcontact;
	
	 @ManyToOne(cascade= CascadeType.ALL)
	 @JoinColumn(name = "sr_id")
	 private Sales_return_note sales_return_note;
}

package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*@Entity
@Table(name="Tax_type_master")
@EntityListeners(AuditingEntityListener.class)

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Tax_type_master")

public class Tax_type_master extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String taxtype_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String taxtype_name;
	
/*	@Column(columnDefinition="tinyint(1) default 0")
	private boolean taxtype_active;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String taxtype_ledger_acc;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String taxtype_remarks;*/
	
	//@Column(columnDefinition="varchar(50) default '0'")
	//private String businessunit_code;
	
	//@Column(columnDefinition="varchar(100) default '0'")
	//private String businessunit_name;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="tax_type_master",cascade = CascadeType.ALL)
	private Set<Gst_input_output_ledger_dtls> gst_input_output_ledger_dtls;
	
}

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

@Entity
@Table(name="Invoice_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Invoice_type extends CommonProperties{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(columnDefinition="varchar(50) default '0'")
		private String invtype_id;
		
		@Column(columnDefinition="varchar(50) default '0'")
		private String invtype_code;
		
		@Column(columnDefinition="varchar(100) default '0'")
		private String invtype_name;
		
		@Column(columnDefinition="varchar(20) default '0'")
		private String invtype_prefix;
		
}

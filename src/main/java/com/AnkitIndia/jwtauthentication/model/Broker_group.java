package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Broker_group", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"group_name"})
})
public class Broker_group extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String group_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String group_code;
	
	@Column(columnDefinition = "varchar(100) default '0'")
	private String group_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent_group;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bg_active;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String control_acc;
	
}

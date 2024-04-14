package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="Jw_grn_partytag_details")
public class Jw_grn_partytag_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String jw_grn_tag;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String party;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String party_name;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double allocated_qty;
	
	@Column(columnDefinition="Double(10,3) default 0.000")
	private double partyitem_qty;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "jid")
	private Jw_grn_itemtag jw_grn_itemtag;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="jw_grn_partytag_details",cascade = CascadeType.ALL)
	private Set<Jw_grn_partywitem_details> jw_grn_partywitem_details;
}

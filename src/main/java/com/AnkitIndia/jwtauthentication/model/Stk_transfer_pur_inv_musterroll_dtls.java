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
@Table(name="Stk_transfer_pur_inv_musterroll_dtls")
public class Stk_transfer_pur_inv_musterroll_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String muster_roll_name;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stpi_id")
	private Stk_transfer_pur_inv sTransfer_pur_inv;
}

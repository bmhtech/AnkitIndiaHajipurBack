package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
@Table(name = "Item_group_jobwork_sales_return_acc")
public class Item_group_jobwork_sales_return_acc extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String item_group_code;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String item_group_id;

	@Column(columnDefinition="TEXT")
	private String [] jw_sr_item_total;
	
	@Lob
	private String jw_sr_item_array;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String jw_sr_discount;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String jw_sr_adjplus;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String jw_sr_adjminus;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ig_id")
    private Item_group_master item_group_master;

	
}

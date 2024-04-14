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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="Item_opening_stk_dtls", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            	"business_unit","item_id"
            })
        })
public class Item_opening_stk_dtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String transe_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tdate;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_id;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double open_item_gr_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double curr_item_gr_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double sold_gr_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double pur_gr_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double prod_gr_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double sale_rtn_gr_qty;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double pur_rtn_gr_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_uom;
	
	@Lob
	private String pack_dtls;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fin_year;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "trans_id")
    private Item_opening_stk opening_stk;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="opening_stk_dtls",cascade = CascadeType.ALL)
	private Set<Item_opening_stk_pack_dtls> item_opening_stk_pack_dtls;
	
	
}

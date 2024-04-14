package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="Stock_Indent_Order")
public class Stock_Indent_Order extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referance_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;

	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String refer_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_item;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String department;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String valid_until;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String approved;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reason;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String approved_remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Indent_Order",cascade = CascadeType.ALL)
	private Set<Stock_Indent_Item_Details> stock_Indent_Item_Details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Indent_Order",cascade = CascadeType.ALL)
	private Set<Stock_Indent_docs> stock_Indent_docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Indent_Order",cascade = CascadeType.ALL)
	private Set<Stock_Indent_Terminations_dyn> stock_Indent_Terminations_dyn;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stock_Indent_Order",cascade = CascadeType.ALL)
	private Stock_Indent_Terminations stock_Indent_Terminations;
}

package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "Item_group_master", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "group_name","group_active"
        })
})
public class Item_group_master extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_group_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_group_id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean group_active;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String group_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String parent_group;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String main_group;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String group_type;
	
//	@Column(columnDefinition="tinyint(1) default 0")
//	private boolean select_acc;
//	
//	@Size(max = 100)
//	private String sales_acc;
//	
//	@Size(max = 100)
//	private String sales_ret_ass;
//	
//	@Size(max = 100)
//	private String pur_acc;
//	
//	@Size(max = 100)
//	private String pur_ret_acc;
//	
//	@Size(max = 100)
//	private String stk_trans_sale;
//	
//	@Size(max = 100)
//	private String stk_trans_purchase;
	
//	@Column(columnDefinition="varchar(100) default 0")
//	private String item_uom;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_master_sales_acc item_group_master_sales_acc;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_master_sales_retacc item_group_master_sales_retacc;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_master_stk_trans_sale item_group_master_stk_trans_sale;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_master_pur_acc item_group_master_pur_acc;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_master_pur_retacc item_group_master_pur_retacc;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_master_stk_trans_pur item_group_master_stk_trans_pur;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_jobwork_sales_acc item_group_jobwork_sales_acc;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_group_master",cascade =CascadeType.ALL)
	private Item_group_jobwork_sales_return_acc item_group_jobwork_sales_return_acc;

	
}

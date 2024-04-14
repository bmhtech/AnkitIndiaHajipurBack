package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
@Table(name="Item_master", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "item_name"
            })
    })
public class Item_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(15) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(10) default 0")
	private String itemname;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean item_active;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String item_group;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String item_group_name;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String sub_group;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String sub_group_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_type;
	
	@Column(columnDefinition="varchar(15) default 'NA'")
	private String item_category;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String item_category_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String alt_name;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String mstock_unit;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String unit_type;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String mstock_unit_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String standard_rate;
	
	@Column(columnDefinition="Double(10,2) default 0.00")
	private double mrp;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String hsn_code;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean inventory_item;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean sales_item;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean purchase_item;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean processed_item;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean impurities_item;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean qc_require;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean all_unit;
	
	@Lob
	private String item_unit;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String upload_picture;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String group_main_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String approval;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean insert_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean stk_status;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean chakki_prod;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean roller_prod;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean input_prod;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_stat_info> item_master_stat_infos;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_master",cascade =CascadeType.ALL)
	private Item_master_inv_data1 item_master_inv_data1;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_master",cascade =CascadeType.ALL)
	private Item_master_inv_data2 item_master_inv_data2;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="item_master",cascade =CascadeType.ALL)
	private Item_master_other_info item_master_other_info;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_pack_mat_tag> item_master_pack_mat_tags;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_qc_details> itmItem_master_qc_details;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_alternative_dtls> item_master_alternative_dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_stock_details> item_master_stock_details;

	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_classification> item_master_classification;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item_master",cascade = CascadeType.ALL)
	private Set<Item_master_size_weight> item_master_size_weight;
	
}

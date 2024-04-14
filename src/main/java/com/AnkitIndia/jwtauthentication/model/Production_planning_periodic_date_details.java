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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



/**
 * @author RamKrishna
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Production_planning_periodic_date_details")
public class Production_planning_periodic_date_details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_plan_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppd_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ppds_id;
	
	private int sl_no;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fromdate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String todate;
	
	/*@Column(name = "shift",columnDefinition="varchar(200) default '0'") 
	private	String shift;*/
	
	@Lob
	private String shift1;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shift2;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProd_plan_id() {
		return prod_plan_id;
	}

	public void setProd_plan_id(String prod_plan_id) {
		this.prod_plan_id = prod_plan_id;
	}

	public String getProd_plan_code() {
		return prod_plan_code;
	}

	public void setProd_plan_code(String prod_plan_code) {
		this.prod_plan_code = prod_plan_code;
	}

	public String getPpd_id() {
		return ppd_id;
	}

	public void setPpd_id(String ppd_id) {
		this.ppd_id = ppd_id;
	}

	public String getPpds_id() {
		return ppds_id;
	}

	public void setPpds_id(String ppds_id) {
		this.ppds_id = ppds_id;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getShift1() {
		return shift1;
	}

	public void setShift1(String shift1) {
		this.shift1 = shift1;
	}

	public String getShift2() {
		return shift2;
	}

	public void setShift2(String shift2) {
		this.shift2 = shift2;
	}

	public Set<Production_planning_shift_details> getProduction_planning_shift_details() {
		return production_planning_shift_details;
	}

	public void setProduction_planning_shift_details(
			Set<Production_planning_shift_details> production_planning_shift_details) {
		this.production_planning_shift_details = production_planning_shift_details;
	}

	public Production_planning_shop_floor_dtls getpShop_floor_dtls() {
		return pShop_floor_dtls;
	}

	public void setpShop_floor_dtls(Production_planning_shop_floor_dtls pShop_floor_dtls) {
		this.pShop_floor_dtls = pShop_floor_dtls;
	}

	@OneToMany(fetch = FetchType.EAGER,mappedBy="pDate_details",cascade = CascadeType.ALL)
	private Set<Production_planning_shift_details> production_planning_shift_details;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "pppid")
    private Production_planning_shop_floor_dtls pShop_floor_dtls;

}

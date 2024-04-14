package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Supp_bussiness_partnerDTO {
	
	private Long id;
	
	private String bp_Id;
	
	private String bp_code;
	
	private String company_id;
	
	private String bp_type;
	
	private String bp_name;
	
	private String alt_name;
	
	private boolean bp_active;
	
	private String group_type;
	
	private String sub_group_type;
	
	private String trans_currency;
	
	private boolean block_active;
	
	private String reason;
	
	private boolean copy_bp_addr;
	
	private String party_nature;
	
	private String def_tds_nature;
	
	private String business_unit;
	
	private boolean all_unit;
	
	private String constitution;
	
	private String ssi_app;
	
	private String ssi_regno;
	
	private boolean broker_status;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String add1;
	
	private int export;	
	
	private String response;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getExport() {
		return export;
	}

	public void setExport(int export) {
		this.export = export;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getBp_Id() {
		return bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}

	public String getBp_code() {
		return bp_code;
	}

	public void setBp_code(String bp_code) {
		this.bp_code = bp_code;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getBp_type() {
		return bp_type;
	}

	public void setBp_type(String bp_type) {
		this.bp_type = bp_type;
	}

	public String getBp_name() {
		return bp_name;
	}

	public void setBp_name(String bp_name) {
		this.bp_name = bp_name;
	}

	public String getAlt_name() {
		return alt_name;
	}

	public void setAlt_name(String alt_name) {
		this.alt_name = alt_name;
	}

	public boolean isBp_active() {
		return bp_active;
	}

	public void setBp_active(boolean bp_active) {
		this.bp_active = bp_active;
	}

	public String getGroup_type() {
		return group_type;
	}

	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}

	public String getSub_group_type() {
		return sub_group_type;
	}

	public void setSub_group_type(String sub_group_type) {
		this.sub_group_type = sub_group_type;
	}

	public String getTrans_currency() {
		return trans_currency;
	}

	public void setTrans_currency(String trans_currency) {
		this.trans_currency = trans_currency;
	}

	public boolean isBlock_active() {
		return block_active;
	}

	public void setBlock_active(boolean block_active) {
		this.block_active = block_active;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isCopy_bp_addr() {
		return copy_bp_addr;
	}

	public void setCopy_bp_addr(boolean copy_bp_addr) {
		this.copy_bp_addr = copy_bp_addr;
	}

	public String getParty_nature() {
		return party_nature;
	}

	public void setParty_nature(String party_nature) {
		this.party_nature = party_nature;
	}

	public String getDef_tds_nature() {
		return def_tds_nature;
	}

	public void setDef_tds_nature(String def_tds_nature) {
		this.def_tds_nature = def_tds_nature;
	}
	
	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}
	
	public boolean isAll_unit() {
		return all_unit;
	}

	public void setAll_unit(boolean all_unit) {
		this.all_unit = all_unit;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getSsi_app() {
		return ssi_app;
	}

	public void setSsi_app(String ssi_app) {
		this.ssi_app = ssi_app;
	}

	public String getSsi_regno() {
		return ssi_regno;
	}

	public void setSsi_regno(String ssi_regno) {
		this.ssi_regno = ssi_regno;
	}

	public boolean isBroker_status() {
		return broker_status;
	}

	public void setBroker_status(boolean broker_status) {
		this.broker_status = broker_status;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	@Override
	public String toString() {
		return "Supp_bussiness_partnerDTO [id=" + id + ", bp_Id=" + bp_Id + ", bp_code=" + bp_code + ", company_id="
				+ company_id + ", bp_type=" + bp_type + ", bp_name=" + bp_name + ", alt_name=" + alt_name
				+ ", bp_active=" + bp_active + ", group_type=" + group_type + ", sub_group_type=" + sub_group_type
				+ ", trans_currency=" + trans_currency + ", block_active=" + block_active + ", reason=" + reason
				+ ", copy_bp_addr=" + copy_bp_addr + ", party_nature=" + party_nature + ", def_tds_nature="
				+ def_tds_nature + ", business_unit=" + business_unit + ", all_unit=" + all_unit + ", constitution="
				+ constitution + ", ssi_app=" + ssi_app + ", ssi_regno=" + ssi_regno + ", broker_status="
				+ broker_status + ", fin_year=" + fin_year + ", modified_type=" + modified_type + ", inserted_on="
				+ inserted_on + ", inserted_by=" + inserted_by + ", updated_on=" + updated_on + ", updated_by="
				+ updated_by + ", add1=" + add1 + "]";
	}
	
	
	
	
}

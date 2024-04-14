package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Broker_masterDTO {
	
	private Long id;
	
	private String broker_Id;
	
	private String broker_code;
	
	private String name;
	
	private String alt_name;
	
	private String group_type;
	
	private String sub_group_type;
	
	private String trans_curr;
	
	private String broker_type;
	
	private String constitution;
	
	private String ssi_app;
	
	private String ssi_regno;

	private boolean broker_active;
	
	private boolean broker_block;
	
	private String fin_year;
	
	private String company_id;

	private int export;	
	
	private String response;

	private String undo_by;
	
	private LocalDateTime undo_on;
	
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

	public String getBroker_Id() {
		return broker_Id;
	}

	public void setBroker_Id(String broker_Id) {
		this.broker_Id = broker_Id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getBroker_type() {
		return broker_type;
	}

	public void setBroker_type(String broker_type) {
		this.broker_type = broker_type;
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

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlt_name() {
		return alt_name;
	}

	public void setAlt_name(String alt_name) {
		this.alt_name = alt_name;
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

	public String getTrans_curr() {
		return trans_curr;
	}

	public void setTrans_curr(String trans_curr) {
		this.trans_curr = trans_curr;
	}

	public boolean isBroker_active() {
		return broker_active;
	}

	public void setBroker_active(boolean broker_active) {
		this.broker_active = broker_active;
	}

	public boolean isBroker_block() {
		return broker_block;
	}

	public void setBroker_block(boolean broker_block) {
		this.broker_block = broker_block;
	}

	public Broker_masterDTO() {
		super();
	}

	public String getUndo_by() {
		return undo_by;
	}

	public void setUndo_by(String undo_by) {
		this.undo_by = undo_by;
	}

	public LocalDateTime getUndo_on() {
		return undo_on;
	}

	public void setUndo_on(LocalDateTime undo_on) {
		this.undo_on = undo_on;
	}

	public Broker_masterDTO(Long id, String broker_Id, String broker_code, String name, String alt_name,
			String group_type, String sub_group_type, String trans_curr, String broker_type, String constitution,
			String ssi_app, String ssi_regno, boolean broker_active, boolean broker_block, String fin_year,
			String company_id, int export, String response, String undo_by, LocalDateTime undo_on) {
		super();
		this.id = id;
		this.broker_Id = broker_Id;
		this.broker_code = broker_code;
		this.name = name;
		this.alt_name = alt_name;
		this.group_type = group_type;
		this.sub_group_type = sub_group_type;
		this.trans_curr = trans_curr;
		this.broker_type = broker_type;
		this.constitution = constitution;
		this.ssi_app = ssi_app;
		this.ssi_regno = ssi_regno;
		this.broker_active = broker_active;
		this.broker_block = broker_block;
		this.fin_year = fin_year;
		this.company_id = company_id;
		this.export = export;
		this.response = response;
		this.undo_by = undo_by;
		this.undo_on = undo_on;
	}

	
	

}

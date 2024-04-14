package com.AnkitIndia.jwtauthentication.responseDTO;

public class Transporter_groupDTO {
	
	private String bp_trans_id;

	private String bp_grp_name;
	
	private String parent_group;
	
	private String bp_trans_code;
	
	private String bp_type;
	
	private boolean bp_trans_active;
	
	private String control_acc;

	public String getBp_trans_id() {
		return bp_trans_id;
	}

	public String getParent_group() {
		return parent_group;
	}

	public void setParent_group(String parent_group) {
		this.parent_group = parent_group;
	}

	public void setBp_trans_id(String bp_trans_id) {
		this.bp_trans_id = bp_trans_id;
	}

	public String getBp_grp_name() {
		return bp_grp_name;
	}

	public void setBp_grp_name(String bp_grp_name) {
		this.bp_grp_name = bp_grp_name;
	}

	public String getBp_trans_code() {
		return bp_trans_code;
	}

	public void setBp_trans_code(String bp_trans_code) {
		this.bp_trans_code = bp_trans_code;
	}

	public String getBp_type() {
		return bp_type;
	}

	public void setBp_type(String bp_type) {
		this.bp_type = bp_type;
	}

	public boolean isBp_trans_active() {
		return bp_trans_active;
	}

	public void setBp_trans_active(boolean bp_trans_active) {
		this.bp_trans_active = bp_trans_active;
	}

	public String getControl_acc() {
		return control_acc;
	}

	public void setControl_acc(String control_acc) {
		this.control_acc = control_acc;
	}

	public Transporter_groupDTO() {
		super();
	}

	public Transporter_groupDTO(String bp_trans_id, String bp_grp_name, String parent_group, String bp_trans_code,
			String bp_type, boolean bp_trans_active, String control_acc) {
		super();
		this.bp_trans_id = bp_trans_id;
		this.bp_grp_name = bp_grp_name;
		this.parent_group = parent_group;
		this.bp_trans_code = bp_trans_code;
		this.bp_type = bp_type;
		this.bp_trans_active = bp_trans_active;
		this.control_acc = control_acc;
	}
	
	
	
	
}

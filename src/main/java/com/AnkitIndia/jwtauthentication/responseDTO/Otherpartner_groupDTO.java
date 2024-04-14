package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
//@Table(name="Otherpartner_group")
public class Otherpartner_groupDTO {
	
	
	
	@Size(max = 50)
	private String op_id;
	
	@Size(max = 100)
	private String op_grp_name;

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public String getOp_grp_name() {
		return op_grp_name;
	}

	public void setOp_grp_name(String op_grp_name) {
		this.op_grp_name = op_grp_name;
	}
}

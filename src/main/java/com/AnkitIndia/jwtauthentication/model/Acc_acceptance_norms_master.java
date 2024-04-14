package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Acc_acceptance_norms_master")
public class Acc_acceptance_norms_master {

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(max = 50)
	private String anm_id;
	
	@Size(max = 50)
	private String anm_code; 
	
	//@NotNull
	@Size(max = 200)
	private String anm_description;
	
	//@NotNull
	@Size(max = 200)
	private String  anm_remarks;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean anm_active;
	
	//@NotNull
	@Size(max = 40)
	private String businessunit_code;
	
	//@NotNull
	@Size(max = 100)
	private String businessunit_name;
	
	@Size(max = 50)
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Size(max = 50)
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Size(max = 50)
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Size(max = 50)
	private String deleted_by;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="acc_acceptance_norms_master",cascade = CascadeType.ALL)
	private Set<Acc_acceptance_norms_master_dts> acc_acceptance_norms_master_dts;
	
	
	public Acc_acceptance_norms_master()
    {
        super();
    }
	
	public Acc_acceptance_norms_master(Long id,String anm_id,String anm_code,String anm_description,String  anm_remarks,boolean anm_active,String businessunit_code,
			String businessunit_name,String modified_type,LocalDateTime inserted_on,String inserted_by,LocalDateTime updated_on,String updated_by,LocalDateTime deleted_on,
			String deleted_by,Set<Acc_acceptance_norms_master_dts> acc_acceptance_norms_master_dts)
    {
        super();
        this.id=id;
        this.anm_id=anm_id;
        this.anm_code=anm_code;
        this.anm_description=anm_description;
        this.anm_remarks=anm_remarks;
        this.anm_active=anm_active;
        this.businessunit_code=businessunit_code;
        this.businessunit_name=businessunit_name;
        this.modified_type=modified_type;
        this.inserted_on=inserted_on;
        this.updated_on=updated_on;
        this.updated_by=updated_by;
        this.deleted_on=deleted_on;
        this.deleted_by=deleted_by;
        this.acc_acceptance_norms_master_dts=acc_acceptance_norms_master_dts;
    }
	
	
	
	
	
	public String getAnm_remarks() {
		return anm_remarks;
	}

	public void setAnm_remarks(String anm_remarks) {
		this.anm_remarks = anm_remarks;
	}

	public boolean isAnm_active() {
		return anm_active;
	}

	public void setAnm_active(boolean anm_active) {
		this.anm_active = anm_active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAnm_id() {
		return anm_id;
	}

	public void setAnm_id(String anm_id) {
		this.anm_id = anm_id;
	}

	public String getAnm_code() {
		return anm_code;
	}

	public void setAnm_code(String anm_code) {
		this.anm_code = anm_code;
	}

	public String getAnm_description() {
		return anm_description;
	}

	public void setAnm_description(String anm_description) {
		this.anm_description = anm_description;
	}

	public String getBusinessunit_code() {
		return businessunit_code;
	}

	public void setBusinessunit_code(String businessunit_code) {
		this.businessunit_code = businessunit_code;
	}

	public String getBusinessunit_name() {
		return businessunit_name;
	}

	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
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

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Set<Acc_acceptance_norms_master_dts> getAcc_acceptance_norms_master_dts() {
		return acc_acceptance_norms_master_dts;
	}

	public void setAcc_acceptance_norms_master_dts(Set<Acc_acceptance_norms_master_dts> acc_acceptance_norms_master_dts) {
		this.acc_acceptance_norms_master_dts = acc_acceptance_norms_master_dts;
	}

	@Override
    public String toString()
    {
        return "Acc_acceptance_norms_master [id=" + id + ", anm_id=" + anm_id + ", anm_code=" + anm_code + ",anm_description="+anm_description+",anm_remarks="+anm_remarks+","
        		+ "anm_active="+anm_active+",businessunit_code="+businessunit_code+",businessunit_name="+businessunit_name+",modified_type="+modified_type+","
        		+ "inserted_on="+inserted_on+",inserted_by="+inserted_by+",updated_on="+updated_on+",updated_by="+updated_by+",deleted_on="+deleted_on+",deleted_by="+deleted_by+"]";
    }

}

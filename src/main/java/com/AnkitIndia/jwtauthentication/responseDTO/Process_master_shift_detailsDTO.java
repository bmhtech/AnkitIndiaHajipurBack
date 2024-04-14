package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Process_master_shift_detailsDTO {
	
	private Long id;
	
	private String process_id;
	
	private String process_no;
	
	private int shiftno;
	
	private String shift_start_time;
	
	private String shift_end_time;
	
	private String tot_shift_hrs;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public String getProcess_no() {
		return process_no;
	}

	public void setProcess_no(String process_no) {
		this.process_no = process_no;
	}

	public int getShiftno() {
		return shiftno;
	}

	public void setShiftno(int shiftno) {
		this.shiftno = shiftno;
	}

	public String getShift_start_time() {
		return shift_start_time;
	}

	public void setShift_start_time(String shift_start_time) {
		this.shift_start_time = shift_start_time;
	}

	public String getShift_end_time() {
		return shift_end_time;
	}

	public void setShift_end_time(String shift_end_time) {
		this.shift_end_time = shift_end_time;
	}

	public String getTot_shift_hrs() {
		return tot_shift_hrs;
	}

	public void setTot_shift_hrs(String tot_shift_hrs) {
		this.tot_shift_hrs = tot_shift_hrs;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public Process_master_shift_detailsDTO() {
		super();
	}

	public Process_master_shift_detailsDTO(String process_id, String process_no, int shiftno, String shift_start_time,
			String shift_end_time, String tot_shift_hrs, String company_id, String fin_year, String modified_type,
			LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on, String updated_by,
			LocalDateTime deleted_on, String deleted_by) {
		super();
		this.process_id = process_id;
		this.process_no = process_no;
		this.shiftno = shiftno;
		this.shift_start_time = shift_start_time;
		this.shift_end_time = shift_end_time;
		this.tot_shift_hrs = tot_shift_hrs;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
	}
	
	
}

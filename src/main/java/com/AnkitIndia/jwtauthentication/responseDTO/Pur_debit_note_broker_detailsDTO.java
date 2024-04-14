package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Pur_debit_note_broker_detailsDTO {

	
private String debitnoteid;
	
	private String  debitnoteno;
	
	private Long sl_no;
    
	private String broker_name;
	
	private double brokerage_amt;
    
	private String broker_other_info;
    
	private double up_brokerage_amt;
	
	private double total_brokerage;
	
		private String company_id;
	    
		private String fin_year;
	    
		private String username;
		
		private String modified_type;
		
		private LocalDateTime inserted_on;
		
		private String inserted_by;
		
		private LocalDateTime updated_on;
		
		private String updated_by;
		
		private LocalDateTime deleted_on;
		
		private String deleted_by;

		public String getDebitnoteid() {
			return debitnoteid;
		}

		public void setDebitnoteid(String debitnoteid) {
			this.debitnoteid = debitnoteid;
		}

		public String getDebitnoteno() {
			return debitnoteno;
		}

		public void setDebitnoteno(String debitnoteno) {
			this.debitnoteno = debitnoteno;
		}

		public Long getSl_no() {
			return sl_no;
		}

		public void setSl_no(Long sl_no) {
			this.sl_no = sl_no;
		}

		public String getBroker_name() {
			return broker_name;
		}

		public void setBroker_name(String broker_name) {
			this.broker_name = broker_name;
		}

		public double getBrokerage_amt() {
			return brokerage_amt;
		}

		public void setBrokerage_amt(double brokerage_amt) {
			this.brokerage_amt = brokerage_amt;
		}

		public String getBroker_other_info() {
			return broker_other_info;
		}

		public void setBroker_other_info(String broker_other_info) {
			this.broker_other_info = broker_other_info;
		}

		public double getUp_brokerage_amt() {
			return up_brokerage_amt;
		}

		public void setUp_brokerage_amt(double up_brokerage_amt) {
			this.up_brokerage_amt = up_brokerage_amt;
		}

		public double getTotal_brokerage() {
			return total_brokerage;
		}

		public void setTotal_brokerage(double total_brokerage) {
			this.total_brokerage = total_brokerage;
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

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
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
		
		
}

package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Pur_return_approval_trans_dyn")
public class Pur_return_approval_trans_dyn {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transborneby;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String modeoftrans;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicleid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicleno;
	
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double freightamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double  advpaid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String chargecode;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transcode;
	
	 @Column(columnDefinition="varchar(50) default 0")
	 private String company_id;
	    
	 @Column(columnDefinition="varchar(50) default 0")
	 private String fin_year;
	    
	 @Column(columnDefinition="varchar(50) default 0")
	 private String username;
		
	 @Column(columnDefinition="varchar(50) default 0")
	 private String modified_type;
		
	 private LocalDateTime inserted_on;
		
	 @Column(columnDefinition="varchar(50) default 0")
	 private String inserted_by;
		
		private LocalDateTime updated_on;
		
		@Column(columnDefinition="varchar(50) default 0")
		private String updated_by;
		
		private LocalDateTime deleted_on;
		
		@Column(columnDefinition="varchar(50) default 0")
		private String deleted_by;
		
		
		@ManyToOne(cascade= CascadeType.ALL)
		@JoinColumn(name = "pr_id")
		private Pur_return_approval_note pur_return_approval_note;


		public Pur_return_approval_trans_dyn() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Pur_return_approval_trans_dyn(Long id, String purreturnid, String purreturnno, String transborneby,
				String modeoftrans, String vehicleid, String vehicleno, double freightamt, double advpaid,
				String chargecode, String transcode, String company_id, String fin_year, String username,
				String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on,
				String updated_by, LocalDateTime deleted_on, String deleted_by,
				Pur_return_approval_note pur_return_approval_note) {
			super();
			this.id = id;
			this.purreturnid = purreturnid;
			this.purreturnno = purreturnno;
			this.transborneby = transborneby;
			this.modeoftrans = modeoftrans;
			this.vehicleid = vehicleid;
			this.vehicleno = vehicleno;
			this.freightamt = freightamt;
			this.advpaid = advpaid;
			this.chargecode = chargecode;
			this.transcode = transcode;
			this.company_id = company_id;
			this.fin_year = fin_year;
			this.username = username;
			this.modified_type = modified_type;
			this.inserted_on = inserted_on;
			this.inserted_by = inserted_by;
			this.updated_on = updated_on;
			this.updated_by = updated_by;
			this.deleted_on = deleted_on;
			this.deleted_by = deleted_by;
			this.pur_return_approval_note = pur_return_approval_note;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getPurreturnid() {
			return purreturnid;
		}


		public void setPurreturnid(String purreturnid) {
			this.purreturnid = purreturnid;
		}


		public String getPurreturnno() {
			return purreturnno;
		}


		public void setPurreturnno(String purreturnno) {
			this.purreturnno = purreturnno;
		}


		public String getTransborneby() {
			return transborneby;
		}


		public void setTransborneby(String transborneby) {
			this.transborneby = transborneby;
		}


		public String getModeoftrans() {
			return modeoftrans;
		}


		public void setModeoftrans(String modeoftrans) {
			this.modeoftrans = modeoftrans;
		}


		public String getVehicleid() {
			return vehicleid;
		}


		public void setVehicleid(String vehicleid) {
			this.vehicleid = vehicleid;
		}


		public String getVehicleno() {
			return vehicleno;
		}


		public void setVehicleno(String vehicleno) {
			this.vehicleno = vehicleno;
		}


		public double getFreightamt() {
			return freightamt;
		}


		public void setFreightamt(double freightamt) {
			this.freightamt = freightamt;
		}


		public double getAdvpaid() {
			return advpaid;
		}


		public void setAdvpaid(double advpaid) {
			this.advpaid = advpaid;
		}


		public String getChargecode() {
			return chargecode;
		}


		public void setChargecode(String chargecode) {
			this.chargecode = chargecode;
		}


		public String getTranscode() {
			return transcode;
		}


		public void setTranscode(String transcode) {
			this.transcode = transcode;
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


		public Pur_return_approval_note getPur_return_approval_note() {
			return pur_return_approval_note;
		}


		public void setPur_return_approval_note(Pur_return_approval_note pur_return_approval_note) {
			this.pur_return_approval_note = pur_return_approval_note;
		}


		@Override
		public String toString() {
			return "Pur_return_approval_trans_dyn [id=" + id + ", purreturnid=" + purreturnid + ", purreturnno="
					+ purreturnno + ", transborneby=" + transborneby + ", modeoftrans=" + modeoftrans + ", vehicleid="
					+ vehicleid + ", vehicleno=" + vehicleno + ", freightamt=" + freightamt + ", advpaid=" + advpaid
					+ ", chargecode=" + chargecode + ", transcode=" + transcode + ", company_id=" + company_id
					+ ", fin_year=" + fin_year + ", username=" + username + ", modified_type=" + modified_type
					+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", updated_on=" + updated_on
					+ ", updated_by=" + updated_by + ", deleted_on=" + deleted_on + ", deleted_by=" + deleted_by
					+ ", pur_return_approval_note=" + pur_return_approval_note + ", getId()=" + getId()
					+ ", getPurreturnid()=" + getPurreturnid() + ", getPurreturnno()=" + getPurreturnno()
					+ ", getTransborneby()=" + getTransborneby() + ", getModeoftrans()=" + getModeoftrans()
					+ ", getVehicleid()=" + getVehicleid() + ", getVehicleno()=" + getVehicleno() + ", getFreightamt()="
					+ getFreightamt() + ", getAdvpaid()=" + getAdvpaid() + ", getChargecode()=" + getChargecode()
					+ ", getTranscode()=" + getTranscode() + ", getCompany_id()=" + getCompany_id() + ", getFin_year()="
					+ getFin_year() + ", getUsername()=" + getUsername() + ", getModified_type()=" + getModified_type()
					+ ", getInserted_on()=" + getInserted_on() + ", getInserted_by()=" + getInserted_by()
					+ ", getUpdated_on()=" + getUpdated_on() + ", getUpdated_by()=" + getUpdated_by()
					+ ", getDeleted_on()=" + getDeleted_on() + ", getDeleted_by()=" + getDeleted_by()
					+ ", getPur_return_approval_note()=" + getPur_return_approval_note() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		
		
		

}

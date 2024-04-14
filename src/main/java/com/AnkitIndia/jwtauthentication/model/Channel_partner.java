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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Channel_partner")
public class Channel_partner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50)
	private String channel_id;
	
	@Size(max = 50)
	private String channel_code;
	
	@Size(max = 50)
	private String channel_name;
	
	@Size(max = 100)
	private String channel_partner;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean channel_active;
	
	@Size(max = 20)
	private String fin_year;
	
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
	
	@Size(max = 50)
	private String company_id;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cPartner",cascade = CascadeType.ALL)
	private Set<Channel_partner_details> channel_partner_details;

	public Channel_partner() {
		super();
	}

	
	public Channel_partner(Long id, @Size(max = 50) String channel_id, @Size(max = 50) String channel_code,
			@Size(max = 50) String channel_name, @Size(max = 100) String channel_partner, boolean channel_active,
			@Size(max = 20) String fin_year, @Size(max = 50) String modified_type, LocalDateTime inserted_on,
			@Size(max = 50) String inserted_by, LocalDateTime updated_on, @Size(max = 50) String updated_by,
			LocalDateTime deleted_on, @Size(max = 50) String deleted_by, @Size(max = 50) String company_id,
			Set<Channel_partner_details> channel_partner_details) {
		super();
		this.id = id;
		this.channel_id = channel_id;
		this.channel_code = channel_code;
		this.channel_name = channel_name;
		this.channel_partner = channel_partner;
		this.channel_active = channel_active;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.company_id = company_id;
		this.channel_partner_details = channel_partner_details;
	}


	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_code() {
		return channel_code;
	}

	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_partner() {
		return channel_partner;
	}

	public void setChannel_partner(String channel_partner) {
		this.channel_partner = channel_partner;
	}

	public boolean isChannel_active() {
		return channel_active;
	}

	public void setChannel_active(boolean channel_active) {
		this.channel_active = channel_active;
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

	public Set<Channel_partner_details> getChannel_partner_details() {
		return channel_partner_details;
	}

	public void setChannel_partner_details(Set<Channel_partner_details> channel_partner_details) {
		this.channel_partner_details = channel_partner_details;
	}


	@Override
	public String toString() {
		return "Channel_partner [id=" + id + ", channel_id=" + channel_id + ", channel_code=" + channel_code
				+ ", channel_name=" + channel_name + ", channel_partner=" + channel_partner + ", channel_active="
				+ channel_active + ", fin_year=" + fin_year + ", modified_type=" + modified_type + ", inserted_on="
				+ inserted_on + ", inserted_by=" + inserted_by + ", updated_on=" + updated_on + ", updated_by="
				+ updated_by + ", deleted_on=" + deleted_on + ", deleted_by=" + deleted_by + ", company_id="
				+ company_id + ", channel_partner_details=" + channel_partner_details + ", getCompany_id()="
				+ getCompany_id() + ", getId()=" + getId() + ", getChannel_id()=" + getChannel_id()
				+ ", getChannel_code()=" + getChannel_code() + ", getChannel_name()=" + getChannel_name()
				+ ", getChannel_partner()=" + getChannel_partner() + ", isChannel_active()=" + isChannel_active()
				+ ", getFin_year()=" + getFin_year() + ", getModified_type()=" + getModified_type()
				+ ", getInserted_on()=" + getInserted_on() + ", getInserted_by()=" + getInserted_by()
				+ ", getUpdated_on()=" + getUpdated_on() + ", getUpdated_by()=" + getUpdated_by() + ", getDeleted_on()="
				+ getDeleted_on() + ", getDeleted_by()=" + getDeleted_by() + ", getChannel_partner_details()="
				+ getChannel_partner_details() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


	
	
	

}

package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class DateDTO 
{
	private LocalDateTime cur_date;

	public LocalDateTime getCur_date() {
		return cur_date;
	}

	public void setCur_date(LocalDateTime cur_date) {
		this.cur_date = cur_date;
	}

	public DateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DateDTO(LocalDateTime cur_date) {
		super();
		this.cur_date = cur_date;
	}
	
	
}

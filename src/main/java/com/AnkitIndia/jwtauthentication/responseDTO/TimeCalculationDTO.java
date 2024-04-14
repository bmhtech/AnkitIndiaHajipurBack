package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class TimeCalculationDTO {

	private LocalDateTime shift_start_time;
	
	private LocalDateTime shift_end_time;

	public LocalDateTime getShift_start_time() {
		return shift_start_time;
	}

	public void setShift_start_time(LocalDateTime shift_start_time) {
		this.shift_start_time = shift_start_time;
	}

	public LocalDateTime getShift_end_time() {
		return shift_end_time;
	}

	public void setShift_end_time(LocalDateTime shift_end_time) {
		this.shift_end_time = shift_end_time;
	}
	
	
}

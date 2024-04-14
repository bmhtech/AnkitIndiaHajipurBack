package com.AnkitIndia.jwtauthentication.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class sales_reg_dynamicDTO {
	private Long id;
	private String reportfields;
    private String sales_report;
    private String reportname;
    private String table_name;
    private String static_report;
    private String reporttype;
    private String reportlist;
}

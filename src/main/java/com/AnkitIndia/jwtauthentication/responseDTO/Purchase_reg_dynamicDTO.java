package com.AnkitIndia.jwtauthentication.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase_reg_dynamicDTO {
	private Long id;
	private String reportfields;
    private String purchase_report;
    private String reportname;
    private String table_name;
}

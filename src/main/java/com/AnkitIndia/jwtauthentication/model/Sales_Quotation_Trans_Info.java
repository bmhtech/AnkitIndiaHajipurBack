package com.AnkitIndia.jwtauthentication.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Sales_Quotation_Trans_Info")
public class Sales_Quotation_Trans_Info extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String quotation_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_borne_by;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String trans_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String charge_code;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String transporters;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "sq_id")
    private Sales_Quotation sQuotation;
}

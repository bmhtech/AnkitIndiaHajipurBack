package com.AnkitIndia.jwtauthentication.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Sales_Quotation_Broker_Dtls")
public class Sales_Quotation_Broker_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String quotation_no;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String broker_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_name;
	
	//@Column(columnDefinition = "Double(10,2)")
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String basis;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String based_on;
	
	@Column(columnDefinition = "Double(10,2)")
	private double rate;
	
	/*@Size(max = 50)
	private String brokerage_acc;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tds_rate;
	
	@Size(max = 50)
	private String tds_acc;*/
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "sq_id")
    private Sales_Quotation sQuotation;
}

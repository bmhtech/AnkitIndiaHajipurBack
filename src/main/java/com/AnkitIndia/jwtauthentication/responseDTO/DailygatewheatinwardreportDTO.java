package com.AnkitIndia.jwtauthentication.responseDTO;

public class DailygatewheatinwardreportDTO 
{
	private String advicenumber;
	
	private String partyname;
	
	private String vehicleno;
	
	private String supp_ref_docno;
	
	private String description;
	
	private String tarebags;
	
	private String slipnumber;
	
	private String tw_date;
	
	private String timein;
	
    private String timeout;
	
    private String grossweight;
    
	private String tareweight;
	
	private String netweight;
	
	private String drivername;
	
	private String drivernumber;
	
	

	public String getTw_date() {
		return tw_date;
	}

	public void setTw_date(String tw_date) {
		this.tw_date = tw_date;
	}

	public String getAdvicenumber() {
		return advicenumber;
	}

	public void setAdvicenumber(String advicenumber) {
		this.advicenumber = advicenumber;
	}

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getSupp_ref_docno() {
		return supp_ref_docno;
	}

	public void setSupp_ref_docno(String supp_ref_docno) {
		this.supp_ref_docno = supp_ref_docno;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTarebags() {
		return tarebags;
	}

	public void setTarebags(String tarebags) {
		this.tarebags = tarebags;
	}

	public String getSlipnumber() {
		return slipnumber;
	}

	public void setSlipnumber(String slipnumber) {
		this.slipnumber = slipnumber;
	}

	public String getTimein() {
		return timein;
	}

	public void setTimein(String timein) {
		this.timein = timein;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getGrossweight() {
		return grossweight;
	}

	public void setGrossweight(String grossweight) {
		this.grossweight = grossweight;
	}

	public String getTareweight() {
		return tareweight;
	}

	public void setTareweight(String tareweight) {
		this.tareweight = tareweight;
	}

	public String getNetweight() {
		return netweight;
	}

	public void setNetweight(String netweight) {
		this.netweight = netweight;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivernumber() {
		return drivernumber;
	}

	public void setDrivernumber(String drivernumber) {
		this.drivernumber = drivernumber;
	}

	public DailygatewheatinwardreportDTO(String advicenumber, String partyname, String vehicleno, String supp_ref_docno,
			String description, String tarebags, String slipnumber, String tw_date, String timein, String timeout,
			String grossweight, String tareweight, String netweight, String drivername, String drivernumber) {
		super();
		this.advicenumber = advicenumber;
		this.partyname = partyname;
		this.vehicleno = vehicleno;
		this.supp_ref_docno = supp_ref_docno;
		this.description = description;
		this.tarebags = tarebags;
		this.slipnumber = slipnumber;
		this.tw_date = tw_date;
		this.timein = timein;
		this.timeout = timeout;
		this.grossweight = grossweight;
		this.tareweight = tareweight;
		this.netweight = netweight;
		this.drivername = drivername;
		this.drivernumber = drivernumber;
	}

	
	

	
	
	
}

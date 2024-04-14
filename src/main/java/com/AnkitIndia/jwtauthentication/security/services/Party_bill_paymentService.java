package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Party_bill_payment;
import com.AnkitIndia.jwtauthentication.model.Partyoutstanding_invoice;

public interface Party_bill_paymentService {

	public Party_bill_payment save(Party_bill_payment pBill_payment);
	
	public List<Party_bill_payment> getPartyBillPayment();
	
	public List<Partyoutstanding_invoice> getPartyOutstanding(String party,String bunit);
	
	public List<Partyoutstanding_invoice> getPartyBusinessUnit(String party);
}

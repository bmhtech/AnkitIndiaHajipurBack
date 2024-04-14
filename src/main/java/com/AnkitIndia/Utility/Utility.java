package com.AnkitIndia.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Groupmaster;
import com.AnkitIndia.jwtauthentication.model.Parent_control;
import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;

import java.text.*;

public class Utility {
static String xx = "",sample="sample";

public static String currDatetime()
{
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();  
	String currdtime=dtf.format(now);		   
	//System.out.println(currdtime);
	return currdtime;
}

public static String serialNoGenarate(int num) {
	String sno = "";
	num += 1;
	if (num >= 1 && num < 10) {
		sno = "0000" + Integer.toString(num);
	} else if (num >= 10 && num < 100) {
		sno = "000" + Integer.toString(num);
	} else if (num >= 100 && num < 1000) {
		sno = "00" + Integer.toString(num);
	} else if (num >= 1000 && num < 10000) {
		sno = "0" + Integer.toString(num);
	} else if (num >= 10000 && num < 100000) {
		sno = Integer.toString(num);
	} else {
		sno = "End Serial No";
	}
	return sno;
}

public static String serialgenarate(int num) {
	String sno="";
	num+=1;
	//System.out.println("num from serialgen::"+num);
	//num=999;
	if(num>=1&&num<10){
		sno="00"+Integer.toString(num);
	}
	else if(num>=10&&num<100){
		sno="0"+Integer.toString(num);
	}
	else if(num>=100&&num<1000){
		sno=Integer.toString(num);
	}
	else{
		sno="End Serial No";
	}
	//System.out.println("serialgen success");
	return sno;
}

public static String serialgen1(int num) {
	String sno="";
num+=1;
//System.out.println("num from serialgen::"+num);
	//num=99999;
	if(num>=1&&num<10){
		sno="000000"+Integer.toString(num);
	}
	else if(num>=10&&num<100){
		sno="00000"+Integer.toString(num);
	}
	else if(num>=100&&num<1000){
		sno="0000"+Integer.toString(num);
	}
	else if(num>=1000&&num<10000){
		sno="000"+Integer.toString(num);
	}
	else if(num>=10000&&num<100000){
		sno="00"+Integer.toString(num);
	}
	else if(num>=100000&&num<1000000){
		sno="0"+Integer.toString(num);
	}
	else if(num>=1000000&&num<10000000){
		sno=Integer.toString(num);
	}
	else{
		sno="End Serial No";
	}
	//System.out.println("serialgen success");
		return sno;
		
}

public static String vouchergen(int num,String vcode,String date, String fyear) {
	
	String sno="";
	num+=1;
	
	if(num>=1&&num<10){
		sno="0000"+Integer.toString(num);
	}
	else if(num>=10&&num<100){
		sno="000"+Integer.toString(num);
	}
	else if(num>=100&&num<1000){
		sno="00"+Integer.toString(num);
	}
	else if(num>=1000&&num<10000){
		sno="0"+Integer.toString(num);
	}
	else if(num>=10000&&num<100000){
		sno=Integer.toString(num);
	}
	else{
		sno="End Serial No";
	}
	//System.out.println("date:::"+date);
		
	String[] d=date.split("-");
	//String yr=d[2];
	String mon=d[1];
	String day=d[0];
	//yr=yr.substring(2, 4);
	//int yr1=Integer.parseInt(yr)+1;
	
	String ss[]=fyear.split("-");
	String yr=ss[0].substring(2, 4);
	String yr1=ss[1].substring(2, 4);
	
	String voucherno=vcode+yr+yr1+mon+day+sno;
	
	System.out.println("Generated Voucherno From Vouchergen:: "+voucherno);

	return voucherno;
}

public static String vouchergen(String num,String vcode,String date,String fyear) {
	
	int x=Integer.parseInt(num);
	x=x+1;
	String sno=Integer.toString(x);
	if(x>=1&&x<10){
		sno="0000"+Integer.toString(x);
	}
	else if(x>=10&&x<100){
		sno="000"+Integer.toString(x);
	}
	else if(x>=100&&x<1000){
		sno="00"+Integer.toString(x);
	}
	else if(x>=1000&&x<10000){
		sno="0"+Integer.toString(x);
	}
	else if(x>=10000&&x<100000){
		sno=Integer.toString(x);
	}
	else{
		sno="End Serial No";
	}
		
	String[] d=date.split("-");
	//String yr=d[2];
	String mon=d[1];
	String day=d[0];
	//yr=yr.substring(2, 4);
	//int yr1=Integer.parseInt(yr)+1;
	
	String ss[]=fyear.split("-");
	String yr=ss[0].substring(2, 4);
	String yr1=ss[1].substring(2, 4);
	
	String voucherno=vcode+yr+yr1+mon+day+sno;
	
	System.out.println("Generated Voucherno From Vouchergen:: "+voucherno);

	return voucherno;
}

public static String shortfin(String year) 
{
	String ss[]=year.split("-");
	String k1=ss[0].substring(2, 4);
	String k2=ss[1].substring(2, 4);
	year=k1+"-"+k2;
	return year;
}

public static String serialgenarate5(int num) {
	String sno="";
	num+=1;
	//System.out.println("num from serialgen::"+num);
	//num=99999;
	if(num>=1&&num<10){
		sno="0000"+Integer.toString(num);
	}
	else if(num>=10&&num<100){
		sno="000"+Integer.toString(num);
	}
	else if(num>=100&&num<1000){
		sno="00"+Integer.toString(num);
	}
	else if(num>=1000&&num<10000){
		sno="0"+Integer.toString(num);
	}
	else if(num>=10000&&num<100000){
		sno=Integer.toString(num);
	}
	else{
		sno="End Serial No";
	}
	//System.out.println("serialgen success");
	return sno;
}

public static boolean areAllTrue(boolean[] array)
{
    boolean result = false;
    if(array != null)
    {
        for(boolean b : array) 
        {
        	 if(!b) 
             {
                 result = false;
             }
             else
             {
                 result = true;
             }
        }
    }
    else
    {
        result = true;
        System.out.println("null");
    }
    return result;
}

public static String convertDate(String originalDate)
{
	System.err.println("Get Date: "+originalDate);
	String newdate="";
	if(originalDate.equals("0") || originalDate.equals(null) || originalDate.equals("")) 
	{
		newdate = originalDate;
	}
	else 
	{
		String date[] = originalDate.split("-");
		newdate = date[2]+"/"+date[1]+"/"+date[0];
	}
	System.err.println("Final Date: "+newdate);
	
	return newdate;	
}

public static String convertDateDD_MM_YYYY(String originalDate)
{
	String newdate="";
	if(originalDate.equals("0") || originalDate.equals(null) || originalDate.equals("")) 
	{
		newdate = originalDate;
	}
	else 
	{
		String date[] = originalDate.split("-");
		if(date[0].length() == 2)
		{
			newdate = originalDate;
		}else {
			newdate = date[2]+"/"+date[1]+"/"+date[0];
		}
	}
	return newdate;	
}

public static String convertDateFormat(String originalDate)
{
	System.err.println("Get Date: "+originalDate);
	String newdate="";
	if(originalDate.equals("0") || originalDate.equals(null) || originalDate.equals("")) 
	{
		newdate = originalDate;
	}
	else 
	{
		String date[] = originalDate.split("-");
		newdate = date[2]+"-"+date[1]+"-"+date[0];
	}
	System.err.println("Final Date: "+newdate);
	
	return newdate;	
}

public static String convertDateYYYYMMDD(String originalDate)
{
	String newdate="";
	if(originalDate.equals("0") || originalDate.equals(null) || originalDate.equals("")) 
	{
		newdate = originalDate;
	}
	else 
	{
		String date[] = originalDate.split("-");
		if(date[0].length() > 2)
		{
			newdate = originalDate;
		}else {
			newdate = date[2]+"-"+date[1]+"-"+date[0];
		}
	}
	return newdate;	
}

public static String convertDateDDMMYYYY(String originalDate)
{
	String newdate="";
	if(originalDate.equals("0") || originalDate.equals(null) || originalDate.equals("")) 
	{
		newdate = originalDate;
	}
	else 
	{
		String date[] = originalDate.split("-");
		if(date[0].length() == 2)
		{
			newdate = originalDate;
		}else {
			newdate = date[2]+"-"+date[1]+"-"+date[0];
		}
	}
	//System.out.println("newdate "+ newdate);
	return newdate;	
}

public static long RoundUpToTen(int n)
{
	int a = (n / 10) * 10; 
    // Larger multiple 
    int b = a + 10; 
  
    // Return of closest of two 
    return (n - a > b - n)? b : a; 
}

public static long RoundUpToHundred(int input)
{
	long i = (long) Math.ceil(input);
    return ((i + 99) / 100) * 100;
}

public static String numberFormat2Decimal(double sg)
{
	DecimalFormat numberFormat2Dec = new DecimalFormat("0.00");
	String twoDec=numberFormat2Dec.format(sg);
	return twoDec;
}                
           
public static String numberFormat3Decimal(double sg)
{
	DecimalFormat numberFormat3Dec = new DecimalFormat("#0.000");
	String threeDec=numberFormat3Dec.format(sg);
	return threeDec;
}

public static List<Groupmaster> GroupmasterInsert(String branchcode,String user,String fyear,int groupuniqucode) 
{
	String x="";
	/*try 
	{*/
		 List<Groupmaster> groupdata =new ArrayList<Groupmaster>();
		 
		 Groupmaster grp1=new Groupmaster(branchcode, "CA", "Capital Account",user,"1","B","L","1","N","Capital Account","1","Capital Account","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp1);
		 Groupmaster grp2=new Groupmaster(branchcode, "CB", "Current Assets",user,"1","B","A","2","N","Current Assets","1","Current Assets","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp2);
		 Groupmaster grp3=new Groupmaster(branchcode, "CC", "Current Liabilities",user,"1","B","L","3","Y","Current Liabilities","1","Current Liabilities","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp3);
		 Groupmaster grp4=new Groupmaster(branchcode, "DA", "Direct Expenses (Expenses (Direct))",user,"1","P","E","4","N","Direct Expenses (Expenses (Direct))","1","Direct Expenses (Expenses (Direct))","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp4);
		 Groupmaster grp5=new Groupmaster(branchcode, "DB", "Direct Incomes (Income (Direct))",user,"1","P","I","5","N","Direct Incomes (Income (Direct))","1","Direct Incomes (Income (Direct))","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp5);
		 Groupmaster grp6=new Groupmaster(branchcode, "FA", "Fixed Assets",user,"1","B","A","6","Y","DIRECT EXPENSES","1","DIRECT EXPENSES","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp6);
		 Groupmaster grp7=new Groupmaster(branchcode, "IA", "Indirect Expenses (Expenses (Indirect))",user,"1","P","E","7","Y","Indirect Expenses (Expenses (Indirect))","1","Indirect Expenses (Expenses (Indirect))","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp7);
		 Groupmaster grp8=new Groupmaster(branchcode, "IB", "Indirect Incomes (Income (Indirect))",user,"1","P","I","8","Y","Indirect Incomes (Income (Indirect))","1","Indirect Incomes (Income (Indirect))","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp8);
		 Groupmaster grp9=new Groupmaster(branchcode, "IC", "Investments",user,"1","B","A","9","Y","Investments","1","Investments","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp9);
		 Groupmaster grp10=new Groupmaster(branchcode, "LA", "Loans (Liability)",user,"1","B","L","10","N","Loans (Liability)","1","Loans (Liability)","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp10);
		 Groupmaster grp11=new Groupmaster(branchcode, "MA", "Misc. Expenses (ASSET)",user,"1","B","A","11","Y","Misc. Expenses (ASSET)","1","Misc. Expenses (ASSET)","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp11);
		 Groupmaster grp12=new Groupmaster(branchcode, "PA", "Purchase Accounts",user,"1","P","E","12","Y","Purchase Accounts","1","Purchase Accounts","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp12);
		 Groupmaster grp13=new Groupmaster(branchcode, "SA", "Sales Accounts",user,"1","P","I","13","N","Sales Accounts","1","Sales Accounts","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp13);
		 Groupmaster grp14=new Groupmaster(branchcode, "SB", "Suspense A/c",user,"1","B","L","14","N","Suspense A/c","1","Suspense A/c","0",branchcode,fyear,"INSERTED");
		 groupdata.add(grp14);
		
		for(Groupmaster g:groupdata)
		{
			String code="GRP"+Utility.serialNoGenarate(groupuniqucode);
			//x+="('"+g.getBranchcode()+"','"+g.getGroupcode()+"','"+g.getGroupname()+"','"+g.getInserted_by()+"','"+g.getSubgroupserialno()+"','"+g.getIncomeexpencetype()+"','"+g.getGroupcategory()+"','"+g.getSequenceno()+"','"+g.getDetailinbs()+"','"+g.getPrintingtext()+"','"+g.getStandardhead()+"','"+g.getReservedgrouphead()+"','"+g.getSchname()+"','"+g.getInserted_location()+"','"+g.getFin_year()+"','"+g.getStatus()+"','"+code+"'),";
			g.setUniqucode(code);
			groupuniqucode++;
		}
		//x=x.substring(0,x.length()-1);
		//System.out.println("hello x: "+x);
	/*} catch (Exception e) 
	{
		e.printStackTrace();
	}*/
	return groupdata;
}

public static List<Parent_control> groupParentControl(String branchcode,String user,String fyear,int groupuniqucode) 
{
	String x="";
	/*try 
	{*/
		 List<Parent_control> groupdata =new ArrayList<Parent_control>();
		
		 Parent_control grp1=new Parent_control(branchcode,  "Capital Account","0","CA",fyear);
		 groupdata.add(grp1);
		 Parent_control grp2=new Parent_control(branchcode, "Current Assets","0","CB",fyear);
		 groupdata.add(grp2);
		 Parent_control grp3=new Parent_control(branchcode, "Current Liabilities","0","CC",fyear);
		 groupdata.add(grp3);
		 Parent_control grp4=new Parent_control(branchcode,  "Direct Expenses (Expenses (Direct))","0","DA",fyear);
		 groupdata.add(grp4);
		 Parent_control grp5=new Parent_control(branchcode,  "Direct Incomes (Income (Direct))","0","DB",fyear);
		 groupdata.add(grp5);
		 Parent_control grp6=new Parent_control(branchcode,  "Fixed Assets","0","FA",fyear);
		 groupdata.add(grp6);
		 Parent_control grp7=new Parent_control(branchcode,  "Indirect Expenses (Expenses (Indirect))","0","IA",fyear);
		 groupdata.add(grp7);
		 Parent_control grp8=new Parent_control(branchcode,  "Indirect Incomes (Income (Indirect))","0","IB",fyear);
		 groupdata.add(grp8);
		 Parent_control grp9=new Parent_control(branchcode,  "Investments","0","IC",fyear);
		 groupdata.add(grp9);
		 Parent_control grp10=new Parent_control(branchcode,  "Loans (Liability)","0","LA",fyear);
		 groupdata.add(grp10);
		 Parent_control grp11=new Parent_control(branchcode,  "Misc. Expenses (ASSET)","0","MA",fyear);
		 groupdata.add(grp11);
		 Parent_control grp12=new Parent_control(branchcode,  "Purchase Accounts","0","PA",fyear);
		 groupdata.add(grp12);
		 Parent_control grp13=new Parent_control(branchcode,  "Sales Accounts","0","SA",fyear);
		 groupdata.add(grp13);
		 Parent_control grp14=new Parent_control(branchcode,  "Suspense A/c","0","SB",fyear);
		 groupdata.add(grp14);
		
		//for(Parent_control g:groupdata)
		//{
			//String code="XXXXX";
			//String code=Utility.getGrpuniq(g.getBranchcode(),g.getCode());
			//x+="('"+g.getBranchcode()+"','"+g.getName()+"','"+g.getParent()+"','"+g.getCode()+"','"+g.getFin_year()+"','"+code+"'),";
		//}
		//x=x.substring(0,x.length()-1);
		//System.out.println("hello x: "+x);
		
	/*} catch (Exception e) 
	{
		e.printStackTrace();
	}*/
	return groupdata;
}

public static List<Subgroupmaster> SubgroupmasterInsert(String branchcode,String user,String fyear,int subgroupuniqucode) 
{
	String x="";
	/*try 
	{*/
		List<Subgroupmaster> subgroupdata =new ArrayList<Subgroupmaster>();
		
		Subgroupmaster grp=new Subgroupmaster(branchcode, "CA", "Capital Account",0,true,user,branchcode,fyear,"B","L","CA", "Capital Account");
		subgroupdata.add(grp);
		Subgroupmaster grp1=new Subgroupmaster(branchcode, "CB", "Current Assets",0,true,user,branchcode,fyear,"B","A","CB", "Current Assets");
		subgroupdata.add(grp1);
		Subgroupmaster grp2=new Subgroupmaster(branchcode, "CC", "Current Liabilities",0,true,user,branchcode,fyear,"B","L", "CC", "Current Liabilities");
		subgroupdata.add(grp2);
		Subgroupmaster grp3=new Subgroupmaster(branchcode, "DA", "Direct Expenses (Expenses (Direct))",0,true,user,branchcode,fyear,"P","E", "DA", "Direct Expenses (Expenses (Direct))");
		subgroupdata.add(grp3);
		Subgroupmaster grp4=new Subgroupmaster(branchcode, "DB", "Direct Incomes (Income (Direct))",0,true,user,branchcode,fyear,"P","I", "DB", "Direct Incomes (Income (Direct))");
		subgroupdata.add(grp4);
		Subgroupmaster grp5=new Subgroupmaster(branchcode, "FA", "Fixed Assets",0,true,user,branchcode,fyear,"B","A", "FA", "Fixed Assets");
		subgroupdata.add(grp5);
		Subgroupmaster grp6=new Subgroupmaster(branchcode, "IA", "Indirect Expenses (Expenses (Indirect))",0,true,user,branchcode,fyear,"P","E", "IA", "Indirect Expenses (Expenses (Indirect))");
		subgroupdata.add(grp6);
		Subgroupmaster grp7=new Subgroupmaster(branchcode, "IB", "Indirect Incomes (Income (Indirect))",0,true,user,branchcode,fyear,"P","I", "IB", "Indirect Incomes (Income (Indirect))");
		subgroupdata.add(grp7);
		Subgroupmaster grp8=new Subgroupmaster(branchcode, "IC", "Investments",0,true,user,branchcode,fyear,"B","A", "IC", "Investments");
		subgroupdata.add(grp8);
		Subgroupmaster grp9=new Subgroupmaster(branchcode, "LA", "Loans (Liability)",0,true,user,branchcode,fyear,"B","L", "LA", "Loans (Liability)");
		subgroupdata.add(grp9);
		Subgroupmaster grp10=new Subgroupmaster(branchcode, "MA", "Misc. Expenses (ASSET)",0,true,user,branchcode,fyear,"B","A", "MA", "Misc. Expenses (ASSET)");
		subgroupdata.add(grp10);
		Subgroupmaster grp11=new Subgroupmaster(branchcode, "PA", "Purchase Accounts",0,true,user,branchcode,fyear,"P","E", "PA", "Purchase Accounts");
		subgroupdata.add(grp11);
		Subgroupmaster grp12=new Subgroupmaster(branchcode, "SA", "Sales Accounts",0,true,user,branchcode,fyear,"P","I", "SA", "Sales Accounts");
		subgroupdata.add(grp12);
		Subgroupmaster grp13=new Subgroupmaster(branchcode, "SB", "Suspense A/c",0,true,user,branchcode,fyear,"B","L", "SB", "Suspense A/c");
		subgroupdata.add(grp13);
		
		/*for(Subgroupmaster g:subgroupdata)
		{
			//String code="XXXXX";
			//String code=Utility.getGrpuniq(g.getBranchcode(),g.getSubgroupcode());
			//x+="('"+g.getBranchcode()+"','"+g.getSubgroupcode()+"','"+g.getSubgroupname()+"','"+g.getLedgerslno()+"','"+g.isStandardhead()+"','"+g.getInserted_by()+"','"+g.getInserted_location()+"','"+fyear+"','"+g.getIncomeexpencetype()+"','"+g.getGroupcategory()+"','"+code+"','"+g.getSubgroupname()+"','"+g.getSubgroupcode()+"'),";
		}
		x=x.substring(0,x.length()-1);
		System.out.println("hello x: "+x);
		
	} catch (Exception e) 
	{
		e.printStackTrace();
	}*/
	return subgroupdata;
}

public static List<Subgroupmaster> getSubgroupmasterDataSet(String branchcode,String user,String fyear,int subgroupuniqucode,LocalDateTime ldt) 
{
	String x="";
	/*try 
	{*/
		List<Subgroupmaster> subgroupdata =new ArrayList<Subgroupmaster>();
		
		Subgroupmaster grp=new Subgroupmaster(branchcode, "CA001", "Reserves & Surplus (Retained Earnings)",0,true,user,branchcode,fyear,"Capital Account","CA",ldt);
		subgroupdata.add(grp);
		
		Subgroupmaster grp1=new Subgroupmaster(branchcode, "CB001", "Bank Accounts",0,true,user,branchcode,fyear,"Current Assets","CB",ldt);
		subgroupdata.add(grp1);
		
		Subgroupmaster grp2=new Subgroupmaster(branchcode, "CB002", "Cash-in-Hand",0,true,user,branchcode,fyear,"Current Assets","CB",ldt);
		subgroupdata.add(grp2);
		
		Subgroupmaster grp3=new Subgroupmaster(branchcode, "CB003", "Deposits (Asset)",0,true,user,branchcode,fyear,"Current Assets","CB",ldt);
		subgroupdata.add(grp3);
		
		Subgroupmaster grp4=new Subgroupmaster(branchcode, "CB004", "Loans & Advances (Asset)",0,true,user,branchcode,fyear,"Current Assets","CB",ldt);
		subgroupdata.add(grp4);
		
		Subgroupmaster grp5=new Subgroupmaster(branchcode, "CB005", "Stock-in-Hand",0,true,user,branchcode,fyear,"Current Assets","CB",ldt);
		subgroupdata.add(grp5);
		
		Subgroupmaster grp6=new Subgroupmaster(branchcode, "CB006", "Sundry Debtors",0,true,user,branchcode,fyear,"Current Assets","CB",ldt);
		subgroupdata.add(grp6);
		
		Subgroupmaster grp7=new Subgroupmaster(branchcode, "CC001", "Duties & Taxes",0,true,user,branchcode,fyear,"Current Liabilities","CC",ldt);
		subgroupdata.add(grp7);
		
		Subgroupmaster grp8=new Subgroupmaster(branchcode, "CC002", "Provisions",0,true,user,branchcode,fyear,"Current Liabilities","CC",ldt);
		subgroupdata.add(grp8);
		
		Subgroupmaster grp9=new Subgroupmaster(branchcode, "CC003", "Sundry Creditors",0,true,user,branchcode,fyear,"Current Liabilities","CC",ldt);
		subgroupdata.add(grp9);
		
		Subgroupmaster grp10=new Subgroupmaster(branchcode, "LA001", "Bank OD A/c (Bank OCC A/c)",0,true,user,branchcode,fyear,"Loans (Liability)","LA",ldt);
		subgroupdata.add(grp10);
		
		Subgroupmaster grp11=new Subgroupmaster(branchcode, "LA002", "Secured Loans",0,true,user,branchcode,fyear,"Loans (Liability)","LA",ldt);
		subgroupdata.add(grp11);
		
		Subgroupmaster grp12=new Subgroupmaster(branchcode, "LA003", "Unsecured Loans",0,true,user,branchcode,fyear,"Loans (Liability)","LA",ldt);
		subgroupdata.add(grp12);
		
		for(Subgroupmaster g:subgroupdata)
		{
			String code="SUB"+Utility.serialNoGenarate(subgroupuniqucode);
			//x+="('"+g.branchcode+"','"+g.subgroupcode+"','"+g.subgroupname+"','"+g.ledgerslno+"','"+g.standardhead+"','"+g.inserted_by+"','"+g.inserted_location+"','"+fyear+"','"+code+"','"+g.parent_subgroup+"','"+g.parent_subgroupcode+"'),";
			g.setUniqucode(code);
			subgroupuniqucode++;
		}
		//x=x.substring(0,x.length()-1);
		//System.out.println("hello x: "+x);
	/*} catch (Exception e) 
	{
		e.printStackTrace();
	}*/
	return subgroupdata;
}

public static List<Parent_control> subGroupParentControlDataSet(String branchcode,String user,String fyear,int groupuniqucode) 
{
	String x="";
	/*try 
	{*/
		List<Parent_control> groupdata =new ArrayList<Parent_control>();
		
		Parent_control grp1=new Parent_control(branchcode,  "Reserves & Surplus (Retained Earnings)","CA001",fyear);
		groupdata.add(grp1);
		Parent_control grp2=new Parent_control(branchcode, "Bank Accounts","CB001",fyear);
		groupdata.add(grp2);
		Parent_control grp3=new Parent_control(branchcode, "Cash-in-Hand","CB002",fyear);
		groupdata.add(grp3);
		Parent_control grp4=new Parent_control(branchcode,  "Deposits (Asset)","CB003",fyear);
		groupdata.add(grp4);
		Parent_control grp5=new Parent_control(branchcode,  "Loans & Advances (Asset)","CB004",fyear);
		groupdata.add(grp5);
		Parent_control grp6=new Parent_control(branchcode,  "Stock-in-Hand","CB005",fyear);
		groupdata.add(grp6);
		Parent_control grp7=new Parent_control(branchcode,  "Sundry Debtors","CB006",fyear);
		groupdata.add(grp7);
		Parent_control grp8=new Parent_control(branchcode,  "Duties & Taxes","CC001",fyear);
		groupdata.add(grp8);
		Parent_control grp9=new Parent_control(branchcode,  "Provisions","CC002",fyear);
		groupdata.add(grp9);
		Parent_control grp10=new Parent_control(branchcode,  "Sundry Creditors","CC003",fyear);
		groupdata.add(grp10);
		Parent_control grp11=new Parent_control(branchcode,  "Bank OD A/c (Bank OCC A/c)","LA001",fyear);
		groupdata.add(grp11);
		Parent_control grp12=new Parent_control(branchcode,  "Secured Loans","LA002",fyear);
		groupdata.add(grp12);
		Parent_control grp13=new Parent_control(branchcode,  "Unsecured Loans","LA003",fyear);
		groupdata.add(grp13);
		
		/*for(SubGroupParent_control g:groupdata)
		{
			String code=Utility.getSubGrpuniq(g.branchcode,g.code);
			String parent=Utility.getSubGrpParent(g.branchcode,g.code);
			x+="('"+g.branchcode+"','"+g.name+"','"+parent+"','"+g.code+"','"+g.fyear+"','"+code+"'),";
		}
		x=x.substring(0,x.length()-1);
		System.out.println("hello x: "+x);
		
	} catch (Exception e) 
	{
		e.printStackTrace();
	}*/

	return groupdata;
}

public static boolean isStringNull(String str) 
{ 
    if (str == null) 
        return true; 
    else
        return false; 
}

public static boolean isNullOrEmpty(String str) {
    if(str != null && !str.trim().isEmpty() && str.trim().compareTo("0") !=0)
        return true;
    return false;
}

public static boolean isStringNullOrEmpty(String str) {
    if(str != null && !str.trim().isEmpty() && str.trim().compareTo("0") !=0)
        return false;
    return true;
}

public static String acVoucherGen(int num, String vcode, String date, String fyear) {

	String sno = "";
	num += 1;

	if (num >= 1 && num < 10) {
		sno = "0000" + Integer.toString(num);
	} else if (num >= 10 && num < 100) {
		sno = "000" + Integer.toString(num);
	} else if (num >= 100 && num < 1000) {
		sno = "00" + Integer.toString(num);
	} else if (num >= 1000 && num < 10000) {
		sno = "0" + Integer.toString(num);
	} else if (num >= 10000 && num < 100000) {
		sno = Integer.toString(num);
	} else {
		sno = "End Serial No";
	}
	System.out.println("date:::" + date);

	String[] d = date.split("-");
	// String yr=d[2];
	String mon = d[1];
	String day = d[0];
	// yr=yr.substring(2, 4);
	// int yr1=Integer.parseInt(yr)+1;

	String ss[] = fyear.split("-");
	String yr = ss[0].substring(2, 4);
	String yr1 = ss[1].substring(2, 4);

	String voucherno = vcode + yr + yr1 + mon + day + sno;

	System.out.println("Generated Voucherno From Vouchergen:: " + voucherno);

	return voucherno;
}

public static Date convertStrToDate(String strDate) {
    Date formatteddate = null;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    if(strDate.equals("0") || strDate.equals(null) || strDate.equals("")) 
	{
		
	}else {
		try {
			Date convertDate = df.parse(Utility.convertDateDD_MM_YYYY(strDate));
			formatteddate=convertDate;
		}
		catch (Exception ex ){
			System.out.println(ex);
		}
	}
	return formatteddate;
}

public static String replaceSpecial(String str) {
	String newstr="";
	if(str.equals("0") || str.equals(null) || str.equals("")) 
	{
		newstr = str;
	}
	else 
	{
		if(str.contains("ampersand") || str.contains("backslash"))
		{
			newstr=str.replace("ampersand", "&").replace("backslash", "/");
		}else {newstr = str;}
	}
	return newstr;
}

}

package com.AnkitIndia.generators;

public class UniqueID {
	
public static String uniqueid(String prefix,long sno) {
		
		long j=sno+1;
		String code="";
		try {			
			
			int k=String.valueOf(j).length();
			String x0="";
			
			if(k==1)
			x0=prefix+"0000"+j;
			
			if(k==2)
			x0=prefix+"000"+j;
			
			if(k==3)
			x0=prefix+"00"+j;
			
			if(k==4)
			x0=prefix+"0"+j;
			
			if(k==5)
			x0=prefix+j;
			
			code=x0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return code;
	}

}

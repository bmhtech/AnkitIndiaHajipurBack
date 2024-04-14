package com.AnkitIndia.generators;

public class UniqueIDTransaction {
	
	public static String uniqueid(String prefix,long sno) {
		
		long j=sno+1;
		String code="";
		try {			
			
			int k=String.valueOf(j).length();
			String x0="";
			
			if(k==1)
			x0=prefix+"0000000"+j;
			
			if(k==2)
			x0=prefix+"000000"+j;
			
			if(k==3)
			x0=prefix+"00000"+j;
			
			if(k==4)
			x0=prefix+"0000"+j;
			
			if(k==5)
			x0=prefix+"000"+j;
			
			if(k==6)
			x0=prefix+"00"+j;
			
			if(k==7)
			x0=prefix+"0"+j;
			
			if(k==8)
			x0=prefix+j;
			
			code=x0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return code;
	}
	
	public static String uniqueId4(String prefix,long sno) {
		
		long j=sno+1;
		String code="";
		try {			
			
			int k=String.valueOf(j).length();
			String x0="";
			
			if(k==1)
			x0=prefix+"000"+j;
			
			if(k==2)
			x0=prefix+"00"+j;
			
			if(k==3)
			x0=prefix+"0"+j;
			
			if(k==4)
			x0=prefix+j;
			
			code=x0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return code;
	}
	
public static String uniqueId6(String prefix,long sno) {
		
		long j=sno+1;
		String code="";
		try {			
			
			int k=String.valueOf(j).length();
			String x0="";
			
			
			if(k==1)
				x0=prefix+"000000"+j;
				
			if(k==2)
				x0=prefix+"00000"+j;
				
			
			if(k==3)
				x0=prefix+"0000"+j;
				
			
			
			if(k==4)
			x0=prefix+"000"+j;
			
			if(k==5)
			x0=prefix+"00"+j;
			
			if(k==6)
			x0=prefix+"0"+j;
			
			if(k==7)
			x0=prefix+j;
			
			code=x0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return code;
	}

		public static String uniqueId5NEW(String prefix,long sno) {
			
			long j=sno+1;
			String code="";
			try {			
				
				int k=String.valueOf(j).length();
				String x0="";
				
				
				if(k==1)
					x0=prefix+"00000"+j;
					
					
				
				if(k==2)
					x0=prefix+"0000"+j;
					
				
				
				if(k==3)
				x0=prefix+"000"+j;
				
				if(k==4)
				x0=prefix+"00"+j;
				
				if(k==5)
				x0=prefix+"0"+j;
				
				if(k==6)
				x0=prefix+j;
				
				code=x0;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return code;
		}


	public static String uniqueId2(String prefix,long sno) {
		long j=sno+1;
		String code="";
		try {			
			
			int k=String.valueOf(j).length();
			String x0="";
			
			if(k==1)
			x0=prefix+"0"+j;
			
			if(k==2)
			x0=prefix+j;
			
			code=x0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return code;
	}
	
	public static String uniqueId5(String prefix,long sno) {
		
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

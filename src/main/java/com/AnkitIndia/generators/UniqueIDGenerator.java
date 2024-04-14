package com.AnkitIndia.generators;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UniqueIDGenerator {
	
	private static final String ZEROS = "000000000000"; 
	
	public String getUniqueID(){
		return generate();
	}
	
	private String generate() {
	    StringBuilder strRetVal = new StringBuilder();
	    String strTemp;
	    try {
	      // IPAddress segment
	      InetAddress addr = InetAddress.getLocalHost();
	      byte[] ipaddr = addr.getAddress();
	      for (byte anIpaddr : ipaddr) {
	        Byte b = new Byte(anIpaddr);
	        strTemp = Integer.toHexString(b.intValue() & 0x000000ff);
	        strRetVal.append(ZEROS.substring(0, 2 - strTemp.length()));
	        strRetVal.append(strTemp);
	      }
	      // CurrentTimeMillis() segment
	      strTemp = Long.toHexString(System.currentTimeMillis());
	      strRetVal.append(ZEROS.substring(0, 12 - strTemp.length()));
	      // random segment
	      SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
	      strTemp = Integer.toHexString(prng.nextInt());
	      while (strTemp.length() < 8) {
	        strTemp = '0' + strTemp;
	      }
	      // IdentityHash() segment
	      strTemp = Long.toHexString(System.identityHashCode(this));
	      strRetVal.append(ZEROS.substring(0, 8 - strTemp.length()));
	      strRetVal.append(strTemp);
	    } catch (NoSuchAlgorithmException nsaex) {
	      throw new RuntimeException("Algorithm 'SHA1PRNG' is unavailiable.", nsaex);
	    } catch (UnknownHostException e) {
			e.printStackTrace();
		}
	    return strRetVal.toString().toUpperCase();
	  }
	
		/*	
	 	public static void main(String args[]){
		UniqueIDGenerator u = new UniqueIDGenerator(); 
		System.out.println(u.getUniqueID());}
		*/
	
}

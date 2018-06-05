/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author ayeola
 */
public class Utilities {

    public Utilities(){
        
    }

     public   String toBinary( byte[] bytes ){

    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
}


 public   byte[] fromBinary( String s ){

    int sLen = s.length();
    byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
    char c;
    for( int i = 0; i < sLen; i++ )
        if( (c = s.charAt(i)) == '1' )
            toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
        else if ( c != '0' )
            throw new IllegalArgumentException();
    return toReturn;
}


    public   String convertStringToHex(String str){

	  char[] chars = str.toCharArray();

	  StringBuffer hex = new StringBuffer();
	  for(int i = 0; i < chars.length; i++){
	    hex.append(Integer.toHexString((int)chars[i]));
	  }

	  return hex.toString();
  }



    public   String fromHex(String s) throws UnsupportedEncodingException {
  byte bs[] = new byte[s.length() / 2];
  for (int i=0; i<s.length(); i+=2) {
    bs[i/2] = (byte) Integer.parseInt(s.substring(i, i+2), 16);
  }
  return new String(bs, "UTF8");
}



    public   byte[] hexStringToByteArray(String data) {
    int k = 0;
    byte[] results = new byte[data.length() / 2];
    for (int i = 0; i + 1 < data.length(); i += 2, k++){
    results[k] = (byte) (Character.digit(data.charAt(i), 16) << 4);
    results[k] += (byte) (Character.digit(data.charAt(i + 1), 16));
}
    return results;
}


     String  HEX_STRING  = "0123456789abcdef";
  public    String convertBinary2Hexadecimal(byte[] binary) {
  StringBuffer buf = new StringBuffer();
  int block = 0;

  for (int i=0; i<binary.length; i++) {
    block = binary[i] & 0xFF;
    buf.append(HEX_STRING.charAt(block >> 4));
    buf.append(HEX_STRING.charAt(binary[i] & 0x0F));
  }
  return buf.toString();
}



 public    String stringToHex(String string) {
  StringBuilder buf = new StringBuilder(200);
  for (char ch: string.toCharArray()) {
    if (buf.length() > 0)
      buf.append(' ');
    buf.append(String.format("%04x", (int) ch));
  }
  return buf.toString();
}



 final protected static char[] hexArray = "0123456789abcdef".toCharArray();
public   String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    int v;
    for ( int j = 0; j < bytes.length; j++ ) {
        v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
}

public void sendFile(File file, DataOutputStream dos) throws FileNotFoundException, IOException {
    if(dos!=null&&file.exists()&&file.isFile())
    {
        FileInputStream input = new FileInputStream(file);
        dos.writeLong(file.length());
        System.out.println(file.getAbsolutePath());
        int read = 0;
        while ((read = input.read()) != -1)
            dos.writeByte(read);
        dos.flush();
        input.close();
        System.out.println("File successfully sent!");
    }
}

public  String convertHexToString(String hex){

	  StringBuilder sb = new StringBuilder();
	  StringBuilder temp = new StringBuilder();
 
	  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
	  for( int i=0; i<hex.length()-1; i+=2 ){

	      //grab the hex in pairs
	      String output = hex.substring(i, (i + 2));
	      //convert hex to decimal
	      int decimal = Integer.parseInt(output, 16);
	      //convert the decimal to character
	      sb.append((char)decimal);

	      temp.append(decimal);
	  }
	  //System.out.println("Decimal : " + temp.toString());

	  return sb.toString();
  }

public  int HexadecimalToInteger(String hexValue) throws IOException{
    BufferedReader read =
  new BufferedReader(new InputStreamReader(System.in));
  System.out.println("Enter the hexadecimal value:!");
 // String s = read.readLine();
  int intValue = Integer.valueOf(hexValue, 16).intValue();

  return intValue;
}


}

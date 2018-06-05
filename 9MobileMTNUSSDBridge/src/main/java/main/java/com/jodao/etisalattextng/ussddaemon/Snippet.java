/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

/**
 *
 * @author ayeola
 */
public class Snippet {

    //handleClient(tmpSock);
						//new Thread(new SessionMTHandler(tmpSock, USSDListener.this, telcoMonitor1)).start();

    //                                outData = new DataOutputStream(clientSocket.getOutputStream());
//                                //clientSocket.setSendBufferSize(57);
//
//                                //BufferedOutputStream  bout = new BufferedOutputStream(outData);
//
////                                inData = new DataInputStream(clientSocket.getInputStream());
//
//				System.out.println("Connection to localhost:Etisalat complete ...");
//				//ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
//				// PrintWriter writer = new PrintWriter(clientSocket.getOutputStream ());
//				 //ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
//
//				//sentence = "390000006500000000000000000000000000000063656C6C756C000000000063656C6C756C0000000000000000000000000000000000000000";
//                      char c ='\uFEFF';
//                      //sentence = String.format("9{0}{0}{0}e{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}cellul{0}{0}{0}{0}{0}cellul{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}{0}",c);
////		              sentence = "9"+c+""+c+""+c+""+c+"e"+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+"cellul"+c+""+c+""+c+""+c+""+c+"cellul"+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""
////                                      +c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+""+c+"";
//                               //c + c + c
//
//
////                              String ussdBind = "<UssdBind><InterfaceVersion>"+interfaceVersion+"</InterfaceVersion><AccountName>"+username+"</AccountName><Password>"+
////				password+"</Password><SystemType>"+systemType+"</SystemType></UssdBind>";
////
////
////                              String headers = "<Header><CommandLength>"+commandLength+"</CommandLength><CommandID>"+CommandId+"</CommandID>"+
////                                      "<CommandStatus>"+commandStatus+"</CommandStatus><SenderCB>"+senderCB+"</SenderCB><ReceiverCB>"+receiverCB+"</ReceiverCB></Header>";
//
////                              String msg = "<UssdBind><header><CommandLength>"+commandLength+"</CommandLength><CommandID>"+CommandId+"</CommandID>" +
////                                      "<CommandStatus>"+commandStatus+"</CommandStatus><SenderCB>"+senderCB+"</SenderCB><ReceiverCB>"+receiverCB+"</ReceiverCB></Header>"+
////                                      "<InterfaceVersion>"+interfaceVersion+"</InterfaceVersion><AccountName>"+username+"</AccountName><Password>"+
////				password+"</Password><SystemType>"+systemType+"</SystemType></UssdBind>";
//
//
////                              byte[] version = new byte[4];
////                              version = interfaceVersion.getBytes();
//
//
////                              byte[] password1 = passwordString.toByteArray();
////                              byte[] username1 = usernameString.toByteArray();
////                              byte[] systemType1 = systemTypeString.toByteArray();
//
//
//                              byte[] user = new byte[11];
//                              user = username.getBytes();
//
//
//                              //byte[] user1 = new byte[11];
//                              //user1 = username.getBytes();
//                              // System.out.println("user length : " +user.length);
//                              byte[] pass = new byte[9];
//                              pass =  password.getBytes();
//
//                              byte[] type = new byte[13];
//                                type =   systemType.getBytes();
//
//                              //String msg = new String(version) + new String(user) + new String(pass) + new String(type);
//
//                             // byte[] msgBytes = ussdBind.getBytes();
//                              //byte[] headerBytes = headers.getBytes();
//                             // byte[] request = msg.getBytes();
//                              //String  msgLength = msgBytes.length+"";
//                              //ByteBuffer msgSize = ByteBuffer.allocate(157);
//                             // msgSize.put(msgBytes);
//
//                              //System.out.println("Messages : " + msgBytes);
////                              System.out.println("User Length : " + user.length);
//                             // byte[] request = msgSize.array();
//
//                              //String msgLength = msgBytes.length + "";
//
////                              ByteBuffer pass1 = ByteBuffer.allocate(12);
////                              char[] cc=password.toCharArray();
////                              for(char cv:cc){
////                                 pass1.putChar(cv) ;
////                              }
//
////                              ByteBuffer user1 = ByteBuffer.allocate(12);
////                              char[] pp=username.toCharArray();
////                              for(char cp:pp){
////                                 user1.putChar(cp) ;
////                              }
//
////                              ByteBuffer interface1 = ByteBuffer.allocate(8);
////                              char[] it=interfaceVersion.toCharArray();
////                              for(char itv:it){
////                                 interface1.putChar(itv) ;
////                              }
////                              ByteBuffer type1 = ByteBuffer.allocate(2);
////                              char[] tp=systemType.toCharArray();
////                              for(char pt:tp){
////                                 type1.putChar(pt) ;
////                              }
//
//                              ByteBuffer buffer1 = ByteBuffer.allocate(4);
//
//                              buffer1.putInt(commandLength);
//                              byte[] cmdLength = buffer1.array();
//
//                              ByteBuffer buffer2 = ByteBuffer.allocate(4);
//
//                              buffer2.putInt(CommandId);
//
//                              byte[] cmdId = buffer2.array();
//
//                              ByteBuffer buffer3 = ByteBuffer.allocate(4);
//
//                              buffer3.putInt(commandStatus);
//
//                              byte[] status = buffer3.array();
//
//                              ByteBuffer buffer4 = ByteBuffer.allocate(4);
//
//                              buffer4.putInt(senderCB);
//
//                              byte[] senderId = buffer4.array();
//                              //System.out.println("senderId : " +senderId.length);
//
//                              ByteBuffer buffer5 = ByteBuffer.allocate(4);
//
//                              buffer5.putInt(receiverCB);
//
//                              byte[] receiverId = buffer5.array();
//
//                              ByteBuffer buffer6 = ByteBuffer.allocate(4);
//
//                              buffer6.putInt(interfaceVersion);
//
//                              byte[] version = buffer6.array();
//
//                              String message =    new String(cmdLength) + new String(cmdId) + new String(status) + new String(senderId) + new String(receiverId) + new String(user) +  new String(version)  + new String(type) +  new String(pass);
//                              int msgLength = message.length();
//                              ByteBuffer bufferSize = ByteBuffer.allocate(4);
//                              bufferSize.putInt(msgLength);
//                              byte[] msgSize = bufferSize.array();
//
//                              //message += new String(msgSize);
//
//                              String binary = new TCPClient().toBinary(message.getBytes());
//
//                              System.out.println("Message Size : " + message.getBytes().length);
//                              System.out.println("Binary Representation : " + new TCPClient().toBinary(message.getBytes()));
//                              System.out.println("Byte Size : " + Byte.SIZE);
//                               //00:00:00:39:00:00:00:65:00:00:00:00:ff:ff:ff:ff:ff:ff:ff:ff:74:65:73:74:32:00:00:00:00:00:00:74:65:73:74:32:00:00:00:00:00:00
//                              //39:00:00:00:65:00:00:00:00:00:00:00:ff:ff:ff:ff:ff:ff:ff:ff:74:65:73:74:00:00:00:00:00:00:00:74:65:73:74:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:10:00:00:00
////                             byte[] requestBytes = new TCPClient().fromBinary(binary);//("390000006500000000000000ffffffffffffffff74657374000000000000007465737400000000000000000000000000000000000010000000");
//                              //390000006500000000000000ffffffffffffffff63656c6c756c000000000063656c6c756c0000000000000000000000000000000010000000
//                              System.out.println("result : " + new TCPClient().fromHex("390000006500000000000000ffffffffffffffff74657374000000000000007465737400000000000000000000000000000000000010000000") );
//
//                              String request = new TCPClient().fromHex("390000006500000000000000ffffffffffffffff74657374000000000000007465737400000000000000000000000000000000000010000000");
//
//                              String hexString1 = "0*FFFFFFFF";
//
//                              System.out.println("Requests : " +tcp.fromHex(hexString1));
//                              String hexString = "390000006500000000000000ffffffffffffffff63656c6c756c000000000063656c6c756c0000000000000000000000000000000010000000";
//                              byte[] byteArray = new TCPClient().hexStringToByteArray("390000006500000000000000EFBFBDEFBFBDEFBFBDEFBFBDEFBFBDEFBFBDEFBFBDEFBFBD7465737400000000746573743");
//                              System.out.println("Byte Array : " + byteArray);
//
//                             byte[] newMsg = tcp.hexStringToByteArray(hexString);
//
//                              String strArray = new String(byteArray);
//
//
//                              System.out.println("String Array : " +strArray);
//
//                              //String byteString =
//
//                              String space = "reserved";
//
//                              System.out.println("To Hexadecimal Messages: " + new TCPClient().convertBinary2Hexadecimal(message.getBytes()));
//
//                              System.out.println("To Hexadecimal : " + new TCPClient().convertBinary2Hexadecimal(pass));
//
//                              System.out.println("Bytes To Hexadecimal : " +tcp.bytesToHex(message.getBytes()));
//
//                              System.out.println("Message In Bytes : " +new String(message));
//
//                              System.out.println("System Type : " +tcp.bytesToHex(systemType.getBytes()));
//
//                              //System.out.println("StringToHexadecimal : " + new TCPClient().stringToHex("test"));
////                             encodeHexString()
//
////                              BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
////                                System.out.println("Enter the byte number:");
////                                  byte b =(byte)6;
////                                  String str = buff.readLine();
////                                  int i =Integer.parseInt(str);
////                                   String hexString1 = Integer.toHexString(i);
////                                     System.out.println("Hexa is:=" + hexString1);
//                             //String requestString = new String(requestBytes);
//
//                             //System.out.println("Request Sent : " + requestString);
//
//                               //String message = new String(msgBytes) + new String(msgLength);
//                             //String message = header + msg;// + msgLength;// + new String(msgBytes);
//
//
//                             //String ss=  convertStringToHex(sentence);
//
////                             System.out.println("cmdLength : " +cmdLength.length);
////                             //System.out.println("header Length : " + header.length());
//                            // System.out.println("request Length : " + request.length);
//                             //System.out.println(" request :" +request);
//                            // System.out.println("commandID : " +cmdId);
//
//
//                             //System.out.println("total message Length : " +message);
//                                //Charset.forName("ISO-8859-1").encode(request.toString());
//                                //System.out.println("Message : " + message);
//                                //System.out.println("Message Length: " + sentence.length());
//
//                                //byte ptext[] = sentence.getBytes("ISO-8859-1");
//                                //String value = new String(ptext, "UTF-8");
//
//                                //sentence
//				//String szUrl = "localhost:6789";
//				//final byte[] utf16Bytes = sentence.getBytes("CP1252");
//				//System.out.println("##################################"+utf16Bytes.length);
//				// end the header section
//				//writer.print ("\r\n");
//
//              //                  m_out.write(0);
//                //m_out.write( temp.getBytes(), 0, temp.length() );
//                //m_out.write( m_push ); //PUT BACK TO NORMAL
//                //m_out.write( m_push ); //PUT BACK TO NORMAL
//                //m_out.flush( );
////                                outData.write(0);
////                                outData.write(sentence.getBytes(), 0,sentence.length());
////
////                                outData.writeInt(sentence.getBytes().length);
////                                outData.write(sentence.getBytes());
////                                outData.flush();
//                              //  outToServer.write(sentence.getBytes().length);
//				//outToServer.writeObject(sentence)
//
////                                 OutputFormat format = new OutputFormat();
////            format.setEncoding( "ISO-8859-1" );
////
////            StringWriter strWriter = new StringWriter();
////            XMLSerializer serializer = new XMLSerializer(strWriter, format);
//                                //bout.write(msgBytes);
//                              String gahs= new String(user) + new String(pass) + new String(type) + new String(cmdLength) + new String(cmdId) + new String(status) + new String(senderId) + new String(receiverId) + new String(version) + new String(version);
//                              try{
//                              outData.write(newMsg);
//
//                                //outToServer.writeByts(message);
//                                //outToServer.writeObject(ussdBind);
//				//outToServer.flush();
//                                outData.flush();
//
//				System.out.println("SENT TO SERVER DATAOUTPUT..WAITING FOR RESPONSE");
//				//ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
//                                inData = new DataInputStream(clientSocket.getInputStream());
//                                //String sss =inFromServer.readLine();
//                                //String sss =(String) inFromServer.readObject();
//                                String sss = inData.readLine();
//
//
//                                System.out.println("ddd : " +sss);
//                                }catch(IOException ex){
//
//                                  new ReconnectionDaemon(this).start();
//                        }
//                                //int length = inData.readInt();
//                                //System.out.println("Received Length is "+length);
//                                //compressedData = new byte[length];
//                                //inData.readFully(compressedData);
//				//modifiedSentence = (String) inFromServer.readObject();
//				//System.out.println("...."+modifiedSentence);
//				TCPClient  client = new TCPClient();
//			        //modifiedSentence  = client.parseXML(msgBytes.toString());
//                                System.out.println("Just Before Clossing ......");
//				//System.out.println(modifiedSentence);
//				clientSocket.close();

}

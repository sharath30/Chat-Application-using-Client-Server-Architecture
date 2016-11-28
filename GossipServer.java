import java.io.*;
import java.net.*;
public class GossipServer
{
  public static void main(String[] args) throws Exception
  {
      ServerSocket sersock = new ServerSocket(3000);
      System.out.println("Server  ready for chatting");
      Socket sock = sersock.accept( );                          
                              // reading from keyboard (keyRead object)
      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                      // sending to client (pwrite object)
      OutputStream ostream = sock.getOutputStream();
      PrintWriter pwrite = new PrintWriter(ostream, true);
 
                              // receiving from server ( receiveRead  object)
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
 
      String receiveMessage, sendMessage; 
	int transferrate;        
	int cnt = 0;     
	int max = 0;
	String str[] = new String[5];
	int i = 0;
	int j = 0;
      while(true)
      {
        if((receiveMessage = receiveRead.readLine()) != null)  
        {
           System.out.println(receiveMessage);  	      
        }        
        sendMessage = keyRead.readLine();
	if(sendMessage.length()>10&&sendMessage.length()<15){
	transferrate = 3;
	}
	else if(sendMessage.length()>15&&sendMessage.length()<50){
	transferrate = 5;
	}
	else transferrate = 2;
        pwrite.println(sendMessage);   
	System.out.println("Transfer rate is "+transferrate+"ms "+"and the letters texted are "+sendMessage.length());         
        pwrite.flush();
	cnt++;
	max = max + sendMessage.length();
	str[i] = sendMessage;
	i++;
	j = j+transferrate;
	if(cnt>=5) {
	Thread.sleep(20000);
	System.out.println("u logged out ");
	Thread.sleep(2000);
	System.out.println();
	System.out.println("u have typed "+max+" chars");
	System.out.println();
	for(int k=0;k<i;k++){
	System.out.println(str[k]);
	}
	System.out.println();
	System.out.println("Total transfer rate : "+j);
	break;
	}
      }              
    }                    
}              

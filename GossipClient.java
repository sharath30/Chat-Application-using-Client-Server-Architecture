import java.io.*;
import java.net.*;
public class GossipClient
{
  public static void main(String[] args) throws Exception
  {
     Socket sock = new Socket("127.0.0.1", 3000);
                               // reading from keyboard (keyRead object)
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                              // sending to client (pwrite object)
     OutputStream ostream = sock.getOutputStream();
     PrintWriter pwrite = new PrintWriter(ostream, true);
 
                              // receiving from server ( receiveRead  object)
     InputStream istream = sock.getInputStream();
     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
 
     System.out.println("Start the chitchat, type and press Enter key");

	int transferrate ;
	int cnt = 0;
 	int max = 0;
	String str[] = new String[5];
	int i = 0,j = 0;
     String receiveMessage, sendMessage;              
     while(true)
     {
        sendMessage = keyRead.readLine();  // keyboard reading
	if(sendMessage.length()>10&&sendMessage.length()<15){
	transferrate = 3;	
	}
	else if(sendMessage.length()>15){
	transferrate = 5;
	}
	else transferrate = 2;
        pwrite.println(sendMessage);       // sending to server
        pwrite.flush();                    // flush the data
        if((receiveMessage = receiveRead.readLine()) != null) //receive from server
        {
            System.out.println(receiveMessage); // displaying at DOS prompts
		System.out.println("Transfer rate is "+transferrate+"ms "+"and the letters texted are "+sendMessage.length());
        }
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

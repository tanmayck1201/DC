import java.io.*;
import java.net.*;

class Client
{
 public static void main(String argv[]) throws Exception
 {

 //Accept from keyboard
  while(true)
         {

System.out.println("Enter message");
  InputStreamReader isr=new InputStreamReader(System.in);
  BufferedReader br = new BufferedReader(isr);
 String x = br.readLine();

Socket clientSocket = new Socket("localhost", 6789);
DataOutputStream DataToServer = new DataOutputStream(clientSocket.getOutputStream());
DataToServer.writeBytes(x + '\n');

 //Read from Client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             String y = inFromClient.readLine();
            System.out.println("Received from Server: " + y);

// clientSocket.close();
}
 }
}
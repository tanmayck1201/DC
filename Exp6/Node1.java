
// This file is a Node in the distributed system.
// It acts as a Server

import java.io.*;
import java.net.*;
import java.util.*;

public class Node1 
{
	public static int SERVER_PORT=7000;
	public static int MAX_CONNECTIONS=2;
	
	public static final HashMap<Integer,Integer> map;
	static{
		map=new HashMap<Integer,Integer>();
		map.put(2,0);
        map.put(3,1);
	}
	
	public static Node_struct node=new Node_struct(1,map,MAX_CONNECTIONS);
	
	
	Socket socket=null;
    static ServerSocket ss;
    
    Node1(Socket newSocket)
    {
        this.socket=newSocket;   
    }
    
    public static void main(String args[]) throws IOException, InterruptedException
    {
    	BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    	
        ss=new ServerSocket(SERVER_PORT);
        System.out.println("Node 1 started as server on : "+SERVER_PORT);
        //System.out.println(map.toString());
        
        int i=0;
        
        //For 2 child clients 
        while(i<2)
        {
            Socket socket = ss.accept(); //Accept connection from client
            String thread_name=Integer.toString(node.ID)+":"+Integer.toString(i+2);
            
            node.t[i]=new cl_thread(socket,thread_name,node); // Give client to new thread and Run thread
            node.t[i].start();
            i=i+1;
         
        }
            
            System.out.println(node.t[0]+"-"+node.t[1]);


        while(true){
        	
        	System.out.println("Do you want to use CS?");
        	String str=sc.readLine();
        	
        	if(str.equalsIgnoreCase("Yes")){
        		
        		if(node.holder==null){
    	    		System.out.println("This Node has TOKEN");
    	    		if(!node.USING){
    	        		System.out.println("This Node will now USE CS");
    	        		CriticalSection cs=new CriticalSection(); //Critical section object
            			// CS execution
    	        		node.USING=true;
                		String[] args1 = {Integer.toString(node.ID)};
                		cs.main(args1);
                		node.USING=false;
    	        	}
    	    	}
        		else if(node.request_q.isEmpty()){
        			//Add request to Request Queue
	        		node.request_q.add(node.ID);
        			//Pass request to holder variable
	        		node.holder.send_request(node.ID,node.ID);
	        		//Waiting to get token
	        		//node.holder.getToken();
        		}
        		else{
        			//Add request to Request Queue
	        		node.request_q.add(node.ID);
        			//Do nothing
        		}
        		
        	}
        	//else repeat the same
        		
    	}
        
        
        
    }
 
}
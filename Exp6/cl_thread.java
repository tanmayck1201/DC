
import java.io.*;
import java.net.*;
//import java.util.*;

public class cl_thread extends Thread {
	public int SERVER_PORT=0;
	
	public BufferedReader sc=null;
	public PrintStream out=null;
    public BufferedReader in=null;
	public Socket socket=null;
	//public static String name=Thread.currentThread().getName();
	public Node_struct node=null;
	
	
	public cl_thread(Socket s,String ID,Node_struct node) throws IOException{
		this.socket=s;
		this.out = new PrintStream(s.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.sc = new BufferedReader(new InputStreamReader(System.in));	 
		this.setName(ID); // 5:2
		this.node=node;
		
	}
   
	public void send_token(int req_ID,int ID) throws InterruptedException{
		System.out.println("Sending TOKEN from "+ID+" for "+req_ID+" on "+this.getName());
	    //Send request with ID of Node
		Thread.sleep(5000);
	    out.println("TOKEN:"+req_ID+":"+ID);//Request from ID to holder
    }

	public void print_q(){
		System.out.println("---Queue elements----");
		
		for(Integer s : node.request_q) { 
			  System.out.print(s.toString()+" | "); 
			}
		System.out.println("\n-------------");
	}
	
	public void get_request() throws IOException, InterruptedException{
    	System.out.println("Listening for any request on "+this.getName()+"........");
    	String str=in.readLine();
    	System.out.println("Received a request on "+this.getName());
    	System.out.println(this.getName());
    	
    	String[] req=str.split(":");
    	
		if(req[0].equalsIgnoreCase("REQUEST")){
			int child_ID=Integer.parseInt(req[2]); //One from where request has come
			int ID=Integer.parseInt(req[1]);
			
			//System.out.println(req[0]+req[1]);
			if(node.holder==null && node.USING==false){ //Possess token and not using
				
				System.out.println(node.ID+" has the TOKEN now");
				//Add request to Request Queue
        		node.request_q.add(child_ID);
        	    print_q();
        	    
        		//Send token downway
        		//Get thread from which we received request
        		int index=node.map.get(child_ID);
        		System.out.println("Index "+index);
				//Reverse holder
        		System.out.println("Before reversing : holder "+node.holder);
        		node.holder=node.t[index];
        		//Send TOKEN and remove top of queue
        		node.request_q.poll();
        		print_q();
        		System.out.println("After reversing : holder "+node.holder);
        		
        		System.out.println(node.t.toString());
        		node.holder.send_token(ID,node.ID);
        		
			}//if doesnot possess token pass request ahead
			else{
				
				if(node.request_q.isEmpty()){
        			//Add request to Request Queue
					System.out.println("Queue before adding in "+node.ID);
					print_q();
					
	        		node.request_q.add(child_ID);
	        		
	        		System.out.println(node.holder);
	        		System.out.println("Queue after adding in "+node.ID);
	        		print_q();
	        		//Pass request to holder variable
	        		node.holder.send_request(ID,node.ID);
	        		
				}
				else{
					//Add request to Request Queue
	        		node.request_q.add(child_ID);
				}
			}
    	}
		else{//on receiving TOKEN
			System.out.println("Token received by "+node.ID+" on "+this.getName()+" for "+req[1]);
			//Remove top of request queue
			int child_ID=node.request_q.poll();
			
			if(child_ID==node.ID){// Request from this node 
				node.holder=null;//Since it has the token now - ROOT NODE
				//Execute CS
				CriticalSection cs=new CriticalSection(); //Critical section object
    			// CS execution
				System.out.println("CS execution in thread");
        		node.USING=true;
        		String[] args1 = {Integer.toString(node.ID)};
        		cs.main(args1);
        		node.USING=false;
        		
        		System.out.println("Checking for req_q in node "+node.ID);
    			if(!node.request_q.isEmpty()){// If queue is not empty at this point
        			int req_ID=node.request_q.poll();
        			//Get thread from which we received request
    	    		int index=node.map.get(req_ID);
    	    		//Reverse holder
    	    		System.out.println("Before reversing : holder "+node.holder);
    	    		node.holder=node.t[index];
    	    		System.out.println("After reversing : holder "+node.holder);
    	    		//Send token down
    	    		node.holder.send_token(Integer.parseInt(req[1]),node.ID);
        		}
    			
			}
			else{
				//Get thread from which we received request
	    		int index=node.map.get(child_ID);
	    		//Reverse holder
	    		System.out.println("Before reversing : holder "+node.holder);
	    		node.holder=node.t[index];
	    		System.out.println("After reversing : holder "+node.holder);
	    		//Send token down
	    		node.holder.send_token(Integer.parseInt(req[1]),node.ID);
	    	
			}
			
			System.out.println("Checking for req_q in thread "+this.getName());
			if(!node.request_q.isEmpty()){// If queue is not empty at this point
    			//Get top but don't remove it
				int req_ID=node.request_q.peek();
    			
	    		//Send token down
	    		node.holder.send_request(req_ID,node.ID);
    		}
			
		}
    	
    	
	}
	
	public void send_request(int ID,int child_ID) throws IOException, InterruptedException{
	    System.out.println("Sending request from "+child_ID+" for "+ID+" to "+node.holder.getName());
	    //Send request with ID of Node
	    System.out.println("REQUEST:"+ID+":"+child_ID);
	    Thread.sleep(5000);
	    out.println("REQUEST:"+ID+":"+child_ID);//Request from ID to holder
    }
	
    public void run()  {
    	//Listen to input port continuosly
    	while(true){
	    	try {
				this.get_request();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
       
    }

	
}

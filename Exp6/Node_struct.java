import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;


public class Node_struct {
	public cl_thread holder=null; //This is root initially
	public Queue<Integer> request_q = new PriorityQueue<Integer>();
	public int ID=1;
	public boolean USING=false; //false-Not Using; true-Executing
	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
	public int MAX_CONNECTIONS=1;
	public cl_thread t[];
	
	public Node_struct(int ID,HashMap<Integer,Integer> map,int MAX_CONNECT){
		this.ID=ID;
		this.map=map;
		this.MAX_CONNECTIONS=MAX_CONNECT;
		this.t=new cl_thread[this.MAX_CONNECTIONS+1];
	}
}

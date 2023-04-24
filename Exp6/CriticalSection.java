
// This is Critical Section program which takes parameter as CLientID.
// The client uses it through the thread assigned to it.
// It executes for some msec of time by sleep method of thread

import java.io.*;
//import java.net.*;

public class CriticalSection {
	 public void main(String args[])throws IOException, InterruptedException
	    {
		 	System.out.println("CS talking: Hello Client " + args[0]);
		 	Thread.sleep(10000); // Execute for some time
	    }
}

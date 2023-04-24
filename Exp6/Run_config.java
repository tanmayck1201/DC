import java.io.IOException;

public class Run_config {
    public static void main(final String[] args) {

        Thread serverThread = new Thread() {
            public void run() {
                try {
					Node1.main(args);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
//VIS
        Thread clientThread = new Thread() {
            public void run() {
               try {
				Node2.main(args);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        };

        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientThread.start();
    }
}

import java.io.File;

public class DisplayFileSize {
   public static long getFileSize(String filename) {
      File file = new File(filename);
      if (!file.exists() || !file.isFile()) {
         System.out.println("File doesn\'t exist");
         return -1;
      }
      return file.length();
   }
   public static void main(String[] args) 
     {
      long size = getFileSize("C:/Users/Tanmay/OneDrive/Documents/DC/DC Prac 01");
      System.out.println("Filesize in bytes: " + size);
     }
}
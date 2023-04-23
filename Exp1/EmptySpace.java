import java.io.*;


public class EmptySpace
{
    // Driver main method
    public static void main(String[] args)
    {
        File file = new File("F:\\");
        double size = file.getFreeSpace() / (1024.0 * 1024 * 1024);
        System.out.printf( "Free Space: %.3f GB\n", size);
    }
}

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Lamport {
public static void main(String[] args) throws Exception
{
Scanner sc = new Scanner(System.in);
System.out.println("Enter no of processes: ");
int n = sc.nextInt();
int[] arr = new int[n];
System.out.println("Enter timestamp ");
for(int i = 0; i<n; i++){
arr[i] = sc.nextInt();
}
for(int i = 0; i<n; i++){
if(arr[i]!=0){
for(int j = 0; j<n; j++){
if(i!=j){
System.out.println("P"+i+ " sends request to " + "P"+j);
}
}
}
}
System.out.println();
for(int i = 0; i<n; i++){
if(arr[i]!=0){
for(int j = 0; j<n; j++){
if(i!=j){
System.out.println("P"+j+ " sends reply to " + "P"+i);
}
}
}
}
while(checkArray(arr)==false){
int min = 0;
for(int i = 0; i<n; i++){
if(arr[i]<arr[min])
min = i;
}
System.out.println("Process "+ min + " enters critical section");
System.out.println("Process "+ min + " finishes execution");
for(int j = 0; j<n; j++){
if(min!=j){
System.out.println("P"+min+ " sends RELEASE message to " + "P"+j);
}
}
arr[min] = 100;
}
System.out.println("Queue is empty.");
}
public static boolean checkArray(int[] a){
int n = a.length;
for(int i = 0; i<n; i++){
if(a[i]!=100)
return false;
}
return true;
}
}
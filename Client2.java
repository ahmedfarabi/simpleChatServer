//developed by ahmed farabi


import java.io.*;
import java.util.*;
import java.net.*;

public class Client2
{
 String name;
 
 public static void main(String args[])
 {
   Scanner sc=new Scanner(System.in);
   
   System.out.println("Client!!!");
    System.out.println("Enter User name : ");
    String user=sc.next();
    System.out.println("Enter Host address : ");
    String host=sc.next();
    System.out.println("Enter port : ");
    int port=sc.nextInt();
   
    try {  
      Socket clientSocket = new Socket(host,port);
      //NetworkUtil nc=new NetworkUtil(host,port);
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true); //true=autoflash
      out.println(user);
        
      new Read(clientSocket).start();
      new Write(clientSocket).start();

    }catch(Exception e)
    {
      System.out.println (e);
    }
 }
}


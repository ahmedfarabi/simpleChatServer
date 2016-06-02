import java.io.*;
import java.net.*;
import java.util.*;


//developed by ahmed farabi


public class Read extends Thread {
  
  Socket clientSocket;
  UserList user;
  
  public Read(Socket clientSocket){
    this.clientSocket=clientSocket;
  }
  public void run(){
    while(true){
      try{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String fromClient=in.readLine();
        if(fromClient!=null)
          System.out.println("Received : \n"+fromClient);
      } catch (Exception e) 
      {
        //System.out.println("Reading Error in network : " + e.toString());   
      }
    }
  }
  
}

class Write extends Thread
{
  Socket clientSocket;
  UserList user;
  
  public Write(Socket clientSocket){
    this.clientSocket=clientSocket;
  }
  public void run(){
    try{
      while(true){
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the destination : ");  
        String destName = cin.readLine();
        if(destName.equals("end of session")){
          cin.close();
          out.close();
          clientSocket.close();
        }
        else
          out.println(destName);
        
        
        System.out.println("Enter message : ");
        String msg=cin.readLine();
        
        if(msg!=null){
          System.out.println("Sent : \nMe : "+msg);
          out.println(msg);
        }
      }
    }catch (IOException e) {
      System.out.println("Disconnected!!!");
    }
  }
}

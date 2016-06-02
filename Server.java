import java.io.*;
import java.util.*;
import java.net.*;

public class Server 
{
  static int port=33333;
  
  public static void main(String args[])
  {
    try
    {
      UserList user=new UserList(); 
      System.out.println("Server!!!");
      System.out.println("Server is running on port: "+port);
      ServerSocket ServSock = new ServerSocket(port);
      
      while (true)
      {
        Socket s=ServSock.accept();
        
        new ServerThread(s,user).start();
      }
    }catch(Exception e)
    {
      System.out.println("Server starts:"+e);
    }
  }
}


class ServerThread extends Thread
{
  private Socket ClientSock;
  private UserList usr;
  
  ServerThread(Socket client,UserList u) 
  {
    this.ClientSock = client;
    this.usr=u;
  }
  
  public void run() 
  {
    try{
      
      BufferedReader in = new BufferedReader(new InputStreamReader(ClientSock.getInputStream()));
      
      String userName = in.readLine();
      usr.sockList.add(ClientSock);
      usr.nameList.add(userName);
      System.out.println(userName+" is successfully connected! :)");
      
      
      while(true){
        String fromMe;
        String dest = in.readLine();
        
        fromMe = in.readLine();
        if(fromMe!=null)
          System.out.println("Received: "+fromMe+" FROM "+userName);
        else
          System.out.println(userName+" is disconnected! :(");
          
        Socket d = usr.search(dest);
        if(fromMe!=null)
        System.out.println("Sent: "+fromMe+" TO "+dest);
        
        
        PrintWriter out = new PrintWriter(d.getOutputStream(), true);
        String st=userName+" : "+fromMe;
        out.println(st);
      }
    }catch (Exception e ){
    }
  }
}



//developed by ahmed farabi


import java.util.*;
import java.net.*;
public class UserList{
  
  List<Socket> sockList=new ArrayList<Socket>(); 
  List<String> nameList=new ArrayList<String>();
  public Socket search(String s){
    Socket user=null;
    int i=0;
    while(i<nameList.size()){
      if(nameList.get(i).equals(s))
        user=sockList.get(i);
      i++;
    }
    return user;
  }
  
}
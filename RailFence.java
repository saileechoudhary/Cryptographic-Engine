package RailFence;
import java.util.*;

class RailFence{
	 public static void main(String args[])throws Exception
	 {
	  RailFenceBasic rf=new RailFenceBasic(); 
	                Scanner scn=new Scanner(System.in);
	                int depth;
	   
	                String plaint,ciphert,decryptedt;
	                 
	                System.out.println("Enter plain text:");
	                plaint=scn.nextLine();
	                 
	                System.out.println("Enter depth for Encryption:(the depth should be less than the length of the plain text");
	                depth=scn.nextInt();
	                
	  ciphert=rf.Encryption(plaint,depth);
	  System.out.println("Encrypted text is:\n"+ciphert);
	                 
	               
	 }
	}
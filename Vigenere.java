package Vigenere;

import java.util.*;

public class Vigenere

{

 public static void main(String[] args)

    {

        Scanner in=new Scanner(System.in);

        String plaint="",ciphert="",key="",l="abcdefghijklmnopqrstuvwxyz";

           
            System.out.print("Enter the plain text\n");

            plaint=in.nextLine();
           
			System.out.print("Enter the key:\n");

            key=in.nextLine();

            int ky=key.length();

            int pln=plaint.length();

        String  y =key;

        int m = pln%ky;

         

            for( int i=1; i<pln/ky ; i++)

            {

                key=key+y;
       
            }

        key=key+key.substring(0,m);  

            //System.out.println(key);

        for (int j=0; j<pln; j++ ){

            ciphert=ciphert + l.charAt((l.indexOf(key.charAt(j)) + l.indexOf(plaint.charAt(j)))%26);

        }

        ciphert=ciphert.toUpperCase();

        System.out.println("Ciphertext\n"+ciphert);
        
    }
}

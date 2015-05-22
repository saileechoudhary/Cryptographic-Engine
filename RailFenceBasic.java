package RailFence;

import java.util.*;

class RailFenceBasic{
 int depth;
 String Encryption(String plaint,int depth)throws Exception
 {
  int r=depth,len=plaint.length();
  int c=len/depth;
  char m[][]=new char[r][c];
  int k=0;
   
  String ciphert="";
   
  for(int i=0;i< c;i++)
  {
   for(int j=0;j< r;j++)
   {
    if(k!=len)
     m[j][i]=plaint.charAt(k++);
    else
     m[j][i]='X';
   }
  }
  for(int i=0;i< r;i++)
  {
   for(int j=0;j< c;j++)
   {
    ciphert+=m[i][j];
   }
  }
  return ciphert;
 }
  
  
 String Decryption(String ciphert,int depth)throws Exception
 {
  int r=depth,len=ciphert.length();
  int c=len/depth;
  char m[][]=new char[r][c];
  int k=0;
   
  String plaint="";
   
   
  for(int i=0;i< r;i++)
  {
   for(int j=0;j< c;j++)
   {
    m[i][j]=ciphert.charAt(k++);
   }
  }
  for(int i=0;i< c;i++)
  {
   for(int j=0;j< r;j++)
   {
    plaint+=m[j][i];
   }
  }
   
  return plaint;
 }
}
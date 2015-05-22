package teaEncryption;

import java.util.Scanner;

public class TeaEncrypt {
	private final static int delta = 0x9E3779B9;
	private final static int passes  = 32;
	private final static int undelta=0xC6EF3720;
	
	private int[] S=new int[4];
     public TeaEncrypt (byte[] key)
	 {
			   // this code generates the key
    	        Scanner in = new Scanner(System.in);
    	        String keyy;
    	        System.out.println("Enter the key: the length of the key is greater than 16");
	            keyy=in.nextLine();
	            int len=keyy.length();
	            while(len<16)
	            {
	               System.out.println("The entered key length is less than 16. Please re enter the key");
	               keyy=in.nextLine();
	               break;
	               
	               
	            }
				 if(key==null)
					 	throw new RuntimeException("INVALID KEY:The Key was Null");
				 if(key.length<16)
					 	throw new RuntimeException("the length of the entered key should be greater than 16");
				 for (int off=0, i=0; i<4; i++) 
				 {
							S[i] = ((key[off++] & 0xff)) |
							((key[off++] & 0xff) <<  8) |
							((key[off++] & 0xff) << 16) |
							((key[off++] & 0xff) << 24);
			      }
		}
     void brew(int[] buf) 
     {
		 		assert1 (buf.length % 2 == 1);
		 		int i, v0, v1, sum, n;
		 		i = 1;
		 		while (i<buf.length) {
		 			n = passes;
		 			v0 = buf[i];
		 			v1 = buf[i+1];
		 			sum = 0;
		 			while (n-->0) {
		 				sum += delta;
		 				v0  += ((v1 << 4 ) + S[0] ^ v1) + (sum ^ (v1 >>> 5)) + S[1];
		 				v1  += ((v0 << 4 ) + S[2] ^ v0) + (sum ^ (v0 >>> 5)) + S[3];
		 			}
		 			buf[i] = v0;
		 			buf[i+1] = v1;
		 			i+=2;
 		}
 	}
     private void assert1(boolean b) {
		// TODO Auto-generated method stub
		
	}
	void pack(byte[] src, int[] dest, int destOffset) {
 		assert1( destOffset + (src.length / 4) <= dest.length);
 		int i = 0, shift = 24;
 		int j = destOffset;
 		dest[j] = 0;
 		while (i<src.length) {
 			dest[j] |= ((src[i] & 0xff) << shift);
 			if (shift==0) {
 				shift = 24;
 				j++;
 				if (j<dest.length) dest[j] = 0;
 			}
 			else {
 				shift -= 8;
 			}
 			i++;
 		}
 	}
     byte[] unpack(int[] src, int srcOffset, int destLength)
     {
		 		assert1 (destLength <= (src.length - srcOffset) * 4);
		 		byte[] dest = new byte[destLength];
		 		int i = srcOffset;
		 		int count = 0;
		 		for (int j = 0; j < destLength; j++) {
		 			dest[j] = (byte) ((src[i] >> (24 - (8*count))) & 0xff);
		 			count++;
		 			if (count == 4) {
		 				count = 0;
		 				i++;
		 			}
		 		}
		 		return dest;
 	}

	
	public byte[] encrypt(byte[] clear) 
	{
						int paddedSize = ((clear.length/8) + (((clear.length%8)==0)?0:1)) * 2;
						int[] buffer = new int[paddedSize + 1];
						buffer[0] = clear.length;
						pack(clear, buffer, 1);
						brew(buffer);
						return unpack(buffer, 0, buffer.length * 4);
						
	 }
	
	void unbrew(int[] buf) {
		assert1( buf.length % 2 == 1);
		int i, v0, v1, sum, n;
		i = 1;
		while (i<buf.length) {
			n = passes;
			v0 = buf[i]; 
			v1 = buf[i+1];
			sum = undelta;
			while (n--> 0) {
				v1  -= ((v0 << 4 ) + S[2] ^ v0) + (sum ^ (v0 >>> 5)) + S[3];
				v0  -= ((v1 << 4 ) + S[0] ^ v1) + (sum ^ (v1 >>> 5)) + S[1];
				sum -= delta;
			}
			buf[i] = v0;
			buf[i+1] = v1;
			i+=2;
		}
	}
	/**
	 * Decrypt an array of bytes.
	 * @param ciper the cipher text to decrypt
	 * @return the decrypted text
	 */
	public byte[] decrypt(byte[] crypt) {
		assert1 (crypt.length % 4 == 0);
		assert1( (crypt.length / 4) % 2 == 1);
		int[] buffer = new int[crypt.length / 4];
		pack(crypt, buffer, 0);
		unbrew(buffer);
		return unpack(buffer, 1, buffer[0]);
	}
	
}


package teaEncryption;
import java.util.Scanner;

public class TeaCall {

	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
					
					
					
		              Scanner in = new Scanner(System.in);
		              String quote;
		              System.out.println("Enter the string that you want to encrypt:");
		              quote=in.nextLine();
					
						/* Create a cipher using the first 16 bytes of the pass phrase */
						TeaEncrypt tea = new TeaEncrypt("And is there honey still for me?".getBytes());

						byte[] original = quote.getBytes();
                        
						/* Run it through the cipher... and back */
						byte[] crypt = tea.encrypt(original);
						System.out.println("cipher length is:" + crypt.length);
						System.out.println("cipher text is:");
						for (int i =0; i<crypt.length; i++)
						{
							System.out.print(crypt[i]+",");
						}
						System.out.println("");
							
						
						byte[] result = tea.decrypt(crypt);
						System.out.println("plain text length is:" + result.length);
						String test = new String(result);
						System.out.println("plain text is:" + test);

						/* Ensure that all went well */
						
				        
				        if (!test.equals(quote)){
				        	 throw new RuntimeException("Fail");	
				        }
				        else
				        {
				        	System.out.println("Success");
				        }
						   
				      
				
				}
		


	}



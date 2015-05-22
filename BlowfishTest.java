package BlowFish;
import java.util.Scanner;


public class BlowfishTest {
	
	public static void main(String args[]) throws Exception {

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter your message : ");
		String myMessage = reader.nextLine();
		System.out.println("Enter your Key : ");
		String myKey = reader.nextLine();

		if (myMessage == null || myKey == null) {
			System.out.println("The key or the plain text cannot be null");
			System.exit(1);
		}
		byte[][] messages = {
				{ (byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x11,
						(byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x11 },
				{ (byte) 0x1D, (byte) 0x9D, (byte) 0x5C, (byte) 0x50,
						(byte) 0x18, (byte) 0xF7, (byte) 0x28, (byte) 0xC2 },
				
				myMessage.getBytes() };

		byte[][] keys = {
				{ (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
						(byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF },
				{ (byte) 0x01, (byte) 0x83, (byte) 0x10, (byte) 0xDC,
						(byte) 0x40, (byte) 0x9B, (byte) 0x26, (byte) 0xD6 },
				myKey.getBytes() /* new byte[8] */
		};

		Blowfish bf = new Blowfish();
		for (int i = 0; i < messages.length; i++) {
			bf.initialize(keys[i]);
		
			System.out.println("Message Text ..... (" + messages[i].length
					+ " bytes): '" + new String(messages[i]) + "'");
			 
			System.out.println("Message Hex ...... (" + messages[i].length
					+ " bytes): '" + Blowfish.getHexString(messages[i]) + "'");
			
			System.out.println("Key .............. (" + keys[i].length
					+ " bytes): '" + Blowfish.getHexString(keys[i]) + "'");
			byte[] encrypted = bf.crypt(messages[i], true);
			System.out.println("Encrypted Hex .... (" + encrypted.length
					+ " bytes): '" + Blowfish.getHexString(encrypted) + "'");
					
			byte[] decrypted = bf.crypt(encrypted, false);
			
			System.out.println("Decrypted Text ... (" + decrypted.length
					+ " bytes): '" + new String(decrypted) + "'");
			System.out.println("Decrypted Hex .... (" + decrypted.length
					+ " bytes): '" + Blowfish.getHexString(decrypted) + "'\n");
			bf.reset();
		}
		

		if (reader != null) {
			reader.close();
		}
	}

}



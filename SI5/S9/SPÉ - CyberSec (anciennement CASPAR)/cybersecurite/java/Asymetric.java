package com.polytech;

/**
* TD2 - RSA signature, encryption/decryption
*
* asymetric clearTextFile SignatureFile CipheredFile DecipheredFile
**/

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.io.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HexFormat;

public class Asymetric{

	static public void main(String argv[]){

		// INITIALIZATION
		
		// load the bouncycastle provider
		//Provider prov = new org.bouncycastle.jce.provider.BouncyCastleProvider();
		//Security.addProvider(prov);

		// create two new entity
		Entity Alice = new Entity();
		Entity Bob = new Entity();
		
		try{
		
			// GET THE CLEAR TEXT
			File aFile = new File(argv[0]);
			FileInputStream in = new FileInputStream(aFile);
			byte[] aMessage = new byte[(int)aFile.length()];
			in.read(aMessage);
			in.close();
			
			// RSA SIGNATURE
			System.out.println("\nRSA SIGNATURE\n");
				// MAKE ALICE SIGN IT
					// display the clear text
					System.out.println("Message == \n"+new String(aMessage));
					// sign it
					byte[] aSignature = Alice.sign(aMessage);
					// display and store the signature
					System.out.println("Alice Signature == \n"+new String(aSignature));
					FileOutputStream out = new FileOutputStream(new File(argv[1]));
					out.write(aSignature);
					out.close();
			
				// BOB CHECKS THE ALICE SIGNATURE
				System.out.println("Bob signature verification == \n"+Bob.checkSignature(aMessage, aSignature, Alice.thePublicKey));
				
			// MY RSA SIGNATURE
			System.out.println("\nMY RSA SIGNATURE\n");
				// MAKE ALICE SIGN IT
					// display the clear text
					System.out.println("Message == \n"+new String(aMessage));
					// sign it
					aSignature = Alice.mySign(aMessage);
					// display and store the signature
					System.out.println("Alice Signature == \n"+new String(aSignature));
					out = new FileOutputStream(new File(argv[1]));
					out.write(aSignature);
					out.close();
			
				// BOB CHECKS THE ALICE SIGNATURE
				System.out.println("Bob signature verification == "+Bob.myCheckSignature(aMessage, aSignature, Alice.thePublicKey));
	
			// RSA ENCRYPTION/DECRYPTION
			System.out.println("\nRSA ENCRYPTION\n");
				// bob encrypt a message with the alice public key
				System.out.println("Clear Text == \n"+new String(aMessage));
				byte[] aCiphered = Bob.encrypt(aMessage, Alice.thePublicKey);
				System.out.println("Ciphered Text== \n"+new String(aCiphered)+"\n");
				out = new FileOutputStream(new File(argv[2]));
				out.write(aCiphered);
				out.close();
				
				// alice decrypt the message
				byte[] aDeciphered = Alice.decrypt(aCiphered);
				System.out.println("Deciphered Text== \n"+new String(aDeciphered));
				out = new FileOutputStream(new File(argv[3]));
				out.write(aDeciphered);
				out.close();

			// PROTOCOL IMPLEMENTATION
				KeyExchangeProtocol();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("java Asymetric clearTextFile SignatureFile CipheredFile DecipheredFile");
		}

		
	}

	static void KeyExchangeProtocol(){
		final byte[] initialization_vector = { 0, 0, 0, 0, 0, 0, 0, 0 };
		AlgorithmParameterSpec desIV = new IvParameterSpec(initialization_vector);
		Entity Alice, Bob;
		Alice = new Entity();
		Bob = new Entity();
		//	Alice sends her public key to Bob.
		PublicKey pubKeyAlice = Alice.thePublicKey;
		try {
			//	Bob generate a DES session key.
			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			SecretKey desKey_Bob = keygenerator.generateKey();
			//	Bob encrypts it with Alice’s public key.
			Cipher RSAcipher = Cipher.getInstance("RSA");
			RSAcipher.init(Cipher.ENCRYPT_MODE, pubKeyAlice);
			byte[] encryptedDESkey = Bob.encrypt(desKey_Bob.getEncoded(), pubKeyAlice);
			//	Alice decrypts the DES key with her private key.
			byte[] rawDesKey_Alice = Alice.decrypt(encryptedDESkey);
			Key desKey_Alice = new SecretKeySpec(rawDesKey_Alice, 0, rawDesKey_Alice.length, "DES");
			//  Alice sends a message to Bob with her session key
			Cipher encrypt_Alice = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encrypt_Alice.init(Cipher.ENCRYPT_MODE, desKey_Alice, desIV);
			byte[] encryptedMessage_Alice = encrypt_Alice.doFinal("Coucou".getBytes());
			HexFormat hex = HexFormat.of();
			System.out.println("Alice encrypted " + hex.formatHex(encryptedMessage_Alice));
			//	Bob decrypts the message with the session key.
			Cipher decrypt_Bob = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decrypt_Bob.init(Cipher.DECRYPT_MODE, desKey_Bob, desIV);
			byte[] decryptedMessage_Bob = decrypt_Bob.doFinal(encryptedMessage_Alice);
			System.out.println("Bob decrypted " + new String(decryptedMessage_Bob));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
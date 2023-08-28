package com.polytech;


import java.security.*;
import javax.crypto.*;

import java.io.*;
import java.util.Arrays;
import java.util.HexFormat;

public class Entity{

	// keypair
	public PublicKey thePublicKey;
	private PrivateKey thePrivateKey;
	
	/**
	  * Entity Constructor
	  * Public / Private Key generation
	 **/
	public Entity(){
		// INITIALIZATION

		// generate a public/private key
		try{
			// get an instance of KeyPairGenerator  for RSA
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			// Initialize the key pair generator for 1024 length
			keyPairGenerator.initialize(1024);
			// Generate the key pair
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			// save the public/private key
			this.thePrivateKey = keyPair.getPrivate();
			this.thePublicKey = keyPair.getPublic();
			
		}catch(Exception e){
			System.out.println("Signature error");
			e.printStackTrace();
		}
	}

	/**
	  * Sign a message
	  * Parameters
	  * aMessage : byte[] to be signed
	  * Result : signature in byte[] 
	  **/
	public byte[] sign(byte[] aMessage){
		
		try{
			// use of java.security.Signature
			// Init the signature with the private key
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(thePrivateKey);
			// update the message
			signature.update(aMessage);
			// sign
			return signature.sign();
		}catch(Exception e){
			System.out.println("Signature error");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	  * Check aSignature is the signature of aMessage with aPK
	  * Parameters
	  * aMessage : byte[] to be signed
	  * aSignature : byte[] associated to the signature
	  * aPK : a public key used for the message signature
	  * Result : signature true or false
	  **/
	public boolean checkSignature(byte[] aMessage, byte[] aSignature, PublicKey aPK){
		try{
			// use of java.security.Signature
			// init the signature verification with the public key
			Signature verifier = Signature.getInstance("SHA1withRSA");
			verifier.initVerify(aPK);
			// update the message
			verifier.update(aMessage);
			// check the signature
			return verifier.verify(aSignature);
		} catch(Exception e){
			System.out.println("Verify signature error");
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	  * Sign a message
	  * Parameters
	  * aMessage : byte[] to be signed
	  * Result : signature in byte[] 
	  **/
	public byte[] mySign(byte[] aMessage){
		
		try{
			// get an instance of a cipher with RSA with ENCRYPT_MODE
			Cipher RSAcipher = Cipher.getInstance("RSA");
			// Init the signature with the Private key
			RSAcipher.init(Cipher.ENCRYPT_MODE, thePrivateKey);
			// get an instance of the java.security.MessageDigest with SHA1
			MessageDigest sha1MessageDigest = MessageDigest.getInstance("SHA-1");
			// process the digest
			byte[] aSha1DigestedMessage = sha1MessageDigest.digest(aMessage);
			// return the encrypted digest
			return RSAcipher.doFinal(aSha1DigestedMessage);
		}catch(Exception e){
			System.out.println("Signature error");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	  * Check aSignature is the signature of aMessage with aPK
	  * Parameters
	  * aMessage : byte[] to be signed
	  * aSignature : byte[] associated to the signature
	  * aPK : a public key used for the message signature
	  * Result : signature true or false
	  **/
	public boolean myCheckSignature(byte[] aMessage, byte[] aSignature, PublicKey aPK){
		try{
			// get an instance of a cipher with RSA with DECRYPT_MODE
			Cipher RSAcipher = Cipher.getInstance("RSA");
			// Init the signature with the private key
			RSAcipher.init(Cipher.DECRYPT_MODE, aPK);
			// decrypt the signature
			byte[] aDecryptedSha1Digest = RSAcipher.doFinal(aSignature);
			// get an instance of the java.security.MessageDigest with SHA1
			MessageDigest sha1MessageDigest = MessageDigest.getInstance("SHA-1");
			// process the digest
			byte[] aSha1DigestedMessage = sha1MessageDigest.digest(aMessage);
			// check if digest1 == digest2
			return Arrays.equals(aSha1DigestedMessage, aDecryptedSha1Digest);

		}catch(Exception e){
			System.out.println("Verify signature error");
			e.printStackTrace();
			return false;
		}
	}	
	
	
	/**
	  * Encrypt aMessage with aPK
	  * Parameters
	  * aMessage : byte[] to be encrypted
	  * aPK : a public key used for the message encryption
	  * Result : byte[] ciphered message
	  **/
	public byte[] encrypt(byte[] aMessage, PublicKey aPK){
		try{
			// get an instance of RSA Cipher
			Cipher RSAcipher = Cipher.getInstance("RSA");
			// init the Cipher in ENCRYPT_MODE and aPK
			RSAcipher.init(Cipher.ENCRYPT_MODE, aPK);
			// use doFinal on the byte[] and return the ciphered byte[]
			return RSAcipher.doFinal(aMessage);

		}catch(Exception e){
			System.out.println("Encryption error");
			e.printStackTrace();
			return null;
		}
	}

	/**
	  * Decrypt aMessage with the entity private key
	  * Parameters
	  * aMessage : byte[] to be encrypted
	  * Result : byte[] deciphered message
	  **/
	public byte[] decrypt(byte[] aMessage){
		try{
			// get an instance of RSA Cipher
			Cipher RSAcipher = Cipher.getInstance("RSA");
			// init the Cipher in DECRYPT_MODE and private key
			RSAcipher.init(Cipher.DECRYPT_MODE, this.thePrivateKey);
			// use doFinal on the byte[] and return the deciphered byte[]
			return RSAcipher.doFinal(aMessage);
		}catch(Exception e){
			System.out.println("Encryption error");
			e.printStackTrace();
			return null;
		}

	}


}
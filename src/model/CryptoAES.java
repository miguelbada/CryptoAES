package model;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
	
	public class CryptoAES {
		
		private static String algoritmoAES = "AES";
		private static String cifrado = "AES/ECB/PKCS5Padding";
		
		public CryptoAES() {
			
		}
		
		 public static Key generarClave(int byteDeCifrado, String clave) throws Exception {
			 // generamos clave de 128,96 o 256 bits para el algoritmo AES
			 KeyGenerator keyGenerator = KeyGenerator.getInstance(algoritmoAES);
			 keyGenerator.init(byteDeCifrado);
			 Key key = keyGenerator.generateKey();
			      
			 //generamos la clave de 16 bits
			 key = new SecretKeySpec(clave.getBytes(),  0, 16, algoritmoAES);
			      
			 return key;
		 }
		 
		 
		 public static Cipher generarCifrado() throws Exception {
			 // retorna un cifrador AES
			 Cipher aes = Cipher.getInstance(cifrado);
			      
			 return aes;
		 }
		 
		 
		 public static byte[] encriptar(Cipher cifrado, Key key, String textoACifrar) throws Exception {
			 // Se inicializa para encriptar el texto
		     cifrado.init(Cipher.ENCRYPT_MODE, key);
		     //Finaliza una operación de cifrado
		     byte[] encriptado = cifrado.doFinal(textoACifrar.getBytes());
		      
		     return encriptado;
	     }
		 
		 
		 public static String desencriptar(Cipher cifrado, Key key, byte[] encriptado) throws Exception{
			 // Se iniciliza para desencriptar con la misma clave
			 cifrado.init(Cipher.DECRYPT_MODE, key);
			 //Finaliza una operación de desifrado
			 byte[] desencriptado = cifrado.doFinal(encriptado); 
		    
			 return new String(desencriptado);
		 } 
		

	   public static void main(String[] args) throws Exception {

	     
		  Key key = generarClave(128, "la clave es la clave");
		  
		  Cipher cifrado = generarCifrado();
		  
		  byte[] textoEncriptado = encriptar(cifrado, key, "Texto original para ser encritado y desencriptado");
		  
		  String textoDesencriptado = desencriptar(cifrado, key, textoEncriptado);
	      

	     // Se imprime el texto encriptado bite a bite
	      for (byte b : textoEncriptado) {
	         System.out.print(Integer.toHexString(0xFF & b));
	      }
	      System.out.println();

	     // Se imprime el texto original
	      System.out.println(textoDesencriptado);
	   }
}

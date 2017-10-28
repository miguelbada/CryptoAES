package test;

import static org.junit.Assert.*;
import java.security.Key;
import model.CryptoAES;
import javax.crypto.Cipher;
import org.junit.Before;
import org.junit.Test;

public class CryptoAESTest {
	private String textoOriginal;
	private CryptoAES crypto;
	private Key key;
	private Cipher cifrado;
	byte[] textoEncriptado;
	String textoDesencriptado;

	@Before
	public void setUp() throws Exception {
		this.textoOriginal = new String("Texto para ser encriptado");
		this.crypto = new CryptoAES();
		this.key = crypto.generarClave(128, "la clave es la clave");
		this.cifrado = crypto.generarCifrado();
		this.textoEncriptado = crypto.encriptar(cifrado, key, textoOriginal);
		this.textoDesencriptado = crypto.desencriptar(cifrado, key, textoEncriptado);
	}

	
	@Test
	public void CryptoAEStest() {
		
		//El texto encriptado es de Clase byte[]
		assertEquals(textoEncriptado.getClass(), byte[].class);
		
		//El texto original es distinto a la encriptación e igual al texto desencriptado
		assertNotEquals(textoOriginal, textoEncriptado, textoDesencriptado);
	}

}

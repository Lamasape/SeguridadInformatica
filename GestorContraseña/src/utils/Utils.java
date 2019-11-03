package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;

public class Utils {
	

	
	private static String keyValue = "92AE31A79FEEB2A3"; //llave
	private static String iv = "0123456789ABCDEF"; // vector de inicialización

	// Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
	private final static String ALGORITHM = "AES";
	// Definición del modo de cifrado a utilizar
	private final static String cI = "AES/CBC/PKCS5Padding";

	/**
	 * Función de tipo String que recibe una llave (key), un vector de
	 * inicialización (iv) y el texto que se desea cifrar
	 * 
	 * @param keyValue       la llave en tipo String a utilizar
	 * @param iv        el vector de inicialización a utilizar
	 * @param cleartext el texto sin cifrar a encriptar
	 * @return el texto cifrado en modo String
	 * @throws Exception puede devolver excepciones de los siguientes tipos:
	 *                   NoSuchAlgorithmException, InvalidKeyException,
	 *                   InvalidAlgorithmParameterException,
	 *                   IllegalBlockSizeException, BadPaddingException,
	 *                   NoSuchPaddingException
	 */
	public static String encriptar(String cleartext) throws Exception {
		Cipher cipher = Cipher.getInstance(cI);
		SecretKeySpec skeySpec = new SecretKeySpec(keyValue.getBytes(), ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(cleartext.getBytes());
		return new String(Base64.encodeBase64(encrypted));
	}

	/**
	 * Función de tipo String que recibe una llave (key), un vector de
	 * inicialización (iv) y el texto que se desea descifrar
	 * 
	 * @param keyValue       la llave en tipo String a utilizar
	 * @param iv        el vector de inicialización a utilizar
	 * @param encrypted el texto cifrado en modo String
	 * @return el texto desencriptado en modo String
	 * @throws Exception puede devolver excepciones de los siguientes tipos:
	 *                   NoSuchAlgorithmException, NoSuchPaddingException,
	 *                   InvalidKeyException, InvalidAlgorithmParameterException,
	 *                   IllegalBlockSizeException
	 */
	public static String desencriptar(String encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance(cI);
		SecretKeySpec skeySpec = new SecretKeySpec(keyValue.getBytes(), ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		byte[] enc = Base64.decodeBase64(encrypted);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] decrypted = cipher.doFinal(enc);
		return new String(decrypted);
	}

	public void encriptarContrasena(String password) {

		MessageDigest md = null;
		try {
			// SHA-512
			md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());
			byte[] mb = md.digest();
			System.out.println(Hex.encodeHex(mb));

			// SHA-1
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			mb = md.digest();
			System.out.println(Hex.encodeHex(mb));

			// MD5
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			mb = md.digest();
			System.out.println(Hex.encodeHex(mb));
		} catch (Exception e) {
			System.out.println("NO SE PUDO ENCRIPTAR" + e.toString());
		}
	}

	public void encriptarContrasena2(String password) {
		MessageDigest md = null;
		try {
			// SHA-512
			md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());
			byte[] mb = md.digest();
			System.out.println(Hex.encodeHex(mb));

			// SHA-1
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			mb = md.digest();
			System.out.println(Hex.encodeHex(mb));

			// MD5
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			mb = md.digest();
			System.out.println(Hex.encodeHex(mb));
		} catch (Exception e) {
			System.out.println("NO SE PUDO ENCRIPTAR" + e.toString());
		}
	}

	public static void borrarTxt(File fileImport) throws IOException {
		FileInputStream fileStream = null;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileImport);
			writer.print("");
			fileStream = new FileInputStream(fileImport);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			writer.close();
		}
	}
	/**
	 *Funcion que recibe un vector de caracteres, y les pone el valor 0, para practicamente vaciarlo
	 * Importante cuando se maneja informacion sensible por memoria, puesto a que si se utiliza una variable primitiva
	 * String, la contraseña puede verse en memoria claramente
	 * @param contrasenia  El vector, que contiene la contrasenia
	 * @return El propio vector se modifica; se retorna el mismo vector pero vacio.
	 * **/
	public static void limpiarContraseñaDeLaMemoria(char[] contrasenia)
	{
		//System.out.println(contrasenia);
		for(int i=0; i<contrasenia.length;i++)
		{
			contrasenia[i]=0;
		}
	}
	/**
	 * Funcion que da los dias de diferencia de una fecha con la actual
	 * @return
	 */
	public long diasDeDiferencia(LocalDate fechaPasada)
	{
		LocalDate date=LocalDate.now();
		long diferencia=ChronoUnit.DAYS.between(fechaPasada, date);
		if(diferencia<0)//Just in case xdxdxdddd
		{
			diferencia*=-1;
		}
		return diferencia;
	}

}

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Utils {

	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();

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

	/**
	 * @param args
	 * @throws Exception
	 */

	public static String encriptar(String valueToEnc) throws Exception {

		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);

	//	System.out.println("valueToEnc.getBytes().length " + valueToEnc.getBytes().length);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
	//	System.out.println("encValue length" + encValue.length);
		byte[] encryptedByteValue = new Base64().encode(encValue);
		String encryptedValue = encryptedByteValue.toString();
	//	System.out.println("encryptedValue " + encryptedValue);

		return encryptedValue;
	}

	public static String desencriptar(String encryptedValue) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);

		byte[] enctVal = c.doFinal(encryptedValue.getBytes());
	//	System.out.println("enctVal length " + enctVal.length);

		byte[] decordedValue = new Base64().decode(enctVal);

		return decordedValue.toString();
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
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

}

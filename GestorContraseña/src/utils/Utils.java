package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Utils {

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

	public static String Encriptar(String texto) {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	public  static String Desencriptar(String textoEncriptado) throws Exception {

		String secretKey = "qualityinfosolutions"; // llave para desenciptar datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}
	
	public static void  borrarTxt(File fileImport) throws IOException {
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

package igu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import modelo.Registro;
import modelo.Usuario;
import servicio.impl.ServiceRegistroImpl;
import utils.Utils;

public class Main {

	private static String ENCRYPT_KEY = "clave-compartida-no-reveloar-nunca";

	public Main() {

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ServiceRegistroImpl service = new ServiceRegistroImpl();

		// LEER CONTRASEÑA MAESTRA
//		 Usuario usuario = service.leerUsuario();
//			System.out.println(usuario.getContrasenaMaestra());
//			try {
//				System.out.println(Utils.Desencriptar(usuario.getContrasenaMaestra()));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		// CREAR CONTRASEÑA POR PRIMERA VEZ
//		service.crearContrasenaMaestra("HALOOO");
		
		
		Registro registro = new Registro();
		registro.setId(2);
		registro.setTitulo("Twitter");
		registro.setNombreUsuario("LauraM");
		registro.setContrasena("contraseña");
		registro.setURL("http://twitter.com");

		// CREAR REGISTRO
//		service.crearRegistro(registro);

		// ELIMINAR REGISTRO
//		service.eliminarRegistro(registro);

		// Modificar Contraseña
		service.modificarContrasenaMaestra("lalalalla");


	}

	
}

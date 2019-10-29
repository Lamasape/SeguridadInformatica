package igu;

import java.io.FileNotFoundException;
import java.io.IOException;
import modelo.Registro;
import servicio.impl.ServiceRegistroImpl;

public class Main {

	private static String ENCRYPT_KEY = "clave-compartida-no-reveloar-nunca";

	public Main() {

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ServiceRegistroImpl service = new ServiceRegistroImpl();

		// LEER CONTRASE�A MAESTRA
//		 Usuario usuario = service.leerUsuario();
//			System.out.println(usuario.getContrasenaMaestra());
//			try {
//				System.out.println(Utils.Desencriptar(usuario.getContrasenaMaestra()));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		// CREAR CONTRASE�A POR PRIMERA VEZ
//		service.crearContrasenaMaestra("HALOOO");
		
		
		Registro registro = new Registro();
		registro.setId(2);
		registro.setTitulo("Twitter");
		registro.setNombreUsuario("LauraM");
		registro.setContrasena("contrase�a");
		registro.setURL("http://twitter.com");

		// CREAR REGISTRO
		service.crearRegistro(registro);

		// ELIMINAR REGISTRO
//		service.eliminarRegistro(registro);

		// Modificar Contrase�a
//		service.modificarContrasenaMaestra("�E");


	}

	
}

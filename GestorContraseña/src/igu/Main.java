package igu;


import java.io.FileNotFoundException;
import java.io.IOException;

import modelo.Registro;
import servicio.impl.ServiceRegistroImpl;

public class Main {

	public Main() {

	}

	public static void main(String[] args) throws FileNotFoundException, IOException  {
		ServiceRegistroImpl service = new ServiceRegistroImpl();
		
		// LEER CONTRASE�A MAESTRA 
		//Usuario usuario = service.leerContrasenaMaestra();
//			System.out.println(usuario.getContrasenaMaestra());
		
		//CREAR CONTRASE�A POR PRIMERA VEZ 
//		service.crearContrasenaMaestra("HALOOO");
		
		
		Registro registro = new Registro();
		registro.setId(2);
		registro.setTitulo("Facebook");
		registro.setNombreUsuario("LauraM");
		registro.setContrasena("contrase�a");
		registro.setURL("http://facebook.com");
		
		//CREAR REGISTRO
//		service.crearRegistro(registro);
		
		//ELIMINAR REGISTRO
//		service.eliminarRegistro(registro);
		
		//Modificar Contrase�a 
		service.modificarContrasenaMaestra("EYYYYYY");
	}
	
	

}

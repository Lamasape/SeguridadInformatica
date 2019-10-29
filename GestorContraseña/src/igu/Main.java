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
		
		// LEER CONTRASEŅA MAESTRA 
		//Usuario usuario = service.leerContrasenaMaestra();
//			System.out.println(usuario.getContrasenaMaestra());
		
		//CREAR CONTRASEŅA POR PRIMERA VEZ 
//		service.crearContrasenaMaestra("HALOOO");
		
		
		Registro registro = new Registro();
		registro.setId(2);
		registro.setTitulo("Facebook");
		registro.setNombreUsuario("LauraM");
		registro.setContrasena("contraseņa");
		registro.setURL("http://facebook.com");
		
		//CREAR REGISTRO
//		service.crearRegistro(registro);
		
		//ELIMINAR REGISTRO
//		service.eliminarRegistro(registro);
		
		//Modificar Contraseņa 
		service.modificarContrasenaMaestra("EYYYYYY");
	}
	
	

}

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
		
		// LEER CONTRASEÑA MAESTRA 
		//Usuario usuario = service.leerContrasenaMaestra();
//			System.out.println(usuario.getContrasenaMaestra());
		
		//CREAR CONTRASEÑA POR PRIMERA VEZ 
//		service.crearContrasenaMaestra("HALOOO");
		
		
		Registro registro = new Registro();
		registro.setId(2);
		registro.setTitulo("Facebook");
		registro.setNombreUsuario("LauraM");
		registro.setContrasena("contraseña");
		registro.setURL("http://facebook.com");
		
		//CREAR REGISTRO
//		service.crearRegistro(registro);
		
		//ELIMINAR REGISTRO
//		service.eliminarRegistro(registro);
		
		//Modificar Contraseña 
		service.modificarContrasenaMaestra("EYYYYYY");
	}
	
	

}

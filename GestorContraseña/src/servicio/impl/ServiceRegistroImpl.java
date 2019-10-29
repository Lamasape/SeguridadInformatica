package servicio.impl;

import java.io.IOException;

import modelo.Registro;
import modelo.Usuario;
import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;
import servicio.ServicioRegistro;

public class ServiceRegistroImpl implements ServicioRegistro {
	Persistencia persistencia = new PersistenciaImpl(); 
	@Override
	public void crearRegistro(Registro registro) {
		persistencia.crearRegistro(registro); 
		System.out.println("Registro Creada");
	}
	@Override
	public Usuario leerUsuario() throws IOException {
		 return persistencia.leerUsuario();
		
	}
	@Override
	public void crearContrasenaMaestra(String contrasena) {
		persistencia.crearContrasenaMaestra(contrasena);
		System.out.println("Contraseña Creada");
	}
	@Override
	public void eliminarRegistro(Registro registro) {
		persistencia.eliminarRegistro(registro);
		System.out.println("Registro eliminado");
		
	}
	@Override
	public void modificarContrasenaMaestra(String contrasena) {
		persistencia.modificarContrasenaMaestra(contrasena);
		System.out.println("CONTRASEÑA MODIFICADA");
		
	}

}

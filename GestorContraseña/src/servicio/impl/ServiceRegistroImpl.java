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
	public Usuario leerContrasenaMaestra() throws IOException {
		 return persistencia.leerContrasenaMaestra();
		
	}
	@Override
	public void crearContrasenaMaestra(String contrasena) {
		persistencia.crearContrasenaMaestra(contrasena);
		System.out.println("Contraseña Creada");
	}

}

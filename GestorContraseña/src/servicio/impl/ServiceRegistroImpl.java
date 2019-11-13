package servicio.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

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
	public void eliminarRegistro(Registro registro, Usuario usuario) {
		persistencia.eliminarRegistro(registro, usuario);
		System.out.println("Registro eliminado");
		
	}
	@Override
	public void modificarContrasenaMaestra(String contrasena) {
		persistencia.modificarContrasenaMaestra(contrasena);
		System.out.println("CONTRASEÑA MODIFICADA");
		
	}
	@Override
	public void crearContrasenaMaestra2(String contraseña, LocalDate fechaDeCreacion) {
		persistencia.crearContrasenaMaestra2(contraseña, fechaDeCreacion);
		
	}
	@Override
	public void crearRegistro2(Registro registro) {
		crearRegistro2(registro);
	}
	@Override
	public File getBdd() {
		
		return persistencia.getBdd();
	}
	@Override
	public void setBdd(File bdd) {
		persistencia.setBdd(bdd);
	}
	@Override
	public OutputStream flujoDelArchivo() {
		// TODO Auto-generated method stub
		return persistencia.flujoDelArchivo();
	}
	
}

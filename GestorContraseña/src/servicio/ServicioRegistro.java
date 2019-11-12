package servicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

import modelo.Registro;
import modelo.Usuario;

public interface ServicioRegistro {
	
	/**
	 * Añadir registro
	 * @param registro
	 */
	void crearRegistro(Registro registro); 
	
	/**
	 * Obtiene la contrasenaMaestra de la bdd
	 * @return contrasenaMaestra
	 * @throws IOException 
	 */
	Usuario leerUsuario() throws IOException; 
	/**
	 * Creamos la ContrasenaMaestra
	 * @param contrasena
	 */
	
	void crearContrasenaMaestra(String contrasena);
	
	/**
	 * Elimina registro bdd
	 * @param registro
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	void eliminarRegistro(Registro registro); 

	/**
	 * Modifica la contrasena maestra 
	 */
	public void modificarContrasenaMaestra(String contrasena);
	/**
	 * Copiar registro a la nueva bdd
	 * 
	 */
	void crearContrasenaMaestra2(String contraseña, LocalDate fechaDeCreacion);
	
	/**
	 * Copiar registro
	 */
	void crearRegistro2(Registro registro);
	
	/**
	 * Exportar base de datos
	 * 
	 */
	File getBdd();
	/**
	 * Importar base de datos
	 */
	void setBdd(File bdd);
	
	public OutputStream flujoDelArchivo() ;
}

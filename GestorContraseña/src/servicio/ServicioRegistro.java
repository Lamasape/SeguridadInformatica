package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;

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

}

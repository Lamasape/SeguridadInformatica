package servicio;

import java.io.IOException;

import modelo.Registro;
import modelo.Usuario;

public interface ServicioRegistro {
	
	/**
	 * A�adir registro
	 * @param registro
	 */
	void crearRegistro(Registro registro); 
	
	/**
	 * Obtiene la contrasenaMaestra de la bdd
	 * @return contrasenaMaestra
	 * @throws IOException 
	 */
	Usuario leerContrasenaMaestra() throws IOException; 
	/**
	 * Creamos la ContrasenaMaestra
	 * @param contrasena
	 */
	
	void crearContrasenaMaestra(String contrasena);
	


}

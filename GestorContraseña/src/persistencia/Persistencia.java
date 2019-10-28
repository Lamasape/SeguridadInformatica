package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;

import modelo.Registro;
import modelo.Usuario;

/** 
 * Contacto directo con la bdd 
 * @author Laura Marcela
 *
 */
public interface Persistencia {

	/**
	 * Añadir registro a la bdd
	 * 
	 * @param registr
	 */
	void crearRegistro(Registro registr);
	
	/**
	 * Eliminar registro
	 * @param registr
	 */
	void eliminarRegistro(Registro registr);
	/**
	 * Crear Contrasena Maestra 
	 */

	void crearContrasenaMaestra(String contrasena); 
	
	/**
	 * Lee la contrasena maestra de la bdd
	 * @return Contrasena Maestra 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	
	Usuario leerContrasenaMaestra() throws  IOException; 

}

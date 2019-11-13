package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

import modelo.Registro;
import modelo.Usuario;

/**
 * Contacto directo con la bdd
 * 
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


	/*
	 * 
	 */
	File getBdd();
	public OutputStream flujoDelArchivo();
	void setBdd(File bdd);
	/**
	 * Copiar registro a la nueva bdd
	 * 
	 */
	void crearRegistro2(Registro registro);
	/**
	 * Crear Contrasena Maestra por primera vez 
	 */

	void crearContrasenaMaestra(String contrasena);
	/**
	 * Copiar Contrasena Maestra por primera vez 
	 * @param contraseña
	 */
	void crearContrasenaMaestra2(String contraseña, LocalDate fechaDeCreacion);

	/**
	 * Lee la contrasena maestra de la bdd
	 * 
	 * @return Contrasena Maestra
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	Usuario leerUsuario() throws IOException;

	/**
	 * ModificarContraseñaMaestra
	 */
	void modificarContrasenaMaestra(String contrasena);

	/**
	 * Modificar registro
	 * 
	 * @param registro
	 */
	void modificarRegistro(Registro registro);

	void eliminarRegistro(Registro registr, Usuario usuario);
}

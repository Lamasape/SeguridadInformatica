package persistencia;

import modelo.Registro;

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
	void addRegistro(Registro registr);

}

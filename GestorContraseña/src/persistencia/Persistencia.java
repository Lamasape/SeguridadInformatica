package persistencia;

import modelo.Registro;

/** 
 * Contacto directo con la bdd 
 * @author Laura Marcela
 *
 */
public interface Persistencia {

	/**
	 * A�adir registro a la bdd
	 * 
	 * @param registr
	 */
	void addRegistro(Registro registr);

}

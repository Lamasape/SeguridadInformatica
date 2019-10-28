package servicio.impl;

import modelo.Registro;
import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;
import servicio.ServicioRegistro;

public class ServiceRegistroImpl implements ServicioRegistro {
	Persistencia persistencia = new PersistenciaImpl(); 
	@Override
	public void addRegistro(Registro registro) {
		persistencia.addRegistro(registro); 
	}

}

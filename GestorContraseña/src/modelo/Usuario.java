package modelo;

import java.time.LocalDate;
import java.util.List;

public class Usuario {

	String contrasenaMaestra;
	LocalDate fechaDeCreacionContraseña;
	List<Registro> registros;
	public String getContrasenaMaestra() {
		return contrasenaMaestra;
	}
	public void setContrasenaMaestra(String contrasenaMaestra) {
		this.contrasenaMaestra = contrasenaMaestra;
	}
	public List<Registro> getRegistros() {
		return registros;
	}
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	public LocalDate getFechaDeCreacionContraseña() {
		return fechaDeCreacionContraseña;
	}
	public void setFechaDeCreacionContraseña(LocalDate fechaDeCreacionContraseña) {
		this.fechaDeCreacionContraseña = fechaDeCreacionContraseña;
	} 
	
	
}

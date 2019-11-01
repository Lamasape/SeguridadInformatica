package modelo;

import java.time.LocalDate;
import java.util.List;

public class Usuario {

	String contrasenaMaestra;
	LocalDate fechaDeCreacionContrase�a;
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
	public LocalDate getFechaDeCreacionContrase�a() {
		return fechaDeCreacionContrase�a;
	}
	public void setFechaDeCreacionContrase�a(LocalDate fechaDeCreacionContrase�a) {
		this.fechaDeCreacionContrase�a = fechaDeCreacionContrase�a;
	} 
	
	
}

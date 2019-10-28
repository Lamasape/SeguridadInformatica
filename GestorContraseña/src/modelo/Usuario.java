package modelo;

import java.util.List;

public class Usuario {

	String contrasenaMaestra;
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
	
	
}

package igu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import modelo.Registro;
import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;
import servicio.impl.ServiceRegistroImpl;
import utils.Utils;

public class Main {

	private static String ENCRYPT_KEY = "clave-compartida-no-reveloar-nunca";

	public Main() {

	}

	public static void main(String[] args) throws Exception {
		ServiceRegistroImpl service = new ServiceRegistroImpl();
		
		System.out.println(Utils.desencriptar("jIcKQX1G6ru2Je7R7vjYjw=="));

		// LEER CONTRASEÑA MAESTRA
//		 Usuario usuario = service.leerUsuario();
//			System.out.println(usuario.getContrasenaMaestra());
//			try {
//				System.out.println(Utils.Desencriptar(usuario.getContrasenaMaestra()));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		// CREAR CONTRASEÑA POR PRIMERA VEZ
		//service.crearContrasenaMaestra("HALOOO");

		System.out.println(service.leerUsuario().getFechaDeCreacionContraseña());
		
		
		// CREAR REGISTRO
		
		Registro registro = new Registro();
		registro.setId(2);
		registro.setTitulo("Facebook");
		registro.setNombreUsuario("pop");
		registro.setContrasena("lel");
		registro.setURL("http://twitter.com");
		
		Registro registro2 = new Registro();
		registro2.setId(2);
		registro2.setTitulo("Facebook");
		registro2.setNombreUsuario("pop");
		registro2.setContrasena("lel");
		registro2.setURL("http://twitter.com");
		

		//service.crearRegistro(registro);
		 
		// ELIMINAR REGISTRO
//		service.eliminarRegistro(registro);

		// Modificar Contraseña
		//service.modificarContrasenaMaestra("lol");
		/*Persistencia persistencia=new PersistenciaImpl();
		String contra=persistencia.leerUsuario().getContrasenaMaestra();
		System.out.println("Contraseña encriptada: "+contra);
		
		contra=Utils.desencriptar(contra);
		System.out.println("Contraseña encriptada: "+contra);
		*/
		/*
		Pruebas con fechas
		  LocalDate date=LocalDate.now();
		String estoIriaEnElArchivo=date.toString();
		System.out.println(estoIriaEnElArchivo);
		LocalDate date2=LocalDate.parse("2018-01-24");
		System.out.println(date2.toString());
		System.out.println("Diferencia entre estos dos dias: "+ChronoUnit.DAYS.between(date, date2));
		*/
		
		//String contrasenia=Utils.desencriptar(Utils.encriptar("lol"));
		//System.out.println(contrasenia);
		
		
		//Manejo de contraseña por memoria
		/*
		char[] cadena=Utils.desencriptar(service.leerUsuario().getContrasenaMaestra()).toCharArray();
		char[] input="lel".toCharArray();
		System.out.println(cadena);//Antes de limpiar la contraseña de la memoria
		if(Arrays.equals(cadena, input))
		{
			System.out.println("Son iguales");
		}
		else
		{
			System.out.println("No son iguales");
		}
		Utils.limpiarContraseñaDeLaMemoria(input);
		System.out.println(cadena);//Despues de limpiar la contraseña de la memoria
		*/
	}

	
}

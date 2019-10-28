package persistencia.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Registro;
import modelo.Usuario;
import persistencia.Persistencia;

public class PersistenciaImpl implements Persistencia {

	String bdd = "bdd.txt";


	@Override
	public void crearContrasenaMaestra(String contraseña) {

		try {
			File archivo = new File("bddVacia.txt");
			FileWriter escribir = new FileWriter(archivo, true);
			escribir.write(contraseña);
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al crear contraseña ");
		}
	}

	@Override
	public Usuario leerContrasenaMaestra() throws IOException {
		String cadena;
		FileReader f;
		BufferedReader b = null;
		int contador = 0;
		Usuario usuario = new Usuario();
		try {
			f = new FileReader(bdd);
			b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				if (contador == 0) {
					usuario.setContrasenaMaestra(cadena);
					contador++;
				} else {
					ArrayList<Registro> listaRegistro = new ArrayList<>();
					Registro registro = new Registro();
					String[] registros = cadena.split("#");
					registro.setTitulo(registros[0]);
					registro.setNombreUsuario(registros[1]);
					registro.setContrasena(registros[2]);
					registro.setURL(registros[3]);

					listaRegistro.add(registro);

					usuario.setRegistros(listaRegistro);

				}
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("ERROR LEER CONTRASENA MAESTRA" + e);
		} catch (IOException e) {
			throw new FileNotFoundException("ERROR LEER CONTRASENA MAESTRA" + e);
		} finally {
			b.close();
		}
		return usuario;
	}

	@Override
	public void crearRegistro(Registro registro) {
		try {
			File archivo = new File("bddVacia.txt");
			FileWriter escribir = new FileWriter(archivo, true);

			escribir.write("\n" + registro.getTitulo()+"#");
			escribir.write(registro.getNombreUsuario()+"#");
			escribir.write(registro.getContrasena()+"#");
			escribir.write(registro.getURL());
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al crear registro ");
		}
		
	}

}

package persistencia.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Registro;
import modelo.Usuario;
import persistencia.Persistencia;
import utils.Utils;

public class PersistenciaImpl implements Persistencia {

	File bdd = new File("/bdd/bdd.txt");
	public OutputStream flujoDelArchivo() 
	{
		try {
			return new FileOutputStream(bdd);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void crearContrasenaMaestra(String contraseña) {
		System.out.println("Contraseña Creada 1");
		try {
			FileWriter escribir = new FileWriter(bdd, true);
			escribir.write(Utils.encriptar(contraseña));
			escribir.write("\n"+LocalDate.now().toString());
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al crear contraseña ");
		}
	}
	
	public void crearContrasenaMaestra2(String contraseña, LocalDate fechaDeCreacion) {

		try {
			FileWriter escribir = new FileWriter(bdd, true);
			escribir.write(contraseña);
			escribir.write("\n"+fechaDeCreacion);
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al crear contraseña ");
		}
	}
	


	@Override
	public Usuario leerUsuario() {
		String cadena;
		FileReader f;
		BufferedReader b = null;
		int contador = 0;
		Usuario usuario = new Usuario();
		ArrayList<Registro> listaRegistro = new ArrayList<>();
		try {
			f = new FileReader(bdd);
			b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				if (contador == 0) {
					usuario.setContrasenaMaestra(cadena);
					contador++;
				} else
				{
					if(contador==1)
					{
						usuario.setFechaDeCreacionContraseña(LocalDate.parse(cadena));
						System.out.println("fecha1: "+cadena);
						System.out.println("fecha:"+ LocalDate.parse(cadena));
						contador++;
					}
					else
					{
						Registro registro = new Registro();
						String[] registros = cadena.split("#");
						registro.setId(Integer.valueOf(registros[0]));
						registro.setTitulo(registros[1]);
						registro.setNombreUsuario(registros[2]);
						registro.setContrasena(registros[3]);
						registro.setURL(registros[4]);
						registro.setInputName(registros[5]);
						listaRegistro.add(registro);
					}
				}
			}

			usuario.setRegistros(listaRegistro);
		} catch (FileNotFoundException e) {
			e.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR ENCRIPTAR" + e.toString());
			e.printStackTrace();
		} finally {
			try {
				b.close();
			} catch (IOException e) {
				System.out.println("ERROR LEERUSUARIO" + e.toString());
				e.printStackTrace();
			}
		}
		return usuario;
	}

	@Override
	public void crearRegistro(Registro registro) {
		try {
			FileWriter escribir = new FileWriter(bdd, true);
			escribir.write("\n" + registro.getId() + "#");
			escribir.write(registro.getTitulo() + "#");
			escribir.write(registro.getNombreUsuario() + "#");
			escribir.write(Utils.encriptar(registro.getContrasena()) + "#");
			escribir.write(registro.getURL() + "#");
			escribir.write(registro.getInputName());
			escribir.close();
		} catch (Exception e) {
			System.out.println("Error al crear registro " + e.toString());
		}

	}
	
	public void crearRegistro2(Registro registro) {
		try {
			FileWriter escribir = new FileWriter(bdd, true);
			escribir.write("\n" + registro.getId() + "#");
			escribir.write(registro.getTitulo() + "#");
			escribir.write(registro.getNombreUsuario() + "#");
			escribir.write(registro.getContrasena() + "#");
			escribir.write(registro.getURL() + "#");
			escribir.write(registro.getInputName());
			escribir.close();
		} catch (Exception e) {
			System.out.println("Error al crear registro " + e.toString());
		}
	}
	
	
	@Override
	public void eliminarRegistro(Registro registr, Usuario usuario) {
		ArrayList<Registro> rgs = (ArrayList<Registro>) usuario.getRegistros();
		System.out.println(usuario.getRegistros().size());
		try {

			for (int i = 0; i < usuario.getRegistros().size(); i++) {
				System.out.println("entro");
				//if (usuario.getRegistros().get(i).getId() == registr.getId()) {
				if(Utils.equalsDeVerdad(usuario.getRegistros().get(i), registr)) {
					System.out.println("debo eliminar:" +rgs.get(i).getNombreUsuario());//
					rgs.remove(i);
				}
			}
			if (rgs.size() > 0) {
				Utils.borrarTxt(bdd);
				crearContrasenaMaestra2(usuario.getContrasenaMaestra(), usuario.getFechaDeCreacionContraseña());
				for (Registro registro2 : rgs) {
					crearRegistro2(registro2);
				}
			} else {
				Utils.borrarTxt(bdd);
				crearContrasenaMaestra2(usuario.getContrasenaMaestra(), usuario.getFechaDeCreacionContraseña());
			}

		} catch (IOException e) {
			System.out.println("Error eliminar registro" + e.toString());
			e.printStackTrace();
		}

	}

	@Override
	public void modificarContrasenaMaestra(String contrasena) {
		Usuario usuario = leerUsuario();

		try {
			usuario.setContrasenaMaestra((Utils.encriptar(contrasena)));
			Utils.borrarTxt(bdd);
			crearContrasenaMaestra(contrasena);
			if (usuario.getRegistros().size() > 0) {
				for (Registro registro : usuario.getRegistros()) {
					crearRegistro2(registro);
				}

			} else {
				Utils.borrarTxt(bdd);
				crearContrasenaMaestra(contrasena);
			}
		} catch (IOException e) {
			System.out.println("Error modificar contraseña" + e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR ENCRIPTAR" + e.toString());
			e.printStackTrace();
		}

	}

	@Override
	public void modificarRegistro(Registro reg) {
		Usuario usuario = leerUsuario();
		try {
			Utils.borrarTxt(bdd);
			for (Registro registro : usuario.getRegistros()) {
				if (reg.getId() == registro.getId()) {
					registro.setContrasena(Utils.encriptar(reg.getContrasena()));
					registro.setNombreUsuario(reg.getTitulo());
					registro.setTitulo(reg.getTitulo());
					registro.setURL(reg.getURL());
					registro.setInputName(reg.getInputName());
				}
			}

			for (Registro registro : usuario.getRegistros()) {
				crearContrasenaMaestra2(usuario.getContrasenaMaestra(),usuario.getFechaDeCreacionContraseña());
				crearRegistro2(registro);
			}

		} catch (IOException e) {
			System.out.println("Error modificar contraseña" + e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR ENCRIPTAR" + e.toString());
			e.printStackTrace();
		}

	}
	
	public File getBdd() {
		return bdd;
	}
	
	public void setBdd(File bdd) {
		this.bdd = bdd;
	}







}

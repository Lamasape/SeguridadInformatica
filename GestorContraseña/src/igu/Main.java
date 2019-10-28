package igu;


import modelo.Registro;
import servicio.impl.ServiceRegistroImpl;

public class Main {

	public Main() {

	}

	public static void main(String[] args) {
		ServiceRegistroImpl service = new ServiceRegistroImpl();
		//			Usuario usuario = service.leerContrasenaMaestra();
//			System.out.println(usuario.getContrasenaMaestra());
//		service.crearContrasenaMaestra("holaMundo");
		Registro registro = new Registro();
		registro.setTitulo("Facebook");
		registro.setNombreUsuario("LauraM");
		registro.setContrasena("contraseña");
		registro.setURL("http://facebook.com");
		service.crearRegistro(registro);
	}

}

package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UtilsContraseñaVerifications {

	/**
	 * Función que sirve para ver si la contraseña cumple con los parametros para que sea segura
	 * @return True si cumple con los parametros, False si no cumple con los parametros
	 */
	public static boolean verificarContrasenia(char[] contra)
	{
		
		if(UtilsContraseñaVerifications.verSiTieneLosDigitosApropiados(contra))
		{
			if(UtilsContraseñaVerifications.verSiTienePorLoMenosUnaMayuscula(contra))
			{
				if(UtilsContraseñaVerifications.verSiTienePorLoMenosUnaMinuscula(contra))
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "La contraseña debe tener por lo menos una minuscula. Vuelva a intentarlo", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "La contraseña debe tener por lo menos una mayuscula. Vuelva a intentarlo", "Error!",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "La contraseña debe tener por lo menos 8 digitos. Vuelva a intentarlo", "Error!",JOptionPane.ERROR_MESSAGE);
		}
			
			
		return false;
	}
	/**
	 * Recibe dos cadenas como parametro y ve si son equivalentes
	 * @param contra1 
	 * @param contra2
	 * @return True si es la misma, false si es diferente
	 */
	public static boolean verSiEsLaMisma(String contra1, String contra2)
	{
		return contra1.equals(contra2);
	}
	/**
	 * Ve si la contraseña tiene por lo menos una mayuscula
	 * @param contrasenia
	 * @return True si tiene por lo menos una mayuscula, false si no tiene ninguna.
	 */
	public static boolean verSiTienePorLoMenosUnaMayuscula(char [] contrasenia)
	{
		for (char c : contrasenia) {
			if (Character.isUpperCase(c))
			{
				System.out.println("lul"+ c);
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Función que mira si una cadena de caracteres tiene por lo menos una minuscula
	 * @param contrasenia
	 * @return True si la cadena tiene por lo menos una minuscula y False si no tiene ninguna.
	 */
	public static boolean verSiTienePorLoMenosUnaMinuscula(char[] contrasenia)
	{
		for (char c : contrasenia) {
			if (Character.isLowerCase(c))
			{
				System.out.println("lol "+c);
				return true;
			}
				
		}
		return false;
	}
	/**
	 * Funcion para ver si un string tiene por lo menos 8 digitos
	 * @param contrasenia
	 * @return True si tiene 8 digitos o mas, false si tiene 7 digitos o menos.
	 */
	public static boolean verSiTieneLosDigitosApropiados(char [] contrasenia)
	{
		if(contrasenia.length >= 8)
		{
			System.out.println("lel"+ contrasenia.length);
			return true;
		}
		return false;
	}
	
}

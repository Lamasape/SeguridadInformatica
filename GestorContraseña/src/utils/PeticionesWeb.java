/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;


public class PeticionesWeb {

    public static void openInBrowser(String url, String email, String inputName){
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
 
            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                String peticionGet = url+'?'+inputName+'='+email;      
                
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + peticionGet);
                } catch (IOException ex) {
                    System.out.println("Error, Navegador por defecto de windows no encontrado");
                }
            }           

        }
    }

    public static void CopiarPortapapeles(String contrasenia) {
        StringSelection textoACopiar = new StringSelection(contrasenia);
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipBoard.setContents(textoACopiar, null);
    }

    public static void BorrarPortapapeles() {
    	try {
    		for(int i=0; i<10;i++)
    		{
	            Runtime.getRuntime().exec("cmd.exe /c \"echo off|clip\"");
	            
    		}
        } catch (IOException ex) {
            System.out.println("Operacion no encontrada");
        }
    }
    
}

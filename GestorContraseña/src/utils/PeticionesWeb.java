/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipBoard.setContents(new StringSelection(""), null);
    }
    
}

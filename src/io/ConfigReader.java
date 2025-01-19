package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

//FUENTE-EXTERNA
//URL: https://github.com/erikcoruna/Rebote
//El código ha sido obtenido de un proyecto del año pasado y posteriormente, se ha adaptado para este proyectos

public class ConfigReader {
	
	public static String nombreProyecto;
	public static boolean baseDatosInicial;
	
	public static void cargarConfiguracion() {
		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(Paths.get("resource/conf/config.properties").toString());
			
			properties.load(input);
			
			nombreProyecto = properties.getProperty("nombre");
			baseDatosInicial = Boolean.valueOf(properties.getProperty("baseDatosInicial"));
				
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

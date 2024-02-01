package uo.cpm.examen.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import uo.cpm.examen.logic.Hotel;

public class FileUtil {
	// ADAPTAR PARA LOS DATOS IMPLEMENTADOS EN EL EXAMEN

	public static void loadFile(String nombreFicheroEntrada, List<Hotel> listaCatalogo) {
		String linea;
		String[] datosArticulo = null;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				listaCatalogo.add(new Hotel(datosArticulo[0], datosArticulo[1], Double.parseDouble(datosArticulo[2]),
						Double.parseDouble(datosArticulo[3])));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}
}

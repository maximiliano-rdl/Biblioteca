package biblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class GestorLibros {

	private static PrintStream out;
	private static Scanner teclado;
	private static String ruta = "libros.txt";

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//Defino la salida
		if (!System.getProperties().get("os.name").equals("Linux") && System.console() != null) // (1) // (2)			
				out = new PrintStream(System.out, true, "Cp850"); // (3)
				teclado = new Scanner(System.in, "Cp850");
		Vector<Libro> vector = new Vector<Libro>(); // (4)
		int i, n;
		Libro dato = null, libro;
		int[] contador = { 0 };
		int opcion, subopcion;
		String[] campos;
		Scanner entrada = null;
		
		//Trato de cargar el archivo que tiene los libros
		try {
			entrada = new Scanner(new FileReader(ruta));
		} catch (FileNotFoundException e) {
			out.println("Ruta de libros incorrecta en " + new File("").getAbsolutePath());
			e.printStackTrace();
		}
		
		//Cargo todos los libros de la entrada en el Vector vector.
		while (entrada.hasNextLine()) {// (5)
			campos = entrada.nextLine().split("\t"); // (6)
			libro = new Libro();
			libro.setISBN(campos[0]);
			libro.setTitulo(campos[1]);
			libro.setAutor(campos[2]);
			libro.setEditorial(campos[3]);
			libro.setEdicion(Integer.parseInt(campos[4]));
			libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
			vector.add(libro);
		}
		entrada.close();// (7)
		libro = new Libro();
		
		//Pregunto por la opcion a elegir
		do {// (8)
			out.println("MENU");// (9)
			out.println("1.- Altas");
			out.println("2.- Consultas");
			out.println("3.- Actualizaciones");
			out.println("4.- Bajas");
			out.println("5.- Ordenar registros");
			out.println("6.- Listar registros");
			out.println("7.- Salir");
			
			do {// (10)
				opcion = leer_entero("Seleccione una opción");// (11)
				if (opcion < 1 // (12)
						|| opcion > 7) // (13)
					out.println("Opción no válida."); // (14)
			} while (opcion < 1 // (15)
					|| opcion > 7); // (16)
			
			out.println(); // (17)
			
			if (vector.isEmpty() // (18)
					&& opcion != 1 // (19)
					&& opcion != 7) { // (20)
				pausar("No hay registros.\n"); // (21)
				continue;
			}
			
			if (opcion < 5) { // (22)
				libro.setISBN(leer_cadena("Ingrese el ISBN del libro\n")); // (23)
				i = vector.indexOf(libro);
				dato = i < 0 ? null : vector.get(i);
				if (dato != null) { // (24)
					out.println(); // (25)
					imprimir(dato, contador);
				}
			}
			
			if (opcion == 1 // (26)
					&& dato != null) // (27)
				out.println("El registro ya existe."); // (28)
			else if (opcion >= 2 // (29)
					&& opcion <= 4 // (30)
					&& dato == null) // (31)
				out.println("\nRegistro no encontrado."); // (32)
			else
				switch (opcion) { // (33)
				case 1: // (34)
					libro.setTitulo(leer_cadena("Ingrese el titulo"));
					libro.setAutor(leer_cadena("Ingrese el autor"));
					libro.setEditorial(leer_cadena("Ingrese el editorial"));
					libro.setEdicion(leer_entero("Ingrese el edicion"));
					libro.setAnno_de_publicacion(leer_entero("Ingrese el anno de publicacion"));
					vector.add(libro);
					libro = new Libro();
					out.println("\nRegistro agregado correctamente.");
					break;
				case 2:
					//out.println("SIN IMPLEMENTAR");
					if(vector.contains(dato))
						out.println(dato.toStringLista());
					else
						out.println("No existe ese libro en la base de libros");
					break;
				case 3: // (35)
					out.println("Men£ de modificaci¢n de campos");
					out.println("1.- titulo");
					out.println("2.- autor");
					out.println("3.- editorial");
					out.println("4.- edicion");
					out.println("5.- anno de publicacion");
					do { // (36)
						subopcion = leer_entero("Seleccione un n£mero de campo a modificar");
						if (subopcion < 1 // (37)
								|| subopcion > 5) // (38)
							out.println("Opci¢n no v lida."); // (39)
					} while (subopcion < 1 // (40)
							|| subopcion > 5); // (41)
					switch (subopcion) { // (42)
					case 1: // (43)
						dato.setTitulo(leer_cadena("Ingrese el nuevo titulo"));
						break;
					case 2: // (44)
						dato.setAutor(leer_cadena("Ingrese el nuevo autor"));
						break;
					case 3: // (45)
						dato.setEditorial(leer_cadena("Ingrese el nuevo editorial"));
						break;
					case 4: // (46)
						dato.setEdicion(leer_entero("Ingrese el nuevo edicion"));
						break;
					case 5: // (47)
						dato.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));
						break;
					}
					out.println("\nRegistro actualizado correctamente."); // (48)
					break;
				case 4: // (49)
					vector.remove(dato);
					out.println("Registro borrado correctamente.");
					break;
				case 5: // (50)
					Collections.sort(vector);
					out.println("Registros ordenados correctamente.");
					break;
				case 6: // (51)
					n = vector.size();
					contador[0] = 0;
					for (i = 0; i < n; i++)// (52)
						imprimir(vector.get(i), contador);
					out.println("Total de registros: " + contador[0] + ".");// (53)
					break;
				}
			if (opcion < 7 // (54)
					&& opcion >= 1) // (55)
				pausar(""); // (56)
		} while (opcion != 7); // (57)
		PrintStream salida = null;
		try {
			salida = new PrintStream(ruta);
		} catch (FileNotFoundException e) {
			System.out.println("Ruta de salida incorrecta");
			e.printStackTrace();
		} // (58)
		n = vector.size();
		for (i = 0; i < n; i++) // (59)
			imprimirEnArchivo(vector.get(i), salida); // (60)
		salida.close();// (61)
	} // (62)

	private static void imprimir(Libro dato, int[] contador) {
		contador[0]++;
	}

	private static void imprimirEnArchivo(Libro libro, PrintStream salida) {
		salida.println(libro.toString());
	}

	private static String leer_cadena(String string) {
		out.println(string);
		return teclado.nextLine();
	}

	private static void pausar(String string) {
		out.println(string);
		//teclado.nextLine();
	}

	private static int leer_entero(String string) {
		out.println(string);
		return Integer.parseInt(teclado.nextLine());
	}

}

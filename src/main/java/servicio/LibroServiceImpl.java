//package servicio;
//
//import modelo.Libro;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LibroServiceImpl implements LibroService {
//// antes de codificar los metodos, armo una estructura para guardar los libros
//// que ire agregando para que luego pueda utilizar los metodos que cree
//
//    private final String NOMBRE_ARCHIVO = "biblioteca.txt"; //creo un archivo para que se guarden los datos ingresados o modificados y no se pierdan al cerrar la app
//    public LibroServiceImpl() { //en estas lineas se crea el archivo en el caso de que no exista o menciona si ya esta creado
//        File archivo = new File(NOMBRE_ARCHIVO);
//        try {
//            if (archivo.createNewFile()) {
//                System.out.println("Archivo creado: " + archivo.getName());
//            } else {
//                System.out.println("El archivo ya existe.");
//            }
//        } catch (IOException e) {
//            System.out.println("Error al crear el archivo: " + e.getMessage());
//        }
//    }
//
//    private List<Libro> libros = new ArrayList<>(); //se creaa la lista interna de esta implementacion de interface para poder utilizarla
//
//    @Override
//    public void agregarLibro(Libro libro) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
//            // Guardamos el libro como una línea de texto separada por ;
//            String linea = libro.getTitulo() + ";" + libro.getAutor() + ";" + libro.getAnioPublicacion() + ";" + libro.getIsbn();
//            writer.write(linea);
//            writer.newLine(); // Salto de línea para el próximo libro
//        } catch (IOException e) {
//            System.out.println("Error al guardar el libro: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public List<Libro> buscarLibroPorTitulo(String tituloBuscado) {
//        List<Libro> resultados = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] partes = linea.split(";");
//                if (partes.length != 4) continue;
//
//                String titulo = partes[0];
//                if (titulo.toLowerCase().contains(tituloBuscado.toLowerCase())) {
//                    String autor = partes[1];
//                    int anio = Integer.parseInt(partes[2]);
//                    String isbn = partes[3];
//                    resultados.add(new Libro(0, titulo, autor, anio, isbn));
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo: " + e.getMessage());
//        }
//
//        return resultados;
//    }
//
//    @Override
//    public List <Libro> buscarLibroPorAutor(String autorBuscado) {
//        List<Libro> resultados = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] partes = linea.split(";");
//                if (partes.length != 4) continue;
//
//                String autor = partes[1];
//                if (autor.toLowerCase().contains(autorBuscado.toLowerCase())) {
//                    String titulo = partes[0];
//                    int anio = Integer.parseInt(partes[2]);
//                    String isbn = partes[3];
//                    resultados.add(new Libro(0, titulo, autor, anio, isbn));
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo: " + e.getMessage());
//        }
//
//        return resultados;
//    }
//
//    @Override
//    public Libro buscarLibroPorIsbn(String isbnBuscado) {
//        List<Libro> resultados = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] partes = linea.split(";");
//                if (partes.length != 4) continue;
//
//                String isbn = partes[3];
//                if (isbn.equals(isbnBuscado)) {
//                    String titulo = partes[0];
//                    String autor = partes[1];
//                    int anio = Integer.parseInt(partes[2]);
//                    resultados.add(new Libro(0, titulo, autor, anio, isbn));
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo: " + e.getMessage());
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public void listarLibros() {
//        int contadorLineas = 0;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
//            String linea;
//            // Acá leerá el archivo línea por línea
//
//            while ((linea = br.readLine()) !=null) {
//                contadorLineas++;
//                String[] partes = linea.split(";"); //divide la linea en partes
//
//                if (partes.length !=4){
//                    System.out.println("Linea mal formateada: " + linea);
//                    continue; //salta esa linea
//                }
//                System.out.println("Titulo: " + partes[0]);
//                System.out.println("Autor: " + partes[1]);
//                System.out.println("Año: " + partes[2]);
//                System.out.println("ISBN: " + partes[3]);
//                System.out.println("----------------------------");
//
//            }
//            if (contadorLineas == 0){
//                System.out.println("El archivo esta vacio");
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo: " + e.getMessage());
//        }
//
//    }
//
//    @Override
//    public void modificarLibro(String isbn, Libro libroModificado) {
//        List<Libro> libros = obtenerTodosLosLibros(); //metodo auxiliar que lee todos los libros del archivo
//        boolean encontrado = false;
//
//        for (int i = 0; i < libros.size(); i++) {
//            if (libros.get(i).getIsbn().equals(isbn)) { //el libro posicionado en la i tiene el mismo
//                // isbn que me dieron?
//                libros.set(i, libroModificado); // si se encontro se reemplaza el libro viejo por el
//                //nuevo en la misma posicion
//                encontrado = true;
//                break;
//            }
//        }
//        if (encontrado) {
//            guardarLibrosEnArchivo(libros); //reescribe y se guardan todos los libros incluido el nuveo
//            System.out.println("Libro modificado con exito.");
//        } else {
//            System.out.println("No se encontró un libro con ese ISBN.");
//        }
//    }
//    private List<Libro> obtenerTodosLosLibros() { //es necesario para leer el archivo y convertir
//        //cada linea en un objeto para trabajar en ellos
//        List<Libro> libros = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] partes = linea.split(";");
//                if (partes.length == 4) {
//                    String titulo = partes[0];
//                    String autor = partes[1];
//                    int anio = Integer.parseInt(partes[2]);
//                    String isbn = partes[3];
//                    libros.add(new Libro(0, titulo, autor, anio, isbn));
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo: " + e.getMessage());
//        }
//        return libros;
//    }
//    private void guardarLibrosEnArchivo(List<Libro> libros) { //es necesario para guardar la lista actualizada
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
//            for (Libro libro : libros) {
//                String linea = libro.getTitulo() + ";" + libro.getAutor() + ";" + libro.getAnioPublicacion() + ";" + libro.getIsbn();
//                bw.write(linea);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error al guardar el archivo: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void eliminarLibroPorIsbn(String isbn) {
//
//    }
//}

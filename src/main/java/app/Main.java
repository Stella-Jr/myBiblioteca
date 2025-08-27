package app;

import dao.LibroDAO;
import dao.PrestamoDAO;
import modelo.Libro;
import dao.PersonaDAO;
import modelo.Persona;
import modelo.Prestamo;
import modelo.PrestamoDetalle;
import util.ISBNUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // se crean los objetos para poder manejarlos
        LibroDAO libroDAO = new LibroDAO();
        PersonaDAO personaDAO = new PersonaDAO();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = Integer.parseInt(scanner.nextLine());
            salir = ejecutarOpcion(opcion, scanner, libroDAO, personaDAO, prestamoDAO);
        }

        System.out.println("¡Hasta luego!");
    }

    private static void mostrarMenu() {
        System.out.print("""
                *** Sistema de Inventario de la Biblioteca ***

                Menu:
                -LIBROS-
                1. Listar libros
                2. Agregar libro
                3. Buscar libro por Titulo
                4. Buscar libro por Autor
                5. Buscar libro por ISBN
                6. Modificar libro
                7. Eliminar libro
                -PERSONA-
                8. Agregar Persona
                9. Listar personas
                10. Modificar persona
                11. Eliminar persona
                -PRESTAMO-
                12. Agregar prestamo
                13. Listar prestamos
                14. Buscar prestamo por Persona
                15. Buscar prestamo por Libro
                16. Buscar prestamo por Libro No Devuelto
                17. Modificar prestamo
                18. Eliminar prestamo
                19. Salir

                Elige una opcion:\s""");
    }

    private static boolean ejecutarOpcion(int opcion, Scanner scanner, LibroDAO libroDAO, PersonaDAO personaDAO, PrestamoDAO prestamoDAO) {
        switch (opcion) {
            case 1 -> listarLibros(libroDAO);
            case 2 -> agregarLibro(scanner, libroDAO);
            case 3 -> buscarLibroPorTitulo(scanner, libroDAO);
            case 4 -> buscarLibroPorAutor(scanner, libroDAO);
            case 5 -> buscarLibroPorIsbn(scanner, libroDAO);
            case 6 -> modificarLibro(scanner, libroDAO);
            case 7 -> eliminarLibroPorId(scanner, libroDAO);
            case 8 -> agregarPersona (scanner, personaDAO);
            case 9 -> listarPersonas(personaDAO);
            case 10 -> modificarPersona (scanner, personaDAO);
            case 11 -> eliminarPersona (scanner, personaDAO);
            case 12 -> agregarPrestamo(scanner, prestamoDAO);
            case 13 -> listarPrestamos(prestamoDAO);
            case 14 -> buscarPrestamoPorPersona(scanner, prestamoDAO);
            case 15 -> buscarPrestamoPorLibro (scanner, prestamoDAO);
            case 16 -> buscarPrestamoPorLibroNoDevuelto (prestamoDAO);
            case 17 -> modificarPrestamo (scanner, prestamoDAO);
            case 18 -> eliminarPrestamo (scanner, prestamoDAO);
            case 19 -> { return true; }
            default -> System.out.println("Opcion invalida");
        }
        return false;
    }

    private static void listarLibros(LibroDAO libroDAO) {
        List<Libro> lista = libroDAO.obtenerLibros();
        if (lista.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : lista) {
                System.out.println(libro);
            }
        }
    }

    private static void agregarLibro(Scanner scanner, LibroDAO libroDAO) {
        System.out.print("Ingrese título: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese autor: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese año de publicación: ");
        int anio = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese ISBN (6 dígitos): ");
        String isbn = scanner.nextLine();

        if (!ISBNUtil.esIsbnValido(isbn)) {
            System.out.println("ISBN inválido. Debe tener exactamente 6 dígitos numéricos.");
            return;
        }

        Libro libro = new Libro(0, titulo, autor, anio, isbn);
        libroDAO.agregarLibro(libro);
    }

    private static void buscarLibroPorTitulo(Scanner scanner, LibroDAO libroDAO) {
        System.out.print("Ingrese título a buscar: ");
        String titulo = scanner.nextLine();

        List<Libro> resultados = libroDAO.buscarLibroPorTitulo(titulo);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            for (Libro libro : resultados) {
                System.out.println(libro);
            }
        }
    }

    private static void buscarLibroPorAutor(Scanner scanner, LibroDAO libroDAO) {
        System.out.print("Ingrese autor a buscar: ");
        String autor = scanner.nextLine();

        List<Libro> resultados = libroDAO.buscarLibroPorAutor(autor);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese autor.");
        } else {
            for (Libro libro : resultados) {
                System.out.println(libro);
            }
        }
    }

    private static void buscarLibroPorIsbn(Scanner scanner, LibroDAO libroDAO) {
        System.out.print("Ingrese ISBN a buscar: ");
        String isbn = scanner.nextLine();

        Libro libro = libroDAO.buscarLibroPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró ningún libro con ese ISBN.");
        } else {
            System.out.println(libro);
        }
    }

    private static void modificarLibro(Scanner scanner, LibroDAO libroDAO) {
        System.out.print("Ingrese ID del libro a modificar: ");
        int libroId = Integer.parseInt(scanner.nextLine());

        System.out.print("Nuevo título: ");
        String titulo = scanner.nextLine();

        System.out.print("Nuevo autor: ");
        String autor = scanner.nextLine();

        System.out.print("Nuevo año: ");
        int anio = Integer.parseInt(scanner.nextLine());

        System.out.print("Nuevo ISBN (6 dígitos): ");
        String isbn = scanner.nextLine();

        if (!ISBNUtil.esIsbnValido(isbn)) {
            System.out.println("ISBN inválido. Debe tener exactamente 6 dígitos numéricos.");
            return;
        }

        Libro libro = new Libro(libroId, titulo, autor, anio, isbn);
        libroDAO.actualizarLibro(libro);
    }

    private static void eliminarLibroPorId(Scanner scanner, LibroDAO libroDAO) {
        System.out.print("Ingrese ID del libro a eliminar: ");
        int libroId = Integer.parseInt(scanner.nextLine());

        libroDAO.eliminarLibroPorId(libroId);
    }

    private static void agregarPersona (Scanner scanner, PersonaDAO personaDAO) {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese direccion de email : ");
        String email = scanner.nextLine();

        Persona persona = new Persona(0,nombre, apellido, dni, email );
        personaDAO.agregarPersona(persona);
        //llamás al método agregarPersona de tu PersonaDAO
        //Dentro de PersonaDAO.agregarPersona, ya esta la verificación del DNI único
        //Si el DNI ya existe, el DAO imprime un mensaje de error y no inserta la persona
        //Si el DNI es único, el DAO realiza la inserción y muestra que la persona fue agregada
    }

    private  static void listarPersonas (PersonaDAO personaDAO) {
        List<Persona> lista = personaDAO.obtenerPersonas();
        if (lista.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (Persona persona : lista) {
                System.out.println(persona);
            }
        }
    }

    private static void modificarPersona (Scanner scanner, PersonaDAO personaDAO) {
        System.out.print("Ingrese DNI de la persona a modificar: ");
        int dni = Integer.parseInt(scanner.nextLine());

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();

        Persona persona= new Persona(0, nombre, apellido, dni, email);
        personaDAO.actualizarPersona(persona);
    }

    private static void eliminarPersona (Scanner scanner, PersonaDAO personaDAO) {
        System.out.print("Ingrese DNI de la persona a eliminar: ");
        int dni = Integer.parseInt(scanner.nextLine());

        personaDAO.eliminarPersona(dni);
    }

    private static void agregarPrestamo (Scanner scanner, PrestamoDAO prestamoDAO){
        System.out.println("Ingrese el ID del libro a pedir: ");
        int libroId = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el ID de la persona que va a querer hacer el prestamo: ");
        int personaId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese la fecha de inicio (año-mes-dia): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Ingrese la fecha de fin (año-mes-dia): ");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

        Prestamo prestamo = new Prestamo(0, libroId, personaId, fechaInicio, fechaFin, null);
        prestamoDAO.agregarPrestamo(prestamo);

    }

    private static void listarPrestamos (PrestamoDAO prestamoDAO) {
        List<Prestamo> lista = prestamoDAO.obtenerPrestamo();
        if (lista.isEmpty()) {
            System.out.println("No hay prestamos registrados.");
        } else {
            for (Prestamo prestamo : lista) {
                System.out.println(prestamo);
            }
        }
    }

    private static void buscarPrestamoPorPersona (Scanner scanner, PrestamoDAO prestamoDAO) {
        System.out.print("Ingrese el ID de la persona a buscar: ");
        int personaId = Integer.parseInt(scanner.nextLine());

        List<Prestamo> resultados = prestamoDAO.buscarPrestamoPorPersona(personaId);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron prestamos con ese ID de persona.");
        } else {
            for (Prestamo prestamo : resultados) {
                System.out.println(prestamo);
            }
        }
    }

    private static void buscarPrestamoPorLibro (Scanner scanner, PrestamoDAO prestamoDAO) {
        System.out.print("Ingrese el ID del libro a buscar: ");
        int libroId = Integer.parseInt(scanner.nextLine());

        List<Prestamo> resultados = prestamoDAO.buscarPrestamoPorLibro(libroId);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron prestamos con ese ID de libro.");
        } else {
            for (Prestamo prestamo : resultados) {
                System.out.println(prestamo);
            }
        }
    }

    private static void buscarPrestamoPorLibroNoDevuelto (PrestamoDAO prestamoDAO) {
        List<PrestamoDetalle> lista = prestamoDAO.buscarPrestamoLibroNoDevuelto();

        if (lista.isEmpty()) {
            System.out.println("No se encontraron prestamos de libros no devueltos.");
        } else {
            for (PrestamoDetalle prestamoDetalle: lista) {
                System.out.println(prestamoDetalle);
            }
        }
    }

    private static void modificarPrestamo (Scanner scanner, PrestamoDAO prestamoDAO) {
        System.out.print("Ingrese ID del prestamo a modificar: ");
        int prestamoId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese la nueva fecha de inicio (año-mes-dia): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Ingrese la nueva fecha de fin (año-mes-dia): ");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

        Prestamo prestamo= new Prestamo(prestamoId, 0, 0,fechaInicio, fechaFin, null);
        prestamoDAO.actualizarPrestamo(prestamo);
    }

    private static void eliminarPrestamo (Scanner scanner, PrestamoDAO prestamoDAO) {
        System.out.print("Ingrese ID del prestamo a eliminar: ");
        int prestamoId = Integer.parseInt(scanner.nextLine());

        boolean eliminado = prestamoDAO.eliminarPrestamo(prestamoId);
        if (eliminado) {
            System.out.println("Préstamo eliminado correctamente.");
        } else {
            System.out.println("No se encontró un préstamo con ese ID.");
        }
    }
}
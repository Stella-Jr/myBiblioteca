//package servicio;
//
//import modelo.Libro;
//
//import java.util.List;
//
//public interface LibroService {
//
//    //creando la firma de los metodos
//
//    void agregarLibro(Libro libro); //agrega un libro a la coleccion, recibe un objeto libro como parametro
//    List<Libro> buscarLibroPorTitulo(String titulo);
//    List<Libro> buscarLibroPorAutor(String autor);
//    Libro buscarLibroPorIsbn(String isbn); //se usa string y no long ya que puede utilizar guiones y ceros
//    void listarLibros(); // para mostrar todos
//    void modificarLibro(String isbn, Libro libroModificado); //el segundo parametro contiene los nuevos datos
//    void eliminarLibroPorIsbn(String isbn); //elimina un libro por isnb como identificador unico
//
//}

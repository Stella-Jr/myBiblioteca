package modelo;

/**
 * Clase libro
 * se representa el modelo de un libro en el sistema
 * Contiene los atributos básicos:
 * - id (clave primaria en la DB)
 * - título
 * - autor
 * - año de publicación
 * - isbn
* Funciona como una "caja de datos" para mover información entre el programa y la base de datos.*/

public class Libro {

    //creando los objetos con PRIVATE para que solo se acceda mediante metodos
    private int libroId;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String isbn;

    // Constructor con todos los atributos
    //Permite crear un objeto Libro dándole todos sus datos al momento de instanciarlo
    //se usa this. para diferenciar el atributo de la clase del parámetro
    public Libro(int libroId, String titulo, String autor, int anioPublicacion, String isbn) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.isbn = isbn;
    }

    // Getters y Setters permiten leer y modificar los atributos
    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // El toString se agrega después de los getters/setters, porque sirve para representar el objeto en texto (generalmente para mostrar en consola o depurar).
    @Override
    public String toString() {
        return  "\n" +
                "* ID: " + libroId + "\n" +
                "* Titulo: " + titulo + "\n" +
                "* Autor: " + autor + "\n" +
                "* Año de Publicación: " + anioPublicacion + "\n" +
                "* ISBN: " + isbn + "\n";
    }
}
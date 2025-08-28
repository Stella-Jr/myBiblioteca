package modelo;

/**
 * Clase Persona
 * Se encarga de representar a una persona dentro del sistema.
 * - Contiene los datos principales: ID, nombre, apellido, DNI y email.
 * - Permite crear objetos Persona con todos sus datos desde el constructor.
 * - Ofrece getters y setters para consultar y modificar los atributos.
 * - Incluye un método toString para mostrar los datos de forma legible.
 * - Implementa equals y hashCode para poder comparar personas y usarlas en colecciones.*/

public class Persona {
    //creando los objetos con PRIVATE para que solo se acceda mediante metodos
    private int personaId;
    private String nombre;
    private String apellido;
    private int dni;
    private String email;

    //creando el contructor para los objetos
    //Permite crear un objeto Persona dándole todos sus datos al momento de instanciarlo
    //Usás this. para diferenciar el atributo de la clase del parámetro
    public Persona (int personaId, String nombre, String apellido, int dni, String email) {
        this.personaId = personaId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;

    }
    //creacion de los getters y setters para la modificacion y obtencion de los atributos

    public int getPersonaID() {
        return personaId;
    }

    public void setPersonaID(int personaID) {
        this.personaId = personaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "\n" +
                "* ID: " + personaId + "\n" +
                "* Nombre: " + nombre + "\n" +
                "* Apellido: " + apellido + "\n" +
                "* Dni: " + dni + "\n" +
                "* Email: " + email + "\n";
    }

    // hashCode genera un número único para cada objeto.
    // Se usa para buscar y comparar objetos más rápido.
    @Override
    public int hashCode() {
        return Integer.hashCode(personaId); // Usa el ID único
    }

    // equals: compara si dos objetos Persona son iguales (por ejemplo, mismo DNI)
    // hashCode: genera un número para cada objeto, usado en búsquedas rápidas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si es el mismo objeto
        if (o == null || getClass() != o.getClass()) return false; // Si es null o de otra clase
        Persona persona = (Persona) o;
        return personaId == persona.personaId; // Comparar por ID único
    }
}


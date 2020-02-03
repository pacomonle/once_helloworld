package es.taes;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String miMensaje = "Mi hola mundo";
        System.out.println(miMensaje);

        Persona persona = new Persona();
        persona.setEdad(45);
        persona.setNombre("Alfonso");
        System.out.println(persona);
    }
}

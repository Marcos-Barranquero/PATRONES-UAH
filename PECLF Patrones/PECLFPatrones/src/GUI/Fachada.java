package GUI;

import Adapters.AdapterFechaDevolucion;
import Adapters.AdapterHoraDevolucion;
import Bridge.Logger;
import ClasesBase.Libro;
import ClasesBase.Persona;
import ClasesBase.Portatil;
import ClasesBase.Usuario;
import Comando.LibroExecuter;
import Comando.PortatilExecuter;
import Comando.PrestamoLibrosExecuter;
import Comando.PrestamoPortatilExecuter;
import Comando.UsuarioExecuter;
import Factory.AbstractFactory;
import Factory.LibroFactory;
import Factory.PortatilFactory;
import Iterator.ColeccionLibros;
import Strategy.OrdenarLibros;
import Strategy.OrdenarLibrosAutor;
import Strategy.OrdenarLibrosEdicion;
import Strategy.OrdenarLibrosEstado;
import Strategy.OrdenarLibrosID;
import Strategy.OrdenarLibrosISBN;
import Strategy.OrdenarLibrosTitulo;
import Factory.UsuarioFactory;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * Clase singleton que hace de intermediario entre la interfaz de usuario y el
 * resto de la aplicación
 *
 * @author Edu
 */
public class Fachada {

    final int MAXIMO_LIBROS = 4;

    // Instacia unica de la clase Fachada
    private static Fachada instanciaUnica;

    private Logger logger;

    // Invocadores de comandos
    private final UsuarioExecuter invocadorUsuarios;
    private final PortatilExecuter invocadorPortatiles;
    private final LibroExecuter invocadorLibros;
    private final PrestamoLibrosExecuter invocadorPrestamoLibros;
    private final PrestamoPortatilExecuter invocadorPrestamoPortatil;

    // Fábricas de objetos
    private final AbstractFactory usuarioFactory;
    private final AbstractFactory portatilFactory;
    private final AbstractFactory libroFactory;

    // OrdenarLibros
    private OrdenarLibros ordLibros;

    // Enum que representan los niveles de log
    private enum LogLevel {
        INFO,
        WARNING,
        ERROR
    }

    /**
     * Constructor privado
     */
    private Fachada() {
        this.logger = Logger.Info();
        this.invocadorUsuarios = new UsuarioExecuter();
        this.invocadorPortatiles = new PortatilExecuter();
        this.invocadorLibros = new LibroExecuter();
        this.invocadorPrestamoLibros = new PrestamoLibrosExecuter();
        this.invocadorPrestamoPortatil = new PrestamoPortatilExecuter();
        this.usuarioFactory = UsuarioFactory.getInstancia();
        this.portatilFactory = PortatilFactory.getInstancia();
        this.libroFactory = LibroFactory.getInstancia();
    }

    /**
     * Devuelve la instancia única de la clase singleton Fachada
     *
     * @return instancia única de la clase
     */
    public static Fachada getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Fachada();
        }

        return instanciaUnica;
    }

    /**
     * Cambia el nivel de logeo del logger
     *
     * @param nivel nivel al que se desea configurar el nivel de logeo
     */
    private void setLogLevel(LogLevel nivel) {
        switch (nivel) {
            case INFO:
                this.logger = Logger.Info();
                break;

            case WARNING:
                this.logger = Logger.Warning();
                break;

            case ERROR:
                this.logger = Logger.Error();
                break;
        }
    }

    /**
     * Devuelve TRUE si el intento de login es correcto, es decir, si existe en
     * la base de datos.
     *
     * @param usuario -> DNI del usuario
     * @param passw -> contraseña del usuario
     * @return
     */
    public boolean hacerLogin(String usuario, String passw) {
        boolean loginOk = false;
        if (invocadorUsuarios.loginCorrecto(usuario, passw)) {
            setLogLevel(LogLevel.INFO);
            logger.log("Nuevo inicio de sesion de usuario con DNI " + usuario);
            loginOk = true;
        }
        return loginOk;
    }

    /**
     * Método encargado de instanciar el nuevo usuario registrado y llamar al
     * método de inserción en la base de datos
     *
     * @param usuario -> nombre del usuario
     * @param passw -> contraseña del usuario
     * @param email -> email del usuario
     * @param dni -> DNI del usuario
     * @param edadTxt -> edad del usuario
     * @param telefonoTxt -> teléfono del usuario
     */
    public void registroUsuario(String usuario, String passw, String email, String dni, String edadTxt, String telefonoTxt) {
        int edad = Integer.valueOf(edadTxt);
        int telefono = Integer.valueOf(telefonoTxt);

        ArrayList<Object> atributosUsuario = new ArrayList<>();
        atributosUsuario.add(usuario);
        atributosUsuario.add(email);
        atributosUsuario.add(passw);
        atributosUsuario.add(dni);
        atributosUsuario.add(edad);
        atributosUsuario.add(telefono);
        atributosUsuario.add(false);
        atributosUsuario.add(false);
        atributosUsuario.add(null);
        atributosUsuario.add(false);

        Usuario u = (Usuario) usuarioFactory.crear(atributosUsuario);
        invocadorUsuarios.insertarUsuario(u);

        setLogLevel(LogLevel.INFO);
        logger.log("Nuevo usuario registrado con DNI " + u.getDni());
    }

    /**
     * Intermediario entre la interfaz y la base de datos para recuperar un
     * usuario en función de su DNI
     *
     * @param dni -> DNI del usuario que se quiere recuperar
     * @return -> objeto de tipo Persona que representa al usuario recuperado
     */
    public Persona recuperarUsuario(String dni) {
        return invocadorUsuarios.recuperar(dni);
    }

    /**
     * Comprueba si un usuario es administrador en función de su DNI
     *
     * @param dni -> DNI del usuario a comprobar
     * @return -> true si es admin, false si no lo es
     */
    public boolean esAdmin(String dni) {
        return invocadorUsuarios.esAdmin(dni);
    }

    /**
     * Elimina un usuario de la base de datos en función de su DNI
     *
     * @param dni -> DNI del usuario a borrar
     */
    public void eliminarUsuario(String dni) {
        invocadorUsuarios.eliminarUsuario(dni);

        setLogLevel(LogLevel.INFO);
        logger.log("Se ha eliminado el usuario con DNI " + dni);
    }

    /**
     * Crea un libro instanciándolo con la fábrica y lo inserta en la base de
     * datos
     *
     * @param nombre -> título del libro
     * @param isbn -> ISBN del libro
     * @param autor -> autor del libro
     * @param edicion -> edición del libro
     */
    public void crearLibro(String nombre, String isbn, String autor, int edicion) {
        int idLibro = invocadorLibros.getMaxIdLibro() + 1;
        ArrayList<Object> atributosLibro = new ArrayList<>();
        atributosLibro.add(idLibro);
        atributosLibro.add(edicion);
        atributosLibro.add(nombre);
        atributosLibro.add(isbn);
        atributosLibro.add(autor);
        atributosLibro.add(false);
        Libro aInsertar = (Libro) libroFactory.crear(atributosLibro);
        invocadorLibros.insertar(aInsertar);

        setLogLevel(LogLevel.INFO);
        logger.log("Nuevo libro registrado con ID " + aInsertar.getIdLibro());
    }

    /**
     * Crea un portátil instanciándolo con la fábrica y lo inserta en la base de
     * datos
     *
     * @param tipo -> tipo de portátil que se va a crear
     */
    public void crearPortatil(String tipo) {
        ArrayList<Object> atributosPortatil = new ArrayList<>();
        atributosPortatil.add(tipo);
        int idPortatil = invocadorPortatiles.getMaxIdPortatil() + 1;
        Portatil aInsertar = (Portatil) portatilFactory.crear(atributosPortatil);
        aInsertar.setIdPortatil(idPortatil);
        invocadorPortatiles.insertar(aInsertar);

        setLogLevel(LogLevel.INFO);
        logger.log("Nuevo portátil registrado con ID " + aInsertar.getIdPortatil());
    }

    /**
     * Recupera todos los libros de la base de datos y los almacena en una
     * colección de libros
     *
     * @return -> colección de libros con todos los libros de la base de datos
     */
    public ColeccionLibros recuperarLibros() {
        ColeccionLibros libros = new ColeccionLibros();
        libros.setLibros(invocadorLibros.recuperarLibros());
        return libros;
    }

    /**
     * Dada una colección de libros, los ordena de una forma u otra dependiendo
     * del parámetro forma
     *
     * @param libros -> colección de libros a ordenar
     * @param forma -> forma de ordenación: 0 = por ID, 1 = por título, 2 = por
     * ISBN, 3 = por autor, 4 = por edición, 5 = por estado
     * @return -> colección de libros ordenada por el atributo especificado
     */
    public ColeccionLibros ordenarLibros(ColeccionLibros libros, int forma) {
        switch (forma) {
            case 0:
                this.ordLibros = new OrdenarLibrosID();
                break;
            case 1:
                this.ordLibros = new OrdenarLibrosTitulo();
                break;
            case 2:
                this.ordLibros = new OrdenarLibrosISBN();
                break;
            case 3:
                this.ordLibros = new OrdenarLibrosAutor();
                break;
            case 4:
                this.ordLibros = new OrdenarLibrosEdicion();
                break;
            case 5:
                this.ordLibros = new OrdenarLibrosEstado();
                break;
        }

        return ordLibros.ordenar(libros);
    }

    /**
     * Dada una colección de libros, los ordena del reverso de una forma u otra
     * dependiendo del parámetro forma
     *
     * @param libros -> colección de libros a ordenar
     * @param forma -> forma de ordenación: 0 = por ID, 1 = por título, 2 = por
     * ISBN, 3 = por autor, 4 = por edición, 5 = por estado
     * @return -> colección de libros ordenada por el atributo especificado
     */
    public ColeccionLibros ordenarLibrosReverso(ColeccionLibros libros, int forma) {
        switch (forma) {
            case 0:
                this.ordLibros = new OrdenarLibrosID();
                break;
            case 1:
                this.ordLibros = new OrdenarLibrosTitulo();
                break;
            case 2:
                this.ordLibros = new OrdenarLibrosISBN();
                break;
            case 3:
                this.ordLibros = new OrdenarLibrosAutor();
                break;
            case 4:
                this.ordLibros = new OrdenarLibrosEdicion();
                break;
            case 5:
                this.ordLibros = new OrdenarLibrosEstado();
                break;
        }

        return ordLibros.reverso(libros);
    }

    /**
     * Método para logear eventos desde la interfaz de usuario
     *
     * @param mensaje -> mensaje a logear
     */
    public void logInfoExterna(String mensaje) {
        setLogLevel(LogLevel.INFO);
        logger.log(mensaje);
    }

    /**
     * Devuelve el número de portátiles asus disponibles en la base de datos
     *
     * @return -> número de portátiles asus disponibles en la base de datos
     */
    public int consultarNumeroPortatilesAsusDisponibles() {
        return invocadorPortatiles.getNumeroDisponibleAsus();
    }

    /**
     * Devuelve el número de portátiles apple disponibles en la base de datos
     *
     * @return -> número de portátiles apple disponibles en la base de datos
     */
    public int consultarNumeroPortatilesAppleDisponibles() {
        return invocadorPortatiles.getNumeroDisponibleApple();
    }

    /**
     * Devuelve una colección con los libros prestados a un usuario en concreto
     *
     * @param dni -> DNI del usuario para el que comprobar los préstamos
     * @return -> colección de libros con los libros prestados a ese usuario
     */
    public ColeccionLibros consultarLibrosPrestados(String dni) {
        return invocadorPrestamoLibros.recuperarLibrosPrestados(dni);
    }

    /**
     * Método que intenta prestar un portátil a un cliente de la biblioteca.
     * Hace una serie de comprobaciones primero para ver si el préstamo es
     * válido
     *
     * @param dni -> DNI del usuario al que hacer el préstamo
     * @param idPortatil -> ID del portátil que se va a prestar
     * @return -> true si todo va bien, false si el préstamo falla
     */
    public boolean prestarPortatil(String dni, int idPortatil) {
        boolean exito = true;
        Usuario recogido = (Usuario) invocadorUsuarios.recuperar(dni);
        Portatil aPrestar = invocadorPortatiles.recuperar(idPortatil);

        // Primero verifico que no está castigado
        if (recogido.estaCastigado()) {
            exito = false;
        }
        // Verifico también que no haya metido un portátil ya prestado...
        if (aPrestar.isPrestado()) {
            exito = false;
        }
        // Ni que el usuario tenga ya un portátil
        if (invocadorPrestamoPortatil.tienePortatil(dni)) {
            exito = false;
        }
        if (exito) {
            // Si no hay ningún problema, creo el préstamo
            int horas = LocalTime.now().getHour();
            int minutos = LocalTime.now().getMinute();

            AdapterHoraDevolucion adapterHora = new AdapterHoraDevolucion(horas, minutos);

            invocadorPrestamoPortatil.crearPrestamoPortatil(idPortatil, adapterHora.getTimeDevolucion(), dni);

            setLogLevel(LogLevel.INFO);
            logger.log("Se ha prestado el portátil con ID " + aPrestar.getIdPortatil() + " al usuario con DNI " + recogido.getDni());
        }
        return exito;
    }

    /**
     * Se intenta devolver el portátil que haya tomado prestado un usuario en
     * base a su DNI. Se comprueba también si debe recibir un castigo por
     * devolver fuera de plazo.
     *
     * @param dni -> DNI del usuario que devuelve el portátil
     * @return -> Número de días de castigo al usuario, 0 si no hay castigo
     */
    public int devolverPortatil(String dni) {
        boolean exito = true;
        int diasCastigado = 0;

        // Verifico que si que tiene portátil
        if (!invocadorPrestamoPortatil.tienePortatil(dni)) {
            exito = false;
        }

        if (exito) {
            int idPortatil = invocadorPrestamoPortatil.getIdPortatilPrestado(dni);
            // Verifico si debe ser castigado
            LocalTime horaMaximaDevolucion = invocadorPrestamoPortatil.getHoraMaximaDevolucion(dni).toLocalTime();

            int diferenciaHoras = (int) Duration.between(horaMaximaDevolucion, LocalTime.now()).toHours();

            // Si se ha pasado, castigo
            if (diferenciaHoras > 0) {
                diasCastigado = diferenciaHoras;
                Date fechaFinCastigo = Date.valueOf(LocalDate.now().plusDays(diferenciaHoras));

                invocadorUsuarios.castigarUsuario(dni, fechaFinCastigo);
                setLogLevel(LogLevel.WARNING);
                logger.log("Usuario con DNI " + dni + " ha devuelto el portátil con ID " + idPortatil + " fuera del plazo acordado. Ha sido castigado " + diferenciaHoras + " días.");
            } else {
                setLogLevel(LogLevel.INFO);
                logger.log("Usuario con DNI " + dni + " ha devuelto el portátil con ID " + idPortatil + " dentro del plazo acordado.");
            }

            invocadorPrestamoPortatil.devolverPortatil(idPortatil, dni);
        }
        return diasCastigado;
    }

    /**
     * Recupera el resto de la información de un portátil a partir de su ID
     *
     * @param idPortatil -> ID del portátil sobre el que se quiere recuperar
     * información
     * @return -> Objeto Portatil con toda la información referente al portátil
     */
    public Portatil recuperarPortatil(int idPortatil) {
        return invocadorPortatiles.recuperar(idPortatil);
    }

    /**
     * Recupera de la base de datos el portátil que se le ha prestado a un
     * usuario
     *
     * @param dni -> DNI del usuario al que se le ha prestado un portátil
     * @return -> Objeto Portatil que almacena toda la información del portátil
     * prestado
     */
    public Portatil getPortatilPrestado(String dni) {
        return invocadorPortatiles.getPortatilPrestado(dni);
    }

    /**
     * Recupera de la base de datos el DNI asociado al portátil prestado
     *
     * @param idPortatil -> ID de portátil
     * @return -> DNI del usuario al que se le ha prestado ese portátil
     */
    public String recuperarDniDePortatilPrestadoConId(int idPortatil) {
        return invocadorPrestamoPortatil.getDniPortatilPrestado(idPortatil);
    }

    /* Libros */
    /**
     * Método que intenta prestar un libro a un cliente de la biblioteca. Hace
     * una serie de comprobaciones primero para ver si el préstamo es válido
     *
     * @param dni -> DNI del usuario al que hacer el préstamo
     * @param idLibro -> ID del libro que se va a prestar
     * @return -> true si todo va bien, false si el préstamo falla
     */
    public boolean prestarLibro(String dni, int idLibro) {
        boolean exito = true;
        Usuario recogido = (Usuario) invocadorUsuarios.recuperar(dni);
        Libro aPrestar = invocadorLibros.recuperar(idLibro);

        // Primero verifico que no está castigado
        if (recogido.estaCastigado()) {
            exito = false;
        }
        // Verifico también que no haya metido un libro ya prestado...
        if (aPrestar.estaPrestado()) {
            exito = false;
        }

        // Ni que el usuario tenga ya el máximo de libros
        if (invocadorLibros.getLibrosPrestados(dni).size() >= MAXIMO_LIBROS) {
            exito = false;
        }
        if (exito) {
            // Si no hay ningún problema, creo el préstamo
            int dia = LocalDate.now().getDayOfMonth();
            int mes = LocalDate.now().getMonthValue();
            int ano = LocalDate.now().getYear();

            AdapterFechaDevolucion adapterDia = new AdapterFechaDevolucion(dia, mes, ano);

            invocadorPrestamoLibros.crearPrestamoLibro(idLibro, adapterDia.getDateDevolucion(), dni);

            setLogLevel(LogLevel.INFO);
            logger.log("Se ha prestado el libro con ID " + aPrestar.getIdLibro() + " al usuario con DNI " + recogido.getDni());
        }
        return exito;
    }

    /**
     *
     * Se intenta devolver un libro a partir de los parámetros que recibe la
     * función. Actualiza la base de datos.
     *
     * @param idLibro ID del libro que se desea devolver
     * @param dni DNI del usuario que realiza la devolución
     * @return número de días a los que ha sido castigado. Será 0 si la
     * devolución se hace dentro del plazo establecido al pedir el libro.
     */
    public int devolverLibro(int idLibro, String dni) {
        int castigo = 0;

        boolean correcto = true;

        // Verifico que el libro se encuentre prestado...
        if (!invocadorLibros.recuperar(idLibro).estaPrestado()) {
            correcto = false;
        }

        if (correcto) {
            // Verifico si debe ser castigado
            LocalDate fechaMaximaDevolucion = invocadorPrestamoLibros.getFechaMaximaDevolucion(idLibro, dni).toLocalDate();
            int diferenciaDias = Period.between(fechaMaximaDevolucion, LocalDate.now()).getDays();

            // Si se ha pasado, castigo
            if (diferenciaDias > 0) {
                LocalDate fechaFinCastigo;

                // Si encima ya estaba castigado, voy a añadir eso a la fecha de castigo
                if (((Usuario) invocadorUsuarios.recuperar(dni)).estaCastigado()) {
                    fechaFinCastigo = ((Usuario) invocadorUsuarios.recuperar(dni)).getFecha().toLocalDate();

                } else {
                    fechaFinCastigo = Date.valueOf(LocalDate.now()).toLocalDate();
                }

                Date fechaCastigo = Date.valueOf(fechaFinCastigo.plusDays(diferenciaDias));
                invocadorUsuarios.castigarUsuario(dni, fechaCastigo);

                setLogLevel(LogLevel.WARNING);
                logger.log("Usuario con DNI " + dni + " ha devuelto el libro con ID " + idLibro + " fuera del plazo acordado. Ha sido castigado " + diferenciaDias + " días.");

                castigo = diferenciaDias;
            } else {
                setLogLevel(LogLevel.INFO);
                logger.log("Usuario con DNI " + dni + " ha devuelto el libro con ID " + idLibro + " dentro del plazo acordado.");
            }

            invocadorPrestamoLibros.devolverLibro(idLibro, dni);

        }
        return castigo;
    }

    /**
     * Recupera todos los datos de un libro en base a su ID
     *
     * @param idLibro -> ID del libro sobre el que se quiere recibir información
     * @return -> Objeto Libro con toda la información sobre ese libro
     */
    public Libro recuperarLibro(int idLibro) {
        return invocadorLibros.recuperar(idLibro);
    }

    /**
     * Devuelve un ArrayList con todos los libros prestados a un usuario
     *
     * @param dni -> DNI del usuario
     * @return -> ArrayList de tipo libro con los libros prestados a ese usuario
     */
    public ArrayList<Libro> getLibrosPrestados(String dni) {
        return invocadorLibros.getLibrosPrestados(dni);
    }
}

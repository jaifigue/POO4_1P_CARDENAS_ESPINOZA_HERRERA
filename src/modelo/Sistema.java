package src.modelo;
import src.util.*;
import src.enums.*;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Clase controladora principal del sistema. Coordina la autenticación de
 * usuarios, la presentación de menús según el rol, las reglas de compra
 * de entradas y kits, la actualización de disponibilidad y la persistencia
 * de los datos en archivos de texto.
 * 
 * Concentra en memoria las cuatro listas principales del sistema (usuarios,
 * partidos, kits y compras) y actúa como único punto de coordinación entre
 * las clases de dominio (Partido, Kit, Compra, Usuario) y la clase de
 * utilidad ManejoArchivos.
 *
 * @author Jair Cárdenas
 * @version 1.0
 */
public class Sistema {
    /** Lista de todos los usuarios (aficionados y organizadores) registrados en el sistema. */
    private ArrayList<Usuario> usuarios;
    /** Lista de todos los partidos cargados en el sistema. */
    private ArrayList<Partido> partidos;
    /** Lista de todos los kits disponibles en el sistema. */
    private ArrayList<Kit> kits;
    /** Lista de todas las compras registradas en el sistema. */
    private ArrayList<Compra> compras;

    /**
     * Crea una nueva instancia del sistema inicializando en vacío las
     * cuatro listas que mantendrán los datos en memoria durante la
     * ejecución del programa.
     */
    public Sistema(){
        this.usuarios = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.compras = new ArrayList<>();
    }

    /**
     * Registra una compra en el sistema: la agrega a la lista en memoria
     * y escribe la línea correspondiente en compras.txt en modo append,
     * de modo que memoria y archivo queden siempre sincronizados en un
     * único punto del código.
     *
     * @param c la compra a registrar; si es null, no se realiza ninguna acción
     */
    public void registrarCompra(Compra c){
        if (c != null){
            compras.add(c);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaTexto = formato.format(c.getFechaCompra());
            String linea = c.getCodigo()+"|"+c.getTipo()
        +"|"+c.getCodigoReferencia()+"|"+fechaTexto+"|"+c.getCantidad()+"|"+c.getValorPagado()+"|"+c.getCodigoAficionado();
            ManejoArchivos.escribirArchivo("compras.txt", linea);
        }
    }

    /**
     * Valida las credenciales ingresadas comparándolas contra la lista de
     * usuarios cargados en memoria. Recorre la lista sin importar si cada
     * usuario es Aficionado u Organizador, ya que ambos conviven en la
     * misma lista de tipo Usuario.
     *
     * @param usuario nombre de usuario ingresado
     * @param clave contraseña ingresada
     * @return el objeto Usuario correspondiente si las credenciales son
     *         correctas, o null si no hay coincidencia
     */
    public Usuario validarCredenciales(String usuario, String clave){
        for (Usuario s : usuarios){
            if((s.getUsuario().equals(usuario)) && (s.getContrasena().equals(clave))){
                return s;
            }
        }
        return null;
    }

    /**
     * Gestiona el inicio de sesión del usuario: solicita usuario y
     * contraseña por consola, valida las credenciales y, de ser correctas,
     * pide una confirmación adicional de identidad (celular para el
     * aficionado, empresa para el organizador) antes de mostrar el menú
     * correspondiente a su rol.
     */
    public void iniciarSesion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("==== INICIO DE SESION ====");
        System.out.print("Usuario: ");
        String usuarioIngresado = sc.nextLine();

        System.out.print("Contraseña: ");
        String claveIngresada = sc.nextLine();

        Usuario usuarioAutenticado = validarCredenciales(usuarioIngresado, claveIngresada);

        if (usuarioAutenticado == null){
            System.out.println("Credenciales incorrectas");
        }
        else {
            System.out.println("Usuario autenticado correctamente.");

            if (usuarioAutenticado instanceof Aficionado){
                System.out.println("Rol detectado: AFICIONADO");
                Aficionado aficionado = (Aficionado) usuarioAutenticado;

                System.out.println("Bienvenida, "+aficionado.getNombres()+" "+aficionado.getApellidos());
                System.out.println("Celular registrado: "+aficionado.getCelular());

                System.out.print("¿Este número celular es correcto? (S/N): ");
                String respuesta = sc.nextLine();

                if (respuesta.equalsIgnoreCase("S")){
                    System.out.println("Identidad confirmada.");
                    mostrarMenu(usuarioAutenticado);
                }

                else {
                    System.out.println("Verificación fallida.");
                    System.out.println("Por motivos de seguridad se cerrará la sesión.\n");
                    System.out.println("Saliendo del sistema...");
                    return;}
            }
            else if (usuarioAutenticado instanceof Organizador){
                System.out.println("Rol detectado: ORGANIZADOR");
                Organizador organizador = (Organizador) usuarioAutenticado;

                System.out.println("Bienvenido, "+organizador.getNombres()+" "+organizador.getApellidos());
                System.out.println("Empresa asignada: "+organizador.getEmpresa());
                
                System.out.print("¿Esta empresa es correcta? (S/N): ");
                String respuesta = sc.nextLine();

                if (respuesta.equalsIgnoreCase("S")){
                    System.out.println("Identidad confirmada.");
                    mostrarMenu(usuarioAutenticado);
                }

                else {
                    System.out.println("Verificación fallida.");
                    System.out.println("Por motivos de seguridad se cerrará la sesión.\n");
                    System.out.println("Saliendo del sistema...");
                    return;
                }

            }
        }

    }

    /**
     * Muestra el menú correspondiente según el tipo real del usuario
     * autenticado (Aficionado u Organizador) y gestiona el ciclo de
     * opciones hasta que el usuario decide salir. Para el aficionado
     * permite consultar partidos, comprar entradas, comprar kits y
     * consultar sus propias compras; para el organizador permite
     * consultar todas las compras y generar el reporte de ventas.
     *
     * @param usuario el usuario ya autenticado (Aficionado u Organizador)
     */
    public void mostrarMenu(Usuario usuario){
        Scanner sc = new Scanner(System.in);
        if (usuario instanceof Aficionado){
            Aficionado aficionado = (Aficionado) usuario;
            System.out.println("Menú de aficionado:");

            int opcion = 0;

            while (opcion != 5){
                System.out.println("1. Consultar partidos"+"\n2. Comprar entrada"+"\n3. Comprar kit"+
                    "\n4. Consultar entradas"+"\n5. Salir");
                
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();

                if(opcion ==1){
                    aficionado.consultarPartidos(partidos);  
                }
                else if (opcion == 2){
                    System.out.print("Ingrese el código del partido: ");
                    String codigo = sc.next();

                    Partido partidoEncontrado = buscarPartidoPorCodigo(codigo);

                    if(partidoEncontrado == null){
                        System.out.println("Partido no encontrado.");
                    }
                    else {
                        System.out.println("Partido encontrado.");
                        System.out.println(partidoEncontrado);

                        System.out.println("Elija la zona:");
                        ZonaEntrada zonaElegida;

                        int stockDisponible = 0;

                        System.out.println("a. General"+"\nb. Preferencial"+"\nc. VIP");

                        String opcionZona = sc.next();
                        if (opcionZona.equalsIgnoreCase("a")){
                            zonaElegida = ZonaEntrada.GENERAL;
                            stockDisponible = partidoEncontrado.getGeneral();
                        }
                        else if (opcionZona.equalsIgnoreCase("b")){
                            zonaElegida = ZonaEntrada.PREFERENCIAL;
                            stockDisponible = partidoEncontrado.getPreferencial();
                        }
                        else if(opcionZona.equalsIgnoreCase("c")){
                            zonaElegida = ZonaEntrada.VIP;
                            stockDisponible = partidoEncontrado.getVip();
                        }
                        else {
                            System.out.println("Zona no válida.");
                            continue;
                        }

                        if (stockDisponible<=0){
                            System.out.println("No hay entradas disponibles.");
                            continue;
                        }

                        System.out.print("Ingrese la cantidad de entradas: ");
                        int entradas = sc.nextInt();

                        if(entradas<=0){
                            System.out.println("Cantidad inválida");
                            continue;
                        }
                        if(entradas>stockDisponible){
                            System.out.println("No hay suficiente stock");
                            continue;
                        }
                        else{
                            System.out.println("Cantidad válida");

                            double precioZona = obtenerPrecioPorZona(zonaElegida);
                            double total = precioZona * entradas;

                            System.out.println("Precio por entrada: $" + precioZona);
                            System.out.println("Total a pagar: $" + total);

                            System.out.print("Ingrese el número de tarjeta: ");
                            String tarjeta = sc.next();

                            System.out.println("Procesando pago..."+"\nPago exitoso.");

                            comprar(aficionado, partidoEncontrado, zonaElegida, entradas);
                        }
                    }
                }
                else if (opcion == 3){
                    System.out.println("==== KITS DISPONIBLES ====");

                    if (kits==null || kits.isEmpty()){
                        System.out.println("No hay kits disponibles.");
                        continue;
                    }
                    else{
                        for (Kit k : kits){
                            System.out.println(k);}
                         System.out.print("Ingrese el código del kit: ");
                        String codigo = sc.next();

                        Kit kitEncontrado = buscarKitPorCodigo(codigo);
                            
                        if(kitEncontrado==null){
                            System.out.println("Kit no encontrado");
                            continue;
                        }
                        else{
                            System.out.println("Kit encontrado");
                            if(kitEncontrado.getDisponibles()<=0){
                            System.out.println("No hay disponibilidad para este kit");
                            continue;}
                            else{
                                System.out.println("Kit disponible");
                                System.out.println(kitEncontrado);}

                                System.out.print("Ingrese la cantidad de kits que desea: ");
                                int cantidad = sc.nextInt();

                                if (cantidad<=0){
                                    System.out.println("Cantidad no válida.");
                                    continue;
                                }
                                else if(cantidad>kitEncontrado.getDisponibles()){
                                    System.out.println("No hay suficientes kits disponibles");
                                    continue;
                                }
                                else {
                                    System.out.println("Cantidad válida");
                                    double total = kitEncontrado.getPrecio() * cantidad;

                                    System.out.println("Precio del kit: $"+kitEncontrado.getPrecio());
                                    System.out.println("Total a pagar: $"+total);

                                    System.out.print("Ingrese el número de la tarjeta: ");
                                    String tarjeta = sc.next();

                                    System.out.println("Procesando pago..."+"\nPago exitoso.");

                                    comprar(aficionado, kitEncontrado, cantidad);
                                }
                            }
                        }
                    }
                else if(opcion == 4){
                    aficionado.consultarEntradas(compras);
                }
                else if(opcion == 5){
                    System.out.println("Saliendo del menú de aficionados...");
                }
                else {
                    System.out.println("Opción no válida.");
                }
            }
        }
        
        else if (usuario instanceof Organizador){
            Organizador organizador = (Organizador) usuario;
            System.out.println("Menú de organizador");

            int opcion = 0;

            while (opcion != 3){
                System.out.println("1. Consultar entradas"+"\n2. Generar reporte"+"\n3. Salir");

                System.out.println("Seleccione una opción: ");
                opcion = sc.nextInt();

                if (opcion == 1){
                    organizador.consultarEntradas(compras);
                }
                else if (opcion == 2){
                    ReporteVentas reporte = new ReporteVentas();
                    reporte.generarResumen(compras);
                    System.out.println(reporte);

                    notificar(organizador, reporte);
                }
                else if(opcion == 3){
                    System.out.println("Saliendo del menú de organizadores...");
                }
                else {
                    System.out.println("Opción no válida.");
                }

                
            }
        }
    }
    //NUEVO ZONA ENTRADA EN EL CONSTRUCTOR

    /**
     * Ejecuta la compra de una entrada individual para un partido: crea el
     * objeto Compra, la registra en memoria y archivo, actualiza el stock
     * de la zona correspondiente directamente sobre el objeto Partido en
     * memoria, y notifica al aficionado. Asume que la validación de stock
     * y cantidad ya se realizó previamente en mostrarMenu().
     *
     * @param a el aficionado que realiza la compra
     * @param p el partido para el cual se compra la entrada
     * @param z la zona de entrada elegida (GENERAL, PREFERENCIAL o VIP)
     * @param cantidad número de entradas a comprar
     * @return la compra recién creada y registrada
     */
    public Compra comprar(Aficionado a, Partido p, ZonaEntrada z, int cantidad){

        double precioZona = obtenerPrecioPorZona(z);
        double total = precioZona * cantidad;

        Compra compraNueva = new Compra(TipoCompra.ENTRADA, p.getCodigo(), 
        new Date(), cantidad, total, a.getCodigoUnico());

        registrarCompra(compraNueva);

        System.out.println("Compra registrada correctamente.");
        System.out.println(compraNueva);

        int nuevoStock;
        if (z == ZonaEntrada.GENERAL){
            nuevoStock = p.getGeneral() - cantidad;
            p.setGeneral(nuevoStock);

        }
        else if(z == ZonaEntrada.PREFERENCIAL){
            nuevoStock = p.getPreferencial() - cantidad;
            p.setPreferencial(nuevoStock);
        }
        else if(z == ZonaEntrada.VIP){
            nuevoStock = p.getVip() - cantidad;
            p.setVip(nuevoStock);
        }
        System.out.println("Disponibilidad actualizada en el sistema.");

        notificar(a, compraNueva, z);

        return compraNueva;
    }

    /**
     * Ejecuta la compra de uno o más kits: crea el objeto Compra, la
     * registra en memoria y archivo, actualiza la disponibilidad del kit
     * directamente sobre el objeto Kit en memoria, y notifica al
     * aficionado. Es la versión sobrecargada de comprar() para kits,
     * distinta de la que recibe un Partido y una ZonaEntrada.
     *
     * @param a el aficionado que realiza la compra
     * @param k el kit que se está comprando
     * @param cantidad número de kits a comprar
     * @return la compra recién creada y registrada
     */
    public Compra comprar(Aficionado a, Kit k, int cantidad){
    
        double total = k.getPrecio() * cantidad;

        Compra compraNueva = new Compra(TipoCompra.KIT, k.getCodigo(), 
        new Date(), cantidad, total, a.getCodigoUnico());

        registrarCompra(compraNueva);

        System.out.println("Compra registrada correctamente.");
        System.out.println(compraNueva);

        int nuevoDisponible = k.getDisponibles() - cantidad;
        k.setDisponibles(nuevoDisponible);
    
        System.out.println("Disponibilidad del kit actualizada en el sistema");

        notificar(a, compraNueva, k);
    
        return compraNueva;
    }

    /**
     * Simula el envío de un correo de confirmación al aficionado tras la
     * compra de una entrada individual, mostrando por consola los datos
     * del partido, la zona y el monto pagado. Es una de las tres versiones
     * sobrecargadas de notificar().
     *
     * @param a el aficionado que recibe la notificación
     * @param c la compra realizada
     * @param z la zona de entrada comprada
     */
    public void notificar(Aficionado a, Compra c, ZonaEntrada z){
        System.out.println("==== SIMULACION DE CORREO ELECTRONICO ====");
        System.out.println("De: correoSistema@mundial.com");
        System.out.println("Para: "+a.getCorreo());
        System.out.println("Asunto: Compra de entrada realizada.");
        System.out.println("Estimado/a: "+a.getNombres()+" "+a.getApellidos()+",");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(c.getFechaCompra());
        System.out.println("Su compra ha sido registrada exitosamente con el código "+c.getCodigo()+" el día "+fechaTexto+
        ".");
        Partido partido = buscarPartidoPorCodigo(c.getCodigoReferencia());
        if (partido != null) {
            System.out.println("Partido: " + partido.getLocal() + " vs " + partido.getVisitante());
        }
        System.out.println("Código del partido: "+c.getCodigoReferencia());
        System.out.println("Zona: "+z);
        System.out.println("Cantidad: "+c.getCantidad());
        System.out.println("Valor pagado: $"+c.getValorPagado());
        System.out.println("Gracias por adquirir sus entradas para el Mundial. Recuerde conservar el código de compra para futuras consultas.");
        EnviadorCorreo.enviarCorreo(a.getCorreo(), "Compra de entrada realizada","Su compra ha sido registrada con el código " + c.getCodigo() + ". Valor pagado: $" + c.getValorPagado());
    }

    /**
     * Simula el envío de un correo de confirmación al aficionado tras la
     * compra de un kit, mostrando por consola los datos del kit y el monto
     * pagado. Es la versión sobrecargada de notificar() específica para
     * compras de tipo kit.
     *
     * @param a el aficionado que recibe la notificación
     * @param c la compra realizada
     * @param k el kit comprado
     */
    public void notificar(Aficionado a, Compra c, Kit k){
        System.out.println("==== SIMULACION DE CORREO ELECTRONICO ====");
        System.out.println("De: correoSistema@mundial.com");
        System.out.println("Para: "+a.getCorreo());
        System.out.println("Asunto: Compra de kit realizada.");
        System.out.println("Estimado/a: "+a.getNombres()+" "+a.getApellidos()+",");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(c.getFechaCompra());
        System.out.println("Su compra ha sido registrada exitosamente con el código "+c.getCodigo()+" el día "+fechaTexto+
        ".");
        System.out.println("Código del kit: "+k.getCodigo());
        System.out.println("Nombre del kit: "+k.getNombre());
        System.out.println("Cantidad: "+c.getCantidad());
        System.out.println("Valor pagado: $"+c.getValorPagado());
        System.out.println("Gracias por adquirir sus kit/s para el Mundial. Recuerde conservar el código de compra para futuras consultas.");
        EnviadorCorreo.enviarCorreo(a.getCorreo(), "Compra de kit realizada","Su compra del kit " + k.getNombre() + " ha sido registrada con el código " + c.getCodigo() + ".");
    }

    /**
     * Simula el envío de un correo al organizador con el resumen del
     * reporte de ventas generado, mostrando por consola las estadísticas
     * totales del sistema. Es la versión sobrecargada de notificar()
     * específica para organizadores.
     *
     * @param o el organizador que recibe la notificación
    //  * @param r el reporte de ventas generado
     */
    public void notificar(Organizador o, ReporteVentas r){
        System.out.println("==== SIMULACION DE CORREO ELECTRONICO ====");
        System.out.println("De: correoSistema@mundial.com");
        System.out.println("Para: "+o.getCorreo());
        System.out.println("Asunto: Reporte de compras del sistema.");
        System.out.println("Estimado/a: "+o.getNombres()+" "+o.getApellidos());
        System.out.println("Se ha generado el reporte de compras del sistema.");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(new Date());
        System.out.println("Fecha de generación del reporte: " + fechaTexto);
        System.out.println("Total de compras registradas: "+r.getTotalCompras());
        System.out.println("Total de compras de entradas individuales: "+r.getTotalEntradas());
        System.out.println("Total de compras de kits: "+r.getTotalKits());
        System.out.println("Monto total recaudado: "+r.getMontoTotal());
        EnviadorCorreo.enviarCorreo(o.getCorreo(), "Reporte de ventas generado","Total de compras: " + r.getTotalCompras() + ". Monto total recaudado: $" + r.getMontoTotal());
    }

    /**
     * Carga todos los datos del sistema desde los archivos de texto,
     * respetando un orden específico de dependencias: primero los
     * partidos, porque los kits hacen referencia a partidos por código y
     * necesitan que ya existan como objetos en memoria para poder
     * resolverlos; las compras se cargan al final porque hacen referencia
     * a partidos o kits ya existentes.
     */
    public void cargarDatos(){
        cargarPartidos();
        cargarUsuarios();
        cargarKits();
        cargarCompras();
    }


    //Métodos auxiliar nuevo que no está en el UML 

    /**
     * Busca un partido en la lista en memoria a partir de su código.
     *
     * @param codigoPartido código del partido a buscar
     * @return el objeto Partido encontrado, o null si no existe ninguno
     *         con ese código
     */
    private Partido buscarPartidoPorCodigo(String codigoPartido){
        for(Partido p : partidos){
            if (codigoPartido.equalsIgnoreCase(p.getCodigo())){
                return p;
            }
        }
        return null;
    }

    /**
     * Traduce una zona de entrada a su precio fijo correspondiente,
     * centralizando la tabla de precios en un único método.
     *
     * @param zona la zona de entrada (GENERAL, PREFERENCIAL o VIP)
     * @return el precio unitario de esa zona; 0.00 si la zona no coincide
     *         con ninguna de las conocidas
     */
    private double obtenerPrecioPorZona(ZonaEntrada zona){
        if (zona == ZonaEntrada.GENERAL){
            return 45.00;
        }
        else if(zona == ZonaEntrada.PREFERENCIAL){
            return 85.00;
        }
        else if(zona == ZonaEntrada.VIP){
            return 150.00;
        }
        else {
            return 0.00;
        }
    }

    /**
     * Busca un kit en la lista en memoria a partir de su código.
     *
     * @param codigo código del kit a buscar
     * @return el objeto Kit encontrado, o null si no existe ninguno con
     *         ese código
     */
    private Kit buscarKitPorCodigo(String codigo){
        for (Kit k : kits){
            if(k.getCodigo().equalsIgnoreCase(codigo)){
                return k;
            }
        }
        return null;
    }


    /**
     * Carga los usuarios del sistema combinando la información general de
     * usuarios.txt con los datos específicos de aficionados.txt u
     * organizadores.txt según el rol de cada usuario, construyendo el
     * objeto Aficionado u Organizador correspondiente.
     */
    private void cargarUsuarios(){
        ArrayList<String> lineasUsuarios = ManejoArchivos.leerFichero("usuarios.txt");
        ArrayList<String> lineasAficionados = ManejoArchivos.leerFichero("aficionados.txt");
        ArrayList<String> lineasOrganizadores = ManejoArchivos.leerFichero("organizadores.txt");

        for (int i = 1; i < lineasUsuarios.size(); i++){
            String linea = lineasUsuarios.get(i);
            String[] datos = linea.split("\\|");

            String codigoUnico = datos[0];
            String cedula = datos[1];
            String nombres = datos[2];
            String apellidos = datos[3];
            String usuario = datos[4];
            String contrasena = datos[5];
            String correo = datos[6];
            String rol = datos[7];

        if (rol.equals("A")) {

            for (int j = 1; j < lineasAficionados.size(); j++) {
                String lineaAf = lineasAficionados.get(j);
                String[] datosAf = lineaAf.split("\\|");

                if (datosAf[0].equals(codigoUnico)) {
                    String celular = datosAf[4];
                    String paisFavorito = datosAf[5];

                    Aficionado aficionado = new Aficionado(
                        codigoUnico, cedula, nombres, apellidos,
                        usuario, contrasena, correo,
                        celular, paisFavorito
                    );

                    usuarios.add(aficionado);
                }
            }

        } else if (rol.equals("O")) {

            for (int j = 1; j < lineasOrganizadores.size(); j++) {
                String lineaOrg = lineasOrganizadores.get(j);
                String[] datosOrg = lineaOrg.split("\\|");

                if (datosOrg[0].equals(codigoUnico)) {
                    String empresa = datosOrg[4];
                    String cargo = datosOrg[5];

                    Organizador organizador = new Organizador(
                        codigoUnico, cedula, nombres, apellidos,
                        usuario, contrasena, correo,
                        empresa, cargo
                    );

                    usuarios.add(organizador);
                }
            }
        }
    }

    System.out.println("Usuarios cargados: " + usuarios.size());
}

    /**
     * Carga los partidos del sistema desde partidos.txt, convirtiendo cada
     * línea de texto en un objeto Partido. Se ejecuta primero dentro de
     * cargarDatos() porque los kits dependen de que los partidos ya
     * existan en memoria.
     */
    private void cargarPartidos(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try{
            ArrayList<String> lineasPartidos = ManejoArchivos.leerFichero("partidos.txt");
            for (int i = 1; i < lineasPartidos.size() ; i++){
                String linea = lineasPartidos.get(i);

                String[] datos = linea.split("\\|");
            
                String codigo = datos[0];
                String local = datos[1];
                String visitante = datos[2];
                Date fecha = formato.parse(datos[3]);
                String estadio = datos[4];
                String ciudad = datos[5];
                int capacidad = Integer.parseInt(datos[6]);
                int general = Integer.parseInt(datos[7]);
                int preferencial = Integer.parseInt(datos[8]);
                int vip = Integer.parseInt(datos[9]);
                String fase = datos[10];

                Partido p = new Partido(codigo, local, visitante, fecha, estadio, ciudad, capacidad, general, preferencial, vip, fase);
                partidos.add(p);
            }
            System.out.println("Partidos cargados: " + partidos.size());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Carga los kits del sistema desde kits.txt. Cada kit hace referencia
     * a uno o más partidos por código (separados por coma), los cuales se
     * traducen a objetos Partido reales mediante buscarPartidoPorCodigo(),
     * por lo que este método debe ejecutarse después de cargarPartidos().
     */
    private void cargarKits(){
        ArrayList<String> lineasKits = ManejoArchivos.leerFichero("kits.txt");
        for (int i = 1; i < lineasKits.size() ; i++){
            String linea = lineasKits.get(i);
            String[] datos = linea.split("\\|");

            String codigo = datos[0];
            String nombre = datos [1];
            String descripcion = datos[2];

            String[] codigosPartidos = datos[3].split(",");
            ArrayList<Partido> partidosDelKit = new ArrayList<>();
            for (int j = 0; j < codigosPartidos.length; j++) {
                String codigoPartido = codigosPartidos[j];

                Partido partido = buscarPartidoPorCodigo(codigoPartido);

                if (partido != null) {
                    partidosDelKit.add(partido);
                }
            }
            
            double precio = Double.parseDouble(datos[4]);
            int disponibles = Integer.parseInt(datos[5]);

            Kit k = new Kit(codigo, nombre, descripcion, partidosDelKit, precio, disponibles);
            kits.add(k);
        }
        System.out.println("Kits cargados: "+kits.size());
    }

    /**
     * Carga las compras previamente registradas desde compras.txt,
     * reconstruyendo cada objeto Compra con el segundo constructor (el que
     * recibe un código ya existente), lo cual también actualiza el
     * contador estático de Compra al valor más alto encontrado, evitando
     * códigos duplicados en ejecuciones futuras. Se ejecuta al final
     * dentro de cargarDatos() porque depende de que partidos y kits ya
     * existan en memoria.
     */
    private void cargarCompras(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ArrayList<String> lineasCompras = ManejoArchivos.leerFichero("compras.txt");
            for (int i = 1; i < lineasCompras.size() ; i++){
                String linea = lineasCompras.get(i);
                if(linea.trim().isEmpty()){
                    continue;
                }
                String[] datos = linea.split("\\|");

                String codigoCompra = datos[0];
                String tipo = datos[1];
                TipoCompra tipoCompra = TipoCompra.valueOf(tipo);
                String codigoReferencia = datos[2];
                Date fecha = formato.parse(datos[3]);
                int cantidad = Integer.parseInt(datos[4]);
                double valorPagado = Double.parseDouble(datos[5]);
                String codigoAficionado = datos[6];

                Compra c = new Compra(codigoCompra, tipoCompra, codigoReferencia, fecha, cantidad, valorPagado, codigoAficionado);
                compras.add(c);
            }
            System.out.println("Compras cargadas: "+compras.size());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /** @return la lista de usuarios cargados en el sistema */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /** @param usuarios nueva lista de usuarios a asignar al sistema */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /** @return la lista de partidos cargados en el sistema */
    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    /** @param partidos nueva lista de partidos a asignar al sistema */
    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    /** @return la lista de kits cargados en el sistema */
    public ArrayList<Kit> getKits() {
        return kits;
    }

    /** @param kits nueva lista de kits a asignar al sistema */
    public void setKits(ArrayList<Kit> kits) {
        this.kits = kits;
    }

    /** @return la lista de compras registradas en el sistema */
    public ArrayList<Compra> getCompras() {
        return compras;
    }

    /** @param compras nueva lista de compras a asignar al sistema */
    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    
    
}
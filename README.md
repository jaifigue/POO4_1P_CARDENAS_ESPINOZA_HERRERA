# POO4_1P_CARDENAS_ESPINOZA_HERRERA

Proyecto de Programacion Orientada a Objetos (Primer Parcial).

## Resumen

Este proyecto implementa un sistema de venta y consulta de entradas para partidos de futbol, incluyendo la compra de kits de partidos.

La aplicacion:

- autentica usuarios por rol (aficionado u organizador).
- permite operaciones distintas segun el tipo de usuario.
- controla disponibilidad de entradas por zonas y de kits.
- registra compras en memoria y en archivos de texto.
- genera un reporte de ventas para organizadores.

## Problema a resolver

El sistema resuelve la gestion de venta de entradas para un evento deportivo, con las siguientes necesidades:

- manejar distintos roles con permisos diferentes.
- mantener la disponibilidad actualizada en tiempo real.
- registrar compras de forma persistente.
- permitir consultas de compras.
- consolidar informacion de ventas para toma de decisiones.

## Estructura de src

- `src/Main.java`: punto de entrada del programa.
- `src/enums/`: enumeraciones del dominio (rol, tipo de compra, estado, zona).
- `src/modelo/`: clases del negocio (usuarios, partidos, kits, compras, sistema y reporte).
- `src/util/ManejoArchivos.java`: lectura y escritura de archivos de texto.

## Clases y archivos

### 1) Main

Archivo: `src/Main.java`

Responsabilidad:

- iniciar la aplicacion.
- cargar los datos.
- comenzar el flujo de inicio de sesion.

Metodo principal:

- `main(String[] args)`: crea `Sistema`, llama `cargarDatos()` e `iniciarSesion()`.

### 2) Enumeraciones (enums)

#### EstadoCompra

Archivo: `src/enums/EstadoCompra.java`

Valores:

- `REGISTRADA`
- `ANULADA`

Uso:

- define el estado valido de una compra.

#### RolUsuario

Archivo: `src/enums/RolUsuario.java`

Valores:

- `A` (Aficionado)
- `O` (Organizador)

Uso:

- identifica el rol del usuario en el sistema.

#### TipoCompra

Archivo: `src/enums/TipoCompra.java`

Valores:

- `ENTRADA`
- `KIT`

Uso:

- diferencia compras individuales de compras por kit.

#### ZonaEntrada

Archivo: `src/enums/ZonaEntrada.java`

Valores:

- `GENERAL`
- `PREFERENCIAL`
- `VIP`

Uso:

- representa la zona seleccionada en una compra de entrada.

### 3) Modelo de dominio

#### Usuario (abstracta)

Archivo: `src/modelo/Usuario.java`

Responsabilidad:

- definir atributos y comportamiento comun para todos los usuarios.

Metodos:

- `consultarEntradas(ArrayList<Compra> compras)` (abstracto, implementado por subclases).
- getters y setters de atributos comunes.

#### Aficionado

Archivo: `src/modelo/Aficionado.java`

Responsabilidad:

- usuario que consulta partidos, compra entradas/kits y revisa sus compras.

Metodos:

- `consultarEntradas(ArrayList<Compra> compras)`: muestra solo compras del aficionado.
- `consultarPartidos(ArrayList<Partido> partidos)`: lista partidos disponibles.
- `toString()`.
- getters y setters de `celular` y `paisFavorito`.

#### Organizador

Archivo: `src/modelo/Organizador.java`

Responsabilidad:

- usuario administrativo que consulta compras y genera reportes.

Metodos:

- `consultarEntradas(ArrayList<Compra> compras)`: muestra todas las compras.
- `toString()`.
- getters y setters de `empresa` y `cargo`.

#### Partido

Archivo: `src/modelo/Partido.java`

Responsabilidad:

- representar un partido con su informacion y stock por zona.

Metodos:

- `toString()`: imprime datos del partido y disponibilidad por zona.
- getters y setters de atributos del partido.

#### Kit

Archivo: `src/modelo/Kit.java`

Responsabilidad:

- representar un paquete de partidos con precio y disponibilidad.

Metodos:

- `toString()`: muestra informacion del kit y partidos incluidos.
- getters y setters de atributos del kit.

#### Compra

Archivo: `src/modelo/Compra.java`

Responsabilidad:

- representar una compra (entrada o kit), con codigo unico y estado.

Metodos:

- `generarCodigo()`: crea codigo incremental de compra.
- `calcularTotal()`: devuelve valor pagado.
- `toString()`.
- constructores para compra nueva y compra cargada desde archivo.
- getters y setters.

#### ReporteVentas

Archivo: `src/modelo/ReporteVentas.java`

Responsabilidad:

- calcular resumen de ventas del sistema.

Metodos:

- `generarResumen(ArrayList<Compra> compras)`: total de compras, entradas, kits y monto total.
- `toString()`.
- getters y setters.

#### Sistema

Archivo: `src/modelo/Sistema.java`

Responsabilidad principal:

- coordinar toda la logica del sistema:
	autenticacion, menus, compras, notificaciones, carga y persistencia de datos.

Metodos publicos principales:

- `registrarCompra(Compra c)`.
- `validarCredenciales(String usuario, String clave)`.
- `iniciarSesion()`.
- `mostrarMenu(Usuario usuario)`.
- `comprar(Aficionado a, Partido p, ZonaEntrada z, int cantidad)`.
- `comprar(Aficionado a, Kit k, int cantidad)`.
- `notificar(Aficionado a, Compra c, ZonaEntrada z)`.
- `notificar(Aficionado a, Compra c, Kit k)`.
- `notificar(Organizador o, ReporteVentas r)`.
- `cargarDatos()`.
- getters y setters.

Metodos auxiliares internos:

- `buscarPartidoPorCodigo(String codigoPartido)`.
- `obtenerPrecioPorZona(ZonaEntrada zona)`.
- `buscarKitPorCodigo(String codigo)`.
- `cargarUsuarios()`.
- `cargarPartidos()`.
- `cargarKits()`.
- `cargarCompras()`.

### 4) Utilidades

#### ManejoArchivos

Archivo: `src/util/ManejoArchivos.java`

Responsabilidad:

- centralizar la lectura y escritura de archivos de texto.

Metodos:

- `leerFichero(String nombreArchivo)`: lee lineas de un archivo.
- `escribirArchivo(String nombreArchivo, String linea)`: agrega una linea al final del archivo.

## Flujo general del sistema

1. `Main` crea `Sistema`.
2. `Sistema.cargarDatos()` carga partidos, usuarios, kits y compras desde archivos.
3. `Sistema.iniciarSesion()` valida credenciales y confirma identidad segun rol.
4. `Sistema.mostrarMenu()` habilita opciones segun tipo de usuario.
5. Las compras actualizan disponibilidad y se guardan en `compras.txt`.
6. El organizador puede generar `ReporteVentas` y recibir notificacion simulada.

## Archivos de datos usados por el sistema

- `usuarios.txt`
- `aficionados.txt`
- `organizadores.txt`
- `partidos.txt`
- `kits.txt`
- `compras.txt`

Estos archivos funcionan como persistencia del proyecto y son leidos/escritos por `Sistema` y `ManejoArchivos`.

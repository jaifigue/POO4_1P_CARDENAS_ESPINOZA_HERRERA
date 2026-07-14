package src.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Clase de utilidad encargada exclusivamente de leer y escribir archivos
 * de texto plano. Centraliza toda la lógica de entrada/salida del sistema
 * para que el resto de las clases (principalmente Sistema) no necesiten
 * conocer FileReader, BufferedReader ni FileWriter directamente.
 * 
 * No mantiene estado propio entre llamadas, por lo que todos sus métodos
 * son estáticos: no es necesario crear una instancia de ManejoArchivos
 * para utilizarla.
 *
 * @author Jair Cárdenas
 */
public class ManejoArchivos {

    /**
     * Lee todas las líneas de un archivo de texto y las retorna como una
     * lista de cadenas, en el mismo orden en que aparecen en el archivo.
     * Utiliza codificación UTF-8 para leer correctamente tildes y la letra
     * ñ. Si el archivo no existe o ocurre algún error durante la lectura,
     * el error se captura e imprime, y el método retorna una lista vacía
     * en lugar de propagar la excepción.
     *
     * @param nombreArchivo nombre o ruta del archivo a leer
     * @return lista con cada línea del archivo como elemento; una lista
     *         vacía si el archivo no existe o no pudo leerse
     */
    public static ArrayList<String> leerFichero(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(nombreArchivo);
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;

    }

    /**
     * Escribe una línea de texto al final de un archivo, sin borrar el
     * contenido previamente existente (modo append). Se utiliza para
     * agregar nuevas compras a compras.txt sin eliminar las compras
     * registradas anteriormente. El salto de línea se agrega manualmente,
     * ya que write() no lo hace de forma automática.
     *
     * @param nombreArchivo nombre o ruta del archivo donde se escribirá
     * @param linea texto a agregar como nueva línea al final del archivo
     */
    public static void escribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(linea+"\n");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
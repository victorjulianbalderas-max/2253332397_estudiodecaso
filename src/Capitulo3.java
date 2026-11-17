import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Capitulo3 {

    
    static BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));

    public static String MostrarMenu(String[] opciones) {
        String cadena = "";
        int i = 0;
        while (i < opciones.length) {
            cadena += (i + 1) + ". " + opciones[i] + "\n";
            i++;
        }
        return cadena;
    }

    
    public static String MostrarMenuForEach(String[] opciones) {
        String cadena = "";
        for (String info : opciones) {
            cadena = cadena + info + "\n";
        }
        return cadena;
    }

    public static String Dialogo(String texto) throws IOException {
        String cadena;
        System.out.print(texto + " : ");
        cadena = lectura.readLine();
        return cadena;
    }

    public static String DesplegarMenu(String Titulo1, String[] menu) throws IOException {
        String cadena;
        cadena = Titulo1 + "\n" + "\n";
        cadena = cadena + MostrarMenu(menu);
        cadena = cadena + "\n Que opcion deseas ";
        return cadena = Dialogo(cadena);
    }

    public static String Leer(String texto) throws IOException {
        String cadena = "";
        cadena = Dialogo(texto);
        if (cadena != null) {
            cadena = cadena.trim();
            if (cadena.isEmpty())
                cadena = null;
        } else
            cadena = null;
        return cadena;
    }

    public static boolean EsNumeroEntero(String dato) {
        for (char c : dato.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean EsNumeroDouble(String dato) {
        boolean valido = false;
        for (char c : dato.toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == '.' && !valido) {
                    valido = true;
                } else {
                    return false;
                }
            }
        }
        return valido;
    }

    public static boolean EvaluarNumerico(String dato, int tipo) {
        boolean valido = false;
        switch (tipo) {
            case 1:
                valido = EsNumeroEntero(dato);
                break;
            case 2:
                valido = EsNumeroDouble(dato);
                break;
        }
        return valido;
    }

   
    public static String RellenarEspacios(String dato, int tamano) {
        String cadena;
        cadena = dato;
        for (int ciclo = dato.length(); ciclo < tamano; ciclo++) {
            cadena = cadena + " ";
        }
        return cadena;
    }

    public static String Rellenarespacios(String dato, int tamano) {
        return String.format("%1$-" + tamano + "s", dato);
    }

    public static String IdTicketSiguiente(String idticket) {
        String idticketnext = "";
        int num = Integer.parseInt(idticket) + 1;
        if (num < 10) {
            idticketnext = "00" + String.valueOf(num).trim();
        } else if ((num > 9) && (num < 100)) {
            idticketnext = "0" + String.valueOf(num).trim();
        } else {
            idticketnext = String.valueOf(num).trim();
        }
        return idticketnext;
    }

    public static int ObtenerUltimaPosicion(String[][] matriz) {
        int ultimaPosicion = -1;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] != null && !matriz[i][0].isEmpty()) {
                ultimaPosicion = i;
            }
        }
        return ultimaPosicion;
    }

    
    public static String Fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatodia = new SimpleDateFormat("dd-MM-yyyy");
        return formatodia.format(fecha);
    }

    
    public static void main(String[] args) throws IOException {
        String[] opciones = {
            "Probar Leer",
            "Probar EsNumeroEntero / EsNumeroDouble / EvaluarNumerico",
            "Probar RellenarEspacios",
            "Probar IdTicketSiguiente",
            "Probar ObtenerUltimaPosicion",
            "Mostrar Fecha",
            "Salir"
        };

        String opcionSeleccionada = "";
        while (!"7".equals(opcionSeleccionada)) {
            opcionSeleccionada = DesplegarMenu("=== CAPITULO 3: FUNCIONES DE APOYO ===", opciones);
            if (opcionSeleccionada == null) {
                break; 
            }
            opcionSeleccionada = opcionSeleccionada.trim();
            System.out.println();

            switch (opcionSeleccionada) {
                case "1": {
                    String texto = Leer("Escribe un texto (los espacios se recortan)");
                    System.out.println("Leer devolvio: " + (texto == null ? "null" : "[" + texto + "]"));
                    break;
                }
                case "2": {
                    String dato = Leer("Escribe un numero");
                    if (dato == null) {
                        System.out.println("No escribiste nada.");
                    } else {
                        System.out.println("EsNumeroEntero : " + EsNumeroEntero(dato));
                        System.out.println("EsNumeroDouble : " + EsNumeroDouble(dato));
                        System.out.println("EvaluarNumerico(tipo 1 = entero)  : " + EvaluarNumerico(dato, 1));
                        System.out.println("EvaluarNumerico(tipo 2 = decimal) : " + EvaluarNumerico(dato, 2));
                    }
                    break;
                }
                case "3": {
                    String palabra = Leer("Escribe una palabra");
                    if (palabra == null) {
                        System.out.println("No escribiste nada.");
                    } else {
                        System.out.println("RellenarEspacios (10): [" + RellenarEspacios(palabra, 10) + "]");
                        System.out.println("Rellenarespacios (10): [" + Rellenarespacios(palabra, 10) + "]");
                    }
                    break;
                }
                case "4": {
                    String idticket = Leer("Escribe el ID de ticket actual (ej. 089)");
                    if (idticket == null || !EsNumeroEntero(idticket)) {
                        System.out.println("El ID debe ser un numero entero.");
                    } else {
                        System.out.println("Siguiente ticket: " + IdTicketSiguiente(idticket));
                    }
                    break;
                }
                case "5": {
                    String[][] matriz = {
                        {"P001", "Refresco", "18.50"},
                        {"P002", "Galletas", "12.00"},
                        {"P003", "Jugo", "15.75"},
                        {null, null, null},
                        {"", "", ""}
                    };
                    System.out.println("Ultima posicion con datos: " + ObtenerUltimaPosicion(matriz));
                    break;
                }
                case "6":
                    System.out.println("Fecha de hoy: " + Fecha());
                    break;
                case "7":
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
            System.out.println();
        }
    }
}

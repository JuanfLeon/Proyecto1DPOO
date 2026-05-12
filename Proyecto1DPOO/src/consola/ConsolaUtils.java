package consola;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsolaUtils {
    static final Scanner sc = new Scanner(System.in);
    
    public static String leerString(String mensaje) {
        String val;
        do {
            System.out.print(mensaje);
            val = sc.nextLine().trim();
            if (val.isEmpty()) System.out.println("No puede estar vacío.");
        } while (val.isEmpty());
        return val;
    }
 
    public static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= min && n <= max) return n;
                System.out.println("Ingrese un número entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, ingrese un número.");
            }
        }
    }
 
    public static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double n = Double.parseDouble(sc.nextLine().trim());
                if (n >= 0) return n;
                System.out.println("El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, ingrese un número.");
            }
        }
    }
 
    public static boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("s")) return true;
            if (r.equals("n")) return false;
            System.out.println("Responda s o n.");
        }
    }
    
    public static Date leerFecha(String mensaje) {

        while(true) {

            try {

                System.out.print(mensaje);

                String fechaTexto = sc.nextLine();

                SimpleDateFormat formato =
                    new SimpleDateFormat("dd/MM/yyyy");

                formato.setLenient(false);

                return formato.parse(fechaTexto);

            }

            catch(ParseException e) {

                System.out.println(
                    "Fecha inválida. Use dd/MM/yyyy"
                );
            }
        }
    }
}

import java.util.Scanner;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

public class Trabajo02_Distribuidas_JacomeOviedo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true; // Controlar si el usuario quiere repetir operaciones

        try {
            // Crear una instancia del servicio SOAP
            Calculator service = new Calculator();
            CalculatorSoap port = service.getCalculatorSoap();

            while (continuar) {
                System.out.println("\n=== Calculadora SOAP ===");
                
                // Leer el primer numero
                System.out.print("Ingresa el primer numero: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingresa un numero entero.");
                    scanner.next(); // Limpiar entrada invalida
                }
                int num1 = scanner.nextInt();

                // Leer el segundo numero
                System.out.print("Ingresa el segundo numero: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingresa un numero entero.");
                    scanner.next(); // Limpiar entrada inválida
                }
                int num2 = scanner.nextInt();

                // Mostrar el menu de opciones
                System.out.println("\nOperaciones matematicas:");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicacion");
                System.out.println("4. Division");
                System.out.print("Selecciona una operacion (1-4): ");
                int opcion = scanner.nextInt();

                // Ejecutar la operacion seleccionada
                switch (opcion) {
                    case 1 -> System.out.println("Resultado de la suma: " + port.add(num1, num2));
                    case 2 -> System.out.println("Resultado de la resta: " + port.subtract(num1, num2));
                    case 3 -> System.out.println("Resultado de la multiplicacion: " + port.multiply(num1, num2));
                    case 4 -> {
                        if (num2 != 0) {
                            System.out.println("Resultado de la division: " + port.divide(num1, num2));
                        } else {
                            System.out.println("Error: Division por cero no permitida.");
                        }
                    }
                    default -> System.out.println("Opcion inválida.");
                }

                // Preguntar si el usuario quiere realizar otra operacion
                System.out.print("\nQuieres realizar otra operacion? (s/n): ");
                String respuesta = scanner.next();
                continuar = respuesta.equalsIgnoreCase("s");
            }

        } catch (Exception e) {
            System.err.println("Error al consumir el servicio SOAP: " + e.getMessage());
        } finally {
            scanner.close();
        }

        System.out.println("Gracias por usar la calculadora SOAP. ¡Hasta luego!");
    }
}

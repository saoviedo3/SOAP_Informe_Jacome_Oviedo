using System;
using CalculatorService;

class Program
{
    static void Main(string[] args)
    {
        try
        {
            // Crear cliente SOAP
            var client = new CalculatorSoapClient(CalculatorSoapClient.EndpointConfiguration.CalculatorSoap);

            // Bucle principal para solicitar operaciones
            while (true)
            {
                Console.WriteLine("\nSeleccione la operación: sumar, restar, multiplicar, dividir (o escriba 'exit' para salir):");
                string? operation = Console.ReadLine()?.ToLower();

                if (operation == "exit")
                {
                    Console.WriteLine("Saliendo del programa...");
                    break;
                }

                // Solicitar primer número
                Console.WriteLine("Ingrese el primer número:");
                if (!int.TryParse(Console.ReadLine(), out int num1))
                {
                    Console.WriteLine("Error: Debe ingresar un número entero válido.");
                    continue;
                }

                // Solicitar segundo número
                Console.WriteLine("Ingrese el segundo número:");
                if (!int.TryParse(Console.ReadLine(), out int num2))
                {
                    Console.WriteLine("Error: Debe ingresar un número entero válido.");
                    continue;
                }

                // Realizar la operación seleccionada
                try
                {
                    int result = operation switch
                    {
                        "sumar" => client.AddAsync(num1, num2).Result,
                        "restar" => client.SubtractAsync(num1, num2).Result,
                        "multiplicar" => client.MultiplyAsync(num1, num2).Result,
                        "dividir" when num2 != 0 => client.DivideAsync(num1, num2).Result,
                        "dividir" => throw new DivideByZeroException("No se puede dividir entre cero."),
                        _ => throw new InvalidOperationException("Operación inválida.")
                    };

                    Console.WriteLine($"Resultado de {operation} ({num1}, {num2}): {result}");
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Error al realizar la operación: {ex.Message}");
                }
            }

            // Cerrar el cliente
            client.Close();
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error crítico: {ex.Message}");
        }
    }
}

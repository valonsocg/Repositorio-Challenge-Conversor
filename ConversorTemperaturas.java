import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorTemperaturas {

    public static void main(String[] args) throws IOException {

        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        do {
            Map<Integer, String> codigoTemperaturas = crearMapaTemperaturas();

            System.out.println("Bienvenido al Conversor de Temperaturas!");

            System.out.println("Ingrese la temperatura de origen: ");
            String temperaturaOrigen = obtenerOpcionValida(scanner, codigoTemperaturas);

            System.out.println("Ingrese la temperatura de destino: ");
            String temperaturaDestino = obtenerOpcionValida(scanner, codigoTemperaturas);

            System.out.println("Ingrese el numero a convertir: ");
            double numero = obtenerNumeroValido(scanner);

            double temperaturaConvertida = convertirTemperatura(numero, temperaturaOrigen, temperaturaDestino);
            System.out.println(numero + " " + temperaturaOrigen + " son: " + temperaturaConvertida + " " + temperaturaDestino);


            System.out.println("Desea hacer otra conversion?");
            System.out.println("1.Si \t 2.Cualquier otra tecla terminara el programa");
            String opcion = scanner.next();
            scanner.nextLine();
            if (!opcion.equals("1")) {
                continuar = false;
                scanner.close();
            }

        } while (continuar);

        System.out.println("Gracias por usar el conversor de Temperaturas!");
    }

    private static Map<Integer, String> crearMapaTemperaturas() {
        Map<Integer, String> codigoTemperaturas = new HashMap<>();

        codigoTemperaturas.put(1, "CELSIUS");
        codigoTemperaturas.put(2, "FAHRENHEIT");
        codigoTemperaturas.put(3, "KELVIN");
        codigoTemperaturas.put(4, "RANKINE");
        return codigoTemperaturas;
    }

    private static String obtenerOpcionValida(Scanner scanner, Map<Integer, String> codigoTemperaturas) {
        int opcion;
        String numTemperatura = null;

        do {
            System.out.println("1:CELSIUS\t 2:FAHRENHEIT\t 3:KELVIN\t 4:RANKINE");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                numTemperatura = codigoTemperaturas.get(opcion);
                if (numTemperatura == null) {
                    System.out.println("Opcion invalida. Por favor, ingrese un numero valido");
                }
            } else {
                scanner.nextLine();
                System.out.println("Opcion invalida. Por favor, ingrese un numero valido");
                opcion = 0;
            }
        } while (numTemperatura == null);

        return numTemperatura;
    }

    private static double obtenerNumeroValido(Scanner scanner) {
        double numero = 0;
        boolean inputValido = false;

        do {
            if (scanner.hasNextDouble()) {
                numero = scanner.nextDouble();
                inputValido = true;
            } else {
                scanner.nextLine();
                System.out.println("Por favor, ingrese un valor numérico válido.");
            }
        } while (!inputValido);

        return numero;
    }

    private static double convertirTemperatura(double numero, String temperaturaOrigen, String temperaturaDestino) {
        double temperaturaConvertida = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        switch (temperaturaOrigen) {
            case "CELSIUS":
                if (temperaturaDestino == "FAHRENHEIT") {
                    temperaturaConvertida = numero * 9 / 5 + 32;
                } else if (temperaturaDestino == "KELVIN") {
                    temperaturaConvertida = numero + 273.15;
                } else if (temperaturaDestino == "RANKINE") {
                    temperaturaConvertida = numero * 9 / 5 + 491.67;
                } else if (temperaturaDestino == "CELSIUS") {
                    temperaturaConvertida = numero;
                }
                break;
            case "FAHRENHEIT":
                if (temperaturaDestino == "CELSIUS") {
                    temperaturaConvertida = (numero - 32) * 5 / 9;
                } else if (temperaturaDestino == "KELVIN") {
                    temperaturaConvertida = (numero - 32) * 5 / 9 + 273.15;
                } else if (temperaturaDestino == "RANKINE") {
                    temperaturaConvertida = numero + 459.67;
                }else if (temperaturaDestino == "FAHRENHEIT") {
                    temperaturaConvertida = numero;
                }
                break;
            case "KELVIN":
                if (temperaturaDestino == "CELSIUS") {
                    temperaturaConvertida = numero - 273.15;
                } else if (temperaturaDestino == "FAHRENHEIT") {
                    temperaturaConvertida = numero * 9 / 5 - 459.67;
                } else if (temperaturaDestino == "RANKINE") {
                    temperaturaConvertida = numero * 9 / 5;
                } else if (temperaturaDestino == "KELVIN") {
                    temperaturaConvertida = numero;
                }
                break;
            case "RANKINE":
                if (temperaturaDestino == "CELSIUS") {
                    temperaturaConvertida = (numero - 491.67) * 5 / 9;
                } else if (temperaturaDestino == "FAHRENHEIT") {
                    temperaturaConvertida = numero - 459.67;
                } else if (temperaturaDestino == "KELVIN") {
                    temperaturaConvertida = numero / 9;
                } else if (temperaturaDestino == "RANKINE") {
                    temperaturaConvertida = numero;
                }
                break;
            default:
                System.out.println("Temperatura de origen no soportada.");
                break;
        }
        return Double.parseDouble(decimalFormat.format(temperaturaConvertida));
    }
}

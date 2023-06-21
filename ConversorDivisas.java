import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorDivisas {

    private static final String API_KEY = "69ebc9bdae4397733782805f";

    public static void main(String[] args) throws IOException {

        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        do {
            Map<Integer, String> codigoDvisas = crearMapaDivisas();

            System.out.println("Bienvenido al Convertidor de Divisas!");

            System.out.println("Ingrese la divisa de origen: ");
            String divisaOrigen = obtenerOpcionValida(scanner, codigoDvisas);

            System.out.println("Ingrese la divisa de destino: ");
            String divisaDestino = obtenerOpcionValida(scanner, codigoDvisas);

            System.out.println("Ingrese la cantidad de dinero a convertir: ");
            BigDecimal monto = obtenerBigDecimalValido(scanner);

            sendHttpGETRequest(divisaOrigen, divisaDestino, monto);

            System.out.println("Desea hacer otra conversion?");
            System.out.println("1.Si \t 2.No");
            if (scanner.nextInt() != 1) {
                continuar = false;
                scanner.close();
            } else {
                scanner.nextLine();
            }

        } while (continuar);

        System.out.println("Gracias por usar el convertidor de Divisas!");
    }

    private static Map<Integer, String> crearMapaDivisas() {
        Map<Integer, String> codigoDvisas = new HashMap<>();

        codigoDvisas.put(1, "USD");
        codigoDvisas.put(2, "EUR");
        codigoDvisas.put(3, "GBP");
        codigoDvisas.put(4, "JPY");
        codigoDvisas.put(5, "KRW");
        codigoDvisas.put(6, "PEN");
        return codigoDvisas;
    }

    private static BigDecimal obtenerBigDecimalValido(Scanner scanner) {
        BigDecimal monto = null;
        boolean inputValido = false;

        do {
            if(scanner.hasNextBigDecimal()){
                monto = scanner.nextBigDecimal();
                inputValido = true;
            } else {
                scanner.nextLine();
                System.out.println("Cantidad de dinero inválida. Por favor, ingrese un valor numérico válido.");
            }
        } while(!inputValido);

        return monto;
}

    private static String obtenerOpcionValida(Scanner scanner, Map<Integer, String> codigoDivisas) {
        int opcion;
        String numDivisa = null;

        do {
            System.out.println("1:USD (US Dolar)\t 2:EUR (Euro)\t 3:GBP (Libra Esterlina)\t " +
                    "4:JPY (Yen Japones)\t 5:KRW (Won Sul-coreano)\t 6:PEN (Sol Peruano)");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                numDivisa = codigoDivisas.get(opcion);
                if (numDivisa == null) {
                    System.out.println("Opcion invalida. Por favor, ingrese un numero valido");
                }
            } else {
                scanner.nextLine();
                System.out.println("Opcion invalida. Por favor, ingrese un numero valido");
                opcion = 0;
            }
        } while (numDivisa == null);

        return numDivisa;
    }

    private static void sendHttpGETRequest(String divisaOrigen, String divisaDestino, BigDecimal monto) throws IOException {
        String GET_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + divisaOrigen + "/" + divisaDestino;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            JSONObject obj = new JSONObject(response.toString());
            BigDecimal tasaDeCambio = obj.getBigDecimal("conversion_rate");
            BigDecimal resultado = monto.multiply(tasaDeCambio).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println(monto + divisaOrigen + "=" + resultado + divisaDestino);
        } else {
            System.out.println("GET request failed");
        }
    }
}

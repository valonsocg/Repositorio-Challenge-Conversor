# Conversor de Divisas

Este proyecto es un conversor de divisas desarrollado en Java que utiliza una API para obtener las tasas de cambio actualizadas. Permite convertir cantidades de una moneda a otra de acuerdo a las tasas de cambio vigentes.

## Funcionalidades

- Conversión de una moneda a otra: Puedes ingresar una cantidad en una moneda determinada y obtener su equivalente en otra moneda seleccionada.
- Actualización de las tasas de cambio: Las tasas de cambio se obtienen de una API externa y se actualizan regularmente para garantizar la precisión de las conversiones.

## Requisitos

- Java Development Kit (JDK) 8 o superior instalado en el sistema.
- Acceso a Internet para obtener las tasas de cambio de la API.

## Configuración

1. Clona o descarga el repositorio del proyecto.

git clone https://github.com/tu-usuario/conversor-divisas.git

markdown
Copy code

2. Importa el proyecto en tu entorno de desarrollo Java preferido (por ejemplo, Eclipse, IntelliJ IDEA, NetBeans, etc.).

3. Configura la API de tasas de cambio:

- Regístrate en una API que proporcione tasas de cambio actualizadas. Algunas opciones populares son:
  - [Open Exchange Rates](https://openexchangerates.org/)
  - [CurrencyLayer](https://currencylayer.com/)
  - [Fixer](https://fixer.io/)
- Obtén una clave de API válida para acceder a las tasas de cambio.
- En el proyecto, busca el archivo `Config.java` en el directorio `src/main/java`.
- Reemplaza el valor de la constante `API_KEY` con tu clave de API.

  ```java
  public class Config {
      public static final String API_KEY = "tu-clave-de-api";
  }
  ```

## Uso

1. Ejecuta la aplicación desde tu entorno de desarrollo o mediante el siguiente comando en la terminal:

```java -jar conversor-divisas.jar```

2. La aplicación te mostrará un menú con las opciones disponibles. Sigue las instrucciones en pantalla para realizar las conversiones.

## Nota

Este proyecto se ha creado con fines educativos y de práctica. No debe utilizarse en entornos de producción ni con fines comerciales. La exactitud de las tasas de cambio obtenidas de la API externa no está garantizada, y se recomienda utilizar este proyecto únicamente con propósitos de aprendizaje.

## Contacto

Si tienes alguna pregunta o sugerencia relacionada con este proyecto, no dudes en contactarme.

¡Gracias por usar el Conversor de Divisas! Espero que te sea útil en tu aprendizaje y práctica.

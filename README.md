# Simón Dice
## Características

- Generación aleatoria de secuencias de colores.
- Interfaz gráfica interactiva.
- Gestión de estados de la aplicación.
- Uso de `LiveData` y `ViewModel` para la lógica de la aplicación.
- Implementación de coroutines para la gestión de estados auxiliares.

## Estructura del Proyecto

- `UI.kt`: Composable. Es el frontend de la aplicación.
- `ModelView.kt`: Esta clase contiene la lógica del programa.
- `Datos.kt`: En este archivo hay un singleton y varios enum donde se guardan y se llaman a los datos principales del programa.

## Estados principales
Utilizados para controlar el flujo del programa.

- **INICIO**: Al iniciar la aplicación o terminar la ronda.
- **GENERANDO**: La aplicación está añadiendo un color a la secuencia y mostrándola.
- **RESPONDIENDO**: El usuario está respondiendo a la secuencia.

## Tecnologías Utilizadas

- Kotlin
- Android Jetpack (Compose, ViewModel, LiveData)
- Gradle

## Cómo Jugar

1. Inicia la aplicación.
2. Presiona el botón "Start" para comenzar una nueva partida.
3. Observa la secuencia de colores generada por la máquina.
4. Repite la secuencia de colores en el mismo orden.
5. Si aciertas, pasarás a la siguiente ronda con una secuencia más larga.
6. Si fallas, la partida terminará y podrás comenzar una nueva.
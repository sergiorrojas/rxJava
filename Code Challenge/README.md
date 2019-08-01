# Procesador de CLI

HOT Observable que replica lo escrito en consola.
Se crean n Subscripciones que generan la Funcion, BiFuncion requerida para la operación determinada. 
Por ejemplo, suma genera BiFunction mientras que imprime genera Function (generadores).
Las subscripciónes colocan la funcion generada en un flujo observable asi como los operandos en un stack (memoria simulada) para ser leidas en su futuro.
Una subscripcion (motor de ejecucion) escucha el flujo observable de funciones y aplica las mismas a los valores colocados en el stack asi como el resultado en un flujo de resultados observable.
El subscriptor de salida, escucha el flujo de salida y lo imprime en pantalla.
```
**Console Input:**
imprime hola
**Consola Output:**
hola
**Console Input:**
suma 3 5
**Consola Output:**
8
**Console Input:**
resta 3 5
**Consola Output:**
-2
**Console Input:**
resta 3 
**Consola Output:**
Operacion resta invalida
```


# Lector de noticias

En este ejemplo se demuestra el uso de Observables y subscriptores

Descripcion de demo:
5 subscritores utilizando la clase suscriber de la version 1.1.8 de la libreria rxjava 
Se crea un observable de una lista finita de objetos tipo noticias (que pueden ser de distintos tipos); espectáculos, politica, deportiva. Donde los suscriptores tendrán que escuchar su objeto noticia correspondiente

- Suscriptor No 1 -> Deportiva
- Suscriptor No 2 -> Espectaculos
- Suscriptor No 3 -> Politica
- Suscriptor No 4 -> Espectaculos y deportiva
- Suscriptor No 5 -> Deportiva, espectaculo y politica

- El suscriptor No 3 se desuscribe, cuando una noticia espectáculos es lanzada.

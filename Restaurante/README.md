# Demo Restaurante

Aplicacion demo que representa un restaurante que recibe 20 ordenes por minuto, las ordenes siempre incluyen: bebida, entrada, plato fuerte y postre. Por lo que existen 3 cocineros principales: Bar man, que se encarga de las bebidas, Repostero que se encarga de preparar el postre y un Chef que se encarga de preparar la entrada y plato fuerte. El chef a su vez tiene 2 ayudantes de cocina; en otras palabras, el chef tiene dos subscriptores.
- Las labores generales de la cocina son: Preparar alimento y emplatar.
- Implementa un conjunto de Observables y Subscriptores que simulan esta situación bajo los siguientes criterios:

  - Bebidas, entradas y postre pueden prepararse de forma asíncronas
  - El plato fuerte se prepara después de la entradas.
  - Ejemplo de Salida por cada orden emitida:
```
[Hilo-Barman] Preparando bebida 1 …
[Hilo-Repostero] Preparando postre 1 ….
[Hilo-Barman] Sirviendo (Emplatando) bebida 1 …
[Hilo-Pinche1] Preparando Entrada 1 ….
[Hilo-Pinche2] Preparando Plato Fuerte 1 ….
[Hilo-Pinche1] Emplatando Entrada 1 ….
[Hilo-Pinche2] Emplatando Plato Fuerte 1 ….
[Hilo-Repostero] Emplatando postre 1 …
```

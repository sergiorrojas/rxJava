# Introducción

La programación reactiva es un paradigma de programación **declarativo** que utiliza flujos o **streams** de datos y la propagación de cambios para
crear aplicaciones **asincronas**, no bloqueantes y dirigidas por **eventos** que puedan ser escaladas fácilmente.

Un stream es una secuencia de eventos ordenados a través del tiempo. El concepto de stream se relaciona comúnmente con un rio que no inicia ni termina. Este tipo de flujo esta normalmente aplicado a datos que no tienen un inicio o fin discreto. Los datos son continuamente enviados en lugar de ser mandados por partes o lotes.
Cualquier cosa puede ser un Stream desde variables, datos entrados por un usuario, propiedades, estructuras de datos o una serie de clicks. 
Estos eventos son emitidos a través del tiempo y pueden contener uno o mas valores, un error o una señal de completado. 
Se pueden realizar diferentes tipos operaciones sobre los streams tales como: 
- Utilizar un stream como entrada para otro stream
- Combinar dos o más streams sobre un solo flujo
- Filtrar y transformar los datos entrantes.
- Combinar datos de diferentes flujos de acuerdo a una función definida
  
 ## Declarativo vs Imperativo
 El paradigma de programación declarativo provee la lógica de un cálculo sin describir la forma en la que se tiene que hacer tal calculo.
 Llevándolo a un ejemplo de la vida real, imagina la diferencia en como hacia tu papá para manejar a un lugar y como lo hacemos los milenials hoy en día. 
 Lo más probable es que tu papá, llegaba a su destino con una dirección y alguna referencia (Forma declarativa), por ejemplo "La cena será en Pizzería La Mona en la Marina al lado del muelle 3". Él ya sabía que caminos y tipo de transporte tomar para poder llegar a su cita.
 En cambio, esa misma tarea hoy en día requerimos que algún tipo de servicio de ubicación nos diga algo como (Forma imperativa):
- Si viajas en auto necesitas dirigirte al norte por la calle teniente Azueta hacia Av. Zaragoza por 1 km
- Girar a la derecha hacia Avenida Rotarismo por 83 m
- Tomar Avenida Cruz Lizárraga hacia Avenida de los deportes en Tellería por 1.8 km
- Tomar Avenida Leonismo Internacional y Avenida de la Marina hacia Paseo de La Isla por 7.4 Km
- Sigue por paseo de La Isla hasta tu destino en la marina. Tu destino se encuentra a la derecha
 
 Algunos ejemplos de lenguajes declarativos son SQL, CSS y HTML. 
 
 SQL: 
 > Select * from users where age > 20;
 
 HTML:
 ```
<html>
  <head>
    <title>Programación Declarativa</title>
  </head>
  <body>
    <p>Crea este texto en un párrafo</p>
  </body>
</html>
```

Los dos ejemplos anteriores nos muestran un enfoque más acerca del "QUE" se tiene que hacer en vez del "COMO" tiene que hacerse    

La programación imperativa describe los cálculos en forma de sentencias que cambian el estado de un programa. Está enfocada en especificar los pasos para ejecutar una acción.

Como ejemplo si quisiéramos hacer una función que regresa el doble de los números pares en una lista en un lenguaje que soporta ambos paradigmas como Python escribiríamos algo como:

#Imperativo
> dobles = [n * 2 for n in numeros if n%2 == 0]

#Declarativo
```
dobles = []
for i in range(20):
  if i % 2 == 0:
    dobles.append(i * 2)
```

El mismo ejemplo en java seria:

#Imperativo
```
    List<Integer> calcularDobleParesImperativo(List<Integer> numeros){
        ArrayList<Integer> dobles = new ArrayList<>();
        for (Integer numero: numeros){
            if(numero % 2 == 0){
                dobles.add(numero * 2);
            }
        }
        return dobles;
    }
```

#Declarativo
```
  List<Integer> calcularDobleParesDeclarativo(List<Integer> numeros){
        return numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .map(numero -> numero * 2)
                .collect(Collectors.toList());
```


## El Manifiesto Reactivo
El Manifiesto reactivo es un documento que se puede encontrar en el siguiente link: https://www.reactivemanifesto.org/ y detalla los principios básicos de la programación reactiva. Fue liberado por primera vez en el 2013 por un grupo de desarrolladores liderados por Jonas Boner.

Este documento explica como al tener un sistema **dirigido por mensajes** permite a sus componentes tener un bajo acoplamiento. A su vez, los sistemas dirigidos por mensajes facilitan la **elasticidad** para incrementar o disminuir la utilización de recursos automáticamente según la demanda lo requiera. Estas dos últimas propiedades proveen a un sistema de **resilencia**, la cual le permite continuar siendo **responsivo** a pesar de fallas en varios componentes. Esta última cualidad es la meta principal de la programación reactiva; el poder proporcionar la mejor experiencia, lo más responsiva posible a los usuarios del sistema.

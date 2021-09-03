# DesarrolloSistemasDistribuidos
Tareas realizadas en la clase de Desarrollo de Sistemas Distribuidos con el profesor Pineda Guerrero Carlos Agosto - Diciembre 2021.

## Tareas
### 1 Cálculo de PI

Desarrollo de la implementación de un programa distribuido, el cual calcula una aproximación de PI utilizando la serie de [Gregory-Leibniz](https://es.wikipedia.org/wiki/Serie_de_Leibniz).
El programa se va a ejecutar en forma distribuida sobre cinco nodos, cada nodo simula una computadora diferente.

![GitHub Logo](https://upload.wikimedia.org/wikipedia/commons/8/84/Star_Topology.png)

El nodo 0 actúa como servidor y los nodos 1, 2, 3 y 4 actúan como clientes. Es necesario pasa rcomo parámetro al programa el 
número de nodo actual, de manera que el programa pueda actuar como servidor o como cliente, según el número de nodo
que pasa como parámetro.

Para ejecutar el servidor 

```
java PI 0
```
Para ejecutar los nodos 

```
java PI 1
```

```
java PI 2
```

```
java PI 3
```

```
java PI 4
```

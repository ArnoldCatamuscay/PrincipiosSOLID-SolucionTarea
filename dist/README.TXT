TÍTULO DEL PROYECTO: Laboratorio 3 - principios SOLID

VERSIÓN: 1.0 - 09/05/2022 
AUTORES: ascatamuscay@unicauca.edu.co
	 cnunez@unicauca.edu.co

DESCRIPCIÓN: Aplicación sencilla que permite guardar y recuperar vehículos y 
además, facturar el pago de camiones, carros y motos de un parqueadero público.

EJECUCIÓN DEL PROGRAMA: Ejecutar el método main de la clase Cliente Main en el 
paquete co.unicauca.parkinglot.presentation

INSTRUCCIONES PARA LAS PRUEBAS: Para realizar las pruebas unitarias ejecutar la
clase ParkingTest en el paquete co.unicauca.parkinglot.domain del folder 
Test Packages. En dicha clase encontraremos 4 métodos que podemos ejecutar por
separado y verificar que los métodos implementados funcionan correctamente.

HERRAMIENTAS UTILIZADAS: Apache NetBeans 13 y SQLite 

IMPLEMENTACIÓN EXTRA: Cabe resaltar que se implementó una especie de sorteo donde 
pueden participar todos los dueños de un vehículo tipo camión, en el cual cada 
camión que marque un parqueo mayor a un día, tendrá la opción de ganar que su 
cobro por parqueo sea totalmente gratis. Las reglas del sorteo consisten en que 
cada camión tendrá un número aleatorio entre 0-100 y se generará otro 
número aleatorio entre 0-100 si ambos números coinciden dicho participante será 
ganador del sorteo.
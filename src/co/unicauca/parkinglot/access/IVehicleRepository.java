/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.access;

import co.unicauca.parkinglot.domain.Vehicle;
import java.util.List;

/**
 * Interfaz de VehicleRepository
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public interface IVehicleRepository {

    /**
     * Metodo que guarda un vehiculo
     *
     * @param newVehicle vehiculo el cual va a ser guardado
     * @return una respuesta booleana para determinar si se guardo o no
     * correctamente
     */
    boolean save(Vehicle newVehicle);

    /**
     * Metodo que lista los vehiculos almacenados
     *
     * @return Un listado con todos los vehiculos almacenados
     */
    List<Vehicle> list();
}

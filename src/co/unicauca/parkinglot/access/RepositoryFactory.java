/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.access;

import co.unicauca.parkinglot.domain.CarParkingCost;
import co.unicauca.parkinglot.domain.IParkingCost;
import co.unicauca.parkinglot.domain.MotoParkingCost;
import co.unicauca.parkinglot.domain.TruckParkingCost;
import co.unicauca.parkinglot.domain.TypeEnum;
import java.util.EnumMap;
import java.util.Map;

/**
 * Fabrica que se encarga de instanciar VehicleRepository o cualquier otro que
 * se cree en el futuro.
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class RepositoryFactory {

    /* Instancia de la fabrica de repositorios*/
    private static RepositoryFactory instance;

    /* Diccionario de vehiculo*/
    private Map<TypeEnum, IParkingCost> vehicleDictionary;

    /**
     * Constructor para la fabrica de repositorios
     */
    private RepositoryFactory() {
        vehicleDictionary = new EnumMap<>(TypeEnum.class);
        vehicleDictionary.put(TypeEnum.MOTO, new MotoParkingCost());
        vehicleDictionary.put(TypeEnum.CAR, new CarParkingCost());
        vehicleDictionary.put(TypeEnum.TRUCK, new TruckParkingCost());
    }

    /**
     * Metodo que devuelve la instancia de la fabrica de repositorios
     *
     * @return La instancia de la fabrica de repositorios actual
     */
    public static RepositoryFactory getInstance() {
        if (instance == null) {
            instance = new RepositoryFactory();
        }
        return instance;
    }

    /**
     * Método que crea una instancia concreta de la jerarquia IVehicleRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IVehicleRepository
     */
    public IVehicleRepository getRepository(String type) {

        IVehicleRepository result = null;

        switch (type) {
            case "default":
                result = new VehicleRepository();
                break;
        }
        return result;
    }

}

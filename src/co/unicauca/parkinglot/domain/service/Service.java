/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain.service;

import co.unicauca.parkinglot.access.IVehicleRepository;
import co.unicauca.parkinglot.domain.CarParkingCost;
import co.unicauca.parkinglot.domain.IParkingCost;
import co.unicauca.parkinglot.domain.MotoParkingCost;
import co.unicauca.parkinglot.domain.TruckParkingCost;
import co.unicauca.parkinglot.domain.Vehicle;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta es la clase encargada de las operaciones con la base de datos
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class Service {

    /**
     * Repositorio con el cual realizamos operaciones con la base de datos
     */
    private IVehicleRepository repository;

    /**
     * Inyección de dependencias en el constructor.
     *
     * @param repository una clase hija de IProductRepository
     */
    public Service(IVehicleRepository repository) {
        this.repository = repository;
    }

    /**
     * Método que calcula el coste del parking para un vehículo
     *
     * @param vehicle El vehículo al que le vamos a calcular el costo de parking
     * @param input La fecha y hora de ingreso del vehículo
     * @param output La fecha y hora de salida del vehículo
     * @return La tarifa que el vehículo debe pagar
     */
    public int calculateParkingCost(Vehicle vehicle, LocalDateTime input, LocalDateTime output) {
        int vehicleTax = 0;
        //Validate product.
        if (vehicle == null) {
            return vehicleTax;
        }

        switch (vehicle.getTipo()) {
            case MOTO:
                IParkingCost moto_tax = new MotoParkingCost();
                vehicleTax = moto_tax.calculateCost(input, output);
                break;
            case CAR: {
                IParkingCost car_tax = new CarParkingCost();
                vehicleTax = car_tax.calculateCost(input, output);
                return vehicleTax;
            }
            case TRUCK: {
                IParkingCost truck_tax = new TruckParkingCost();
                vehicleTax = truck_tax.calculateCost(input, output);
                return vehicleTax;
            }
            default:
                break;
        }
        return vehicleTax;
    }

    /**
     * Método para guardar un vehículo en la base de datos
     *
     * @param newVehicle El vehículo que vamos a insertar en la base de datos
     * @return true si se pudo insertar el vehículo, en caso contrario retorna false
     */
    public boolean saveVehicle(Vehicle newVehicle) {

        //Validate product
        if (newVehicle == null || newVehicle.getPlaca().isBlank() || newVehicle.getTipo().toString().isBlank()) {
            return false;
        }

        repository.save(newVehicle);
        return true;
    }

    /**
     * Método que lista los vehículos que hemos ingresado
     *
     * @return Un ArrayList con los vehículos que han ingresado
     */
    public List<Vehicle> listVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = repository.list();

        return vehicles;
    }
}

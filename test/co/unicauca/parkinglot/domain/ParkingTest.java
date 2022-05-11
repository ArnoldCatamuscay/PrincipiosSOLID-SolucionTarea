/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.unicauca.parkinglot.domain;

import co.unicauca.parkinglot.access.IVehicleRepository;
import co.unicauca.parkinglot.access.RepositoryFactory;
import co.unicauca.parkinglot.domain.service.Service;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 * Esta clase se encarga de realizar las pruebas unitarias al proyecto
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class ParkingTest {

    /**
     * Prueba del método calcularCosto para un vehículo de tipo MOTO
     */
    @Test
    public void MotosTest() {
        System.out.println("Moto hora y media");
        Vehicle veh = new Vehicle("FTK-123", TypeEnum.MOTO);
        LocalDateTime input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 17, 0);
        LocalDateTime output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 18, 30);
        IVehicleRepository repo = RepositoryFactory.getInstance().getRepository("default");
        Service service = new Service(repo);
        long expResult = 2500L;
        long result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
        System.out.println("Moto menos una hora");
        output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 17, 45);
        expResult = 1000L;
        result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
        System.out.println("Moto 3 horas y 45 minutos");
        input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 11, 45);
        expResult = 4800L;
        result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
    }

    /**
     * Prueba del método calcularCosto para un vehículo de tipo CAR
     */
    @Test
    public void CarTest() {
        System.out.println("Carro 2 horas y 10 minutos");
        Vehicle veh = new Vehicle("FTK-123", TypeEnum.CAR);
        LocalDateTime input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        LocalDateTime output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 10, 10);
        IVehicleRepository repo
                = RepositoryFactory.getInstance().getRepository("default");
        Service service = new Service(repo);
        long expResult = 6400L;
        long result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);

        System.out.println("Carro menos una hora");
        output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 45);
        expResult = 2000L;
        result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
        System.out.println("Carro 1 horas y 30 minutos");
        input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 9, 30);
        expResult = 5000L;
        result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
    }

    /**
     * Prueba del método calcularCosto para un vehículo de tipo TRUCK
     */
    @Test
    public void TruckTest() {
        System.out.println("Camion menos de 12 horas");
        Vehicle veh = new Vehicle("JNK-838", TypeEnum.TRUCK);
        LocalDateTime input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        LocalDateTime output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 13, 0);
        IVehicleRepository repo
                = RepositoryFactory.getInstance().getRepository("default");
        Service service = new Service(repo);
        long expResult = 10000L;
        long result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
        System.out.println("Camión mas de 12 horas");
        input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 22, 0);
        expResult = 15000L;
        result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
        System.out.println("Camión 3 dias y una hora");
        input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        output = LocalDateTime.of(2021, Month.FEBRUARY, 25, 9, 15);
        expResult = 45700;
        result = service.calculateParkingCost(veh, input, output);
        assertEquals(expResult, result);
    }

    /**
     * Prueba de los métodos saveVehicle y listVehicles
     */
    @Test
    public void PersistenceTest() {
        System.out.println("Guardar y listar");
        Vehicle veh = new Vehicle("QET-646", TypeEnum.MOTO);
        IVehicleRepository repo
                = RepositoryFactory.getInstance().getRepository("default");

        Service service = new Service(repo);
        service.saveVehicle(veh);
        veh = new Vehicle("NBV-987", TypeEnum.CAR);
        service.saveVehicle(veh);
        veh = new Vehicle("IJY-987", TypeEnum.TRUCK);
        service.saveVehicle(veh);
        List<Vehicle> list = service.listVehicles();
        long expResult = 3L;
        long result = service.listVehicles().size();
        assertEquals(expResult, result);
        assertEquals("QET-646", service.listVehicles().get(0).getPlaca());
    }
}

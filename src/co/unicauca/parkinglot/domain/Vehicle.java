/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain;

/**
 * Esta es la clase Vehicle
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class Vehicle {

    /**
     * Placa del vehículo
     */
    private String placa;
    /**
     * Tipo de vehículo, de la clase TypeEnum
     */
    private TypeEnum tipo;

    /**
     * Constructor con parámetros para los vehúclos
     *
     * @param placa La placa del vehículo
     * @param tipo El tipo de vehículo de la clas TypeEnum
     */
    public Vehicle(String placa, TypeEnum tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    /**
     * Constructor vacío para los vehículos
     */
    public Vehicle() {

    }

    /**
     * Getter.
     *
     * @return la placa del vehículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Setter.
     *
     * @param placa La placa del vehículo
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Getter.
     *
     * @return El tipo vehículo
     */
    public TypeEnum getTipo() {
        return tipo;
    }

    /**
     * Setter.
     *
     * @param tipo El tipo de vehículo
     */
    public void setTipo(TypeEnum tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devuelve una cadena con la información del vehículo
     *
     * @return Una cadena que contiene la placa y el tipo de vehículo
     */
    @Override
    public String toString() {
        return "Vehicle{" + "placa=" + placa + ", tipo=" + tipo + '}';
    }

}

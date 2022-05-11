/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain;

import java.time.LocalDateTime;

/**
 * Cobro de parqueo al vehiculo Moto. Utiliza el patrón de diseño Strategy
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class MotoParkingCost implements IParkingCost {

    /**
     * Metodo que devuelve el cobro de parqueado para un vehiculo Moto
     *
     * @param input Fecha de entrada del vehiculo Moto
     * @param output Fecha de salida del vehiculo Moto
     * @return El cobro de parqueo para el vehiculo Moto según la logica del
     * negocio
     */
    @Override
    public int calculateCost(LocalDateTime input, LocalDateTime output) {
        int TAX = 0;
        int h_inicio = input.getHour();
        int h_fin = output.getHour();
        int min_inicio = input.getMinute();
        int min_fin = output.getMinute();
        int estancia_h = h_fin - h_inicio;
        int estancia_m = (Math.abs(min_inicio - min_fin) * 1000) / 60;
        if (Math.floorMod(estancia_m, 100) != 0) {
            double parteEntera = estancia_m / 100;
            estancia_m = ((int) parteEntera + 1) * 100;
        }
        if (estancia_h < 1) {
            TAX = 1000;
        } else if (estancia_h == 1 && estancia_m == 0) {
            TAX = 2000;
        } else if (estancia_h == 1 && estancia_m != 0) {
            TAX = 2000 + estancia_m;
        } else if (estancia_h > 1) {
            TAX = 2000 + (estancia_h - 1) * 1000 + estancia_m;
        }
        return TAX;
    }
}

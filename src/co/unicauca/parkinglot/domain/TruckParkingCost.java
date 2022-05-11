/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Cobro de parqueo al vehiculo Camion. Utiliza el patrón de diseño Strategy
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class TruckParkingCost implements IParkingCost {

    /**
     * Metodo que devuelve el cobro de parqueado para un vehiculo Camion
     *
     * @param input Fecha de entrada del vehiculo Camion
     * @param output Fecha de salida del vehiculo Camion
     * @return El cobro de parqueo para el vehiculo Camion según la logica del
     * negocio
     */
    @Override
    public int calculateCost(LocalDateTime input, LocalDateTime output) {
        Random rnd = new Random();
        int camionero = rnd.nextInt(5);
        int baloto = rnd.nextInt(5);

        int TAX = 0;
        int d_inicio = input.getDayOfMonth();
        int d_fin = output.getDayOfMonth();
        int h_inicio = input.getHour();
        int h_fin = output.getHour();
        int estancia_d = d_fin - d_inicio;
        int estancia_h = h_fin - h_inicio;
        int estancia_t = (estancia_h * 15000) / 24;
        if (Math.floorMod(estancia_t, 100) != 0) {
            double parteEntera = estancia_t / 100;
            estancia_t = ((int) parteEntera + 1) * 100;
        }
        if (estancia_h <= 12 && estancia_d == 0) {
            TAX = 10000;
        } else if (estancia_h >= 12 && estancia_h < 24) {
            TAX = 15000;
        } else if (estancia_d > 0 && estancia_h > 0) {
            System.out.println("Boleto del camión: " + camionero + " | Boleto ganador: " + baloto);
            if (camionero == baloto) {
                TAX = 0;
                //System.out.println("Boleto del camión: " + camionero + " | Boleto ganador: " + baloto );
                System.out.println("Gano sorteo, es decir, su parqueo es gratis. Vuelva pronto.");
            } else {
                TAX = (15000 * estancia_d) + estancia_t;
                System.out.println("No gano sorteo, buena suerte para la próxima. Vuelva pronto.");
            }
        }
        return TAX;
    }
}

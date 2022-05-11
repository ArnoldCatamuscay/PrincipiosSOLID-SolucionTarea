/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.access;

import co.unicauca.parkinglot.domain.TypeEnum;
import co.unicauca.parkinglot.domain.Vehicle;
import co.unicauca.parkinglot.domain.service.Service;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author INGESIS
 * @version 1.0
 * @since 09/05/2022
 */
public class VehicleRepository implements IVehicleRepository {

    /**
     * Variable de conexión
     */
    private Connection conn;

    /**
     * Constructor para el repositorio de vehiculos
     */
    public VehicleRepository() {
        initDatabase();
    }

    /**
     * Metodo que guarda un vehiculo
     *
     * @param newProduct vehiculo el cual va a ser guardado
     * @return una respuesta booleana para determinar si se guardo o no
     * correctamente
     */
    @Override
    public boolean save(Vehicle newProduct) {
        try {
            //Validate product
            if (newProduct == null || newProduct.getPlaca().isBlank() || newProduct.getTipo().toString().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO Vehicle ( Placa, Tipo ) "
                    + "VALUES ( ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newProduct.getPlaca());
            pstmt.setString(2, newProduct.getTipo().toString());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Metodo que lista los vehiculos almacenados
     *
     * @return Un listado con todos los vehiculos almacenados
     */
    @Override
    public List<Vehicle> list() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {

            String sql = "SELECT Placa, Tipo FROM Vehicle";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vehicle newProduct = new Vehicle();
                newProduct.setPlaca(rs.getString("Placa"));
                newProduct.setTipo(TypeEnum.valueOf(rs.getString("Tipo")));

                vehicles.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicles;
    }

    /**
     * Metodo que inicializa la base de datos
     */
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Vehicle (\n"
                + "	Placa text PRIMARY KEY,\n"
                + "	Tipo text NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite la conexión a la base de datos
     */
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./mydatabase.db";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite la desconexión a la base de datos
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

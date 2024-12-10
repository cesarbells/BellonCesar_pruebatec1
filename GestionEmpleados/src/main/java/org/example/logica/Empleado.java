package org.example.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import java.text.SimpleDateFormat;


// Indica que esta clase es una entidad JPA que representa la tabla "Empleado" en la base de datos
@Entity
public class Empleado implements Serializable {

    // Atributos de la clase: representan las propiedades de un empleado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Especifica que el ID se generará automáticamente
    private Long id;  // ID único del empleado (clave primaria en la base de datos)

    private String nombreEmpleado;  // Nombre del empleado
    private String apellidoEmpleado;  // Apellido del empleado
    private String cargoEmpleado;  // Cargo o puesto que ocupa el empleado
    private float salarioEmpleado;  // Salario mensual del empleado

    // La anotación @Temporal se usa para almacenar solo la fecha sin la parte de la hora
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;  // Fecha de inicio del empleado en la empresa

    // Constructor por defecto, requerido por JPA
    public Empleado() {}

    // Constructor con parámetros, utilizado para inicializar un objeto Empleado con valores específicos
    public Empleado(String nombreEmpleado, String apellidoEmpleado, String cargoEmpleado, float salarioEmpleado, Date fechaInicio) {
        this.id = id;  // El ID se asigna automáticamente por JPA
        this.nombreEmpleado = nombreEmpleado;  // Inicializa el nombre del empleado
        this.apellidoEmpleado = apellidoEmpleado;  // Inicializa el apellido del empleado
        this.cargoEmpleado = cargoEmpleado;  // Inicializa el cargo del empleado
        this.salarioEmpleado = salarioEmpleado;  // Inicializa el salario del empleado
        this.fechaInicio = fechaInicio;  // Inicializa la fecha de inicio del empleado
    }

    // Métodos getter y setter para acceder y modificar los atributos de la clase

    // Getter para el ID
    public Long getId() {
        return id;
    }

    // Setter para el ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para el nombre del empleado
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    // Setter para el nombre del empleado
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    // Getter para el apellido del empleado
    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    // Setter para el apellido del empleado
    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    // Getter para el cargo del empleado
    public String getCargoEmpleado() {
        return cargoEmpleado;
    }

    // Setter para el cargo del empleado
    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
    }

    // Getter para el salario del empleado
    public float getSalarioEmpleado() {
        return salarioEmpleado;
    }

    // Setter para el salario del empleado
    public void setSalarioEmpleado(float salarioEmpleado) {
        this.salarioEmpleado = salarioEmpleado;
    }

    // Getter para la fecha de inicio del empleado
    public Date getFechaInicio() {
        return fechaInicio;
    }

    // Setter para la fecha de inicio del empleado
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format(
                "| %-10d | %-15s | %-15s | %-10s | %-10.2f | %-6s |",
                id, nombreEmpleado, apellidoEmpleado, cargoEmpleado, salarioEmpleado, sdf.format(fechaInicio)
        );
    }
}


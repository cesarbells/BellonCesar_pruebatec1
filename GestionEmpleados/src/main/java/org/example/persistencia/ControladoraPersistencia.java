package org.example.persistencia;
import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;  // Importación de las clases necesarias para el manejo de excepciones y el logging
import java.util.List;

public class ControladoraPersistencia {

    // Instancia de EmpleadoJpaController para manejar las operaciones CRUD de los empleados
    EmpleadoJpaController empleJPA = new EmpleadoJpaController();

    /**
     * Método para crear un nuevo empleado en la base de datos.
     * Utiliza el método 'create' de la clase EmpleadoJpaController.
     *
     * @param emp El objeto Empleado que se desea guardar en la base de datos.
     */
    public void crearEmpleado(Empleado emp) {
        empleJPA.create(emp);  // Llama al método 'create' para insertar el empleado en la base de datos
    }

    /**
     * Método para eliminar un empleado de la base de datos dado su ID.
     * Utiliza el método 'destroy' de la clase EmpleadoJpaController para realizar la eliminación.
     *
     * @param id El ID del empleado que se desea eliminar.
     */
    public void borrarEmpleado(Long id) {
        try {
            // Llama al método 'destroy' para eliminar el empleado de la base de datos
            empleJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            // Si el empleado no existe (excepción), se captura y se registra un mensaje de error en el log
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para obtener la lista de todos los empleados almacenados en la base de datos.
     *
     * @return Una lista de objetos Empleado con los datos de todos los empleados.
     */
    public List<Empleado> traerEmpleados() {
        // Llama al método 'findEmpleadoEntities' para obtener la lista de empleados
        return empleJPA.findEmpleadoEntities();
    }

    /**
     * Método para modificar la información de un empleado en la base de datos.
     * Utiliza el método 'edit' de la clase EmpleadoJpaController para realizar la actualización.
     *
     * @param empl El objeto Empleado con los datos actualizados.
     */
    public void modificarEmpleado(Empleado empl) {
        try {
            // Llama al método 'edit' para actualizar los datos del empleado en la base de datos
            empleJPA.edit(empl);
        } catch (Exception ex) {
            // Si ocurre algún error durante la actualización, se captura y se registra un mensaje de error en el log
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

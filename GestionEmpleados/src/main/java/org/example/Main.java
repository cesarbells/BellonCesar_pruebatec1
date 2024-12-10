package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Inicialización del scanner para leer entradas del usuario
        Scanner scanner = new Scanner(System.in);

        // Declaración de variables
        int opcion;                // Almacena la opción seleccionada por el usuario
        Long idEmpleado;           // Almacena el ID del empleado
        // Variables para almacenar la información del empleado
        String nombre = "", apellidos = "", cargo = "", fecha = "";
        Date fechaInicio;          // Variable para almacenar la fecha de inicio del empleado

        // Instancia del controlador de persistencia para interactuar con la base de datos
        ControladoraPersistencia controlEmpleados = new ControladoraPersistencia();

        // Bucle principal que se ejecuta hasta que el usuario decide salir
        do {
            // Menú de opciones para gestionar empleados
            System.out.println("Gestión de Empleados");
            System.out.println("--------------------------------------");
            System.out.println("1. Agregar un nuevo empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Actualizar información de un empleado");
            System.out.println("4. Eliminar un empleado");
            System.out.println("5. Buscar empleados por cargo");
            System.out.println("6. Salir");
            System.out.print("Escoja una opción: ");

            // Leer la opción seleccionada por el usuario
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            // Obtener la lista de empleados desde la base de datos
            List<Empleado> empleados = controlEmpleados.traerEmpleados();

            // Estructura de control según la opción seleccionada
            switch (opcion) {
                case 1:
                    // Opción 1: Agregar un nuevo empleado

                    float salario = 0; // Declarar la variable salario
                    Empleado empleadoNuevo = null; // Declarar empleadoNuevo

                    // Validación del nombre
                    while (true) {
                        System.out.print("Introduzca el nombre del empleado: ");
                        nombre = scanner.nextLine();
                        if (nombre.isEmpty()) {
                            System.out.println("El nombre no puede estar vacío. Intente de nuevo.");
                        } else {
                            break; // Si el nombre no está vacío, salimos del bucle
                        }
                    }

                    // Validación de los apellidos
                    while (true) {
                        System.out.print("Introduzca los apellidos del empleado: ");
                        apellidos = scanner.nextLine();
                        if (apellidos.isEmpty()) {
                            System.out.println("Los apellidos no pueden estar vacíos. Intente de nuevo.");
                        } else {
                            break; // Si los apellidos no están vacíos, salimos del bucle
                        }
                    }

                    // Validación del cargo
                    while (true) {
                        System.out.print("Introduzca el cargo del empleado: ");
                        cargo = scanner.nextLine();
                        if (cargo.isEmpty()) {
                            System.out.println("El cargo no puede estar vacío. Intente de nuevo.");
                        } else {
                            break; // Si el cargo no está vacío, salimos del bucle
                        }
                    }

                    // Validación del salario
                    while (true) {
                        System.out.print("Introduzca el salario del empleado: ");
                        String salarioInput = scanner.nextLine();

                        if (salarioInput.isEmpty()) {
                            System.out.println("El salario no puede estar vacío. Intente de nuevo.");
                        } else {
                            try {
                                salario = Float.parseFloat(salarioInput); // Convertir la entrada a float
                                if (salario <= 0) {
                                    System.out.println("El salario debe ser un valor positivo. Intente de nuevo.");
                                } else {
                                    // Salario válido, salir del bucle
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("El salario debe ser un número válido. Intente de nuevo.");
                            }
                        }
                    }

                    // Validación de la fecha
                    while (true) {
                        System.out.print("Introduce la fecha de inicio del empleado (formato dd/MM/yyyy): ");
                        fecha = scanner.nextLine();

                        if (fecha.isEmpty()) {
                            System.out.println("La fecha no puede estar vacía. Intente de nuevo.");
                        } else {
                            // Convertir la fecha de String a Date
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            fechaInicio = null;
                            try {
                                fechaInicio = sdf.parse(fecha);  // Convertir el String a Date
                                break;  // Si la conversión es exitosa, salimos del bucle
                            } catch (ParseException e) {
                                System.out.println("El formato de la fecha es incorrecto. Debe ser dd/MM/yyyy.");
                            }
                        }
                    }

                    // Crear un nuevo objeto Empleado con los datos proporcionados
                    empleadoNuevo = new Empleado(nombre, apellidos, cargo, salario, fechaInicio);

                    // Guardar el empleado en la base de datos (esto depende de tu implementación)
                    controlEmpleados.crearEmpleado(empleadoNuevo);

                    System.out.println("Empleado agregado exitosamente: " + empleadoNuevo.getNombreEmpleado());

                    break;

                case 2:
                    // Opción 2: Listar todos los empleados
                    if (empleados.isEmpty()) {
                        System.out.println("No se encontraron empleados.");
                    } else {
                        if (empleados.isEmpty()) {
                            System.out.println("No se encontraron empleados.");
                        } else {
                            System.out.println("Listado de empleados:");
                            System.out.println("-----------------------------------------------------------------------------------------");
                            for (Empleado empleado : empleados) {
                                System.out.println(empleado); // Llama automáticamente a toString()
                            }
                            System.out.println("-----------------------------------------------------------------------------------------");
                        }
                        break;
                    }
                    break;

                case 3:
                    // Opción 3: Actualizar la información de un empleado
                    System.out.print("Introduce el id del empleado a modificar: ");
                    idEmpleado = scanner.nextLong();
                    scanner.nextLine(); // Limpiar el buffer

                    // Comprobar si el idEmpleado es un valor no válido (por ejemplo, 0)
                    if (idEmpleado <= 0) {
                        System.out.println("El ID del empleado no puede ser 0.");
                    } else {
                        // Buscar el empleado por ID
                        List<Empleado> buscaempleados = controlEmpleados.traerEmpleados();
                        Empleado empleadoParaModificar = null;
                        for (Empleado empleado : buscaempleados) {
                            if (empleado.getId().equals(idEmpleado)) {
                                empleadoParaModificar = empleado;
                                break; // Sale del ciclo cuando encuentra el empleado
                            }
                        }

                        // Verificar si el empleado fue encontrado
                        if (empleadoParaModificar != null) {
                            String nuevoNombre;
                            //Solicitar nuevos valores para actualizar los datos del empleado
                            do {
                                System.out.print("Nuevo nombre: ");
                                nuevoNombre = scanner.nextLine().trim();
                                if (nuevoNombre.isEmpty()) {
                                    System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo.");
                                }
                            } while (nuevoNombre.isEmpty());

                            String nuevoApellido;
                            do {
                                System.out.print("Nuevo apellido: ");
                                nuevoApellido = scanner.nextLine().trim();
                                if (nuevoApellido.isEmpty()) {
                                    System.out.println("El apellido no puede estar vacío. Inténtalo de nuevo.");
                                }
                            } while (nuevoApellido.isEmpty());

                            String nuevoCargo;
                            do {
                                System.out.print("Nuevo cargo: ");
                                nuevoCargo = scanner.nextLine().trim();
                                if (nuevoCargo.isEmpty()) {
                                    System.out.println("El cargo no puede estar vacío. Inténtalo de nuevo.");
                                }
                            } while (nuevoCargo.isEmpty());

                            Double nuevoSalario = null;
                            do {
                                System.out.print("Nuevo salario: ");
                                if (scanner.hasNextDouble()) {
                                    nuevoSalario = scanner.nextDouble();
                                    scanner.nextLine(); // Limpiar el buffer
                                    if (nuevoSalario <= 0) {
                                        System.out.println("El salario debe ser un número positivo. Inténtalo de nuevo.");
                                        nuevoSalario = null;
                                    }
                                } else {
                                    System.out.println("Por favor, ingresa un número válido para el salario.");
                                    scanner.nextLine(); // Limpiar el buffer
                                }
                            } while (nuevoSalario == null);

                            // Actualizar los valores del empleado
                            empleadoParaModificar.setNombreEmpleado(nuevoNombre);
                            empleadoParaModificar.setApellidoEmpleado(nuevoApellido);
                            empleadoParaModificar.setCargoEmpleado(nuevoCargo);
                            empleadoParaModificar.setSalarioEmpleado(nuevoSalario.floatValue());

                            // Guardar los cambios en la base de datos
                            controlEmpleados.modificarEmpleado(empleadoParaModificar);
                            System.out.println("Empleado modificado exitosamente.");
                        } else {
                            System.out.println("No se encontró un empleado con el ID proporcionado.");
                        }
                    }
                    break;

                case 4:
                    // Opción 4: Eliminar un empleado
                    System.out.print("Introduzca el ID del empleado que quiere eliminar: ");
                    idEmpleado = scanner.nextLong();
                    scanner.nextLine();  // Limpiar el buffer

                    // Verificar si el ID es válido
                    if (idEmpleado <= 0) {
                        System.out.println("El ID del empleado no puede ser 0 o negativo.");
                    } else {
                        // Llamar al método para eliminar el empleado por su ID
                        controlEmpleados.borrarEmpleado(idEmpleado);
                        System.out.println("Empleado eliminado exitosamente.");
                    }
                    break;

                case 5:
                    // Opción 5: Buscar empleados por cargo
                    System.out.print("Ingresa el cargo para filtrar: ");
                    cargo = scanner.nextLine();

// Verificar si la cadena no está vacía
                    if (cargo.trim().isEmpty()) {
                        System.out.println("La cadena no puede estar vacía.");
                    } else {
                        // Filtrar empleados por cargo
                        List<Empleado> filtroempleados = controlEmpleados.traerEmpleados();
                        List<Empleado> empleadosFiltrados = new ArrayList<>();
                        for (Empleado empleado : filtroempleados) {
                            if (empleado.getCargoEmpleado().equalsIgnoreCase(cargo)) {
                                empleadosFiltrados.add(empleado);
                            }
                        }

                        // Mostrar empleados filtrados o mensaje si no se encuentran
                        if (empleadosFiltrados.isEmpty()) {
                            System.out.println("No se encontraron empleados con el cargo: " + cargo);
                        } else {
                            System.out.println("Listado de empleados con el cargo: " + cargo);
                            System.out.println("-----------------------------------------------------------------------------------------");
                            for (Empleado empl : empleadosFiltrados) {
                                System.out.println(empl); // Aquí se utiliza el método toString()
                            }
                            System.out.println("-----------------------------------------------------------------------------------------");

                        }
                    }

                    break;

                case 6:
                    // Opción 6: Salir del sistema
                    System.out.println("Ha decidido salir del sistema.");
                    break;

                default:
                    // Si el usuario elige una opción no válida
                    System.out.println("Ha escogido una opción incorrecta. Inténtelo de nuevo.");
            }
        } while (opcion != 6);  // Continuar hasta que el usuario seleccione la opción 6 (salir)

        // Cerrar el scanner al finalizar
        scanner.close();
    }
}

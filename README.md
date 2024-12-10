Este código implementa una aplicación de consola en Java que permite gestionar un sistema básico de empleados. Utiliza JPA (Java Persistence API) para la interacción con la base de datos a través de una clase denominada ControladoraPersistencia. Aquí tienes una descripción detallada de su funcionamiento:

<h2><b>Estructura General</b></h2>
<h3><b>Clases Utilizadas:</b></h3>

Empleado: Representa a un empleado con atributos como nombre, apellidos, cargo, salario y fecha de inicio.
ControladoraPersistencia: Es el controlador encargado de manejar la persistencia de datos en la base de datos.
Entradas del Usuario: Utiliza Scanner para recibir datos ingresados por el usuario.

Opciones del Menú: Un menú interactivo guía al usuario para realizar distintas operaciones sobre los empleados:
<ol>
<li><b> Agregar un nuevo empleado.</b></li>
<li><b>Listar empleados.</b></li>
<li><b>Actualizar información de un empleado.</b></li>
<li><b>Eliminar un empleado.</b></li>
<li><b>Buscar empleados por cargo.</b></li>
<li><b>Salir.</b></li>
</ol>
<h3><b>Descripción de las Operaciones</b></h3>
<b>1. Agregar un Nuevo Empleado</b>
<p></p>Se solicita al usuario ingresar datos como nombre, apellidos, cargo, salario y fecha de inicio.
Cada entrada es validada para asegurar que los datos sean correctos:
Cadena no vacía para nombre, apellidos y cargo.
Salario como un valor positivo.
Fecha en formato dd/MM/yyyy, convertida a un objeto Date.
Una vez recopilada y validada la información, se crea un objeto Empleado, que se guarda en la base de datos mediante controlEmpleados.crearEmpleado.</p>
<b>2. Listar Empleados</b>
<p></p>Recupera todos los empleados de la base de datos utilizando controlEmpleados.traerEmpleados.
Si no hay empleados registrados, muestra un mensaje indicándolo.
Si hay empleados, se imprimen en consola los detalles de cada uno utilizando su método toString.</p>
<b>3. Actualizar Información de un Empleado</b>b>
<p></p>Solicita al usuario el id del empleado que desea modificar.
Busca al empleado en la lista recuperada desde la base de datos.
Si el empleado existe, solicita nuevos datos (nombre, apellidos, cargo, salario) para actualizar su información, validando cada entrada.
Los cambios se aplican al objeto Empleado correspondiente y se persisten en la base de datos mediante controlEmpleados.modificarEmpleado.</p>
<b>4. Eliminar un Empleado</b>
<p></p>Solicita al usuario el id del empleado que desea eliminar.
Si el id es válido, llama al método controlEmpleados.borrarEmpleado para eliminar el registro de la base de datos.</p>
<b>5. Buscar Empleados por Cargo</b>
Solicita al usuario un cargo como criterio de búsqueda.
<p></p>Filtra la lista de empleados para encontrar aquellos cuyo cargo coincide (ignorando mayúsculas/minúsculas).
Si encuentra empleados, los muestra en consola. De lo contrario, indica que no hay coincidencias.</p>
<b>6. Salir</b>
<p>Finaliza el programa</p>

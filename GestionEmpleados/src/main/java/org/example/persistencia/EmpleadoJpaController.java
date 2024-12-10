package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class EmpleadoJpaController implements Serializable {

    // Constructor que recibe una fábrica de EntityManager
    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;  // Inicializa el EntityManagerFactory
    }

    // Constructor por defecto que crea un EntityManagerFactory
    public EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("jpaPU");  // Crea el EntityManagerFactory con la unidad de persistencia 'jpaPU'
    }

    // Atributo para la fábrica de EntityManager
    private EntityManagerFactory emf = null;

    /**
     * Obtiene el EntityManager asociado con la fábrica de EntityManager
     *
     * @return El EntityManager que maneja las transacciones en la base de datos
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();  // Crea y retorna un nuevo EntityManager
    }

    /**
     * Método para crear un nuevo empleado en la base de datos.
     *
     * @param empleado El objeto Empleado que se quiere persistir
     */
    public void create(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();  // Obtiene el EntityManager
            em.getTransaction().begin();  // Inicia la transacción
            em.persist(empleado);  // Persiste el objeto empleado en la base de datos
            em.getTransaction().commit();  // Realiza el commit de la transacción
        } finally {
            if (em != null) {
                em.close();  // Cierra el EntityManager después de la operación
            }
        }
    }

    /**
     * Método para modificar un empleado en la base de datos.
     *
     * @param empleado El objeto Empleado con los datos modificados
     * @throws NonexistentEntityException Si el empleado no existe en la base de datos
     * @throws Exception Si ocurre cualquier otro error durante la modificación
     */
    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();  // Obtiene el EntityManager
            em.getTransaction().begin();  // Inicia la transacción
            empleado = em.merge(empleado);  // Fusiona los cambios del empleado en la base de datos
            em.getTransaction().commit();  // Realiza el commit de la transacción
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = empleado.getId();  // Obtiene el ID del empleado modificado
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("El empleado con id " + id + " no existe.");  // Lanza una excepción si el empleado no existe
                }
            }
            throw ex;  // Lanza la excepción si ocurre un error durante la modificación
        } finally {
            if (em != null) {
                em.close();  // Cierra el EntityManager después de la operación
            }
        }
    }

    /**
     * Método para eliminar un empleado de la base de datos.
     *
     * @param id El ID del empleado a eliminar
     * @throws NonexistentEntityException Si el empleado no existe en la base de datos
     */
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();  // Obtiene el EntityManager
            em.getTransaction().begin();  // Inicia la transacción
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);  // Obtiene una referencia al empleado con el ID especificado
                empleado.getId();  // Accede al ID del empleado para verificar que existe
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El empleado con id " + id + " no existe.", enfe);  // Lanza una excepción si el empleado no se encuentra
            }
            em.remove(empleado);  // Elimina el empleado de la base de datos
            em.getTransaction().commit();  // Realiza el commit de la transacción
        } finally {
            if (em != null) {
                em.close();  // Cierra el EntityManager después de la operación
            }
        }
    }

    /**
     * Método para obtener todos los empleados de la base de datos.
     *
     * @return Una lista con todos los empleados
     */
    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);  // Llama al método privado para obtener todos los empleados
    }

    /**
     * Método para obtener una lista de empleados con un número máximo de resultados.
     *
     * @param maxResults El número máximo de resultados a retornar
     * @param firstResult El primer resultado a retornar
     * @return Una lista de empleados con los parámetros especificados
     */
    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);  // Llama al método privado para obtener los empleados con paginación
    }

    /**
     * Método privado para obtener empleados con paginación.
     *
     * @param all Si se deben obtener todos los empleados o solo los limitados por los parámetros
     * @param maxResults El número máximo de resultados a retornar
     * @param firstResult El primer resultado a retornar
     * @return Una lista de empleados con los parámetros especificados
     */
    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();  // Obtiene el EntityManager
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();  // Crea una consulta con Criteria API
            cq.select(cq.from(Empleado.class));  // Selecciona la entidad Empleado
            Query q = em.createQuery(cq);  // Crea la consulta
            if (!all) {
                q.setMaxResults(maxResults);  // Si no es todos, establece el número máximo de resultados
                q.setFirstResult(firstResult);  // Establece el primer resultado para la paginación
            }
            return q.getResultList();  // Ejecuta la consulta y retorna los resultados
        } finally {
            em.close();  // Cierra el EntityManager después de la operación
        }
    }

    /**
     * Método para encontrar un empleado por su ID.
     *
     * @param id El ID del empleado a buscar
     * @return El empleado con el ID especificado o null si no se encuentra
     */
    public Empleado findEmpleado(Long id) {
        EntityManager em = getEntityManager();  // Obtiene el EntityManager
        try {
            return em.find(Empleado.class, id);  // Busca el empleado por su ID
        } finally {
            em.close();  // Cierra el EntityManager después de la operación
        }
    }

    /**
     * Método para obtener el número total de empleados en la base de datos.
     *
     * @return El número total de empleados
     */
    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();  // Obtiene el EntityManager
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();  // Crea una consulta con Criteria API
            Root<Empleado> rt = cq.from(Empleado.class);  // Obtiene la raíz de la consulta para la entidad Empleado
            cq.select(em.getCriteriaBuilder().count(rt));  // Cuenta el número de empleados
            Query q = em.createQuery(cq);  // Crea la consulta
            return ((Long) q.getSingleResult()).intValue();  // Ejecuta la consulta y convierte el resultado en un número entero
        } finally {
            em.close();  // Cierra el EntityManager después de la operación
        }
    }
}

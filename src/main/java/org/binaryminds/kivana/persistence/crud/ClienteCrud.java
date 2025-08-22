package org.binaryminds.kivana.persistence.crud;

import org.binaryminds.kivana.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ClienteCrud extends JpaRepository<Cliente, Integer> {
    //puede sustituir al DAO
    //esta interfaz genera todos los metodos genericos del CRUD

}

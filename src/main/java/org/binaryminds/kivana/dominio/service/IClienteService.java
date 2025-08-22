package org.binaryminds.kivana.dominio.service;

import org.binaryminds.kivana.persistence.entity.Cliente;

import java.util.List;

public interface IClienteService {
    //firmas de metodo
     List<Cliente> listarClientes();
     Cliente buscarClientePorId(Integer codigo);
     void guardarCiente(Cliente cliente);
     void eliminarCliente(Cliente cliente);

}

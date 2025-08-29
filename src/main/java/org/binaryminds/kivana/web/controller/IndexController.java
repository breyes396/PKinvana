package org.binaryminds.kivana.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.binaryminds.kivana.dominio.service.IClienteService;
import org.binaryminds.kivana.persistence.entity.Cliente;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();
    }

    public void guardarCliente(){
        logger.info("Cliente a guardar: " + this.clienteSeleccionado);
        //agregar (insert)
        if (this.clienteSeleccionado.getCodigoCliente() == null){
            this.clienteService.guardarCiente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente agregado"));
        }

        //modificar (update)
        else {
            this.clienteService.guardarCiente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente actualizado"));

        }
        //ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        //actualizar la tabla con un metodo AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente","formulario-clientes:tabla-clientes");
        //reset del cliente seleccionado
        this.clienteSeleccionado=null;
    }

    public void eliminarCliente(){
        //Mostrar en consola
        logger.info("Cliente a eliminar: "+this.clienteSeleccionado);
        //llamar a nuestro servicio de emilinar de Clientes
        this.clienteService.eliminarCliente(clienteSeleccionado);
        //Eliminarlo de la lista clientes
        this.clientes.remove(clienteSeleccionado);
        //Limpiar nuestro cliente seleccionado
        this.clienteSeleccionado=null;
        //enviar un mensaje emergente
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado"));
        //Actualizar la tabla con AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente", "formulario-clientes:tabla-clientes");
    }
}

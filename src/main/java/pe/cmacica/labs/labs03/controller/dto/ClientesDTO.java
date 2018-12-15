package pe.cmacica.labs.labs03.controller.dto;


import pe.cmacica.labs.labs03.dominio.Cliente;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClientesDTO {

    @NotNull
    @Valid
    private List<Cliente> clientes;


    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}

package pe.cmacica.labs.labs03.controller.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import pe.cmacica.labs.labs03.dominio.Cliente;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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

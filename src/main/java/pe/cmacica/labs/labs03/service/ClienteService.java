package pe.cmacica.labs.labs03.service;

import pe.cmacica.labs.labs03.dominio.Cliente;

import java.util.List;

public interface ClienteService {


    List<Cliente> listar();
    Cliente getCliente(int id);
    int eliminar(int id);
    int update(Cliente cliente);

    void insert(Cliente cliente);
}

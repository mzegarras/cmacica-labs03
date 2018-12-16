package pe.cmacica.labs.labs03.service;

import pe.cmacica.labs.labs03.dominio.Cliente;

import java.util.List;

public interface ClienteService {


    List<Cliente> listar();
    Cliente getCliente(int id);

    void notify(Cliente cliente,String routing);

    void eliminarNotify(Cliente cliente);
    int eliminar(Cliente cliente);

    int update(Cliente cliente);
    void updateNotify(Cliente cliente);

    void insert(Cliente cliente);
    void insertNotify(Cliente cliente);
    void insert(List<Cliente> clientes);

    void transferir(String cuentaOrigen,String cuentaDestino,double monto);
}

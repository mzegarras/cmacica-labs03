package pe.cmacica.labs.labs03.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listar() {
        return clienteRepository.listar();
    }

    @Override
    public Cliente getCliente(int id) {
        return clienteRepository.getCliente(id);
    }

    @Override
    public int eliminar(int id) {
        return clienteRepository.eliminar(id);
    }

    @Override
    public int update(Cliente cliente) {
        return clienteRepository.update(cliente);
    }

    @Override
    public void insert(Cliente cliente) {

        clienteRepository.insert(cliente);

    }

















}

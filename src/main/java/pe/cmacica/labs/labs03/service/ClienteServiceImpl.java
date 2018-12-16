package pe.cmacica.labs.labs03.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.cmacica.labs.labs03.config.RabbitConfig;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    //@Autowired
    public ClienteServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }



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
    public void eliminarNotify(int id) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_CLIENTES,
                RabbitConfig.EXCHANGE_CLIENTES_DELETE,
                id);
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
    public void updateNotify(Cliente cliente) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_CLIENTES,
                RabbitConfig.EXCHANGE_CLIENTES_UPDATE,
                cliente);
    }

    @Override
    public void insert(Cliente cliente) {

        //this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_CLIENTES_CREATE, cliente);



        clienteRepository.insert(cliente);

    }

    @Override
    public void insertNotify(Cliente cliente) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_CLIENTES,
                RabbitConfig.EXCHANGE_CLIENTES_CREATE,
                cliente);
    }

    @Override
    @Transactional
    public void insert(List<Cliente> clientes) {


        clientes.forEach(cliente -> {

            clienteRepository.insert(cliente);


            if(cliente.getPaterno().equalsIgnoreCase("ZEGARRA")){
                throw new RuntimeException();
            }

        });



    }

    @Transactional
    @Override
    public void transferir(String cuentaOrigen, String cuentaDestino, double monto) {

        clienteRepository.debitarCuenta(cuentaOrigen,monto);

        clienteRepository.abonarCuenta(cuentaDestino,monto);

    }


}

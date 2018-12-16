package pe.cmacica.labs.labs03.Listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.cmacica.labs.labs03.config.RabbitConfig;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.service.ClienteService;

@Component
public class ClienteDeleteListener {

    static final Logger logger = LoggerFactory.getLogger(ClienteDeleteListener.class);


    @Autowired
    private ClienteService clienteService;


    @RabbitListener(queues = RabbitConfig.QUEUE_CLIENTES_DELETE)
    public void processOrder(Cliente cliente) {
        clienteService.eliminar(cliente);
        logger.info("Delete: {}",cliente);
    }

}

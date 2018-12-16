package pe.cmacica.labs.labs03.Listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.cmacica.labs.labs03.config.RabbitConfig;
import pe.cmacica.labs.labs03.dominio.Operation;
import pe.cmacica.labs.labs03.service.ApplicationService;
import pe.cmacica.labs.labs03.service.ClienteService;

@Component
public class ApplicationListeners{
        static final Logger logger = LoggerFactory.getLogger(ClienteCreateListener.class);

        @Autowired
        private ClienteService clienteService;

        @Autowired
        private ApplicationService applicationService;

        @RabbitListener(queues = RabbitConfig.QUEUE_APPLICATIONS)
        public void processOrder(final Operation operation) {

            logger.info("Received getId {}", operation.getTxId());
            logger.info("Received getStatus: {}", operation.getStatus());

            applicationService.save(operation.getTxId(),operation);


        }

}

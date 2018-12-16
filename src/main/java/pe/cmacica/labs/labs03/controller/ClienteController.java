package pe.cmacica.labs.labs03.controller;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.cmacica.labs.labs03.controller.dto.ClientesDTO;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.service.ClienteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public HttpEntity<List<Cliente>> listar(){

        List<Cliente> list = clienteService.listar();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public HttpEntity<Cliente> listar(@PathVariable("id") int id){


        if(id==5){
            return ResponseEntity.notFound().build();
        }

        Cliente cliente =  clienteService.getCliente(id);

        return ResponseEntity.ok(cliente);

    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);


    @PostMapping("/batch")
    public HttpEntity<String> guardar(@Valid @RequestBody ClientesDTO clientesDTO){

        clienteService.insert(clientesDTO.getClientes());

        return ResponseEntity.ok().build();

    }


    @PostMapping
    public HttpEntity<String> guardar(@RequestHeader(name = "X-CORRELATION", required = false) String correlationId,
                                      @Valid @RequestBody Cliente cliente){

        LOGGER.debug("GUARDAR");

        /*
        if(StringUtils.isBlank(cliente.getNombres())){
            return ResponseEntity.badRequest().build();
        }*/

        cliente.setTxId(correlationId);
        clienteService.insertNotify(cliente);


        LOGGER.debug("{}",cliente.getId());
        LOGGER.debug(cliente.getNombres());


        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("applications/correlations/{correlationId}")
                .buildAndExpand(cliente.getTxId()).toUri();

        return ResponseEntity.accepted().location(location).build();



        //return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public HttpEntity<String> guardar(
                                      @PathVariable("id") int id,
                                      @Valid @RequestBody Cliente cliente){

        LOGGER.debug("UPDATE");
        if(id==0){
            return ResponseEntity.badRequest().build();
        }

        LOGGER.debug("{}",cliente.getId());
        LOGGER.debug(cliente.getNombres());


        cliente.setId(id);
        clienteService.updateNotify(cliente);

        //return ResponseEntity.accepted().build();
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public HttpEntity<String> eliminar(@PathVariable("id") int id){

        LOGGER.debug("DELETE");

        if(id==0){
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente = new Cliente();
        cliente.setId(id);

        clienteService.eliminarNotify(cliente);

        //return ResponseEntity.accepted().build();
        return ResponseEntity.ok().build();

    }



}

package pe.cmacica.labs.labs03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.cmacica.labs.labs03.dominio.Operation;
import pe.cmacica.labs.labs03.service.ApplicationService;


@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;


    @GetMapping("/correlations/{id}")
    public HttpEntity<Operation> listar(@PathVariable("id") String id){

        Operation operation = applicationService.get(id);

        return ResponseEntity.ok(operation);
    }

}

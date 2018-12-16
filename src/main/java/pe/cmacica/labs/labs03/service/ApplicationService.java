package pe.cmacica.labs.labs03.service;

import pe.cmacica.labs.labs03.dominio.Operation;

public interface ApplicationService {


    void save(String key, Operation operation);
    Operation get(String key);

}

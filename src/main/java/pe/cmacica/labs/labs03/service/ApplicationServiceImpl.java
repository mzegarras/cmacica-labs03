package pe.cmacica.labs.labs03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pe.cmacica.labs.labs03.dominio.Operation;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(String key, Operation operation) {


        HashOperations<String, String, Operation> hashOperations = redisTemplate.opsForHash();

        hashOperations.put("TX", operation.getTxId(), operation);

    }

    @Override
    public Operation get(String key) {
        HashOperations<String, String, Operation> hashOperations = redisTemplate.opsForHash();

        return hashOperations.get("TX",key);
    }


}

package com.yuliia.khoptiak.assistant.server.service;

import com.yuliia.khoptiak.assistant.server.entity.Assistant;
import com.yuliia.khoptiak.assistant.server.repository.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantServiceImpl implements AssistantService {

    @Autowired
    private AssistantRepository repository;

    public List<Assistant> getAll() {
        return repository.findAll();
    }

    public Assistant getByID(long id) {
        return repository.findOne(id);
    }

    public Assistant save(Assistant assistant) {
        return repository.saveAndFlush(assistant);
    }

    public void remove(long id) {
        repository.delete(id);

    }
}

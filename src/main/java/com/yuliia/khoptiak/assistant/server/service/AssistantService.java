package com.yuliia.khoptiak.assistant.server.service;

import com.yuliia.khoptiak.assistant.server.entity.Assistant;

import java.util.List;

public interface AssistantService {
    List<Assistant> getAll();
    Assistant getByID(long id);
    Assistant save(Assistant assistant);
    void  remove(long id);
}

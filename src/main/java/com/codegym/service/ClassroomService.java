package com.codegym.service;

import com.codegym.model.Classroom;

public interface ClassroomService {
    Iterable<Classroom> findAll();
    Classroom findbyId(Long id);
    void save(Classroom classroom);
    void remove(Long id);
}

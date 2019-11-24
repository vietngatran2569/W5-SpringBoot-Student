package com.codegym.service;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<Student> findAll(Pageable pageable);

    Student findById(Long id);

    void save(Student student);

    void remove(Long id);

    Page<Student> findAllByFirstNameContaining(String firstName, Pageable pageable);
    Iterable<Student> findAllByClassroom(Classroom classroom);

    Page<Student> findAllByClassroom(Pageable pageable, Classroom classroom);
    Page<Student> findAllByOrderByFirstNameAsc(Pageable pageable);
    Page<Student> findAllByOrderByFirstNameDesc(Pageable pageable);
}

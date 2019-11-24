package com.codegym.service.impl;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import com.codegym.repository.StudentRepository;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
         studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findAllByFirstNameContaining(String firstName, Pageable pageable) {
        return studentRepository.findAllByFirstNameContaining(firstName,pageable);
    }

    @Override
    public Iterable<Student> findAllByClassroom(Classroom classroom) {
        return studentRepository.findAllByClassroom(classroom);
    }

    @Override
    public Page<Student> findAllByClassroom(Pageable pageable, Classroom classroom) {
        return studentRepository.findAllByClassroom(pageable,classroom);
    }

    @Override
    public Page<Student> findAllByOrderByFirstNameAsc(Pageable pageable) {
        return studentRepository.findAllByOrderByFirstNameAsc(pageable);
    }

    @Override
    public Page<Student> findAllByOrderByFirstNameDesc(Pageable pageable) {
        return studentRepository.findAllByOrderByFirstNameDesc(pageable);
    }
}

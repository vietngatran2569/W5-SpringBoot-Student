package com.codegym.controller;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import com.codegym.service.ClassroomService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ClassroomService classroomService;

    @ModelAttribute("classrooms")
    public Iterable<Classroom> classrooms() {
        return classroomService.findAll();
    }

    @GetMapping("/students")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5) Pageable pageable) {
        Page<Student> students;
        if (s.isPresent()) {
            students = studentService.findAllByFirstNameContaining(s.get(), pageable);
        } else {
            students = studentService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create-student")
    public ModelAndView saveCustomer(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("message", "New student created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            ModelAndView modelAndView = new ModelAndView("student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-student")
    public ModelAndView updateCustomer(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("student/edit");
        modelAndView.addObject("student", student);
        modelAndView.addObject("message", "Student Update successful");
        return modelAndView;
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            ModelAndView modelAndView = new ModelAndView("student/delete");
            modelAndView.addObject("student", student);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@ModelAttribute("student") Student student) {
        studentService.remove(student.getId());
        return "redirect:students";
    }

    @GetMapping("/searchByClass")
    public ModelAndView getBookByClass(@RequestParam("search") String search, Pageable pageable){
        Classroom classroom = classroomService.findbyId(Long.parseLong(search));
        Page<Student> students = studentService.findAllByClassroom(pageable,classroom);
        ModelAndView modelAndView = new ModelAndView("student/list");
        modelAndView.addObject("students",students);
        modelAndView.addObject("search",search);
        return modelAndView;
    }

    @GetMapping("/sortByNameAsc")
    public ModelAndView getStudentSortByNameAsc(Pageable pageable){
        Page<Student> students = studentService.findAllByOrderByFirstNameAsc(pageable);
        ModelAndView modelAndView = new ModelAndView("student/list");
        modelAndView.addObject("students",students);
        return modelAndView;
    }

    @GetMapping("/sortByNameDesc")
    public ModelAndView getStudentSortByPriceDesc(Pageable pageable){
        Page<Student> students = studentService.findAllByOrderByFirstNameDesc(pageable);
        ModelAndView modelAndView = new ModelAndView("student/list");
        modelAndView.addObject("students",students);
        return modelAndView;
    }
}

package com.lluqteq.demoShell.controller;

import com.lluqteq.demoShell.domain.Student;
import com.lluqteq.demoShell.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller("studentController")
@RequestScope
public class StudentController {


    public static final String studentPage = "studentProfile.xhtml";

    private StudentService service;

    private String name;

    private String surname;

    private String cellphoneNumber;

    private Date dateOfBirth;



    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    private void logError(String message) {
        printFacesMessage(message);
    }

    private void printFacesMessage(String message) {
        printFacesMessage(message, null);
    }

    private void printFacesMessage(String message, String ...args) {
        //use placeholders %1s, %2s...
        final String messageWithArgs = String.format(message, args);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(messageWithArgs, message));
    }

    public List<String> getStudentIds() {
        return getAllStudents().stream().map(student -> student.getId()).collect(Collectors.toList());
    }
    public List<String> getStudentNames() {
        return getAllStudents().stream().map(student -> student.getName() + " " + student.getSurname()).collect(Collectors.toList());
    }

    public List<Student> getAllStudents() {
        return service.findAllStudents();
    }

    public Student getStudentById(String studentId){
        return service.findById(studentId).orElse(null);
    }

    public String addStudent(){
        final Student newStudent = new Student();
        newStudent.setName(getName());
        newStudent.setSurname(getSurname());
        newStudent.setCellphoneNumber(getCellphoneNumber());
        newStudent.setDateOfBirth(getDateOfBirth());

        final String generatedStudentId = service.generateStudentId(getName(), getSurname());
        newStudent.setId(generatedStudentId);

        service.save(newStudent);
        printFacesMessage("Saved Student - %1s %2s", name, surname);
        return studentPage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

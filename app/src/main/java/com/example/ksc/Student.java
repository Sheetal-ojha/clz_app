package com.example.ksc;

public class Student {

    private String name, email, studentId, stdClass, phone, password;

    public Student(String name, String email, String studentId, String stdClass, String phone, String password) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.stdClass = stdClass;
        this.phone = phone;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getStudentId() { return studentId; }
    public String getStdClass() { return stdClass; }
    public String getPhone() { return phone; }
    public String getPassword() { return password; }

    public char[] getId() {
        return new char[0];
    }

    public Object getGrade() {
        return null;}
    }

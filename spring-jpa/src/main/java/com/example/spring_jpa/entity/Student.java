package com.example.spring_jpa.entity;

import jakarta.persistence.*;

@Entity // mark the class as entity class
@Table(name="student") // it tells this class corresponds to 'student' table in database
public class Student {
    /* Fields */

    @Id // because this is the primary key column
    @GeneratedValue(strategy=GenerationType.IDENTITY) // it tells that Id is generated and managed by database
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    /* Constructors */

    public Student() {} // no-argument constructor is mandatory

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /* Getters */

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    /* Setters */

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.example.spring_rest.entity;

import jakarta.persistence.*;

// This class works as POJO class and Entity class.
@Entity // marks the class as entity, meaning it maps to a table in db.
@Table(name="employee") // it tells this class is map `employee` table in db.
public class Employee {
    /* Fields */

    @Id // mark this field as unique identifier of the class.
    @GeneratedValue(strategy=GenerationType.IDENTITY) // specify how ID value generated, in this case managed by db
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    /* Constructors */

    public Employee() {} // no-argument constructor is required by jpa

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /* Getters */

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() {return lastName; }
    public String getEmail() {return email; }

    /* Setters */

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) {this.email = email; }
    public void setId(int id) {this.id = id; }

    // this method is useful during print the object
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
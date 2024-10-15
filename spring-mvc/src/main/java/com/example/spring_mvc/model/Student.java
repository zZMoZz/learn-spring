package com.example.spring_mvc.model;


import com.example.spring_mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

import java.util.List;

public class Student {
    // Add validation rules
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 18, message = ">=18")
    @Max(value = 60, message = "<=60")
    private Integer age;

    private String country;

    private String favLang;

    private List<String> favOS;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Exactly 5 chars")
    private String postalCode;

    // here I use default values, you can reassign them easily
    // @CourseCode(value="CCC", message="please start with CCC")
    @CourseCode
    private String courseCode;

    public Student() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavLang() {
        return favLang;
    }

    public void setFavLang(String favLang) {
        this.favLang = favLang;
    }

    public List<String> getFavOS() {
        return favOS;
    }

    public void setFavOS(List<String> favOS) {
        this.favOS = favOS;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}

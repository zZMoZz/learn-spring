package com.example.advanced_spring_jpa.model;

import jakarta.persistence.*;

@Entity // mark this class as entity which map a table. and add this class in component scanning.
@Table(name = "instructor_detail")
public class InstructorDetails {

    /* Fields */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    /* Relations */

    // transform unidirectional relation to bidirectional.
    // this approach indicate that InstructorDetails is inverse side of this relation, while Instructor is owning side.
    // `mappedBy` tells spring to look at "instructorDetails" property in Instructor class
    // and use information of `@JoinColumn` to help find associated instructor.
    @OneToOne(mappedBy = "instructorDetails",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    /* Constructors */

    public InstructorDetails() {} // no-argument constructor is required for jpa.

    public InstructorDetails(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    /* Getters & Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /* toString */

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby;
    }
}

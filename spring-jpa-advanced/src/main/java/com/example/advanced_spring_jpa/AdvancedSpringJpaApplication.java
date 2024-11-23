package com.example.advanced_spring_jpa;

import com.example.advanced_spring_jpa.dao.AppDao;
import com.example.advanced_spring_jpa.model.Course;
import com.example.advanced_spring_jpa.model.Instructor;
import com.example.advanced_spring_jpa.model.InstructorDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedSpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedSpringJpaApplication.class, args);
	}

	// this bean runs after all beans loaded and spring boot app has started.
	// it returns a single method, which will be executed on application startup.
	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			createInstructor(appDao);
			//removeInstructor(appDao);
			//retrieveInstructor(appDao);
			//retrieveInstructorDetails(appDao);
			//removeInstructorDetails(appDao);
			//createInstructorWithCourses(appDao);
		};
	}

	private void createInstructorWithCourses(AppDao appDao) {
		// create instructor
		Instructor instructor = new Instructor("new", "new", "new@gmail.com");

		// create instructor details
		InstructorDetails instructorDetails = new InstructorDetails("new", "new");

		// add instructor details reference to instructor
		instructor.setInstructorDetails(instructorDetails);

		// create courses
		Course course1 = new Course("course1");
		Course course2 = new Course("course2");

		// add courses to instructor
		instructor.addCourse(course1); // this will add course to courses list in instructor
		instructor.addCourse(course2); // and add instructor to course.

		// save instructor, so save related entities also
		// 	- will save all courses that in the list.
		//  - will save details that in instructorDetails field.
		appDao.save(instructor);
	}


	private void removeInstructorDetails(AppDao appDao) {
		appDao.deleteInstructorDetailsById(5);
	}

	private void retrieveInstructorDetails(AppDao appDao) {
		InstructorDetails instructorDetails = appDao.findInstructorDetailsById(2);
		System.out.println(instructorDetails);
		System.out.println(instructorDetails.getInstructor());
	}

	private void retrieveInstructor(AppDao appDao) {
		// it will retrieve instructor and instructor details immediately.
		Instructor instructor = appDao.findInstructorById(2);
		System.out.println(instructor);
	}

	private void removeInstructor(AppDao appDao) {
		appDao.deleteInstructorById(2);
	}

	private void createInstructor(AppDao appDao) {
		// create instructor
		Instructor instructor = new Instructor("new", "new", "new@gmail.com");

		// create instructor details
		InstructorDetails instructorDetails = new InstructorDetails("new", "new");

		// connect them
		//instructor.setInstructorDetails(instructorDetails);

		instructorDetails.setInstructor(instructor);

		// save instructor & instructor details
		appDao.saveInstructorDetails(instructorDetails);
	}
}

package com.example.spring_jpa;

import com.example.spring_jpa.dao.StudentDAO;
import com.example.spring_jpa.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Bean // this is special bean. since it work automatically after app start
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) { // inject StudentDAO
		return runner -> {                                              // will inject automatically 'StudentDAOImpl'
			System.out.println("Hello");
			//createStudent(studentDAO);                                // once there no other bean implement 'StudentDAO'
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(1);

		studentDAO.deleteByLastName("CC");
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student
		Student student = studentDAO.findById(1);
		// modify it
		student.setFirstName("Mona");
		// update database with modification
		studentDAO.update(student);

		// update last name of a student
		studentDAO.updateLastName(2, "Karim");

	}

	private void readStudent(StudentDAO studentDAO) {
		// read one student
		Student student = studentDAO.findById(1);
		System.out.println(student);

		// read all students
		List<Student> students1 = studentDAO.findAllStudents();
		for (Student s : students1)
			System.out.println(s);

		// read student by last name or first name
		List<Student> students2 = studentDAO.findByFirstNameOrLastName("A", "Mohsen");
		for (Student s : students2)
			System.out.println(s);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create student object
		Student student = new Student("Mohamed", "Mohsen", "rr@gmail.com");

		// save student object
		studentDAO.save(student);

		// display id of it
		System.out.println("Id:" + student.getId());
	}
	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		Student student1 = new Student("A", "AA", "A@GG");
		Student student2 = new Student("B", "BB", "B@GG");
		Student student3 = new Student("C", "CC", "C@GG");

		// save them to database throw DAO interface methods
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}
}

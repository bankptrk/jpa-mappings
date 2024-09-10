package com.bankptrk.cruddemo;

import com.bankptrk.cruddemo.dao.AppDAO;
import com.bankptrk.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 3; // student id from database
		System.out.println("Delete student id: " + theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done!");

	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 3; // student id from database
		Student student = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Finding student id: " + theId);
		student.addCourse(new Course("Rubik's Cube - How to Speed Cube"));
		student.addCourse(new Course("Atrai 2600 - Game Development"));

		System.out.println("Saving student: " + student);
		System.out.println("associated courses: " + student.getCourses());

		appDAO.update(student);
		System.out.println("Done");

	}

	private void findStudentAndCourse(AppDAO appDAO) {
		int theId = 3; // student id from database
		Student student = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Loaded student: " + student);
		System.out.println("Courses: " + student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 11; // course id from database
		Course course = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: " + course);
		System.out.println("Students: " + course.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("Spiderman");
		course.addStudent(new Student("John","Doe","john@gmail.com"));
		course.addStudent(new Student("Peter","Parker","peter@gmail.com"));

		System.out.println("Saving the course: " + course);
		System.out.println("associated students: " + course.getStudents());

		appDAO.save(course);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10; // course id from database
		System.out.println("Deleteing course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10; // course id from database
		Course course = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println(course);
		System.out.println(course.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course course = new Course("How to fly like bird");
		// add some review
		course.addReview(new Review("Great courses ... loved it!"));
		course.addReview(new Review("Cool course!"));
		course.addReview(new Review("Dumb course!"));

		// save the course
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		appDAO.save(course);
		System.out.println("Done!");
	}

	private void deleteCousre(AppDAO appDAO) {
		int theId = 11; // course id from database
		System.out.println("Finding course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Delete course id: " + theId);
		System.out.println("Done");
	}

	private void updateCourses(AppDAO appDAO) {
		int theId = 10; // course id from database
		System.out.println("Finding course id: " + theId);
		Course course = appDAO.findCourseById(theId);
		System.out.println("Update course id: " + theId);
		course.setTitle("Huhhhhh");
		appDAO.update(course);
		System.out.println("Done");
	}


	private void updateInstructor(AppDAO appDAO) {
		int theId=1; // instructor id from database
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Updata insturctor id: " + theId);
		instructor.setLastName("Tester");
		appDAO.update(instructor);
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1; // instructor id from database
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Insturctor: " + instructor);

		// find courses for insturctor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		instructor.setCourses(courses);

		System.out.println("the associated courses: " + instructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1; // instructor id from database
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor =  appDAO.findInstructorById(theId);
		System.out.println("Instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("John","Wick","john@gmail.com");

		// create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com","Gamer");


		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// create some courses
		Course course1 = new Course("Air Guitar - The Ultimate Guide");
		Course course2 = new Course("The Pinball Masterclass");

		// add courses to instructor
		instructor.add(course1);
		instructor.add(course2);

		// save the instructor
		// NOTE: this will ALSO save the courses because of CascadeType.PERSIST
		System.out.println("Saving instructor: "+ instructor);
		System.out.println("The course: " + instructor.getCourses());
		appDAO.save(instructor);
		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting InstructorDetail id : " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructorDetail id : " + theId);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("InstructorDetail: " + instructorDetail);
		System.out.println("the associate instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instrcutor id: "+theId);
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor: "+instructor);
		System.out.println("the associate instructorDetail only: " + instructor.getInstructorDetail());
	}

	private void creatInstructor(AppDAO appDAO) {
		// create the instructor

		Instructor instructor = new Instructor("Medthee","heetong","medthee@gmail.com");

		// create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com","Guitar");
		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor
		// NOTE: this will ALSO save the details object because of CascadeType.ALL
		System.out.println("Saving instructor: "+ instructor);
		appDAO.save(instructor);
		System.out.println("Done");
	}

}

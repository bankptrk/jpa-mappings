package com.bankptrk.cruddemo.dao;

import com.bankptrk.cruddemo.entity.Course;
import com.bankptrk.cruddemo.entity.Instructor;
import com.bankptrk.cruddemo.entity.InstructorDetail;
import com.bankptrk.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor instructor);
    void update(Course course);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    void save(Course course);
    Course findCourseAndReviewsByCourseId(int theId);
    Course findCourseAndStudentsByCourseId(int theId);
    Student findStudentAndCoursesByStudentId(int theId);
    void update(Student student);
    void deleteStudentById(int theId);
}

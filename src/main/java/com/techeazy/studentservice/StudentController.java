package com.techeazy.studentservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final CourseClient courseClient;

    public StudentController(CourseClient courseClient){
             this.courseClient = courseClient ;
    }
    private static final List<Student> STUDENT_LIST = List.of(
            new Student(1L, "Siddhesh"),
            new Student(2L, "Rohit"),
            new Student(3L, "Virat")
    );

    @GetMapping("/{id}")
    public Map<String, Object> getStudentWithCourses(@PathVariable Long id) {
        Student student = STUDENT_LIST.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);

        List<Object> courses = courseClient.getCourses(id);

        return Map.of(
                "student", student,
                "courses", courses
        );
    }
}

package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;
import java.util.*;

public class StudentService {
    private static StudentService instance;
    private final List<Student> students = new ArrayList<>();

    private StudentService() {
    }

    public static StudentService getInstance() {
        if (instance == null)
            instance = new StudentService();
        return instance;
    }

    public void addStudent(String name, int age) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul " + name + " există deja!");
            }
        }
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Studentul '" + name + "' nu a fost găsit."));
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        findByName(studentName).addGrade(subject, grade);
    }

    public void printAllStudents() {
        students.forEach(s -> System.out.println(s + " Note: " + s.getGrades()));
    }

    public void printTopStudents() {
        students.stream()
                .sorted((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()))
                .forEach(System.out::println);
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sums = new EnumMap<>(Subject.class);
        Map<Subject, Integer> counts = new EnumMap<>(Subject.class);

        for (Student s : students) {
            s.getGrades().forEach((subj, grade) -> {
                sums.put(subj, sums.getOrDefault(subj, 0.0) + grade);
                counts.put(subj, counts.getOrDefault(subj, 0) + 1);
            });
        }

        Map<Subject, Double> averages = new EnumMap<>(Subject.class);
        sums.forEach((subj, sum) -> averages.put(subj, sum / counts.get(subj)));
        return averages;
    }
}
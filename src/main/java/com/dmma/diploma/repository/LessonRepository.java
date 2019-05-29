package com.dmma.diploma.repository;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    String query = "SELECT * " +
            "from lesson " +
            "where " +
            "week_day = ? " +
            "and week_number = ? " +
            "and lesson_number = ? " +
            "and done IS NOT true " +
            "and canceled IS NOT true";

    List<Lesson> findByLessonNumberAndWeekNumber(Integer lessonNumber, Integer weekNumber);

    List<Lesson> findByWeekNumberAndClassRoom(Integer weekNumber, ClassRoom classRoom);

    List<Lesson> findByTeacher(Teacher teacher);
    List<Lesson> findByClassRoom(ClassRoom classRoom);

    @Query(value = query, nativeQuery = true)
    List<Lesson> findCurrentLessons(Integer weekDay, Integer weekNumber, Integer lessonNumber);
}

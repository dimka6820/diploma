package com.dmma.diploma.repository;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByLessonNumberAndWeekNumber(Integer lessonNumber, Integer weekNumber);

    List<Lesson> findByWeekNumberAndClassRoom(Integer weekNumber, ClassRoom classRoom);
}

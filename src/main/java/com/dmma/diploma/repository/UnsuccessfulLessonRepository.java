package com.dmma.diploma.repository;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.UnsuccessfulLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnsuccessfulLessonRepository extends JpaRepository<UnsuccessfulLesson, Long> {

    UnsuccessfulLesson findByLesson(Lesson lesson);
}

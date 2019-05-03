package com.dmma.diploma.repository;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.UnsuccessfulLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnsuccessfulLessonRepository extends JpaRepository<UnsuccessfulLesson, Long> {

    List<UnsuccessfulLesson> findByLesson(Lesson lesson);
}

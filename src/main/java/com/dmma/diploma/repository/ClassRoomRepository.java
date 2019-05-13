package com.dmma.diploma.repository;

import com.dmma.diploma.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    ClassRoom findByNumberAndBody(String number, Integer body);

    List<ClassRoom> findByBody(Integer body);
}

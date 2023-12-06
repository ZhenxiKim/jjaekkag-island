package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}

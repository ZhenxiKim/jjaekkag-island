package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.domain.Lesson;
import com.jjaekkag.jjaekkagisland.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jeongheekim
 * @date 12/3/23
 */

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;


    @Transactional(readOnly = true)
    public Lesson getLesson(Long lessonSeq) {
        return lessonRepository.findById(lessonSeq).orElseThrow();
    }
}

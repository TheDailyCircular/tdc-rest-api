package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.EntityIdNotFoundException;
import com.thedailycircular.tdc.model.Resume;
import com.thedailycircular.tdc.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResumeServices {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume get(Long id) {
        if (!resumeRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Resume with ID: " + id + " doesn't exist");
        }
        return resumeRepository.getOne(id);
    }

    public Resume save(Resume resume) {
        return resumeRepository.save(resume);
    }

}

package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.Resume;
import com.dailycircular.dailycircular.repository.ResumeRepository;
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
        /* set resume field for educationalQualifications */
        resume
                .getEducationalQualifications()
                .forEach(educationalQualification -> educationalQualification.setResume(resume));
        /* set resume field for workExperiences */
        resume
                .getWorkExperiences()
                .forEach(workExperience -> workExperience.setResume(resume));

        return resumeRepository.save(resume);
    }

    public Resume update(Resume resume) {
        if (resume.getId() == null) {
            throw new EntityIdNotFoundException("Resume id is needed for update");
        }
        if (!resumeRepository.existsById(resume.getId())) {
            throw new EntityIdNotFoundException("Resume with ID: " + resume.getId() + " doesn't exist");
        }

        return save(resume);
    }
}

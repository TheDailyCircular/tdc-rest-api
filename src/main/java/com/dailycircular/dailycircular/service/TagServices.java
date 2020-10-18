package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TagServices {
    @Autowired
    private TagRepository tagRepository;

    public Tag saveOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }

    public Page<Tag> getAll(Integer page, Integer size) {
        return tagRepository.findAll(PageRequest.of(page, size));
    }

    public Tag getById(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Tag with ID: " + id + " doesn't exist");
        }
        return tagRepository.getOne(id);
    }

    public void delete(Long id) {
        if( !tagRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Organization with ID: " + id + " doesn't exist");
        }
        tagRepository.deleteById(id);
    }
}

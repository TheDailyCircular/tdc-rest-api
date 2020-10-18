package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.repository.CircularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class CircularServices {

    @Autowired
    private CircularRepository circularRepository;

    public Circular saveOrUpdate(Circular circular) {
        return circularRepository.save(circular);
    }

    public Page<Circular> getAll(Date date, Integer page, Integer size) {
        return circularRepository.findAll(date, PageRequest.of(page, size, Sort.Direction.DESC, "createdAt"));
    }

    public boolean existsById(Long id) {
        return circularRepository.existsById(id);
    }

    public Circular get(Long id) {
        if (!circularRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Circular with ID: " + id + " doesn't exist");
        }
        return circularRepository.getOne(id);
    }

    public void delete(Long id) {
        if (!circularRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Circular with ID: " + id + " doesn't exist");
        }
        circularRepository.deleteById(id);
    }
}

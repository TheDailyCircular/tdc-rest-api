package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.EntityIdNotFoundException;
import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.repository.CircularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CircularServices {

    @Autowired
    private CircularRepository circularRepository;

    public Circular saveOrUpdate(Circular circular) {
        return circularRepository.save(circular);
    }

    public List<Circular> getAll() {
        return circularRepository.findAll();
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

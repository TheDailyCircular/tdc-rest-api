package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.EntityIdNotFoundException;
import com.thedailycircular.tdc.model.Message;
import com.thedailycircular.tdc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServices {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveOrUpdate(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Message get(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Message with ID: " + id + " doesn't exist");
        }
        return messageRepository.getOne(id);
    }

    public void delete(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Message with ID: " + id + " doesn't exist");
        }
        messageRepository.deleteById(id);
    }
}

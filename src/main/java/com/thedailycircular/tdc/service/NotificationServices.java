package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.EntityIdNotFoundException;
import com.thedailycircular.tdc.model.Notification;
import com.thedailycircular.tdc.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationServices {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification saveOrUpdate(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public Notification get(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Notification with ID: " + id + " doesn't exist");
        }
        return notificationRepository.getOne(id);
    }

    public void delete(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Notification with ID: " + id + " doesn't exist");
        }
        notificationRepository.deleteById(id);
    }
}

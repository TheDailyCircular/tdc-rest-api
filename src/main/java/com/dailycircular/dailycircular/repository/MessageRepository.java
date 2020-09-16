package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}

package com.ellachihwa.lapa.repository;

import com.ellachihwa.lapa.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}

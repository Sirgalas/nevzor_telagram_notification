package ru.sergalas.notification.entity.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergalas.notification.entity.notification.entity.Notification;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
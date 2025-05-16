package ru.sergalas.notification.entity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergalas.notification.entity.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByChatId(Long chatId);
}
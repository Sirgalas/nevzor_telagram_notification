package ru.sergalas.notification.entity.user.entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.enums.UserStatusEnums;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "bot_users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(name = "chat_id", unique = true, nullable = false)
    Long chatId;

    @Column(name = "first_name",nullable = false)
    String firstName;

    @Enumerated(EnumType.STRING)
    UserActionEnum action;

    @Column(name = "register_at", nullable = false)
    LocalDateTime registerAt;

    @OneToMany
    Set<Notification> notifications;

    @Column(name = "current_notification_id")
    UUID currentNotification;
}

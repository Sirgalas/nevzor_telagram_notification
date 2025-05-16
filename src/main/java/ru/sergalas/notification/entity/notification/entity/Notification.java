package ru.sergalas.notification.entity.notification.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.enums.NotificationStatus;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Enumerated(EnumType.STRING)
    NotificationStatus status;

    @Column(name = "seconds")
    Long seconds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}

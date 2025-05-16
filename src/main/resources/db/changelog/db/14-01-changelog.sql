-- liquibase formatted sql

-- changeset Sergalas:1747245430900-1
CREATE TABLE bot_users_notifications
(
    user_id          UUID NOT NULL,
    notifications_id UUID NOT NULL,
    CONSTRAINT pk_bot_users_notifications PRIMARY KEY (user_id, notifications_id)
);

-- changeset Sergalas:1747245430900-2
ALTER TABLE bot_users
    ADD action VARCHAR(255);

-- changeset Sergalas:1747245430900-3
ALTER TABLE bot_users_notifications
    ADD CONSTRAINT uc_bot_users_notifications_notifications UNIQUE (notifications_id);

-- changeset Sergalas:1747245430900-4
ALTER TABLE bot_users_notifications
    ADD CONSTRAINT fk_botusenot_on_notification FOREIGN KEY (notifications_id) REFERENCES notifications (id);

-- changeset Sergalas:1747245430900-5
ALTER TABLE bot_users_notifications
    ADD CONSTRAINT fk_botusenot_on_user FOREIGN KEY (user_id) REFERENCES bot_users (id);


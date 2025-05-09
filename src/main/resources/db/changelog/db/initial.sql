-- liquibase formatted sql

-- changeset Sergalas:1746825392666-1
CREATE TABLE bot_users
(
    id          UUID         NOT NULL,
    chat_id     BIGINT       NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    register_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_bot_users PRIMARY KEY (id)
);

-- changeset Sergalas:1746825392666-3
CREATE TABLE notifications
(
    id          UUID NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    status      VARCHAR(255),
    seconds     BIGINT,
    user_id     UUID,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

-- changeset Sergalas:1746825392666-4
ALTER TABLE bot_users
    ADD CONSTRAINT uc_bot_users_chat UNIQUE (chat_id);

-- changeset Sergalas:1746825392666-6
ALTER TABLE notifications
    ADD CONSTRAINT FK_NOTIFICATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES bot_users (id);



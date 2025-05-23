-- liquibase formatted sql

-- changeset Sergalas:1747635203034-1
ALTER TABLE bot_users
    ADD current_notification_id UUID;


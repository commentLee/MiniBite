use minibite;

-- 기존 테이블이 있다면 삭제 (의존성 순서대로 삭제)
DROP TABLE IF EXISTS `preferences`;
DROP TABLE IF EXISTS `group_members`;
DROP TABLE IF EXISTS `group_chats`;
DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `post_reactions`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `user_groups`;
DROP TABLE IF EXISTS `health_logs`;
DROP TABLE IF EXISTS `meal_logs`;
DROP TABLE IF EXISTS `ai_recommendations`;
DROP TABLE IF EXISTS `missions`;
DROP TABLE IF EXISTS `users`; -- 혹시 남아있을 users
DROP TABLE IF EXISTS `user`;  -- 새로 만들 user
DROP TABLE IF EXISTS `preference`;

CREATE TABLE `preferences` (
    `user_id`    VARCHAR(100)    NOT NULL,
    `allergies`    TEXT    NULL,
    `dislikes`    TEXT    NULL,
    `preferred_textures`    TEXT    NULL,
    `preferred_flavors`    TEXT    NULL
);

CREATE TABLE `post_reactions` (
    `reaction_id`    INT    NOT NULL,
    `post_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `reaction_type`    VARCHAR(50)    NOT NULL
);

CREATE TABLE `meal_logs` (
    `log_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `meal_time`    DATETIME    NOT NULL,
    `photo_url`    VARCHAR(255)    NULL,
    `satisfaction_score`    DECIMAL(3,1)    NULL,
    `emotional_state`    VARCHAR(50)    NULL,
    `fullness_level`    VARCHAR(50)    NULL,
    `nausea`    TINYINT(1)    NULL,
    `bloating`    TINYINT(1)    NULL
);

CREATE TABLE `group_members` (
    `group_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `joined_at`    DATETIME    NOT NULL
);

CREATE TABLE `posts` (
    `post_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `group_id`    INT    NOT NULL,
    `created_at`    DATETIME    NOT NULL,
    `photo_url`    VARCHAR(255)    NULL,
    `ai_summary`    TEXT    NULL,
    `satisfaction`    INT    NULL,
    `had_side_effect`    TINYINT(1)    NULL
);

CREATE TABLE `health_logs` (
    `log_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `log_date`    DATE    NOT NULL,
    `medication_taken`    TINYINT(1)    NULL,
    `dosage`    DECIMAL(10,2)    NULL,
    `weight`    DECIMAL(5,2)    NULL,
    `water_intake_ml`    INT    NULL,
    `supplements`    TEXT    NULL
);

CREATE TABLE `user_groups` (
    `group_id`    INT    NOT NULL,
    `name`    VARCHAR(255)    NOT NULL,
    `target_weight`    VARCHAR(50)    NULL,
    `current_week`    INT    NULL,
    `dose_level`    VARCHAR(50)    NULL
);

CREATE TABLE `ai_recommendations` (
    `recommendation_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `created_at`    DATETIME    NOT NULL,
    `condition`    VARCHAR(255)    NULL,
    `recommended_menu`    VARCHAR(255)    NULL,
    `alternative_options`    TEXT    NULL,
    `reason_by_llm`    TEXT    NULL
);

CREATE TABLE `missions` (
    `mission_id`    INT    NOT NULL,
    `group_id`    INT    NOT NULL,
    `week`    INT    NULL,
    `mission_desc`    TEXT    NULL,
    `success_rate_percent`    INT    NULL
);

CREATE TABLE `users` (
    `user_id`    VARCHAR(100)    NOT NULL,
    `password`    VARCHAR(255)    NOT NULL,
    `nickname`    VARCHAR(100)    NOT NULL,
    `phone`    VARCHAR(20)    NULL,
    `height`    INT    NULL,
    `weight`    DECIMAL(5,2)    NULL,
    `gender`    VARCHAR(10)    NULL,
    `age`    INT    NULL,
    `persona_type`    VARCHAR(50)    NULL,
    `onboarding_date`    DATE    NULL
);

CREATE TABLE `group_chats` (
    `message_id`    INT    NOT NULL,
    `group_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `sent_at`    DATETIME    NOT NULL,
    `message`    TEXT    NULL
);

CREATE TABLE `comments` (
    `comment_id`    INT    NOT NULL,
    `post_id`    INT    NOT NULL,
    `user_id`    VARCHAR(100)    NOT NULL,
    `comment_text`    TEXT    NOT NULL,
    `created_at`    DATETIME    NOT NULL
);

ALTER TABLE `preferences` ADD CONSTRAINT `PK_PREFERENCES` PRIMARY KEY (
    `user_id`
);

ALTER TABLE `post_reactions` ADD CONSTRAINT `PK_POST_REACTIONS` PRIMARY KEY (
    `reaction_id`
);

ALTER TABLE `meal_logs` ADD CONSTRAINT `PK_MEAL_LOGS` PRIMARY KEY (
    `log_id`
);

ALTER TABLE `group_members` ADD CONSTRAINT `PK_GROUP_MEMBERS` PRIMARY KEY (
    `group_id`,
    `user_id`
);

ALTER TABLE `posts` ADD CONSTRAINT `PK_POSTS` PRIMARY KEY (
    `post_id`
);

ALTER TABLE `health_logs` ADD CONSTRAINT `PK_HEALTH_LOGS` PRIMARY KEY (
    `log_id`
);

ALTER TABLE `user_groups` ADD CONSTRAINT `PK_USER_GROUPS` PRIMARY KEY (
    `group_id`
);

ALTER TABLE `ai_recommendations` ADD CONSTRAINT `PK_AI_RECOMMENDATIONS` PRIMARY KEY (
    `recommendation_id`
);

ALTER TABLE `missions` ADD CONSTRAINT `PK_MISSIONS` PRIMARY KEY (
    `mission_id`
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
    `user_id`
);

ALTER TABLE `group_chats` ADD CONSTRAINT `PK_GROUP_CHATS` PRIMARY KEY (
    `message_id`
);

ALTER TABLE `comments` ADD CONSTRAINT `PK_COMMENTS` PRIMARY KEY (
    `comment_id`
);

ALTER TABLE `preferences` ADD CONSTRAINT `FK_users_TO_preferences_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `users` (
    `user_id`
);

ALTER TABLE `group_members` ADD CONSTRAINT `FK_user_groups_TO_group_members_1` FOREIGN KEY (
    `group_id`
)
REFERENCES `user_groups` (
    `group_id`
);

ALTER TABLE `group_members` ADD CONSTRAINT `FK_users_TO_group_members_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `users` (
    `user_id`
);


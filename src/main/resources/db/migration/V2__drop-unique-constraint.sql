ALTER TABLE subscriptions
    DROP CONSTRAINT uc_subscriptions_channel;

ALTER TABLE subscriptions
    DROP CONSTRAINT uc_subscriptions_source;

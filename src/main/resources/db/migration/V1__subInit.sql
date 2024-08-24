CREATE TABLE sources
(
    id VARCHAR(64) NOT NULL,
    CONSTRAINT pk_sources PRIMARY KEY (id)
);

CREATE TABLE subscribers
(
    id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_subscribers PRIMARY KEY (id)
);

CREATE TABLE subscriber_source
(
    source_id     VARCHAR(64)  NOT NULL,
    subscriber_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_subscriber_source PRIMARY KEY (source_id, subscriber_id)
);

ALTER TABLE subscriber_source
    ADD CONSTRAINT fk_subsou_on_sources FOREIGN KEY (source_id) REFERENCES sources (id);

ALTER TABLE subscriber_source
    ADD CONSTRAINT fk_subsou_on_subscribers FOREIGN KEY (subscriber_id) REFERENCES subscribers (id);

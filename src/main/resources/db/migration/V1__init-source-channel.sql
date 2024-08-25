CREATE TABLE channels
(
    id         VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_channels PRIMARY KEY (id)
);

CREATE TABLE sources
(
    id         VARCHAR(64)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_sources PRIMARY KEY (id)
);

CREATE TABLE channel_source_mapping
(
    channel_id VARCHAR(255) NOT NULL,
    source_id  VARCHAR(64)  NOT NULL,
    CONSTRAINT pk_channel_source_mapping PRIMARY KEY (channel_id, source_id)
);

ALTER TABLE channel_source_mapping
    ADD CONSTRAINT uc_channel_source_mapping_channel UNIQUE (channel_id);

ALTER TABLE channel_source_mapping
    ADD CONSTRAINT fk_chasoumap_on_channels_entity FOREIGN KEY (channel_id) REFERENCES channels (id);

ALTER TABLE channel_source_mapping
    ADD CONSTRAINT fk_chasoumap_on_sources_entity FOREIGN KEY (source_id) REFERENCES sources (id);
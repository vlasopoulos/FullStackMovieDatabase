ALTER TABLE name_basics ADD COLUMN name_ts tsvector
    GENERATED ALWAYS AS (to_tsvector('english', primary_name)) STORED;

CREATE INDEX name_ts_idx ON name_basics USING GIN (name_ts);
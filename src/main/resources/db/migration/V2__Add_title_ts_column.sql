ALTER TABLE title_basics ADD COLUMN title_ts tsvector
    GENERATED ALWAYS AS
        (setweight(to_tsvector('english', coalesce(primary_title, '')), 'A') ||
         setweight(to_tsvector('english', coalesce(original_title, '')), 'B')) STORED;
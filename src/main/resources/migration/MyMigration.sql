CREATE TABLE accounts
(
    pew INTEGER DEFAULT 2,
    foo VARCHAR(255) NOT NULL
);
COMMENT
ON COLUMN accounts.pew IS 'my comment';
CREATE INDEX ON accounts (pew);

DROP DATABASE IF EXISTS vectordb;
CREATE DATABASE vectordb;
USE vectordb;
DROP TABLE IF EXISTS vector_store;
CREATE TABLE vector_store (
 id CHAR(36) NOT NULL DEFAULT (UUID()),
 content TEXT,
 metadata LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
 embedding VECTOR(1536) NOT NULL,
 PRIMARY KEY(id),
 VECTOR INDEX vector_store_embedding_idx (embedding)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE Authors (
                         author_id CHAR(36) PRIMARY KEY, -- UUID
                         name VARCHAR(255) NOT NULL,
                         dateOfBirth DATE NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Таблиця "Categories"
CREATE TABLE Categories (
                            category_id CHAR(36) PRIMARY KEY, -- UUID
                            name VARCHAR(255) NOT NULL UNIQUE,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Таблиця "Books"
CREATE TABLE Books (
                       id CHAR(36) PRIMARY KEY, -- UUID
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(13) NOT NULL UNIQUE, -- ISBN-13 формат
                       publicationYear INT NOT NULL CHECK (publicationYear >= 0),
                       authorId CHAR(36) NOT NULL,
                       categoryId CHAR(36) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Зовнішні ключі
                       FOREIGN KEY (authorId) REFERENCES Authors(author_id) ON DELETE CASCADE,
                       FOREIGN KEY (categoryId) REFERENCES Categories(category_id) ON DELETE CASCADE
);
CREATE DATABASE MXH;

USE MXH;

-- Tạo bảng người dùng
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    role VARCHAR(50), -- vai trò ng dùng 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng bài viết
CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL, -- tiêu đề bài viết
    body TEXT, -- nội dung bài viết
    user_id INT NOT NULL, -- id người đăng
    status VARCHAR(50), -- trạng thái bài viết
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tạo bảng theo dõi
CREATE TABLE follows (
    following_user_id INT NOT NULL, -- người theo dõi
    followed_user_id INT NOT NULL, -- người được theo dõi 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (following_user_id, followed_user_id),
    FOREIGN KEY (following_user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (followed_user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (id, username, role) VALUES (1, 'demo_user', 'member');
INSERT INTO users (id, username, role) VALUES (2, 'Admin', 'ADMIN');
INSERT INTO users (id, username, role) VALUES (3, 'user_A', 'menber');

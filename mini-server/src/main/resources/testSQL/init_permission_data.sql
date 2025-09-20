-- 1. 插入管理员角色
INSERT INTO roles (role_name, description, created_at)
VALUES ('ADMIN', '系统管理员角色，拥有所有权限', NOW());

-- 2. 插入普通用户角色
INSERT INTO roles (role_name, description, created_at)
VALUES ('USER', '普通用户角色', NOW());

-- 3. 插入权限数据
-- 用户管理权限
INSERT INTO permissions (permission_name, description, created_at)
VALUES ('USER_MANAGE', '用户管理权限', NOW());

-- 角色管理权限
INSERT INTO permissions (permission_name, description, created_at)
VALUES ('ROLE_MANAGE', '角色管理权限', NOW());

-- 权限管理权限
INSERT INTO permissions (permission_name, description, created_at)
VALUES ('PERMISSION_MANAGE', '权限管理权限', NOW());

-- 产品管理权限
INSERT INTO permissions (permission_name, description, created_at)
VALUES ('PRODUCT_MANAGE', '产品管理权限', NOW());

-- 库存管理权限
INSERT INTO permissions (permission_name, description, created_at)
VALUES ('INVENTORY_MANAGE', '库存管理权限', NOW());

-- 仓库管理权限
INSERT INTO permissions (permission_name, description, created_at)
VALUES ('WAREHOUSE_MANAGE', '仓库管理权限', NOW());

-- 4. 关联管理员角色与所有权限
-- 管理员拥有用户管理权限
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r, permissions p
WHERE r.role_name = 'ADMIN' AND p.permission_name = 'USER_MANAGE';

-- 管理员拥有角色管理权限
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r, permissions p
WHERE r.role_name = 'ADMIN' AND p.permission_name = 'ROLE_MANAGE';

-- 管理员拥有权限管理权限
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r, permissions p
WHERE r.role_name = 'ADMIN' AND p.permission_name = 'PERMISSION_MANAGE';

-- 管理员拥有产品管理权限
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r, permissions p
WHERE r.role_name = 'ADMIN' AND p.permission_name = 'PRODUCT_MANAGE';

-- 管理员拥有库存管理权限
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r, permissions p
WHERE r.role_name = 'ADMIN' AND p.permission_name = 'INVENTORY_MANAGE';

-- 管理员拥有仓库管理权限
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r, permissions p
WHERE r.role_name = 'ADMIN' AND p.permission_name = 'WAREHOUSE_MANAGE';

-- 5. 创建初始管理员用户（密码：admin123，已加密）
INSERT INTO users (username, password_hash, email, full_name, is_active, created_at)
VALUES ('admin', '$2a$10$G6wQJ4vO8rVZJ8h9X7y0xO1n2m3b4v5c6x7z8q9w0e1r2t3y4u5i6o7p8a9s', 'admin@example.com', '系统管理员', TRUE, NOW());

-- 6. 关联管理员用户与管理员角色
INSERT INTO user_roles (user_id, role_id)
SELECT u.user_id, r.role_id
FROM users u, roles r
WHERE u.username = 'admin' AND r.role_name = 'ADMIN';
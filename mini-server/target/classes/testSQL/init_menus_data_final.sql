-- 菜单表初始化数据 - 适应修改后的表结构（parent_id和permission_key为NOT NULL）
-- --------------------------------------------------------

-- 先清空菜单表数据
TRUNCATE TABLE menus RESTART IDENTITY;

-- 插入一级菜单数据（使用'0'作为顶级菜单的parent_id）
INSERT INTO menus (menu_id, menu_name, icon, path, parent_id, sort_order, is_visible, menu_type, permission_key, created_at)
VALUES
    -- 仪表盘菜单
    ('1', '仪表盘', 'dashboard', '/dashboard', '0', 1, true, 'menu', 'DASHBOARD_VIEW', NOW()),
    
    -- 采购管理菜单
    ('2', '采购管理', 'shopping-cart', '/purchase', '0', 2, true, 'menu', 'PURCHASE_MANAGE', NOW()),
    
    -- 销售管理菜单
    ('3', '销售管理', 'trending-up', '/sales', '0', 3, true, 'menu', 'SALES_MANAGE', NOW()),
    
    -- 库存管理菜单
    ('4', '库存管理', 'database', '/inventory', '0', 4, true, 'menu', 'INVENTORY_MANAGE', NOW()),
    
    -- 系统管理菜单
    ('5', '系统管理', 'cog', '/system', '0', 5, true, 'menu', 'SYSTEM_MANAGE', NOW());

-- 插入二级菜单数据
INSERT INTO menus (menu_id, menu_name, icon, path, parent_id, sort_order, is_visible, menu_type, permission_key, created_at)
VALUES
    -- 采购管理子菜单
    ('201', '采购订单', 'file-text', '/purchase/orders', '2', 1, true, 'menu', 'PURCHASE_ORDER_VIEW', NOW()),
    ('202', '供应商管理', 'users', '/purchase/suppliers', '2', 2, true, 'menu', 'SUPPLIER_MANAGE', NOW()),
    ('203', '采购退货', 'undo', '/purchase/returns', '2', 3, true, 'menu', 'PURCHASE_RETURN_VIEW', NOW()),
    
    -- 销售管理子菜单
    ('301', '销售订单', 'file-text', '/sales/orders', '3', 1, true, 'menu', 'SALES_ORDER_VIEW', NOW()),
    ('302', '客户管理', 'users', '/sales/customers', '3', 2, true, 'menu', 'CUSTOMER_MANAGE', NOW()),
    ('303', '销售退货', 'undo', '/sales/returns', '3', 3, true, 'menu', 'SALES_RETURN_VIEW', NOW()),
    
    -- 库存管理子菜单
    ('401', '库存查询', 'search', '/inventory/query', '4', 1, true, 'menu', 'INVENTORY_QUERY', NOW()),
    ('402', '库存调整', 'sliders', '/inventory/adjust', '4', 2, true, 'menu', 'INVENTORY_ADJUST', NOW()),
    ('403', '仓库管理', 'building', '/inventory/warehouses', '4', 3, true, 'menu', 'WAREHOUSE_MANAGE', NOW()),
    ('404', '商品管理', 'package', '/inventory/products', '4', 4, true, 'menu', 'PRODUCT_MANAGE', NOW()),
    
    -- 系统管理子菜单
    ('501', '用户管理', 'user', '/system/users', '5', 1, true, 'menu', 'USER_MANAGE', NOW()),
    ('502', '角色管理', 'users-cog', '/system/roles', '5', 2, true, 'menu', 'ROLE_MANAGE', NOW()),
    ('503', '权限管理', 'key', '/system/permissions', '5', 3, true, 'menu', 'PERMISSION_MANAGE', NOW()),
    ('504', '菜单管理', 'list', '/system/menus', '5', 4, true, 'menu', 'MENU_MANAGE', NOW()),
    ('505', '系统设置', 'settings', '/system/settings', '5', 5, true, 'menu', 'SYSTEM_SETTINGS', NOW());

-- 插入三级菜单（权限管理子菜单）
INSERT INTO menus (menu_id, menu_name, icon, path, parent_id, sort_order, is_visible, menu_type, permission_key, created_at)
VALUES
    ('50301', '菜单添加', '', '', '504', 1, true, 'button', 'MENU_ADD', NOW()),
    ('50302', '菜单编辑', '', '', '504', 2, true, 'button', 'MENU_EDIT', NOW()),
    ('50303', '菜单删除', '', '', '504', 3, true, 'button', 'MENU_DELETE', NOW()),
    ('50304', '角色添加', '', '', '502', 1, true, 'button', 'ROLE_ADD', NOW()),
    ('50305', '角色编辑', '', '', '502', 2, true, 'button', 'ROLE_EDIT', NOW()),
    ('50306', '角色删除', '', '', '502', 3, true, 'button', 'ROLE_DELETE', NOW());




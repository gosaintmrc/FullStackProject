-- 库存管理子菜单SQL
-- 将商品主数据管理、入库管理、出库管理、库存查询与报表、库存盘点添加为库存管理的子菜单

-- 首先检查库存管理菜单是否存在
SELECT menu_id, menu_name FROM menus WHERE menu_id = '4';

-- 插入库存管理子菜单数据
-- 注意：库存管理的parent_id是'4'
INSERT INTO menus (menu_id, menu_name, icon, path, parent_id, sort_order, is_visible, menu_type, permission_key, created_at)
VALUES
    -- 商品主数据管理
    ('401', '商品主数据管理', 'package', '/inventory/products', '4', 1, true, 'menu', 'PRODUCT_MASTER_MANAGE', NOW()),
    
    -- 入库管理
    ('402', '入库管理', 'truck', '/inventory/inbound', '4', 2, true, 'menu', 'INVENTORY_INBOUND_MANAGE', NOW()),
    
    -- 出库管理
    ('403', '出库管理', 'truck-loading', '/inventory/outbound', '4', 3, true, 'menu', 'INVENTORY_OUTBOUND_MANAGE', NOW()),
    
    -- 库存查询与报表
    ('404', '库存查询与报表', 'chart-bar', '/inventory/reports', '4', 4, true, 'menu', 'INVENTORY_REPORT_QUERY', NOW()),
    
    -- 库存盘点
    ('405', '库存盘点', 'clipboard-check', '/inventory/check', '4', 5, true, 'menu', 'INVENTORY_CHECK_MANAGE', NOW());

-- 插入相应的按钮权限数据
INSERT INTO menus (menu_id, menu_name, icon, path, parent_id, sort_order, is_visible, menu_type, permission_key, created_at)
VALUES
    -- 商品主数据管理按钮权限
    ('40101', '商品添加', '', '', '401', 1, true, 'button', 'PRODUCT_ADD', NOW()),
    ('40102', '商品编辑', '', '', '401', 2, true, 'button', 'PRODUCT_EDIT', NOW()),
    ('40103', '商品删除', '', '', '401', 3, true, 'button', 'PRODUCT_DELETE', NOW()),
    
    -- 入库管理按钮权限
    ('40201', '入库单创建', '', '', '402', 1, true, 'button', 'INBOUND_CREATE', NOW()),
    ('40202', '入库单审核', '', '', '402', 2, true, 'button', 'INBOUND_AUDIT', NOW()),
    
    -- 出库管理按钮权限
    ('40301', '出库单创建', '', '', '403', 1, true, 'button', 'OUTBOUND_CREATE', NOW()),
    ('40302', '出库单审核', '', '', '403', 2, true, 'button', 'OUTBOUND_AUDIT', NOW()),
    
    -- 库存盘点按钮权限
    ('40501', '盘点单创建', '', '', '405', 1, true, 'button', 'CHECK_CREATE', NOW()),
    ('40502', '盘点单审核', '', '', '405', 2, true, 'button', 'CHECK_AUDIT', NOW());

-- 查询插入的子菜单数据以验证
SELECT menu_id, menu_name, parent_id, path FROM menus WHERE parent_id = '4' ORDER BY sort_order ASC;
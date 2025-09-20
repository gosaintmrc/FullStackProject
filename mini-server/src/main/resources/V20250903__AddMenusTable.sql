-- 菜单表: menus
-- 存储系统菜单结构，支持多级菜单
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS menus (
    menu_id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    menu_name VARCHAR(100) NOT NULL,
    icon VARCHAR(50),
    path VARCHAR(255),
    parent_id VARCHAR(36) NOT NULL,
    sort_order INT DEFAULT 0,
    is_visible BOOLEAN DEFAULT TRUE,
    menu_type VARCHAR(20) NOT NULL,
    permission_key VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

COMMENT ON COLUMN menus.menu_id IS '菜单主键，唯一标识符';
COMMENT ON COLUMN menus.menu_name IS '菜单名称，显示在界面上的文字';
COMMENT ON COLUMN menus.icon IS '菜单图标，使用的图标名称';
COMMENT ON COLUMN menus.path IS '菜单对应的路由路径';
COMMENT ON COLUMN menus.parent_id IS '上级菜单ID，为空表示一级菜单';
COMMENT ON COLUMN menus.sort_order IS '排序号，用于菜单的显示顺序';
COMMENT ON COLUMN menus.is_visible IS '是否显示，控制菜单的可见性';
COMMENT ON COLUMN menus.menu_type IS '菜单类型，如菜单、按钮等';
COMMENT ON COLUMN menus.permission_key IS '关联的权限标识';
COMMENT ON COLUMN menus.created_at IS '创建时间';

-- 为常用查询字段创建索引
CREATE INDEX IF NOT EXISTS idx_menus_parent_id ON menus (parent_id);
CREATE INDEX IF NOT EXISTS idx_menus_permission_key ON menus (permission_key);
CREATE INDEX IF NOT EXISTS idx_menus_menu_name ON menus (menu_name);
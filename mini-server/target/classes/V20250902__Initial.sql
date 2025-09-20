-- --------------------------------------------------------
-- 启用UUID扩展
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--  产品主数据表: products
--  存储所有商品的基本信息
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS products (
    product_id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_name VARCHAR(255) NOT NULL,
    sku VARCHAR(50) UNIQUE,
    brand VARCHAR(100),
    category VARCHAR(100),
    supplier VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
    );
COMMENT ON COLUMN products.product_id IS '产品主键，唯一标识符';
COMMENT ON COLUMN products.product_name IS '商品名称，例如“茅台飞天 2022”';
COMMENT ON COLUMN products.sku IS '库存单位，用于唯一标识商品，例如 SKU-2023081501';
COMMENT ON COLUMN products.brand IS '商品品牌，例如“茅台”';
COMMENT ON COLUMN products.category IS '商品类别，例如“白酒”、“红酒”';
COMMENT ON COLUMN products.supplier IS '商品供应商';
COMMENT ON COLUMN products.created_at IS '记录创建时间';

-- --------------------------------------------------------
--  仓库表: warehouses
--  存储仓库信息
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS warehouses (
    warehouse_id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    warehouse_name VARCHAR(255) NOT NULL,
    address TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
    );
COMMENT ON COLUMN warehouses.warehouse_id IS '仓库主键，唯一标识符';
COMMENT ON COLUMN warehouses.warehouse_name IS '仓库名称，例如“上海一号仓库”';
COMMENT ON COLUMN warehouses.address IS '仓库地址';
COMMENT ON COLUMN warehouses.created_at IS '记录创建时间';

-- --------------------------------------------------------
--  库存主表: inventory_items
--  存储每批/件商品的详细库存信息，是库存管理的核心
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS inventory_items (
    item_id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id VARCHAR(36) NOT NULL,
    batch_number VARCHAR(50) NOT NULL,
    serial_number VARCHAR(50),
    quantity INT NOT NULL CHECK (quantity >= 0),
    unit_of_measure VARCHAR(10) NOT NULL,
    warehouse_id VARCHAR(36) NOT NULL,
    expiry_date DATE,
    entry_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    status VARCHAR(20) NOT NULL,
    cost_price DECIMAL(10, 2) NOT NULL,
    is_traceable BOOLEAN NOT NULL DEFAULT FALSE
    );
COMMENT ON COLUMN inventory_items.item_id IS '库存条目主键，唯一标识符';
COMMENT ON COLUMN inventory_items.product_id IS '关联的产品主键';
COMMENT ON COLUMN inventory_items.batch_number IS '批次号，用于追踪同一批次商品';
COMMENT ON COLUMN inventory_items.serial_number IS '序列号，用于高价值商品的单品追踪';
COMMENT ON COLUMN inventory_items.quantity IS '库存数量';
COMMENT ON COLUMN inventory_items.unit_of_measure IS '计量单位，如“瓶”、“箱”';
COMMENT ON COLUMN inventory_items.warehouse_id IS '关联的仓库主键';
COMMENT ON COLUMN inventory_items.expiry_date IS '保质期或生产日期';
COMMENT ON COLUMN inventory_items.entry_date IS '入库日期';
COMMENT ON COLUMN inventory_items.status IS '库存状态，如“available”';
COMMENT ON COLUMN inventory_items.cost_price IS '商品入库成本';
COMMENT ON COLUMN inventory_items.is_traceable IS '是否需要进行批次或序列号追踪';


-- 为常用查询字段创建索引，以提高查询性能
CREATE INDEX IF NOT EXISTS idx_inventory_product_id ON inventory_items (product_id);
CREATE INDEX IF NOT EXISTS idx_inventory_batch_number ON inventory_items (batch_number);
CREATE INDEX IF NOT EXISTS idx_inventory_status ON inventory_items (status);

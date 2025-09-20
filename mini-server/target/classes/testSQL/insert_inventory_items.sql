-- 插入库存条目数据（item_id由数据库自动生成UUID）
-- 注意：请先执行products和warehouses表的插入，然后根据实际生成的UUID值修改下面的product_id和warehouse_id
INSERT INTO inventory_items (product_id, batch_number, serial_number, quantity, unit_of_measure, warehouse_id, expiry_date, status, cost_price, is_traceable)
VALUES 
('product-uuid-1', 'MT20230801', 'SN-MT001', 100, '瓶', 'warehouse-uuid-1', '2030-08-01', 'available', 1499.00, true),
('product-uuid-1', 'MT20230802', 'SN-MT002', 50, '箱', 'warehouse-uuid-2', '2030-09-01', 'available', 1480.00, true),
('product-uuid-2', 'WLY20230701', 'SN-WLY001', 80, '瓶', 'warehouse-uuid-1', '2030-07-01', 'available', 1099.00, true),
('product-uuid-3', 'FJ20230601', 'SN-FJ001', 120, '瓶', 'warehouse-uuid-3', '2030-06-01', 'available', 899.00, true),
('product-uuid-4', 'QD20230801', null, 500, '箱', 'warehouse-uuid-4', '2024-08-01', 'available', 59.90, false),
('product-uuid-5', 'ZY20230501', null, 200, '瓶', 'warehouse-uuid-5', '2025-05-01', 'available', 188.00, false),
('product-uuid-2', 'WLY20230702', 'SN-WLY002', 60, '箱', 'warehouse-uuid-2', '2030-07-02', 'available', 1080.00, true),
('product-uuid-4', 'QD20230802', null, 300, '瓶', 'warehouse-uuid-1', '2024-08-02', 'available', 8.90, false);
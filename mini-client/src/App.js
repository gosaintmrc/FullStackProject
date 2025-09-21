import {Box, Collapse, List, ListItemButton, ListItemIcon, ListItemText, Toolbar, Typography} from "@mui/material";
import {
    AssignmentLate as AssignmentLateIcon,
    BarChart as BarChartIcon,
    Dashboard as DashboardIcon,
    ExpandLess,
    ExpandMore,
    Inventory as InventoryIcon,
    Layers as LayersIcon,
    LocalPostOffice as LocalPostOfficeIcon,
    ShoppingCart as ShoppingCartIcon,
    Storage as StorageIcon,
    Store as StoreIcon,
    Warehouse as WarehouseIcon,
} from '@mui/icons-material';
import LocalShippingIcon from '@mui/icons-material/LocalShipping';
import FireTruckIcon from '@mui/icons-material/FireTruck';
import LockIcon from '@mui/icons-material/Lock';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import {useEffect, useState} from "react";
import {menuAPI} from "./api/APIService";


// 获取菜单图标组件
const getMenuIcon = (iconName) => {
    if (!iconName) return null;

    const normalizedIconName = iconName.toLowerCase().replace(/icon$/, '');

    const iconMap = {
        dashboard: <DashboardIcon/>,
        'shopping-cart': <ShoppingCartIcon/>,
        'trending-up': <StoreIcon/>,
        database: <StorageIcon/>,
        cog: <AdminPanelSettingsIcon/>,
        inventory: <InventoryIcon/>,
        package: <LocalPostOfficeIcon/>,
        truck: <LocalShippingIcon/>,
        'truck-loading': <FireTruckIcon/>,
        'chart-bar': <BarChartIcon/>,
        'clipboard-check': <AssignmentLateIcon/>,
        layers: <LayersIcon/>,
        lock: <LockIcon/>,
        warehouse: <WarehouseIcon/>,
    };

    return iconMap[normalizedIconName] || <LayersIcon/>;
};

/** 递归菜单的实现*/
const RecursiveMenu = ({menu}) => {
    /** 控制菜单打开 */
    const [open, setOpen] = useState(false);
    const hasChildren = menu.children && menu.children.length > 0;
    const [activeMenuId, setActiveMenuId] = useState(null);
    const handleClick = () => {
        if (hasChildren) {
            setOpen(!open);
        }
    };
    // 高亮逻辑：使用当前路径进行匹配
    const isActive = activeMenuId ? (menu.id === activeMenuId) : false; // 简化的高亮逻辑，React Router会自动处理路径匹配

    return (
        <>
            <ListItemButton onClick={handleClick}>
                <ListItemIcon sx={{
                    minWidth: 36,
                    color: isActive ? 'common.white' : 'inherit',
                }}>
                    {getMenuIcon(menu.icon)}
                </ListItemIcon>
                <ListItemText primary={menu.menuName}/>
                {hasChildren && (open ? <ExpandLess/> : <ExpandMore/>)}
            </ListItemButton>
            {hasChildren && (
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding sx={{pl: 2}}>
                        {menu.children.map(child => (
                            <RecursiveMenu key={child.menuId} menu={child}/>
                        ))}
                    </List>
                </Collapse>
            )}
        </>
    );
};

function App() {
    /** 菜单的加载状态 默认加载中*/
    const [loadingMenus, setLoadingMenus] = useState(true);
    /** 菜单数据 */
    const [menus, setMenus] = useState([]);

    useEffect(() => {
        const loadMenus = async () => {
            try {
                const menusData = await menuAPI.getAll();
                //设置菜单数据
                console.log(menusData);
                setMenus(menusData);
            } catch (error) {
                console.error('加载菜单失败:', error);
            } finally {
                /** 加载结束*/
                setLoadingMenus(false);
            }
        };
        loadMenus();
    }, [])
    return (
        <Box sx={{display: 'flex', minHeight: '100vh', flexDirection: 'row'}}>
            <Box sx={{bgcolor: 'grey.900', color: 'grey.400', boxSizing: 'border-box'}}>
                <Toolbar sx={{my: 4}}>
                    <Box display="flex" alignItems="center">
                        <StoreIcon sx={{color: 'primary.main', mr: 1, fontSize: 32}}/>
                        <Typography variant="h6" color="grey.50" sx={{fontWeight: 'bold'}}>
                            Mini ERP
                        </Typography>
                    </Box>
                </Toolbar>

                {/*构建菜单*/}
                <List sx={{px: 2}}>
                    {loadingMenus ? (
                            <Typography variant="body2" sx={{p: 2, color: 'text.secondary'}}>
                                加载菜单中...
                            </Typography>
                        ) :
                        (menus.map(menu => (
                            <RecursiveMenu key={menu.menuId} menu={menu}/>
                        )))
                    }
                </List>
            </Box>
            <Box sx={{bgcolor: 'red'}}>Test</Box>
        </Box>
    );
}

export default App;

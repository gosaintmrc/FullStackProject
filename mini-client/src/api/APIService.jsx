

export const menuAPI = {
    /**
     * 获取所有菜单 http://localhost:8080/api/menus/all
     */
    getAll: async () => {
        try {
            const response = await fetch('/api/menus/all');
            console.log('请求的 URL:', response.url);
            console.log('HTTP 状态码:', response.status);
            if (!response.ok) {
                throw new Error(`请求失败: ${response.status}`);
            }
            const data = await response.json();
            console.log('获取到的数据:', data);
            return data; // 关键：返回数据！
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    },
}
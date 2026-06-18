/**
 * 管理后台公共 API 工具
 */
const AdminAPI = {
    getToken() {
        return localStorage.getItem('token');
    },

    checkAuth() {
        if (!this.getToken()) {
            window.location.href = 'login.html';
            return false;
        }
        return true;
    },

    async request(url, options = {}) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.getToken()}`,
            ...options.headers
        };

        try {
            const response = await fetch(url, { ...options, headers });
            if (response.status === 401) {
                localStorage.removeItem('token');
                localStorage.removeItem('userInfo');
                window.location.href = 'login.html';
                return null;
            }
            const result = await response.json();
            if (response.status === 403) {
                if (typeof ElementPlus !== 'undefined') {
                    ElementPlus.ElMessage.error(result.message || '无权限访问，请使用管理员账号登录');
                }
                return result;
            }
            return result;
        } catch (error) {
            console.error('API请求失败:', error);
            if (typeof ElementPlus !== 'undefined') {
                ElementPlus.ElMessage.error('请求失败');
            }
            return null;
        }
    },

    buildParams(query, page, size) {
        const params = new URLSearchParams();
        if (page != null) params.append('page', page);
        if (size != null) params.append('size', size);
        Object.entries(query || {}).forEach(([key, value]) => {
            if (value !== '' && value !== null && value !== undefined) {
                params.append(key, value);
            }
        });
        return params.toString();
    },

    async getPage(url, query, page, size) {
        const qs = this.buildParams(query, page, size);
        const result = await this.request(`${url}?${qs}`);
        if (result && result.code === 200 && result.data) {
            return {
                records: result.data.records || [],
                total: result.data.total || 0
            };
        }
        if (result && result.code !== 200 && typeof ElementPlus !== 'undefined') {
            ElementPlus.ElMessage.error(result.message || '加载失败');
        }
        return { records: [], total: 0 };
    },

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('userInfo');
        window.location.href = 'login.html';
    },

    goBack() {
        window.location.href = 'dashboard.html';
    }
};

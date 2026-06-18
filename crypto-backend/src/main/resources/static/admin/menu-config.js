/**
 * 菜单 key → 页面配置（builtin 内嵌 / iframe 子页 / list 通用列表）
 */
const MENU_REGISTRY = {
    dashboard: { type: 'builtin' },
    'user-list': { type: 'builtin' },
    'user-asset': { type: 'builtin' },
    'user-kyc': { type: 'builtin' },
    'deposit-list': { type: 'builtin' },
    'withdraw-list': { type: 'builtin' },

    'spot-order': { type: 'iframe', src: 'spot-trade.html' },
    'seconds-order': { type: 'iframe', src: 'seconds-contract.html' },
    'seconds-contract-order': { type: 'iframe', src: 'seconds-contract.html' },
    'cycle-order': { type: 'iframe', src: 'cycle-contract.html' },
    'cycle-contract-order': { type: 'iframe', src: 'cycle-contract.html' },
    'finance-product': { type: 'iframe', src: 'finance-product.html' },
    'mining-config': { type: 'iframe', src: 'mining-config.html' },
    'loan-config': { type: 'iframe', src: 'loan-config.html' },
    'ieo-project': { type: 'iframe', src: 'ieo-project.html' },
    'new-coin-list': { type: 'iframe', src: 'ieo-project.html' },
    'new-coin-audit': { type: 'iframe', src: 'ieo-project.html' },
    'currency-config': { type: 'iframe', src: 'currency-config.html' },
    'cycle-contract-config': { type: 'iframe', src: 'currency-config.html' },
    'seconds-contract-config': { type: 'iframe', src: 'currency-config.html' },
    'trading-pair': { type: 'iframe', src: 'trading-pair.html' },
    'system-config': { type: 'iframe', src: 'system-config.html' },
    'deposit-channel': { type: 'iframe', src: 'system-config.html' },
    'withdraw-channel': { type: 'iframe', src: 'system-config.html' },

    'user-order': {
        type: 'list', title: '归集订单', api: '/admin/deposit/list',
        columns: [
            { prop: 'orderNo', label: '订单号', width: 180 },
            { prop: 'userId', label: '用户ID', width: 100 },
            { prop: 'currency', label: '币种', width: 80 },
            { prop: 'amount', label: '金额', width: 120 },
            { prop: 'toAddress', label: '归集地址', width: 160, tooltip: true },
            { prop: 'status', label: '状态', width: 100, tag: { 0: '待确认', 1: '成功', 2: '失败' } },
            { prop: 'createTime', label: '创建时间', width: 180 }
        ]
    },
    'user-auth': {
        type: 'list', title: '授权管理', api: '/admin/player/list',
        columns: [
            { prop: 'id', label: '用户ID', width: 80 },
            { prop: 'username', label: '登录名', width: 120 },
            { prop: 'email', label: '邮箱', width: 180 },
            { prop: 'emailVerified', label: '邮箱验证', width: 100, tag: { 0: '未验证', 1: '已验证' } },
            { prop: 'phoneVerified', label: '手机验证', width: 100, tag: { 0: '未验证', 1: '已验证' } },
            { prop: 'googleAuthEnabled', label: '谷歌验证', width: 100, tag: { 0: '未启用', 1: '已启用' } },
            { prop: 'status', label: '状态', width: 80, tag: { 0: '禁用', 1: '正常' } }
        ]
    },
    'user-bank': {
        type: 'list', title: '银行卡管理', api: '/admin/extension/user-banks',
        columns: [
            { prop: 'id', label: 'ID', width: 80 },
            { prop: 'userId', label: '用户ID', width: 100 },
            { prop: 'bankName', label: '银行名称', width: 150 },
            { prop: 'cardNo', label: '卡号', width: 180 },
            { prop: 'holderName', label: '持卡人', width: 120 },
            { prop: 'status', label: '状态', width: 80, tag: { 0: '禁用', 1: '正常' } },
            { prop: 'createTime', label: '创建时间', width: 180 }
        ]
    },
    'user-login-log': {
        type: 'list', title: '登录日志', api: '/admin/extension/login-logs',
        columns: [
            { prop: 'id', label: '用户ID', width: 80 },
            { prop: 'username', label: '登录名', width: 120 },
            { prop: 'loginIp', label: '登录IP', width: 140 },
            { prop: 'loginTime', label: '登录时间', width: 180 },
            { prop: 'registerDomain', label: '注册域名', width: 160, tooltip: true }
        ]
    },
    'new-coin-order': {
        type: 'list', title: '申购订单', api: '/admin/ieo-project/list',
        columns: [
            { prop: 'id', label: 'ID', width: 80 },
            { prop: 'projectName', label: '项目名称', width: 180 },
            { prop: 'currency', label: '币种', width: 100 },
            { prop: 'price', label: '价格', width: 120 },
            { prop: 'totalAmount', label: '总量', width: 120 },
            { prop: 'status', label: '状态', width: 100, tag: { 0: '下架', 1: '上架' } },
            { prop: 'startTime', label: '开始时间', width: 180 }
        ]
    },
    'cycle-contract-position': {
        type: 'list', title: '持仓列表', api: '/admin/cycle-contract/list',
        query: { status: 0 },
        columns: [
            { prop: 'orderNo', label: '订单号', width: 180 },
            { prop: 'userId', label: '用户ID', width: 100 },
            { prop: 'symbol', label: '交易对', width: 120 },
            { prop: 'direction', label: '方向', width: 80, tag: { 1: '看涨', 2: '看跌' } },
            { prop: 'amount', label: '金额', width: 120 },
            { prop: 'openPrice', label: '开仓价', width: 120 },
            { prop: 'status', label: '状态', width: 100, tag: { 0: '进行中', 1: '盈利', 2: '亏损', 3: '平局' } },
            { prop: 'createTime', label: '创建时间', width: 180 }
        ]
    }
};

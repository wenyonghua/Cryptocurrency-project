import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '../layouts/MainLayout.vue'
import Home from '../views/Home.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            component: MainLayout,
            children: [
                {
                    path: '',
                    name: 'home',
                    component: Home
                },
                {
                    path: 'market',
                    name: 'market',
                    component: () => import('../views/Market.vue')
                },
                {
                    path: 'assets',
                    name: 'assets',
                    component: () => import('../views/Assets.vue')
                },
                {
                    path: 'mine',
                    name: 'mine',
                    component: () => import('../views/Mine.vue')
                }
            ]
        },
        // Mine Sub-pages
        {
            path: '/mine/security',
            name: 'security-center',
            component: () => import('../views/SecurityCenter.vue')
        },
        {
            path: '/mine/identity',
            name: 'identity-verification',
            component: () => import('../views/IdentityVerification.vue')
        },
        {
            path: '/terms',
            name: 'service-terms',
            component: () => import('../views/ServiceTerms.vue')
        },
        {
            path: '/help',
            name: 'help-center',
            component: () => import('../views/HelpCenter.vue')
        },
        // Trading
        {
            path: '/trade/seconds',
            name: 'seconds-trade',
            component: () => import('../views/SecondsTrade.vue')
        },
        {
            path: '/trade/spot',
            name: 'spot-trade',
            component: () => import('../views/SpotTrade.vue')
        },
        {
            path: '/trade/cycle',
            name: 'cycle-trade',
            component: () => import('../views/CycleTrade.vue')
        },
        // Assets
        {
            path: '/assets/deposit',
            name: 'deposit',
            component: () => import('../views/Deposit.vue')
        },
        {
            path: '/assets/withdraw',
            name: 'withdraw',
            component: () => import('../views/Withdraw.vue')
        },
        // Features
        {
            path: '/feature/mining',
            name: 'mining',
            component: () => import('../views/Mining.vue')
        },
        {
            path: '/feature/loan',
            name: 'loan',
            component: () => import('../views/Loan.vue')
        },
        {
            path: '/feature/swap',
            name: 'swap',
            component: () => import('../views/Swap.vue')
        },
        {
            path: '/feature/promotion',
            name: 'promotion',
            component: () => import('../views/Promotion.vue')
        },
        {
            path: '/feature/finance',
            name: 'finance',
            component: () => import('../views/Finance.vue')
        },
        {
            path: '/feature/ieo',
            name: 'ieo',
            component: () => import('../views/IEO.vue')
        },
        // Utils
        {
            path: '/language',
            name: 'language-selection',
            component: () => import('../views/LanguageSelection.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/Login.vue')
        }
    ]
})

export default router

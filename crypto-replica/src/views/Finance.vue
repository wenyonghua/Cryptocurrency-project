<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();

const products = ref([
    { name: 'USDT AI Quant', rate: '0.75%', min: 500, days: 7, progress: 45 },
    { name: 'BTC Growth', rate: '1.20%', min: 1000, days: 15, progress: 80 },
    { name: 'ETH Staking', rate: '3.50%', min: 2000, days: 30, progress: 20 },
]);
</script>

<template>
  <div class="finance bg-gray-50 min-h-screen pb-10">
    <van-nav-bar :title="t('finance.title')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <div class="p-4">
        <div v-for="(item, index) in products" :key="index" class="bg-white rounded-lg p-4 mb-4 shadow-sm relative overflow-hidden">
            <div class="absolute top-0 right-0 bg-[#4BBD83] text-white text-[10px] px-2 py-1 rounded-bl-lg">{{ t('common.hot') }}</div>
            
            <div class="font-bold text-lg mb-4">{{ item.name }}</div>
            
            <div class="flex justify-between mb-4">
                <div class="text-center">
                    <div class="text-2xl font-bold text-[#4BBD83]">{{ item.rate }}</div>
                    <div class="text-xs text-gray-400">{{ t('finance.daily_rate') }}</div>
                </div>
                 <div class="text-center">
                    <div class="text-lg font-bold text-gray-800">{{ item.days }}</div>
                    <div class="text-xs text-gray-400">{{ t('finance.days') }}</div>
                </div>
                 <div class="text-center">
                    <div class="text-lg font-bold text-gray-800">{{ item.min }}</div>
                    <div class="text-xs text-gray-400">{{ t('finance.min_entry') }}</div>
                </div>
            </div>
            
            <div class="mb-3">
                <div class="flex justify-between text-xs text-gray-400 mb-1">
                    <span>{{ t('finance.progress') }}</span>
                    <span>{{ item.progress }}%</span>
                </div>
                <van-progress :percentage="item.progress" color="#4BBD83" stroke-width="6" :show-pivot="false" />
            </div>
            
            <van-button block color="#4BBD83" plain round size="small" class="font-bold">{{ t('finance.invest') }}</van-button>
        </div>
    </div>
  </div>
</template>

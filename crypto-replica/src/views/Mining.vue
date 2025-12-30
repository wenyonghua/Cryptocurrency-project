<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();

const products = ref([
    { name: 'BTC Mining', min: 1500, max: 1500, days: 30, rate: '0.2%~0.5%' },
    { name: 'ETH Mining', min: 500, max: 5000, days: 90, rate: '0.5%~0.8%' },
    { name: 'USDT Mining', min: 100, max: 10000, days: 7, rate: '0.1%~0.3%' },
]);
</script>

<template>
  <div class="mining bg-gray-50 min-h-screen pb-10">
    <van-nav-bar :title="t('mining.title')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <!-- Header Stats -->
    <div class="bg-[#4BBD83] text-white p-6">
        <div class="flex justify-between text-center">
            <div class="flex-1 border-r border-white/20">
                <div class="text-xs opacity-80 mb-1">{{ t('mining.managed') }} (USDT)</div>
                <div class="text-xl font-bold">0.00</div>
            </div>
            <div class="flex-1">
                <div class="text-xs opacity-80 mb-1">{{ t('mining.yield') }} (USDT)</div>
                <div class="text-xl font-bold">0.00</div>
            </div>
        </div>
    </div>

    <!-- Product List -->
    <div class="p-4">
        <div v-for="(item, index) in products" :key="index" class="bg-white rounded-lg p-4 mb-3 shadow-sm">
            <div class="flex justify-between items-center mb-3">
                <div class="font-bold text-lg">{{ item.name }}</div>
                <van-tag type="success" plain>{{ t('mining.running') }}</van-tag>
            </div>
            <div class="grid grid-cols-2 gap-y-3 text-sm mb-4">
                <div>
                    <div class="text-gray-400 text-xs">{{ t('mining.limit') }}</div>
                    <div class="font-medium">{{ item.min }}-{{ item.max }}</div>
                </div>
                 <div>
                    <div class="text-gray-400 text-xs">{{ t('mining.cycle') }}</div>
                    <div class="font-medium">{{ item.days }} Days</div>
                </div>
                 <div>
                    <div class="text-gray-400 text-xs">{{ t('mining.daily_yield') }}</div>
                    <div class="font-bold text-[#4BBD83]">{{ item.rate }}</div>
                </div>
                 <div>
                    <div class="text-gray-400 text-xs">{{ t('mining.available') }}</div>
                    <div class="font-medium">{{ t('mining.unlimited') }}</div>
                </div>
            </div>
            <van-button block color="#4BBD83" round size="small" class="font-bold">{{ t('mining.buy_now') }}</van-button>
        </div>
    </div>
  </div>
</template>

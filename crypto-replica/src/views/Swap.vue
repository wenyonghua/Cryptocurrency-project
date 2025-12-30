<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const fromAmount = ref('');
const toAmount = ref('');
const fromCoin = ref('USDT');
const toCoin = ref('BTC');

const swap = () => {
    // Mock swap logic
    const temp = fromCoin.value;
    fromCoin.value = toCoin.value;
    toCoin.value = temp;
}
</script>

<template>
  <div class="swap bg-white min-h-screen">
    <van-nav-bar :title="t('swap.title')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <div class="p-6">
        <!-- From Box -->
        <div class="bg-gray-50 rounded-xl p-4 mb-2">
            <div class="flex justify-between mb-2">
                <span class="text-gray-500 text-xs">{{ t('swap.from') }}</span>
                <span class="text-gray-500 text-xs">{{ t('trade.balance') }}: 1240.50</span>
            </div>
            <div class="flex justify-between items-center">
                <input v-model="fromAmount" type="number" placeholder="0.00" class="bg-transparent text-xl font-bold w-full outline-none" />
                <div class="flex items-center gap-1 font-bold bg-white px-2 py-1 rounded shadow-sm">
                    <div class="w-5 h-5 bg-[#26A17B] rounded-full flex items-center justify-center text-[8px] text-white">T</div>
                    {{ fromCoin }}
                    <van-icon name="arrow-down" size="12" />
                </div>
            </div>
        </div>
        
        <!-- Swap Icon -->
        <div class="flex justify-center -my-3 z-10 relative">
            <div class="bg-white p-2 rounded-full shadow-md cursor-pointer border border-gray-100" @click="swap">
                <van-icon name="exchange" class="transform rotate-90 text-[#4BBD83]" size="20" />
            </div>
        </div>
        
        <!-- To Box -->
        <div class="bg-gray-50 rounded-xl p-4 mt-2 mb-6">
            <div class="flex justify-between mb-2">
                <span class="text-gray-500 text-xs">{{ t('swap.to_est') }}</span>
            </div>
            <div class="flex justify-between items-center">
                <input v-model="toAmount" readonly placeholder="0.00" class="bg-transparent text-xl font-bold w-full outline-none text-gray-500" />
                <div class="flex items-center gap-1 font-bold bg-white px-2 py-1 rounded shadow-sm">
                     <div class="w-5 h-5 bg-[#F9AA4B] rounded-full flex items-center justify-center text-[8px] text-white">B</div>
                    {{ toCoin }}
                    <van-icon name="arrow-down" size="12" />
                </div>
            </div>
        </div>
        
        <div class="flex justify-between text-sm text-gray-500 mb-6 px-2">
            <span>{{ t('swap.rate') }}</span>
            <span>1 {{ toCoin }} ≈ 43,200 {{ fromCoin }}</span>
        </div>
        
        <van-button block color="#4BBD83" round class="font-bold text-lg shadow-lg">{{ t('swap.confirm') }}</van-button>
    </div>
  </div>
</template>

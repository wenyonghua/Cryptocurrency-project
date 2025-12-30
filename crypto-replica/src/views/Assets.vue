<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const router = useRouter();

const hideBalance = ref(false);
const activeTab = ref(0); // 0: Platform, 1: Financial, 2: Contract

const assets = ref([
    { currency: 'USDT', available: '68,044.08', frozen: '0.0000', equivalent: '68,044.08' },
    { currency: 'BTC', available: '0.905990', frozen: '0.0000', equivalent: '80,835.46' },
    { currency: 'ETH', available: '10.5000', frozen: '0.0000', equivalent: '25,234.12' },
    { currency: 'TRX', available: '15000.00', frozen: '0.0000', equivalent: '1,500.00' },
]);
</script>

<template>
  <div class="assets bg-gray-50 min-h-screen pb-20">
    <!-- Top Tabs -->
    <div class="bg-white flex pt-4 pb-2 border-b border-gray-100">
        <div 
            class="flex-1 text-center py-2 relative text-sm font-medium transition-colors"
            :class="activeTab === 0 ? 'text-[#4BBD83]' : 'text-gray-500'"
            @click="activeTab = 0"
        >
            {{ t('assets.platform_assets') }}
            <div v-if="activeTab === 0" class="absolute bottom-0 left-1/2 -translate-x-1/2 w-8 h-1 bg-[#4BBD83] rounded-full"></div>
        </div>
        <div 
            class="flex-1 text-center py-2 relative text-sm font-medium transition-colors"
            :class="activeTab === 1 ? 'text-[#4BBD83]' : 'text-gray-500'"
            @click="activeTab = 1"
        >
             {{ t('assets.financial_assets') }}
             <div v-if="activeTab === 1" class="absolute bottom-0 left-1/2 -translate-x-1/2 w-8 h-1 bg-[#4BBD83] rounded-full"></div>
        </div>
        <div 
            class="flex-1 text-center py-2 relative text-sm font-medium transition-colors"
            :class="activeTab === 2 ? 'text-[#4BBD83]' : 'text-gray-500'"
            @click="activeTab = 2"
        >
             {{ t('assets.contract_assets') }}
             <div v-if="activeTab === 2" class="absolute bottom-0 left-1/2 -translate-x-1/2 w-8 h-1 bg-[#4BBD83] rounded-full"></div>
        </div>
    </div>

    <div class="p-6 bg-white mb-2">
        <!-- Balance Header -->
        <div class="text-gray-500 text-sm mb-4 flex items-center">
            {{ t('assets.account_balance') }}
            <van-icon v-if="hideBalance" name="closed-eye" class="ml-2 cursor-pointer" @click="hideBalance = false" />
            <van-icon v-else name="eye-o" class="ml-2 cursor-pointer" @click="hideBalance = true" />
            <van-icon name="replay" class="ml-2 text-gray-400 rotate-90" />
        </div>
        <div class="text-4xl font-bold text-gray-800 tracking-wide mb-8">
             {{ hideBalance ? '******' : '1,383,185.67' }}
        </div>

        <!-- Action Buttons Grid -->
        <div class="flex justify-between px-2">
            <div class="flex flex-col items-center gap-2" @click="router.push('/deposit')">
                <div class="w-12 h-12 rounded-full bg-[#4BBD83] flex items-center justify-center shadow-sm active:opacity-80 transition-opacity">
                    <van-icon name="down" color="#fff" size="20" />
                </div>
                <span class="text-sm text-gray-600">{{ t('assets.deposit') }}</span>
            </div>
            
            <div class="flex flex-col items-center gap-2" @click="router.push('/withdraw')">
                <div class="w-12 h-12 rounded-full bg-[#4BBD83] flex items-center justify-center shadow-sm active:opacity-80 transition-opacity">
                    <van-icon name="ascending" color="#fff" size="20" />
                </div>
                <span class="text-sm text-gray-600">{{ t('assets.withdraw') }}</span>
            </div>

            <div class="flex flex-col items-center gap-2">
                <div class="w-12 h-12 rounded-full bg-[#4BBD83] flex items-center justify-center shadow-sm active:opacity-80 transition-opacity">
                    <van-icon name="exchange" color="#fff" size="20" />
                </div>
                <span class="text-sm text-gray-600">{{ t('assets.swap') }}</span>
            </div>

            <div class="flex flex-col items-center gap-2">
                <div class="w-12 h-12 rounded-full bg-[#4BBD83] flex items-center justify-center shadow-sm active:opacity-80 transition-opacity">
                    <van-icon name="refund-o" color="#fff" size="20" />
                </div>
                <span class="text-sm text-gray-600">{{ t('assets.transfer') }}</span>
            </div>
        </div>
    </div>
    
    <!-- Asset List -->
     <div class="bg-white p-4">
        <h3 class="font-bold text-base mb-4">{{ t('assets.details') }}</h3>
        
        <div v-for="item in assets" :key="item.currency" class="border-b border-gray-100 last:border-0 pb-6 mb-6 last:mb-0">
            <div class="flex items-center gap-2 mb-4">
                <div class="w-6 h-6 rounded-full bg-[#4BBD83] flex items-center justify-center text-white text-xs font-bold">
                    {{ item.currency[0] }}
                </div>
                <span class="font-bold text-gray-800">{{ item.currency }}</span>
            </div>
            
            <div class="grid grid-cols-3 gap-2 text-left">
                <div>
                     <div class="text-xs text-gray-400 mb-1">{{ t('assets.avail') }}</div>
                     <div class="font-bold text-sm text-gray-800">{{ item.available }}</div>
                </div>
                <div class="text-center">
                     <div class="text-xs text-gray-400 mb-1">{{ t('assets.occupied') }}</div>
                     <div class="font-bold text-sm text-gray-800">{{ item.frozen }}</div>
                </div>
                <div class="text-right">
                     <div class="text-xs text-gray-400 mb-1">{{ t('assets.equivalent') }}</div>
                     <div class="font-bold text-sm text-gray-800">{{ item.equivalent }}</div>
                </div>
            </div>
        </div>
    </div>
  </div>
</template>

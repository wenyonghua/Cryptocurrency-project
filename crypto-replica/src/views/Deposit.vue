<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { showToast } from 'vant';

const { t } = useI18n();
const showQR = ref(false);
const selectedChain = ref('TRC20');
const amount = ref('');

const chains = ['TRC20', 'ERC20', 'BTC'];

const selectChain = (chain: string) => {
    selectedChain.value = chain;
    showQR.value = false; // Reset flow
}
</script>

<template>
  <div class="deposit bg-gray-50 min-h-screen pb-16">
    <van-nav-bar :title="t('assets.deposit')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <div class="bg-white p-4 mb-2">
        <div class="text-sm text-gray-500 mb-2">{{ t('assets.chain_name') }}</div>
        <div class="flex gap-2">
            <div 
                v-for="chain in chains" 
                :key="chain"
                class="px-4 py-1 rounded border text-sm transition-colors cursor-pointer"
                :class="selectedChain === chain ? 'border-[#4BBD83] text-[#4BBD83] bg-[#4BBD83]/10' : 'border-gray-200'"
                @click="selectedChain = chain"
            >
                {{ chain }}
            </div>
        </div>
    </div>
    
    <div class="bg-white p-6 flex flex-col items-center">
        <div class="w-48 h-48 bg-gray-100 mb-4 flex items-center justify-center">
            <!-- Mock QR -->
            <van-icon name="qr" size="150" color="#333" />
        </div>
        <div class="text-sm text-gray-500 mb-1">{{ t('assets.deposit_addr') }}</div>
        <div class="font-bold text-gray-800 break-all text-center px-8 mb-4">
            TSPFKj8...MockAddress...Eqz
        </div>
        <van-button size="small" color="#4BBD83" plain round class="px-8">{{ t('assets.copy_addr') }}</van-button>
        
        <div class="w-full mt-8">
             <div class="text-sm text-gray-500 mb-2">{{ t('assets.min_deposit') }} (10 USDT)</div>
             <van-field v-model="amount" type="number" :placeholder="t('trade.amount')" border class="bg-gray-50 rounded border border-gray-100" />
        </div>
        
        <van-button block color="#4BBD83" class="mt-6 font-bold" @click="showToast(t('common.submit'))">
            {{ t('common.confirm') }}
        </van-button>
    </div>
    
    <div class="p-4 text-xs text-gray-400">
        <p class="mb-1">• {{ t('assets.notice_1') }}</p>
        <p>• {{ t('assets.notice_2') }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const selectedChain = ref('TRC20');
const address = ref('');
const amount = ref('');
const password = ref('');

const chains = ['TRC20', 'ERC20'];
</script>

<template>
  <div class="withdraw bg-gray-50 min-h-screen pb-16">
    <van-nav-bar :title="t('assets.withdraw')" left-arrow @click-left="$router.back()" fixed placeholder />
    
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
    
    <div class="bg-white p-4">
        <div class="mb-4">
            <div class="text-sm text-gray-500 mb-2">{{ t('assets.withdraw_addr') }}</div>
            <van-field 
                v-model="address" 
                :placeholder="t('assets.paste_addr')" 
                class="bg-gray-50 rounded border border-gray-100"
                right-icon="scan"
            />
        </div>
        
        <div class="mb-4">
            <div class="text-sm text-gray-500 mb-2">{{ t('trade.amount') }}</div>
            <van-field 
                v-model="amount" 
                type="number"
                :placeholder="t('assets.min_withdraw') + ' 10 USDT'" 
                class="bg-gray-50 rounded border border-gray-100"
            >
                <template #right-icon>
                    <span class="text-[#4BBD83] font-bold">{{ t('common.all') }}</span>
                </template>
            </van-field>
            <div class="text-xs text-gray-400 mt-1 flex justify-between">
                <span>{{ t('common.available') }}: 1240.50 USDT</span>
                <span>{{ t('assets.fee') }}: 1 USDT</span>
            </div>
        </div>
        
         <div class="mb-6">
            <div class="text-sm text-gray-500 mb-2">{{ t('assets.fund_pwd') }}</div>
            <van-field 
                v-model="password" 
                type="password"
                :placeholder="t('assets.enter_pwd')" 
                class="bg-gray-50 rounded border border-gray-100"
            />
        </div>
        
        <van-button block color="#4BBD83" class="font-bold shadow-lg" shadow>
            {{ t('assets.withdraw') }}
        </van-button>
    </div>
  </div>
</template>

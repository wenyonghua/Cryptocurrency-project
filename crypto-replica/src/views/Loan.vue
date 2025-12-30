<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const amount = ref('');
const days = ref(7);
</script>

<template>
  <div class="loan bg-gray-50 min-h-screen pb-10">
    <van-nav-bar :title="t('loan.title')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <div class="p-4">
        <div class="bg-white rounded-lg p-6 shadow-sm mb-4">
             <div class="text-center mb-6">
                 <div class="text-gray-500 text-sm mb-1">{{ t('loan.max_amount') }}</div>
                 <div class="text-3xl font-bold text-[#4BBD83]">500,000.00</div>
             </div>
             
             <van-cell-group inset>
                 <van-field v-model="amount" :label="t('loan.amount')" :placeholder="t('loan.enter_amount')" type="number">
                     <template #right-icon>USDT</template>
                 </van-field>
                 <van-cell :title="t('loan.daily_rate')" value="0.16%" />
                 <van-cell :title="t('loan.period')" value="7 Days" is-link />
             </van-cell-group>
             
             <div class="mt-6">
                 <div class="flex justify-between text-sm mb-2">
                     <span class="text-gray-500">{{ t('loan.repayment') }}</span>
                     <span class="font-bold">{{ amount ? (Number(amount) * 1.0112).toFixed(2) : '0.00' }} USDT</span>
                 </div>
                 <van-button block color="#4BBD83" round class="font-bold shadow-lg">{{ t('loan.apply') }}</van-button>
             </div>
        </div>
        
        <div class="text-xs text-gray-400 px-2 leading-relaxed">
            * {{ t('loan.notice') }}<br>
            * {{ t('loan.daily_rate') }} is calculated daily.<br>
            * Ensure sufficient collateral balance.
        </div>
    </div>
  </div>
</template>

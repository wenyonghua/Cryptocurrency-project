<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter, useRoute } from 'vue-router';

const { t } = useI18n();
const router = useRouter();
const route = useRoute();

const symbol = ref('BTC/USDT');

onMounted(() => {
    if (route.query.symbol) {
        symbol.value = route.query.symbol as string;
    }
});
</script>

<template>
  <div class="cycle-trade bg-black min-h-screen text-white">
      <!-- Custom Dark Header -->
      <div class="flex items-center px-4 h-12 sticky top-0 z-50 bg-black text-white">
          <van-icon name="arrow-left" size="20" @click="router.back()" class="p-2 -ml-2" />
          <div class="flex-1 text-center font-bold text-lg">{{ symbol }} {{ t('cycle.title') }}</div>
          <van-icon name="chart-trending-o" size="20" class="p-2 -mr-2" />
      </div>
      
      <!-- Mock Trading Layout -->
      <div class="flex h-[calc(100vh-46px)]">
          <!-- Order Book -->
          <div class="w-1/3 border-r border-white/10 p-1 text-xs">
              <div v-for="i in 10" :key="'sell'+i" class="flex justify-between mb-1 text-[#D62548]">
                  <span>43,{{ 250 + i }}</span>
                  <span>{{ (Math.random()).toFixed(3) }}</span>
              </div>
              <div class="text-center font-bold text-lg my-2 text-[#4BBD83]">43,250.00</div>
               <div v-for="i in 10" :key="'buy'+i" class="flex justify-between mb-1 text-[#4BBD83]">
                  <span>43,{{ 250 - i }}</span>
                  <span>{{ (Math.random()).toFixed(3) }}</span>
              </div>
          </div>
          
          <!-- Trade Form -->
          <div class="w-2/3 p-4">
              <div class="flex gap-2 mb-4">
                  <van-button block color="#4BBD83" size="small" class="font-bold">{{ t('cycle.buy') }}</van-button>
                  <van-button block color="#333" size="small" class="text-gray-400">{{ t('cycle.sell') }}</van-button>
              </div>
              
              <div class="bg-white/10 rounded p-2 mb-2 flex justify-between items-center">
                  <span>{{ t('cycle.market_price') }}</span>
                  <van-icon name="arrow-down" />
              </div>
              
              <div class="bg-white/10 rounded p-2 mb-4">
                  <input type="text" :placeholder="t('trade.amount') + ' (USDT)'" class="bg-transparent w-full outline-none text-white text-sm" />
              </div>
              
              <div class="grid grid-cols-4 gap-2 mb-6">
                  <div class="bg-white/5 rounded py-1 text-center text-xs">25%</div>
                  <div class="bg-white/5 rounded py-1 text-center text-xs">50%</div>
                  <div class="bg-white/5 rounded py-1 text-center text-xs">75%</div>
                  <div class="bg-white/5 rounded py-1 text-center text-xs">100%</div>
              </div>
              
              <van-button block color="#4BBD83" class="font-bold mb-4">{{ t('trade.buy_long') }}</van-button>
               <div class="text-xs text-gray-500 text-center">{{ t('assets.avail') }}: 1240.50 USDT</div>
          </div>
      </div>
  </div>
</template>

<style scoped>
:deep(.van-nav-bar__title), :deep(.van-icon) {
    color: white !important;
}
</style>

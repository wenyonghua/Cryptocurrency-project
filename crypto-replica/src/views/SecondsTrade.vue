<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useI18n } from 'vue-i18n';
import TradeChart from '@/components/TradeChart.vue';

const { t } = useI18n();
const router = useRouter();
const route = useRoute();

// State
const symbol = ref('BTC/USDT');
const activeMode = ref(0); // 0: Seconds, 1: Spot, 2: Cycle
const duration = ref(60);
const amount = ref('');
const balance = ref('1240.50');

const durations = [
    { label: '30s', value: 30, profit: '30%' },
    { label: '60s', value: 60, profit: '60%' },
    { label: '180s', value: 180, profit: '80%' },
    { label: '300s', value: 300, profit: '90%' },
];

const selectedDuration = ref(durations[1]); // Default to 60s

onMounted(() => {
    if (route.query.symbol) {
        symbol.value = route.query.symbol as string;
    }
});

const selectDuration = (item: any) => {
    selectedDuration.value = item;
};

const handleTrade = (direction: 'up' | 'down') => {
    console.log(`Trade ${direction} for ${selectedDuration.value.value}s`);
    // Logic for trade
};

const switchMode = (mode: number) => {
    activeMode.value = mode;
    // Navigation logic could go here if these were separate routes
    if (mode === 1) router.push({ name: 'spot-trade', query: { symbol: symbol.value } });
    if (mode === 2) router.push({ name: 'cycle-trade', query: { symbol: symbol.value } });
};
</script>

<template>
  <div class="seconds-trade bg-white min-h-screen pb-24 relative">
    <!-- Custom Header with Tabs -->
    <div class="flex items-center px-2 h-12 bg-white sticky top-0 z-50 border-b border-gray-100">
        <van-icon name="arrow-left" size="20" class="text-gray-800 p-2" @click="router.back()" />
        
        <div class="flex-1 flex justify-center space-x-6">
            <div 
                class="relative py-3 font-bold text-base cursor-pointer"
                :class="activeMode === 0 ? 'text-[#4BBD83]' : 'text-gray-500'"
                @click="switchMode(0)"
            >
                {{ t('market.seconds') }}
                <div v-if="activeMode === 0" class="absolute bottom-0 left-1/2 -translate-x-1/2 w-8 h-0.5 bg-[#4BBD83]"></div>
            </div>
             <div 
                class="relative py-3 font-medium text-base cursor-pointer hover:font-bold transition-all"
                :class="activeMode === 1 ? 'text-[#4BBD83]' : 'text-gray-500'"
                @click="switchMode(1)"
            >
                {{ t('market.spot') }}
            </div>
             <div 
                class="relative py-3 font-medium text-base cursor-pointer hover:font-bold transition-all"
                :class="activeMode === 2 ? 'text-[#4BBD83]' : 'text-gray-500'"
                @click="switchMode(2)"
            >
                {{ t('market.cycle') }}
            </div>
        </div>
        
        <div class="w-8"></div> <!-- Spacer for balance -->
    </div>
    
    <!-- Coin Sub-Header -->
    <div class="p-4 py-3">
         <div class="flex justify-between items-start mb-4">
             <div class="flex items-center space-x-2">
                 <van-icon name="wap-nav" size="20" class="text-gray-600" />
                 <span class="text-xl font-bold text-gray-800">{{ symbol }}</span>
                 <van-icon name="info-o" size="18" class="text-[#4BBD83] bg-green-50 rounded-full" />
             </div>
         </div>
         
         <div class="flex justify-between items-end">
             <div>
                 <div class="text-4xl font-bold text-[#4BBD83] tracking-tight">88684.57</div>
                 <div class="text-sm text-[#4BBD83] font-medium mt-1 font-mono">+1.33%</div>
             </div>
             
             <div class="grid grid-cols-2 gap-x-8 gap-y-1 text-xs text-gray-400 text-right">
                 <span>{{ t('market.high') }}</span>
                 <span class="text-gray-900 font-medium">89567.75</span>
                 
                 <span>{{ t('market.low') }}</span>
                 <span class="text-gray-900 font-medium">86891.70</span>
                 
                 <span>{{ t('market.vol') }}</span>
                 <span class="text-gray-900 font-medium">13.99K</span>
             </div>
         </div>
    </div>
    
    <!-- Time Toggles & Chart -->
     <div class="border-t border-gray-100">
         <div class="flex space-x-6 px-4 py-2 text-xs text-gray-500">
             <span class="text-[#4BBD83] font-bold border-b-2 border-[#4BBD83] pb-1">1m</span>
             <span>5m</span>
             <span>15m</span>
             <span>30m</span>
             <span>1h</span>
             <span>2h</span>
         </div>
         <div class="h-[350px] relative border-b border-gray-100">
            <TradeChart :height="350" />
            <!-- Middle Line Mock -->
            <div class="absolute top-1/2 w-full border-t border-dashed border-red-300 pointer-events-none flex items-center justify-end pr-2 text-white text-xs">
                 <span class="bg-[#4BBD83] px-1 rounded-sm">88684.57</span>
            </div>
         </div>
     </div>
     
     <div class="px-4 py-2">
         <div class="flex justify-between text-xs text-gray-400 mb-1">
             <span>Volume (20)</span>
             <span>0   4</span>
             <span>10</span>
         </div>
         <!-- Volume Bars Mock -->
         <div class="h-10 flex space-x-1 items-end opacity-50">
             <div class="w-1 bg-red-300 h-2"></div>
             <div class="w-1 bg-green-300 h-4"></div>
             <div class="w-1 bg-green-300 h-8"></div>
             <div class="w-1 bg-red-300 h-3"></div>
             <div class="w-1 bg-green-300 h-5"></div>
              <div class="w-1 bg-green-300 h-2"></div>
         </div>
     </div>

    <!-- Bottom Actions -->
    <div class="fixed bottom-0 left-0 right-0 p-4 bg-white border-t border-gray-100 flex space-x-4 z-50 shadow-[0_-2px_10px_rgba(0,0,0,0.05)]">
         <button 
            class="flex-1 bg-[#4BBD83] text-white h-12 rounded-sm text-base font-medium active:opacity-90 transition-opacity"
            @click="handleTrade('up')"
        >
            {{ t('trade.look_up') }}
         </button>
         <button 
            class="flex-1 bg-[#D62548] text-white h-12 rounded-sm text-base font-medium active:opacity-90 transition-opacity"
            @click="handleTrade('down')"
        >
            {{ t('trade.look_down') }}
         </button>
    </div>
  </div>
</template>

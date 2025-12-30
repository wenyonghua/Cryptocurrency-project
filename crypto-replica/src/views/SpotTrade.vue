<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const router = useRouter();
const route = useRoute();

// Top Navigation State
const activeMode = ref(1); // 0: Seconds, 1: Spot, 2: Cycle

// Trade State
const symbol = ref('BTC/USDT');
const activeSide = ref('buy'); // 'buy' | 'sell'
const orderType = ref('limit'); // 'limit' | 'market'
const price = ref('88684.57');
const amount = ref('');
const balance = ref('1240.50');

// Mock Data
const orderBookAsks = ref([
    { price: '88690.50', amount: '0.124' },
    { price: '88689.00', amount: '0.052' },
    { price: '88688.20', amount: '1.200' },
    { price: '88687.10', amount: '0.450' },
    { price: '88686.00', amount: '0.010' },
]);

const orderBookBids = ref([
    { price: '88684.57', amount: '0.550' },
    { price: '88683.50', amount: '1.100' },
    { price: '88682.10', amount: '0.020' },
    { price: '88680.90', amount: '0.330' },
    { price: '88679.00', amount: '0.150' },
]); 

onMounted(() => {
    if (route.query.symbol) {
        symbol.value = route.query.symbol as string;
    }
});

const switchMode = (mode: number) => {
    activeMode.value = mode;
    if (mode === 0) router.push({ name: 'seconds-trade', query: { symbol: symbol.value } });
    if (mode === 2) router.push({ name: 'cycle-trade', query: { symbol: symbol.value } });
};
</script>

<template>
  <div class="spot-trade bg-white min-h-screen pb-20">
    <!-- Custom Header with Tabs -->
    <div class="flex items-center px-2 h-12 bg-white sticky top-0 z-50 border-b border-gray-100">
        <van-icon name="arrow-left" size="20" class="text-gray-800 p-2" @click="router.back()" />
        
        <div class="flex-1 flex justify-center space-x-6">
            <div 
                class="relative py-3 font-medium text-base cursor-pointer hover:font-bold transition-all"
                :class="activeMode === 0 ? 'text-[#4BBD83]' : 'text-gray-500'"
                @click="switchMode(0)"
            >
                {{ t('market.seconds') }}
            </div>
             <div 
                class="relative py-3 font-bold text-base cursor-pointer"
                :class="activeMode === 1 ? 'text-[#4BBD83]' : 'text-gray-500'"
                @click="switchMode(1)"
            >
                {{ t('market.spot') }}
                 <div v-if="activeMode === 1" class="absolute bottom-0 left-1/2 -translate-x-1/2 w-8 h-0.5 bg-[#4BBD83]"></div>
            </div>
             <div 
                class="relative py-3 font-medium text-base cursor-pointer hover:font-bold transition-all"
                :class="activeMode === 2 ? 'text-[#4BBD83]' : 'text-gray-500'"
                @click="switchMode(2)"
            >
                {{ t('market.cycle') }}
            </div>
        </div>
        
        <div class="w-8"></div> 
    </div>

    <!-- Coin Sub-Header -->
    <div class="px-4 py-3 border-b border-gray-50">
         <div class="flex justify-between items-center">
             <div class="flex items-center space-x-2">
                 <van-icon name="wap-nav" size="20" class="text-gray-600" />
                 <span class="text-xl font-bold text-gray-800">{{ symbol }}</span>
                 <span class="text-xs bg-green-100 text-green-600 px-1 rounded ml-2">+1.33%</span>
             </div>
             <div class="flex items-center text-gray-400">
                 <van-icon name="chart-trending-o" size="20" />
             </div>
         </div>
    </div>

    <!-- Main Trading Layout -->
    <div class="flex p-4 gap-4">
        <!-- Left: Order Book -->
        <div class="w-[40%] flex flex-col">
            <div class="flex justify-between text-xs text-gray-400 mb-2">
                <span>{{ t('trade.price') }}</span>
                <span>{{ t('trade.amt') }}</span>
            </div>
            
            <!-- Asks (Red) -->
            <div class="flex flex-col-reverse gap-1 mb-2">
                <div v-for="(ask, i) in orderBookAsks" :key="i" class="flex justify-between text-xs relative h-5 items-center">
                    <span class="text-[#D62548] z-10">{{ ask.price }}</span>
                    <span class="text-gray-400 z-10">{{ ask.amount }}</span>
                    <div class="absolute right-0 top-0 bottom-0 bg-red-50" :style="{ width: (parseFloat(ask.amount) * 30) + '%' }"></div>
                </div>
            </div>
            
            <div class="text-lg font-bold text-[#4BBD83] text-center my-2">{{ price }}</div>
            
            <!-- Bids (Green) -->
             <div class="flex flex-col gap-1">
                <div v-for="(bid, i) in orderBookBids" :key="i" class="flex justify-between text-xs relative h-5 items-center">
                    <span class="text-[#4BBD83] z-10">{{ bid.price }}</span>
                    <span class="text-gray-400 z-10">{{ bid.amount }}</span>
                    <div class="absolute right-0 top-0 bottom-0 bg-green-50" :style="{ width: (parseFloat(bid.amount) * 20) + '%' }"></div>
                </div>
            </div>
        </div>
        
        <!-- Right: Trade Form -->
        <div class="flex-1 flex flex-col">
            <!-- Buy/Sell Toggle -->
            <div class="flex bg-gray-100 rounded p-1 mb-4">
                 <div 
                    class="flex-1 text-center py-2 rounded-sm text-sm font-bold cursor-pointer transition-all"
                    :class="activeSide === 'buy' ? 'bg-[#4BBD83] text-white shadow-sm' : 'text-gray-500'"
                    @click="activeSide = 'buy'"
                >
                    {{ t('trade.buy') }}
                </div>
                <div 
                    class="flex-1 text-center py-2 rounded-sm text-sm font-bold cursor-pointer transition-all"
                    :class="activeSide === 'sell' ? 'bg-[#D62548] text-white shadow-sm' : 'text-gray-500'"
                    @click="activeSide = 'sell'"
                >
                    {{ t('trade.sell') }}
                </div>
            </div>
            
            <!-- Order Type -->
            <div class="flex items-center justify-between mb-3 text-sm text-gray-500 border border-gray-200 rounded px-2 py-2">
                <span>{{ t('trade.limit_order') }}</span>
                <van-icon name="arrow-down" />
            </div>
            
            <!-- Price Input -->
             <div class="relative mb-3">
                <input 
                    v-model="price" 
                    type="number"
                    class="w-full bg-white border border-gray-200 rounded px-3 py-2 text-sm font-bold text-gray-800 outline-none focus:border-[#4BBD83]"
                    :placeholder="t('trade.price')"
                />
            </div>
            
            <!-- Amount Input -->
             <div class="relative mb-3">
                <input 
                    v-model="amount" 
                    type="number"
                    class="w-full bg-white border border-gray-200 rounded px-3 py-2 text-sm font-bold text-gray-800 outline-none focus:border-[#4BBD83]"
                    :placeholder="t('trade.amount')"
                />
                 <span class="absolute right-3 top-1/2 -translate-y-1/2 text-xs text-gray-400">USDT</span>
            </div>
            
            <!-- Percent Sliders (Mock) -->
            <div class="grid grid-cols-4 gap-2 mb-4">
                <div v-for="p in ['25%', '50%', '75%', '100%']" :key="p" class="text-[10px] text-center bg-gray-50 border border-gray-100 rounded py-1 text-gray-500">
                    {{ p }}
                </div>
            </div>
            
            <div class="flex justify-between text-xs text-gray-400 mb-4">
                <span>{{ t('trade.avail') }}</span>
                <span class="text-gray-800 font-bold">{{ balance }} USDT</span>
            </div>
            
            <button 
                class="w-full py-3 rounded text-white font-bold text-base transition-opacity hover:opacity-90"
                :class="activeSide === 'buy' ? 'bg-[#4BBD83]' : 'bg-[#D62548]'"
            >
                {{ activeSide === 'buy' ? t('trade.buy') : t('trade.sell') }} {{ symbol.split('/')[0] }}
            </button>
        </div>
    </div>
    
    <!-- Open Orders -->
    <div class="border-t border-gray-100 mt-2">
        <div class="flex text-sm font-bold text-gray-500 p-4 border-b border-gray-50">
            <span class="text-gray-800 border-b-2 border-[#4BBD83] pb-2">{{ t('trade.open_orders') }}</span>
        </div>
        <van-empty :description="t('trade.no_open_orders')" image="search" image-size="60" />
    </div>
  </div>
</template>

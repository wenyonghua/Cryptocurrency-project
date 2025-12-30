<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';
import TradeChart from '@/components/TradeChart.vue';

const { t } = useI18n();

const activeSide = ref('buy');
const orderType = ref('limit');
const price = ref('43250.00');
const amount = ref('');
const showChart = ref(true);

const orderBookAsks = [
    { price: '43255.50', amount: '0.124' },
    { price: '43254.00', amount: '0.052' },
    { price: '43253.20', amount: '1.200' },
    { price: '43252.10', amount: '0.450' },
    { price: '43251.00', amount: '0.010' },
];

const orderBookBids = [
    { price: '43250.00', amount: '0.550' },
    { price: '43249.50', amount: '1.100' },
    { price: '43248.10', amount: '0.020' },
    { price: '43247.90', amount: '0.330' },
    { price: '43246.00', amount: '0.150' },
];
</script>

<template>
  <div class="trade bg-white min-h-screen pb-16">
    <van-nav-bar fixed placeholder title="BTC/USDT">
        <template #right>
            <van-icon name="chart-trending-o" size="18" @click="showChart = !showChart" />
        </template>
    </van-nav-bar>

    <!-- Chart Toggle -->
    <div v-if="showChart" class="border-b border-gray-100">
        <TradeChart />
    </div>

    <!-- Trade Interface -->
    <div class="flex p-4">
        <!-- Input Form -->
        <div class="flex-1 pr-4">
            <div class="flex mb-4 bg-gray-100 rounded p-1">
                <div 
                    class="flex-1 text-center py-1 rounded text-sm font-bold cursor-pointer transition-colors"
                    :class="activeSide === 'buy' ? 'bg-green-500 text-white' : 'text-gray-500'"
                    @click="activeSide = 'buy'"
                >
                    {{ t('trade.buy') }}
                </div>
                <div 
                    class="flex-1 text-center py-1 rounded text-sm font-bold cursor-pointer transition-colors"
                    :class="activeSide === 'sell' ? 'bg-red-500 text-white' : 'text-gray-500'"
                    @click="activeSide = 'sell'"
                >
                    {{ t('trade.sell') }}
                </div>
            </div>

            <van-dropdown-menu class="mb-2">
                <van-dropdown-item v-model="orderType" :options="[{ text: t('trade.limit_order'), value: 'limit' }, { text: t('trade.market_order'), value: 'market' }]" />
            </van-dropdown-menu>

            <div class="mb-2">
                <van-field v-model="price" :placeholder="t('trade.price') + ' (USDT)'" type="number" class="bg-gray-50 border rounded" />
            </div>
            <div class="mb-4">
                <van-field v-model="amount" :placeholder="t('trade.amount') + ' (BTC)'" type="number" class="bg-gray-50 border rounded" />
            </div>

            <div class="text-xs text-gray-500 mb-4 flex justify-between">
                <span>{{ t('trade.avail') }}:</span>
                <span>1240.50 USDT</span>
            </div>

            <van-button 
                block 
                :color="activeSide === 'buy' ? '#4BBD83' : '#D62548'"
                class="font-bold rounded"
            >
                {{ activeSide === 'buy' ? t('trade.buy') : t('trade.sell') }} BTC
            </van-button>
        </div>

        <!-- Order Book -->
        <div class="w-1/3 text-xs">
            <div class="flex justify-between text-gray-400 mb-1">
                <span>{{ t('trade.price') }}</span>
                <span>{{ t('trade.amt') }}</span>
            </div>
            <!-- Asks -->
            <div class="mb-2">
                <div v-for="ask in orderBookAsks" :key="ask.price" class="flex justify-between py-0.5 text-red-500">
                    <span>{{ ask.price }}</span>
                    <span class="text-gray-500">{{ ask.amount }}</span>
                </div>
            </div>
            
            <div class="text-lg font-bold text-center my-2 text-green-500">43250.00</div>
            
            <!-- Bids -->
            <div>
                 <div v-for="bid in orderBookBids" :key="bid.price" class="flex justify-between py-0.5 text-green-500">
                    <span>{{ bid.price }}</span>
                    <span class="text-gray-500">{{ bid.amount }}</span>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Open Orders -->
    <div class="px-4 mt-4">
        <div class="font-bold text-sm mb-2">{{ t('trade.open_orders') }}</div>
        <van-empty :description="t('trade.no_open_orders')" image-size="60" />
    </div>
  </div>
</template>

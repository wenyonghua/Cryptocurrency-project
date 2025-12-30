<script setup lang="ts">
import { ref, computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const router = useRouter();
const searchText = ref('');

const activeTab = ref(1); // Default to Spot

// Mock data with more details
const spotList = ref([
    { symbol: 'BTC/USDT', price: '88665.80', change: 1.34, color: '#F7931A' },
    { symbol: 'ETH/USDT', price: '2960.98', change: 1.13, color: '#627EEA' },
    { symbol: 'XRP/USDT', price: '1.865000', change: -0.30, color: '#23292F' },
    { symbol: 'LTC/USDT', price: '77.1700', change: 0.67, color: '#345D9D' },
    { symbol: 'BNB/USDT', price: '841.20', change: 0.04, color: '#F3BA2F' },
    { symbol: 'SOL/USDT', price: '122.46', change: 0.43, color: '#808080' }, 
    { symbol: 'DOGE/USDT', price: '0.125680', change: -1.21, color: '#C2A633' },
    { symbol: 'ADA/USDT', price: '0.5500', change: 0.0, color: '#0033AD' },
]);

const futuresList = ref([
    { symbol: 'BTC/USDT', price: '88700.00', change: 1.35, color: '#F7931A' },
    { symbol: 'ETH/USDT', price: '2965.00', change: 1.10, color: '#627EEA' },
]);

// Filtered Computed Properties
const filteredSpotList = computed(() => {
    if (!searchText.value) return spotList.value;
    return spotList.value.filter(item => 
        item.symbol.toLowerCase().includes(searchText.value.toLowerCase())
    );
});

const filteredFuturesList = computed(() => {
    if (!searchText.value) return futuresList.value;
    return futuresList.value.filter(item => 
        item.symbol.toLowerCase().includes(searchText.value.toLowerCase())
    );
});

// Helper for icon text/color (if no real icon)
const getIconStyle = (symbol: string, color: string) => {
    return {
        backgroundColor: color || '#ccc',
    };
};

const goToTrade = (symbol: string) => {
    // Determine target based on active tab
    // Tab indices: 0: Favorites, 1: Seconds, 2: Spot, 3: Cycle
    // Note: In the Vant Tabs, index corresponds to the rendered order or name if provided.
    // Based on template below:
    // Tab 1: Favorites
    // Tab 2: Seconds (This was active in user screenshot, index 1 if 0-based?)
    // Let's rely on the bound activeTab value.
    // In template: v-model:active="activeTab"
    
    // We should probably explicitly name tabs to be safe, but let's check current order.
    // Order: Favorites, Seconds, Spot, Cycle.
    // activeTab starts at 1, which likely corresponds to the SECOND tab (Seconds) if 0-based index or name is not used? 
    // Wait, let's assign names to tabs to be precise.
    
    // Actually, let's just use the current order:
    // 0: Favorites
    // 1: Seconds
    // 2: Spot
    // 3: Cycle
    
    if (activeTab.value === 1) {
        router.push({ name: 'seconds-trade', query: { symbol } });
    } else if (activeTab.value === 2) {
        // Spot
         router.push({ name: 'spot-trade', query: { symbol } });
    } else if (activeTab.value === 3) {
        // Cycle
         router.push({ name: 'cycle-trade', query: { symbol } });
    } else {
        // Default or favorites, maybe go to spot?
        router.push({ name: 'spot-trade', query: { symbol } });
    }
};
</script>

<template>
  <div class="market bg-white min-h-screen pb-16">
    <!-- Search Bar -->
     <div class="p-3">
        <div class="bg-gray-100 rounded-lg flex items-center px-3 py-2">
            <van-icon name="search" class="text-gray-400 mr-2" size="18" />
            <input 
                v-model="searchText"
                type="text" 
                :placeholder="t('market.search_coin')" 
                class="bg-transparent border-none outline-none text-sm flex-1 text-gray-700 placeholder-gray-400"
            />
        </div>
     </div>

    <!-- Tabs -->
    <van-tabs 
        v-model:active="activeTab" 
        sticky 
        color="#4BBD83" 
        title-active-color="#4BBD83"
        line-width="20px"
        line-height="3px"
        class="market-tabs"
    >
      <van-tab :title="t('market.favorites')" name="0">
        <van-empty :description="t('market.no_favorites')" image="search" />
      </van-tab>
      
      <van-tab :title="t('market.seconds')" name="1">
         <!-- Reuse Spot List for demo, but filter or different data -->
         <div class="px-4">
             <!-- Header -->
            <div class="flex text-xs text-gray-400 py-3 items-center">
                <div class="w-[40%] flex items-center">
                    {{ t('market.pair') }}
                    <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
                <div class="w-[30%] text-center flex items-center justify-center">
                    {{ t('market.last_price') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
                <div class="w-[30%] text-right flex items-center justify-end">
                    {{ t('market.chg') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
            </div>
            
             <div 
                v-for="item in filteredSpotList" 
                :key="item.symbol" 
                class="py-4 border-b border-gray-50 flex justify-between items-center active:bg-gray-50 transition-colors"
                @click="goToTrade(item.symbol)"
             >
                 <div class="w-[40%] flex items-center">
                     <!-- Currency Icon Mock -->
                     <div 
                        class="w-8 h-8 rounded-full flex items-center justify-center text-white text-[10px] font-bold mr-3 shadow-sm shrink-0"
                        :style="getIconStyle(item.symbol, item.color)"
                     >
                         <van-icon :name="item.symbol.includes('BTC') ? 'bitcoin' : 'gem-o'" size="16" />
                     </div>
                     <span class="font-bold text-gray-800 text-base">{{ item.symbol }}</span>
                 </div>
                 
                 <div class="w-[30%] text-center font-medium text-gray-800 text-base">
                     {{ item.price }}
                 </div>
                 
                 <div class="w-[30%] flex justify-end">
                    <div 
                        class="min-w-[70px] py-1.5 rounded text-center text-white font-bold text-sm"
                        :class="item.change >= 0 ? 'bg-[#D1F3E0] text-[#4BBD83]' : 'bg-[#FFD9de] text-[#D62548]'"
                    >
                        {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
                    </div>
                 </div>
             </div>
         </div>
      </van-tab>

      <van-tab :title="t('market.spot')" name="2">
         <div class="px-4">
             <!-- Header -->
            <div class="flex text-xs text-gray-400 py-3 items-center">
                <div class="w-[40%] flex items-center">
                    {{ t('market.pair') }}
                    <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
                <div class="w-[30%] text-center flex items-center justify-center">
                    {{ t('market.last_price') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
                <div class="w-[30%] text-right flex items-center justify-end">
                    {{ t('market.chg') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
            </div>
            
             <div 
                v-for="item in filteredSpotList" 
                :key="item.symbol" 
                class="py-4 border-b border-gray-50 flex justify-between items-center active:bg-gray-50 transition-colors"
                @click="goToTrade(item.symbol)"
             >
                 <div class="w-[40%] flex items-center">
                     <div 
                        class="w-8 h-8 rounded-full flex items-center justify-center text-white text-[10px] font-bold mr-3 shadow-sm shrink-0"
                        :style="getIconStyle(item.symbol, item.color)"
                     >
                        <!-- Simple logic to try use Vant icons or text -->
                        <van-icon v-if="item.symbol.includes('BTC')" name="bitcoin" size="18" />
                        <span v-else-if="item.symbol.includes('ETH')">E</span>
                        <span v-else>{{ item.symbol[0] }}</span>
                     </div>
                     <span class="font-bold text-gray-800 text-base">{{ item.symbol }}</span>
                 </div>
                 <div class="w-[30%] text-center font-medium text-gray-800 text-base">
                     {{ item.price }}
                 </div>
                 <div class="w-[30%] flex justify-end">
                    <div 
                        class="min-w-[70px] py-1.5 rounded text-center font-bold text-sm"
                        :class="item.change >= 0 ? 'bg-[#E5F9F0] text-[#4BBD83]' : 'bg-[#FFEBED] text-[#D62548]'"
                    >
                        {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
                    </div>
                 </div>
             </div>
         </div>
      </van-tab>
      
      <van-tab :title="t('market.cycle')" name="3">
         <div class="px-4">
             <!-- Header -->
             <div class="flex text-xs text-gray-400 py-3 items-center">
                <div class="w-[40%] flex items-center">
                    {{ t('market.pair') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
                <div class="w-[30%] text-center flex items-center justify-center">
                    {{ t('market.last_price') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
                <div class="w-[30%] text-right flex items-center justify-end">
                    {{ t('market.chg') }}
                     <div class="flex flex-col ml-1 scale-75">
                         <van-icon name="play" class="-rotate-90 text-[10px]" />
                         <van-icon name="play" class="rotate-90 text-[10px]" />
                    </div>
                </div>
            </div>
            
            <!-- List -->
            <div 
                v-for="item in filteredFuturesList" 
                :key="item.symbol" 
                class="py-4 border-b border-gray-50 flex justify-between items-center active:bg-gray-50 transition-colors"
                @click="goToTrade(item.symbol)"
            >
                 <div class="w-[40%] flex items-center">
                     <div 
                        class="w-8 h-8 rounded-full flex items-center justify-center text-white text-[10px] font-bold mr-3 shadow-sm shrink-0"
                        :style="getIconStyle(item.symbol, item.color)"
                     >
                       <van-icon v-if="item.symbol.includes('BTC')" name="bitcoin" size="18" />
                        <span v-else-if="item.symbol.includes('ETH')">E</span>
                        <span v-else>{{ item.symbol[0] }}</span>
                     </div>
                     <span class="font-bold text-gray-800 text-base">{{ item.symbol }}</span>
                 </div>
                 <div class="w-[30%] text-center font-medium text-gray-800 text-base">
                     {{ item.price }}
                 </div>
                 <div class="w-[30%] flex justify-end">
                    <div 
                        class="min-w-[70px] py-1.5 rounded text-center font-bold text-sm"
                        :class="item.change >= 0 ? 'bg-[#E5F9F0] text-[#4BBD83]' : 'bg-[#FFEBED] text-[#D62548]'"
                    >
                        {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
                    </div>
                 </div>
             </div>
         </div>
      </van-tab>
    </van-tabs>
  </div>
</template>

<style scoped>
/* Customize Tab Styles */
:deep(.van-tab) {
    font-size: 15px;
    font-weight: 500;
    color: #666;
}
:deep(.van-tab--active) {
    font-weight: bold;
}
:deep(.van-tabs__line) {
    bottom: 20px; 
}
:deep(.van-tabs__wrap) {
    height: 44px;
}
</style>

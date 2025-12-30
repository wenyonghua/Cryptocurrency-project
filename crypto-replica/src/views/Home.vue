<script setup lang="ts">
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const router = useRouter();
const { t, locale } = useI18n();

const showDrawer = ref(false);
const activeTab = ref(0);

// Exact 12 items as per analysis
const gridItems = computed(() => [
    { text: t('grid.mining'), icon: 'diamond-o', color: '#8A2BE2', route: '/feature/mining' },
    { text: t('grid.loan'), icon: 'refund-o', color: '#1E90FF', route: '/feature/loan' },
    { text: t('grid.swap'), icon: 'exchange', color: '#FFD700', route: '/feature/swap' },
    { text: t('grid.cycle'), icon: 'clock-o', color: '#FF4500', route: '/trade/cycle' },
    { text: t('grid.promotion'), icon: 'bullhorn-o', color: '#FF69B4', route: '/feature/promotion' },
    { text: t('grid.spot'), icon: 'chart-trending-o', color: '#32CD32', route: '/trade/spot' },
    { text: t('grid.seconds'), icon: 'underway-o', color: '#FF6347', route: '/trade/seconds' },
    { text: t('grid.finance'), icon: 'balance-o', color: '#4682B4', route: '/feature/finance' },
    { text: t('grid.ieo'), icon: 'fire-o', color: '#DC143C', route: '/feature/ieo' },
    { text: t('grid.deposit'), icon: 'gold-coin-o', color: '#00FA9A', route: '/assets/deposit' },
    { text: t('grid.withdraw'), icon: 'card-o', color: '#00BFFF', route: '/assets/withdraw' },
    { text: t('grid.support'), icon: 'service-o', color: '#9370DB', route: '/feature/support' }, // Support usually opens chat
]);

const coins = ref([
    { symbol: 'BTC', price: '43250.00', change: 2.5 },
    { symbol: 'ETH', price: '2250.00', change: -1.2 },
    { symbol: 'TRX', price: '0.1050', change: 0.5 },
    { symbol: 'BNB', price: '320.00', change: 1.1 },
    { symbol: 'SOL', price: '98.50', change: 5.4 },
    { symbol: 'XRP', price: '0.6200', change: -0.8 },
]);

const switchLang = () => {
    locale.value = locale.value === 'en' ? 'zh' : 'en';
}

const handleGridClick = (route: string) => {
    router.push(route);
}
</script>

<template>
  <div class="home bg-gray-50 min-h-screen">
    <!-- Header: Logo Left, Service & User Right -->
    <van-nav-bar fixed placeholder z-index="50">
        <template #left>
            <!-- Logo area -->
            <div class="flex items-center">
                 <div class="font-black text-xl tracking-tighter text-[#4BBD83] mr-1">NYSE</div>
                 <div class="h-4 w-[1px] bg-gray-300 mx-2"></div>
                 <div class="text-xs font-bold text-gray-500">{{ t('home.global') }}</div>
            </div>
        </template>
        <template #right>
            <div class="flex items-center gap-3">
                 <van-icon name="service-o" size="24" color="#333" />
                 <van-icon name="user-circle-o" size="24" color="#333" @click="showDrawer = true" />
            </div>
        </template>
    </van-nav-bar>

    <!-- Side Drawer -->
    <van-popup 
        v-model:show="showDrawer" 
        position="left"
        :style="{ width: '85%', height: '100%', maxWidth: '350px' }"
    >
        <div class="flex flex-col h-full bg-white relative">
            <!-- Close Button -->
            <div class="absolute top-4 right-4 z-10" @click="showDrawer = false">
                <van-icon name="cross" size="24" color="#333" />
            </div>

            <!-- User Header -->
            <div class="pt-12 pb-6 px-6">
                <div class="flex items-center mb-1">
                    <img src="https://img.icons8.com/color/96/vip-badge.png" class="w-10 h-10 mr-3" alt="VIP" />
                    <div>
                        <div class="flex items-center text-gray-800 font-bold text-lg">
                            <span>UID: 2859416</span>
                            <van-icon name="description" class="ml-2 text-[#4BBD83] cursor-pointer" />
                        </div>
                        <div class="text-gray-500 text-sm mt-1">
                            {{ t('home.credit_score') }}: 0
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Menu List -->
            <div class="flex-1 overflow-y-auto px-4 pb-4">
                 <!-- Menu Item Component for consistency -->
                 <div class="space-y-6">
                     <!-- Primary Cert -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70" @click="router.push('/mine/identity')">
                        <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/id-verified.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.primary_cert') }}</span>
                        </div>
                        <van-icon name="arrow" color="#ccc" />
                    </div>

                    <!-- Advanced Cert -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70" @click="router.push('/mine/identity')">
                        <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/guarantee.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.advanced_cert') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>

                    <!-- Fund Password -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70" @click="router.push('/mine/security')">
                         <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/password.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.fund_pwd') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>

                    <!-- Login Password -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70" @click="router.push('/mine/security')">
                        <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/password--v1.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.login_pwd') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>

                    <!-- Email Auth -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70" @click="router.push('/mine/security')">
                        <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/email.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.email_auth') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>

                    <!-- Service Terms -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70">
                         <div class="flex items-center">
                            <img src="https://img.icons8.com/ios/50/checked-2--v1.png" class="w-6 h-6 mr-4 opacity-60" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.terms') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>

                    <!-- Help Center -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70">
                         <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/idea.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span class="text-gray-700 text-base font-medium">{{ t('drawer.help') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>
                    
                    <!-- Language -->
                    <div class="flex justify-between items-center cursor-pointer active:opacity-70" @click="router.push('/language')">
                         <div class="flex items-center">
                            <img src="https://img.icons8.com/color/48/globe.png" class="w-6 h-6 mr-4 opacity-80" />
                            <span>{{ t('drawer.language') }}</span>
                        </div>
                         <van-icon name="arrow" color="#ccc" />
                    </div>
                 </div>
            </div>
        </div>
    </van-popup>

    <!-- Swipe Banners -->
    <van-swipe class="my-swipe h-44" :autoplay="3000" indicator-color="white">
      <van-swipe-item class="bg-gradient-to-r from-[#4BBD83] to-[#2E8B57] flex items-center justify-center p-4">
         <div class="text-center text-white">
             <div class="text-2xl font-bold mb-2">{{ t('home.new_user_bonus') }}</div>
             <div class="text-sm opacity-90">{{ t('home.sign_up_bonus') }}</div>
         </div>
      </van-swipe-item>
       <van-swipe-item class="bg-gradient-to-r from-[#D62548] to-[#CD5C5C] flex items-center justify-center p-4">
         <div class="text-center text-white">
             <div class="text-2xl font-bold mb-2">{{ t('home.seconds_contract') }}</div>
             <div class="text-sm opacity-90">{{ t('home.seconds_desc') }}</div>
         </div>
      </van-swipe-item>
    </van-swipe>

    <!-- Notice Bar -->
    <van-notice-bar
      left-icon="volume-o"
      :text="t('home.notice')"
      background="#fff"
      color="#333"
    />

    <!-- Function Grid (12 Items) -->
    <van-grid :column-num="4" :border="false" class="py-2">
      <van-grid-item 
        v-for="(item, index) in gridItems" 
        :key="index" 
        class="mb-2"
        @click="handleGridClick(item.route)"
      >
          <div class="flex flex-col items-center">
              <!-- Using colorful squared background for icons to match target style closer -->
              <div class="w-12 h-12 rounded-2xl flex items-center justify-center mb-1 text-white shadow-sm from-white/10" 
                   :style="{ backgroundColor: item.color, backgroundImage: 'linear-gradient(135deg, rgba(255,255,255,0.2) 0%, rgba(0,0,0,0.05) 100%)' }">
                  <van-icon :name="item.icon" size="24" />
              </div>
              <span class="text-xs text-gray-600 transform scale-90 mt-1">{{ item.text }}</span>
          </div>
      </van-grid-item>
    </van-grid>

    <!-- Market Tabs -->
    <div class="bg-white mt-2 min-h-[300px]">
        <van-tabs v-model:active="activeTab" sticky animated swipeable color="#4BBD83" line-width="20px">
            <van-tab :title="t('home.hot')">
                <div class="px-4">
                    <div v-for="coin in coins" :key="coin.symbol" class="flex justify-between items-center py-4 border-b border-gray-100 last:border-0" @click="$router.push('/trade/seconds')">
                        <div class="flex items-center">
                            <div class="w-8 h-8 rounded-full bg-gray-100 flex items-center justify-center mr-3 font-bold text-xs text-gray-400 border border-gray-200">
                                {{ coin.symbol[0] }}
                            </div>
                            <div>
                                <div class="font-bold text-base text-gray-800 flex items-center">
                                    {{ coin.symbol }}
                                    <span class="text-[10px] bg-gray-100 text-gray-500 px-1 ml-1 rounded">USDT</span>
                                </div>
                                <div class="text-xs text-gray-400">Vol 243.5M</div>
                            </div>
                        </div>
                        <div class="text-center">
                            <div class="font-bold text-gray-800">{{ coin.price }}</div>
                            <div class="text-xs text-gray-400">≈ ${{ coin.price }}</div>
                        </div>
                        <div>
                            <van-button 
                                size="mini" 
                                :color="coin.change >= 0 ? '#4BBD83' : '#D62548'"
                                class="min-w-[70px] font-bold !text-white !border-0 rounded-sm h-8"
                            >
                                {{ coin.change >= 0 ? '+' : '' }}{{ coin.change }}%
                            </van-button>
                        </div>
                    </div>
                </div>
            </van-tab>
            <van-tab :title="t('home.forex')">
                <van-empty :description="t('home.forex') + ' Data'" image="search" />
            </van-tab>
            <van-tab :title="t('home.gold')">
                 <van-empty :description="t('home.gold') + ' Data'" image="search" />
            </van-tab>
        </van-tabs>
    </div>
  </div>
</template>

<style scoped>
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: normal;
}
:deep(.van-grid-item__content) {
    padding: 8px 4px;
}
/* Move popup from right */
:deep(.van-popup--right) {
    border-top-left-radius: 16px;
    border-bottom-left-radius: 16px;
}
</style>

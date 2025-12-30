<script setup lang="ts">
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
</script>

<template>
  <van-tabbar route fixed placeholder safe-area-inset-bottom active-color="#4BBD83" inactive-color="#7d7e80">
    <van-tabbar-item replace to="/" icon="wap-home-o">{{ t('nav.home') }}</van-tabbar-item>
    <van-tabbar-item replace to="/market" icon="chart-trending-o">{{ t('nav.market') }}</van-tabbar-item>
    <van-tabbar-item replace to="/trade/seconds" icon="exchange">{{ t('nav.trade') }}</van-tabbar-item>
    <van-tabbar-item replace to="/assets" icon="balance-list-o">{{ t('nav.assets') }}</van-tabbar-item>
    <van-tabbar-item replace to="/mine" icon="user-o">{{ t('nav.mine') }}</van-tabbar-item>
  </van-tabbar>
</template>

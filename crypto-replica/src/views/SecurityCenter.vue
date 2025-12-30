<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const router = useRouter();

// Just a menu list for now, or actual forms inline?
// Let's make it a menu that opens actions, or inline forms if simple.
// For now, replicating structure of "Security Center" often seen
</script>

<template>
  <div class="security-center bg-gray-50 min-h-screen">
      <van-nav-bar 
        :title="t('mine.security')" 
        left-arrow 
        @click-left="router.back()" 
        fixed 
        placeholder 
    />
    
    <div class="mt-2">
        <van-cell-group inset>
            <van-cell :title="t('drawer.login_pwd')" is-link value="Modify" />
            <van-cell :title="t('drawer.fund_pwd')" is-link value="Set" />
        </van-cell-group>
        
        <van-cell-group inset class="mt-2">
             <van-cell :title="t('drawer.email_auth')" is-link value="Verified" />
             <!-- Phone, Google Auth etc could go here -->
        </van-cell-group>
    </div>
  </div>
</template>

<script setup lang="ts">
import TabBar from '@/components/TabBar.vue';
import { RouterView } from 'vue-router';
</script>

<template>
  <div class="min-h-screen bg-gray-50 flex justify-center font-sans text-gray-900">
    <div class="w-full max-w-[480px] bg-white min-h-screen relative flex flex-col shadow-2xl overflow-hidden">
        <!-- Main Content Area -->
        <main class="flex-1 overflow-y-auto scrollbar-hide">
            <RouterView />
        </main>
        
        <!-- Bottom Navigation -->
        <TabBar />
    </div>
  </div>
</template>

<style>
/* Hide scrollbar for Chrome, Safari and Opera */
.scrollbar-hide::-webkit-scrollbar {
    display: none;
}
/* Hide scrollbar for IE, Edge and Firefox */
.scrollbar-hide {
    -ms-overflow-style: none;  /* IE and Edge */
    scrollbar-width: none;  /* Firefox */
}
</style>

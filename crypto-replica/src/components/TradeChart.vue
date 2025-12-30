<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import { createChart, ColorType } from 'lightweight-charts';

const chartContainer = ref<HTMLElement | null>(null);
let chart: any = null;
let candleSeries: any = null;

const generateData = () => {
  const initialDate = new Date(2023, 0, 1).getTime() / 1000;
  const data = [];
  let price = 43000;
  for (let i = 0; i < 100; i++) {
    const time = initialDate + i * 24 * 60 * 60;
    const open = price + Math.random() * 500 - 250;
    const close = open + Math.random() * 500 - 250;
    const high = Math.max(open, close) + Math.random() * 100;
    const low = Math.min(open, close) - Math.random() * 100;
    data.push({ time, open, high, low, close });
    price = close;
  }
  return data;
};

onMounted(() => {
  if (!chartContainer.value) return;
  
  chart = createChart(chartContainer.value, {
    layout: {
      background: { type: ColorType.Solid, color: '#ffffff' },
      textColor: '#333',
    },
    grid: {
      vertLines: { color: '#f0f3fa' },
      horzLines: { color: '#f0f3fa' },
    },
    width: chartContainer.value.clientWidth,
    height: 300,
  });
  
  candleSeries = chart.addCandlestickSeries({
    upColor: '#4BBD83', 
    downColor: '#D62548', 
    borderVisible: false, 
    wickUpColor: '#4BBD83', 
    wickDownColor: '#D62548'
  });
  
  candleSeries.setData(generateData());
  
  // Resize observer
  const resizeObserver = new ResizeObserver(entries => {
      if (entries.length === 0 || entries[0].target !== chartContainer.value) { return; }
      const newRect = entries[0].contentRect;
      chart.applyOptions({ width: newRect.width });
  });
  resizeObserver.observe(chartContainer.value);
});

onUnmounted(() => {
    if (chart) {
        chart.remove();
        chart = null;
    }
});
</script>

<template>
  <div class="h-[300px] w-full relative">
      <div ref="chartContainer" class="w-full h-full"></div>
  </div>
</template>

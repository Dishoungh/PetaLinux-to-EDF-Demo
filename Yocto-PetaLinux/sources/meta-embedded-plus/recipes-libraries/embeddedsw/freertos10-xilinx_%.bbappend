EXTRA_OECMAKE:append:emb-plus-amr = " \
    -DXILTIMER_en_interval_timer=ON \
    -Dfreertos_tick_rate=1000 \
    -Dfreertos_total_heap_size=131072 \
    -Dfreertos_support_static_allocation=1 \
    "

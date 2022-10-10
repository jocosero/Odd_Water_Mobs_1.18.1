package com.jocosero.odd_water_mobs.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ODD_WATER_MOBS_TAB = new CreativeModeTab("odd_water_mobs_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_ANGLERFISH.get());
        }
    };
}




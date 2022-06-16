package com.nyfaria.growlight.init;

import com.nyfaria.growlight.GrowLight;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GrowLight.MODID);

    public static final RegistryObject<Item> GROW_LIGHT = ITEMS.register("grow_light",()-> new BlockItem(BlockInit.GROW_LIGHT.get(), new Item.Properties().tab(ItemGroup.TAB_MISC)));
}

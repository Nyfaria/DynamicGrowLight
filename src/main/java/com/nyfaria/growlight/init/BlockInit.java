package com.nyfaria.growlight.init;

import com.nyfaria.growlight.GrowLight;
import com.nyfaria.growlight.block.GrowLightBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GrowLight.MODID);

    public static final RegistryObject<Block> GROW_LIGHT = BLOCKS.register("grow_light",()-> new GrowLightBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.COLOR_BLACK)));
}

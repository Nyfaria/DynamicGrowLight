package com.nyfaria.growlight.mixin;

import com.nyfaria.growlight.caps.LightHolderAttacher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkLightProvider;
import net.minecraft.world.lighting.LightEngine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public class LightEngineMixin {

    @Shadow
    @Final
    protected IChunkLightProvider chunkSource;

    @Inject(method = "getLightValue", at = @At("HEAD"), cancellable = true)
    public void getBrightness(BlockPos pLevelPos, CallbackInfoReturnable<Integer> cir) {
        LightHolderAttacher.getLightHolder((World) chunkSource.getLevel()).ifPresent(
                holder -> {
                    if(holder.getLightBlocks().contains(pLevelPos)) {
                        cir.setReturnValue(15);
                    }
                }
        );
    }
}

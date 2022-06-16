package com.nyfaria.growlight.event;

import com.nyfaria.growlight.init.BlockInit;
import com.nyfaria.growlight.caps.LightHolderAttacher;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){
        if(event.getWorld().isClientSide())return;
        if(event.getState().is(BlockInit.GROW_LIGHT.get())){
            LightHolderAttacher.getLightHolder((ServerWorld)event.getWorld()).ifPresent(holder->holder.removeSourceBlock(event.getPos(),true));
        }
        event.getWorld().blockUpdated(event.getPos().above(),event.getWorld().getBlockState(event.getPos().above()).getBlock());
    }
}

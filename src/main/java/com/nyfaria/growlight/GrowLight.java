package com.nyfaria.growlight;

import com.nyfaria.growlight.caps.LightHolderAttacher;
import com.nyfaria.growlight.init.BlockInit;
import com.nyfaria.growlight.init.ItemInit;
import com.nyfaria.growlight.network.NetworkHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(GrowLight.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowLight {
    public static final String MODID = "growlight";
    public static final Logger LOGGER = LogManager.getLogger();

    public GrowLight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        NetworkHandler.register();
        LightHolderAttacher.register();
    }

}

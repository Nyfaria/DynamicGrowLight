package com.nyfaria.growlight.caps;

import dev._100media.capabilitysyncer.core.CapabilityAttacher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nullable;

public class LightHolderAttacher extends CapabilityAttacher {
    private static final Class<LightHolder> CAPABILITY_CLASS = LightHolder.class;
    @CapabilityInject(LightHolder.class)
    public static final Capability<LightHolder> LIGHT_CAPABILITY = null;
    public static final ResourceLocation LIGHT_RL = new ResourceLocation("example", "light_capability");

    @Nullable
    public static LightHolder getExampleLightHolderUnwrap(World level) {
        return getLightHolder(level).orElse(null);
    }

    public static LazyOptional<LightHolder> getLightHolder(World level) {
        return level.getCapability(LIGHT_CAPABILITY);
    }

    private static void attach(AttachCapabilitiesEvent<World> event, World level) {
        genericAttachCapability(event, new LightHolder(level), LIGHT_CAPABILITY, LIGHT_RL);
    }

    public static void register() {
        CapabilityAttacher.registerCapability(CAPABILITY_CLASS);
        CapabilityAttacher.registerLevelAttacher(LightHolderAttacher::attach, LightHolderAttacher::getLightHolder);
    }
}
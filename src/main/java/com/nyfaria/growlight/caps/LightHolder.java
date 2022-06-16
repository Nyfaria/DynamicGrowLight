package com.nyfaria.growlight.caps;

import dev._100media.capabilitysyncer.core.LevelCapability;
import dev._100media.capabilitysyncer.network.LevelCapabilityStatusPacket;
import dev._100media.capabilitysyncer.network.SimpleLevelCapabilityStatusPacket;
import com.nyfaria.growlight.network.NetworkHandler;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.ArrayList;
import java.util.List;

public class LightHolder extends LevelCapability {
    private List<BlockPos> sourceBlocks = new ArrayList<>();
    private List<BlockPos> lightBlocks = new ArrayList<>();


    public LightHolder(World level) {
        super(level);
    }

    public List<BlockPos> getSourceBlocks() {
        return sourceBlocks;
    }

    public List<BlockPos> getLightBlocks() {
        return lightBlocks;
    }

    public void addSourceBlock(BlockPos exampleInt, boolean sync) {
        this.sourceBlocks.add(exampleInt);
        updateTracking();
        updateLightBlocks();
    }

    public void removeSourceBlock(BlockPos blockPos, boolean sync) {
        this.sourceBlocks.remove(blockPos);
        updateTracking();
        updateLightBlocks();
        level.blockUpdated(blockPos.above(), level.getBlockState(blockPos.above()).getBlock());
    }

    public void updateLightBlocks() {
        lightBlocks.forEach(blockPos -> {
            level.sendBlockUpdated(blockPos,level.getBlockState(blockPos),level.getBlockState(blockPos),3);
        });
        lightBlocks.clear();
        sourceBlocks.forEach(
                blockPos -> {
                    for (int y = 1; y <= 8; y++) {
                        for (int x = -y; x <= y; x++) {
                            for (int z = -y; z <= y; z++) {
                                BlockPos thePos = blockPos.below(y).north(x).west(z);
                                BlockState blockState = level.getBlockState(thePos);
                                if (blockState.isAir() || blockState.propagatesSkylightDown(level, thePos)) {
                                    lightBlocks.add(thePos);
                                    level.sendBlockUpdated(thePos,blockState, blockState,3);
                                }
                            }
                        }
                    }
                }
        );

    }

    @Override
    public CompoundNBT serializeNBT(boolean savingToDisk) {
        CompoundNBT nbt = new CompoundNBT();
        sourceBlocks.forEach(bp ->
                nbt.putLong(bp.asLong() + "_source", bp.asLong())

        );
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt, boolean readingFromDisk) {
        sourceBlocks.clear();
        nbt.getAllKeys().forEach(key ->
                sourceBlocks.add(BlockPos.of(nbt.getLong(key))
                ));
        updateLightBlocks();
    }

    @Override
    public LevelCapabilityStatusPacket createUpdatePacket() {
        // Make sure to register this update packet to your network channel!
        return new SimpleLevelCapabilityStatusPacket(LightHolderAttacher.LIGHT_RL, this);
    }

    @Override
    public SimpleChannel getNetworkChannel() {
        // Return the network channel here
        return NetworkHandler.INSTANCE;
    }
}
package com.nyfaria.growlight.block;

import com.nyfaria.growlight.caps.LightHolderAttacher;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GrowLightBlock extends Block {
    public GrowLightBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public void onPlace(BlockState pState, World pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        if (!pLevel.isClientSide) {
            LightHolderAttacher.getLightHolder(pLevel).ifPresent(lightHolder -> lightHolder.addSourceBlock(pPos, true));
        }
    }

    @Override
    public void spawnAfterBreak(BlockState pState, ServerWorld pLevel, BlockPos pPos, ItemStack pStack) {
        super.spawnAfterBreak(pState, pLevel, pPos, pStack);
        LightHolderAttacher.getLightHolder(pLevel).ifPresent(lightHolder -> lightHolder.removeSourceBlock(pPos, true));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, IBlockReader pReader, BlockPos pPos) {
        return true;
    }
}

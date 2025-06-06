package net.wolfrest.luminous_helmet.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;

public class InvisibleLightBlock extends Block {
    public InvisibleLightBlock() {
        super(BlockBehaviour.Properties.of()
                .replaceable()
                .noCollission()
                .noOcclusion()
                .lightLevel(state -> 13)
                .air());
    }

    // No colisiona con nada, tampoco con raycasts
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return true;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }
}
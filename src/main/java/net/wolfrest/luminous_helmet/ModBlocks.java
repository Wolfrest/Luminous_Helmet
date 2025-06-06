package net.wolfrest.luminous_helmet;

import net.wolfrest.luminous_helmet.block.InvisibleLightBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "luminous_helmet");

    public static final RegistryObject<Block> INVISIBLE_LIGHT = BLOCKS.register(
            "invisible_light", InvisibleLightBlock::new);
}
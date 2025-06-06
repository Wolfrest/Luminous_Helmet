package net.wolfrest.luminous_helmet.event;

import net.wolfrest.luminous_helmet.ModBlocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "luminous_helmet", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HelmetLightEvent {

    // Guarda la última posición de luz de cada jugador
    private static final Map<UUID, BlockPos> lastLightPos = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        Level level = player.level();
        if (level.isClientSide) return;

        ItemStack helmet = player.getInventory().getArmor(3);
        boolean hasLuminousHelmet = helmet != null && helmet.getItem().getDescriptionId().contains("luminous_helmet");

        UUID playerId = player.getUUID();
        BlockPos currentPos = player.blockPosition();

        if (hasLuminousHelmet) {
            // Borra la luz anterior si cambiaste de bloque
            BlockPos lastPos = lastLightPos.get(playerId);
            if (lastPos != null && !lastPos.equals(currentPos)) {
                if (level.getBlockState(lastPos).getBlock() == ModBlocks.INVISIBLE_LIGHT.get()) {
                    level.setBlockAndUpdate(lastPos, Blocks.AIR.defaultBlockState());
                }
            }
            // Coloca la luz en la nueva posición si no existe ya
            if (level.getBlockState(currentPos).isAir()) {
                level.setBlockAndUpdate(currentPos, ModBlocks.INVISIBLE_LIGHT.get().defaultBlockState());
            }
            // Actualiza la posición
            lastLightPos.put(playerId, currentPos);
        } else {
            // Si el jugador ya no tiene el casco, borra la luz si existe
            BlockPos lastPos = lastLightPos.remove(playerId);
            if (lastPos != null) {
                if (level.getBlockState(lastPos).getBlock() == ModBlocks.INVISIBLE_LIGHT.get()) {
                    level.setBlockAndUpdate(lastPos, Blocks.AIR.defaultBlockState());
                }
            }
        }
    }
}
package net.wolfrest.luminous_helmet.client;

import com.legacy.lucent.api.Lucent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.wolfrest.luminous_helmet.item.ModItems;

    @Mod.EventBusSubscriber(modid = "luminous_helmet", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ClientSetup {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MinecraftForge.EVENT_BUS.register(ClientSetup.class); // Registra esta clase a los eventos Forge
        }

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                Player player = Minecraft.getInstance().player;
                if (player != null && player.getInventory().getArmor(3).getItem() == ModItems.LUMINOUS_HELMET.get()) {
                    Lucent.API.addLight(player.position(), 15);
                }
            }
        }

        @SubscribeEvent
        public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(
                    LuminousHelmetModel.LAYER_LOCATION,
                    () -> LuminousHelmetModel.createBodyLayer(CubeDeformation.NONE));
        }
    }
package net.wolfrest.luminous_helmet.client;

import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = "luminous_helmet", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // No es necesario hacer nada aquí por ahora
    }

    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                LuminousHelmetModel.LAYER_LOCATION,
                () -> LuminousHelmetModel.createBodyLayer(CubeDeformation.NONE)); // Aquí se registra tu capa del modelo
    }
}
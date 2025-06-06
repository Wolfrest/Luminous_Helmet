package net.wolfrest.luminous_helmet.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.wolfrest.luminous_helmet.LuminousHelmet;

public class HelmetLayers {
    public static final ModelLayerLocation LUMINOUS_HELMET = layer("luminous_helmet");

    public static void init(IEventBus modBus) {
        modBus.addListener(HelmetLayers::initLayers);
    }

    public static void initPost(FMLClientSetupEvent event) {
        // Si algún día necesitas lógica extra de cliente
    }

    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LUMINOUS_HELMET,
                () -> LuminousHelmetModel.createBodyLayer(CubeDeformation.NONE));
    }

    private static ModelLayerLocation layer(String name) {
        return layer(name, "main");
    }

    private static ModelLayerLocation layer(String name, String layer) {
        return new ModelLayerLocation(
                ResourceLocation.fromNamespaceAndPath(LuminousHelmet.MODID, name),
                layer // ← CORREGIDO: ahora se usa el parámetro correctamente
        );
    }
}

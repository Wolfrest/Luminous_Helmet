package net.wolfrest.luminous_helmet.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.wolfrest.luminous_helmet.LuminousHelmet;
import net.wolfrest.luminous_helmet.client.LuminousHelmetModel;

import java.util.function.Consumer;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LuminousHelmet.MODID);

    public static final RegistryObject<Item> LUMINOUS_HELMET = ITEMS.register("luminous_helmet",
            () -> new LuminousHelmetItem(ModArmorMaterials.LUMINOUS_HELMET, ArmorItem.Type.HELMET,
                    new Item.Properties()) {
                @Override
                @OnlyIn(Dist.CLIENT)
                public void initializeClient(Consumer<IClientItemExtensions> consumer) {
                    consumer.accept(new IClientItemExtensions() {
                        private final NonNullLazy<LuminousHelmetModel<LivingEntity>> model =
                                NonNullLazy.of(() -> new LuminousHelmetModel<>(getModels().bakeLayer(LuminousHelmetModel.LAYER_LOCATION)));

                        @Override
                        public HumanoidModel<?> getHumanoidArmorModel(
                                LivingEntity entity,
                                ItemStack stack,
                                EquipmentSlot slot,
                                HumanoidModel<?> original) {
                            return model.get();
                        }

                        private EntityModelSet getModels() {
                            return Minecraft.getInstance().getEntityModels();
                        }
                    });
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

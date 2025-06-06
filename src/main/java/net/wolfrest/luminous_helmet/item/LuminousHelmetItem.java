package net.wolfrest.luminous_helmet.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;
import net.wolfrest.luminous_helmet.LuminousHelmet;
import net.wolfrest.luminous_helmet.client.LuminousHelmetModel;

import java.util.function.Consumer;

public class LuminousHelmetItem extends ArmorItem {
    public LuminousHelmetItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, net.minecraft.world.entity.Entity entity, EquipmentSlot slot, String type) {
        // Esta es la ruta absoluta en la carpeta assets/<modid>/textures/models/armor/luminous_helmet.png
        // ¡CUIDADO con el nombre y la carpeta!
        return LuminousHelmet.MODID + ":textures/models/armor/luminous_helmet.png";
    }

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

            private net.minecraft.client.model.geom.EntityModelSet getModels() {
                return net.minecraft.client.Minecraft.getInstance().getEntityModels();
            }
        });
    }
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        // Esto permite repararlo con lingote de cobre
        return repair.getItem() == Items.COPPER_INGOT || super.isValidRepairItem(toRepair, repair);
    }
}
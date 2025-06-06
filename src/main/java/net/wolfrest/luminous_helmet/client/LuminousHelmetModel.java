package net.wolfrest.luminous_helmet.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class LuminousHelmetModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("luminous_helmet", "luminous_helmet"), "main");

	private final ModelPart head;

	public LuminousHelmetModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		// Baja el casco 1px (offset Y a 1.0F)
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(32, 13).addBox(-6.0F, -8.0F, -6.0F, 12.0F, 2.0F, 1.0F, deformation)
						.texOffs(32, 16).addBox(-6.0F, -8.0F, 5.0F, 12.0F, 2.0F, 1.0F, deformation)
						.texOffs(0, 22).addBox(-6.0F, -8.0F, -5.0F, 1.0F, 2.0F, 10.0F, deformation)
						.texOffs(22, 22).addBox(5.0F, -8.0F, -5.0F, 1.0F, 2.0F, 10.0F, deformation)
						.texOffs(0, 0).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 3.0F, 10.0F, deformation)
						.texOffs(0, 34).addBox(-2.0F, -13.0F, -5.7F, 4.0F, 4.0F, 1.0F, deformation)
						.texOffs(0, 13).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 1.0F, 8.0F, deformation),
				PartPose.offset(0.0F, 8.0F, 0.0F) //
		);

		head.addOrReplaceChild("bone2", CubeListBuilder.create()
						.texOffs(10, 34).addBox(-18.0F, -30.9F, -1.0F, 0.0F, 2.0F, 2.0F, deformation)
						.texOffs(20, 34).addBox(-18.0F, -25.9F, -1.0F, 0.0F, 4.0F, 1.0F, deformation)
						.texOffs(18, 34).addBox(-18.0F, -28.9F, 0.0F, 0.0F, 3.0F, 1.0F, deformation),
				PartPose.offset(12.0F, 24.0F, 0.0F)
		);

		head.addOrReplaceChild("bone3", CubeListBuilder.create()
						.texOffs(14, 34).addBox(-18.0F, -30.9F, -1.0F, 0.0F, 2.0F, 2.0F, deformation)
						.texOffs(32, 19).addBox(-18.0F, -25.9F, -1.0F, 0.0F, 2.0F, 1.0F, deformation)
						.texOffs(22, 34).addBox(-18.0F, -28.9F, 0.0F, 0.0F, 3.0F, 1.0F, deformation),
				PartPose.offset(24.0F, 24.0F, 0.0F)
		);

		// Resto de partes vacías (necesarias para HumanoidModel)
		root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// No animación custom
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
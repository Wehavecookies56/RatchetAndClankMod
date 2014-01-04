package com.gugu42.rcmod.weapons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDualVipers extends ModelBase {
	// fields
	ModelRenderer handle;
	ModelRenderer top1;
	ModelRenderer bot1;
	ModelRenderer bot2;
	ModelRenderer top2;

	public ModelDualVipers() {
		textureWidth = 64;
		textureHeight = 32;

		handle = new ModelRenderer(this, 0, 0);
		handle.addBox(0F, 0F, 0F, 2, 6, 2);
		handle.setRotationPoint(0F, 0F, 0F);
		handle.setTextureSize(64, 32);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
		top1 = new ModelRenderer(this, 0, 20);
		top1.addBox(0F, 0F, 0F, 2, 2, 10);
		top1.setRotationPoint(0F, -2F, -8F);
		top1.setTextureSize(64, 32);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		bot1 = new ModelRenderer(this, 51, 0);
		bot1.addBox(0F, 0F, 0F, 2, 8, 2);
		bot1.setRotationPoint(0F, 0F, -7F);
		bot1.setTextureSize(64, 32);
		bot1.mirror = true;
		setRotation(bot1, 0F, 0F, 0F);
		bot2 = new ModelRenderer(this, 24, 0);
		bot2.addBox(0F, 0F, 0F, 2, 2, 3);
		bot2.setRotationPoint(0F, 0F, -5F);
		bot2.setTextureSize(64, 32);
		bot2.mirror = true;
		setRotation(bot2, 0F, 0F, 0F);
		top2 = new ModelRenderer(this, 24, 9);
		top2.addBox(0F, 0F, 0F, 2, 1, 3);
		top2.setRotationPoint(0F, -3F, -6F);
		top2.setTextureSize(64, 32);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		handle.render(f5);
		top1.render(f5);
		bot1.render(f5);
		bot2.render(f5);
		top2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
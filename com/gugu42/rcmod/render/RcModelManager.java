package com.gugu42.rcmod.render;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RcModelManager {

	// Future class where all models will go

	public IModelCustom modelBombGlove;
	public final ResourceLocation textureLocationBombGlove = new ResourceLocation(
			"rcmod:models/BombGlove.png");

	public IModelCustom modelPyrocitor;
	public final ResourceLocation textureLocationPyrocitor = new ResourceLocation(
			"rcmod:models/Pyrocitor.png");

	public IModelCustom modelRYNO;
	public final ResourceLocation textureLocationRYNO = new ResourceLocation(
			"rcmod:models/RYNO.png");

	public IModelCustom modelWalloper;
	public final ResourceLocation textureLocationWalloper = new ResourceLocation(
			"rcmod:models/Walloper.png");

	public IModelCustom modelClankBackpack;
	public final ResourceLocation textureLocationClankBackpack = new ResourceLocation(
			"rcmod:models/ClankBackpack.png");

	public IModelCustom modelClankHeliBody;
	public final ResourceLocation textureLocationClankHeliBody = new ResourceLocation(
			"rcmod:models/BodyHeli.png");
	
	public IModelCustom modelClankHeli;
	public IModelCustom modelClankHeli2;
	public final ResourceLocation textureLocationClankHeli = new ResourceLocation(
			"rcmod:models/Heli.png");

	public RcModelManager() {
		modelBombGlove = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/BombGlove.obj");
		modelPyrocitor = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/Pyrocitor.obj");
		modelRYNO = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/RYNO.obj");
		modelClankBackpack = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/ClankBackpack.obj");
		modelWalloper = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/Walloper.obj");
		modelClankHeliBody = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/BodyHeli.obj");
		modelClankHeli = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/Heli.obj");
		modelClankHeli2 = AdvancedModelLoader
				.loadModel("/assets/rcmod/models/Heli2.obj");
	}

}

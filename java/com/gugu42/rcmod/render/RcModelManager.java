package com.gugu42.rcmod.render;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

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
	
//	public TessellatorModel modelClankBackpack;

	public IModelCustom modelClankHeliBody;
	public final ResourceLocation textureLocationClankHeliBody = new ResourceLocation(
			"rcmod:models/BodyHeli.png");
	
//	public TessellatorModel modelClankHeliBody;
	
	public IModelCustom modelClankHeli;
	
//	public TessellatorModel modelClankHeli;
	
	public IModelCustom modelClankHeli2;
	public final ResourceLocation textureLocationClankHeli = new ResourceLocation(
			"rcmod:models/Heli.png");
	
//	public TessellatorModel modelClankHeli2;
	
//	public TessellatorModel modelBlaster;
//	public TessellatorModel modelBombGlove2;
//	public TessellatorModel modelPyrocitor2;
//	public TessellatorModel modelRYNO2;
//	public TessellatorModel modelWalloper2;
//	public TessellatorModel modelVisibombGun;
//	public TessellatorModel modelDevastator;
//	public TessellatorModel modelDecoyGlove;
//	public TessellatorModel modelDroneDevice;
//	public TessellatorModel modelGloveOfDoom;
//	public TessellatorModel modelMineGlove;
//	public TessellatorModel modelMorphORay;
//	public TessellatorModel modelSuckCannon;
//	public TessellatorModel modelTaunter;
//	public TessellatorModel modelTeslaClaw;
	
	

	public RcModelManager() {
		modelBombGlove = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/BombGlove.obj"));
		modelPyrocitor = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Pyrocitor.obj"));
		modelRYNO = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/RYNO.obj"));
		modelClankBackpack = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/ClankBackpack.obj"));
		modelWalloper = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Walloper.obj"));
		modelClankHeliBody = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/BodyHeli.obj"));
//		modelClankBackpack = new TessellatorModel("/assets/rcmod/models/ClankBackpack.obj");
//		
//		modelClankHeliBody = new TessellatorModel("/assets/rcmod/models/BodyHeli.obj");
//		modelClankHeli = new TessellatorModel("/assets/rcmod/models/Heli.obj");
//		modelClankHeli2 = new TessellatorModel("/assets/rcmod/models/Heli2.obj");
		
		modelClankHeli = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Heli.obj"));
		modelClankHeli2 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Heli2.obj"));
		
//		modelBlaster = new TessellatorModel("/assets/rcmod/models/Blaster0.obj");
//		modelBombGlove2 = new TessellatorModel("/assets/rcmod/models/BombGlove.obj");
//		modelPyrocitor2 = new TessellatorModel("/assets/rcmod/models/Pyrocitor.obj"); //Doesn't have a MTL file. Might cause problems.
//		modelRYNO2 = new TessellatorModel("/assets/rcmod/models/RYNO.obj");
//		modelWalloper2 = new TessellatorModel("/assets/rcmod/models/Walloper.obj");
//		modelVisibombGun = new TessellatorModel("/assets/rcmod/models/visibomb.obj");
//		modelDevastator = new TessellatorModel("/assets/rcmod/models/Devastator.obj");
//		modelDecoyGlove = new TessellatorModel("/assets/rcmod/models/DecoyGlove.obj");
//		modelDroneDevice = new TessellatorModel("/assets/rcmod/models/droneglove.obj");
//		modelGloveOfDoom = new TessellatorModel("/assets/rcmod/models/GloveofDoom.obj");
//		modelMineGlove = new TessellatorModel("/assets/rcmod/models/MineGlove.obj");
//		modelMorphORay = new TessellatorModel("/assets/rcmod/models/MorphORay.obj");
//		modelSuckCannon = new TessellatorModel("/assets/rcmod/models/SuckCannon.obj");
//		modelTaunter = new TessellatorModel("/assets/rcmod/models/Taunter.obj");
//		modelTeslaClaw = new TessellatorModel("/assets/rcmod/models/TeslaClaw.obj");
	}

}

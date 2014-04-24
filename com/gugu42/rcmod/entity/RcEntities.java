package com.gugu42.rcmod.entity;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityPyrocitorAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;

import cpw.mods.fml.common.registry.EntityRegistry;

public class RcEntities {

	public RcEntities() {

	}

	public static void initModEntities() {
		EntityRegistry.registerGlobalEntityID(EntityTNTCrate.class, "tntcrate",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityTNTCrate.class, "tntcrate", 50,
				RcMod.instance, 256, 1, false);
	}

	public static void initRc1Entities() {
		EntityRegistry.registerGlobalEntityID(EntityBlasterAmmo.class,
				"blasterammo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBlasterAmmo.class,
				"blasterammo", 52, RcMod.instance, 256, 1, false);

		EntityRegistry.registerGlobalEntityID(EntityBombGloveAmmo.class,
				"bombgloveammo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBombGloveAmmo.class,
				"bombgloveammo", 53, RcMod.instance, 256, 1, false);

		EntityRegistry.registerGlobalEntityID(EntityRYNOAmmo.class, "rynoammo",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityRYNOAmmo.class, "rynoammo", 54,
				RcMod.instance, 256, 1, false);

		EntityRegistry.registerGlobalEntityID(EntityPyrocitorAmmo.class,
				"pyrocitorammo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityPyrocitorAmmo.class,
				"pyrocitorammo", 55, RcMod.instance, 256, 1, false);

		EntityRegistry.registerGlobalEntityID(EntityVisibombAmmo.class,
				"visibombrocket", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityVisibombAmmo.class,
				"visibombrocket", 56, RcMod.instance, 256, 1, false);

		EntityRegistry.registerGlobalEntityID(EntityWrenchThrown.class,
				"wrenchThrown", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityWrenchThrown.class,
				"wrenchThrown", 57, RcMod.instance, 256, 1, false);
	}

}

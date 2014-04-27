package com.gugu42.rcmod.items;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRcRecord extends ItemRecord {

	private String recordSoundName;
	private String recordName;
	private String recordNameDesc;
	
	protected ItemRcRecord(String recordSoundName, String recordName, String recordNameDesc) {
		super(recordSoundName);
		this.recordSoundName = recordSoundName;
		this.recordName = recordName;
		this.recordNameDesc = recordNameDesc;
	}

	@Override
	public ResourceLocation getRecordResource(String name) {
		return new ResourceLocation(this.recordSoundName);
	}

	@SideOnly(Side.CLIENT)
	public String getRecordTitle() {
		return this.recordName;
	}

	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal() {
		return StatCollector.translateToLocal("item." + this.recordNameDesc + ".desc");
	}

}

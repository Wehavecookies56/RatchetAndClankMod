package com.gugu42.rcmod.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RcSoundHandler {
	@SubscribeEvent
	public void onSound(SoundLoadEvent event) {
		// You add them the same way as you add blocks.
//		event.manager.addSound("rcmod:MenuOpen.wav");
//		event.manager.addSound("rcmod:MenuVendorQuit.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech0.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech_wait1.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech2.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech_wait2.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech3.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech5.wav");
//		event.manager.addSound("rcmod:MenuVendorBuy.wav");
//		event.manager.addSound("rcmod:MenuVendorAmmoMaxedOut.wav");
//		event.manager.addSound("rcmod:MenuVendorExit.wav");
//		event.manager.addSound("rcmod:ExplosiveCrateCountdown.wav");
//		event.manager.addSound("rcmod:ExplosiveCrateExplosion.wav");
//		event.manager.addSound("rcmod:CrateWoodBreak.wav");
//		event.manager.addSound("rcmod:BoltCollect1.wav");
//		event.manager.addSound("rcmod:BoltCollect2.wav");
//		event.manager.addSound("rcmod:BoltCollect3.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech_weapClicked1.wav");
//		event.manager.addSound("rcmod:VendorSalesman/vendor_speech_weapClicked2.wav");
//		event.manager.addSound("rcmod:MenuSelect.wav");
//		event.manager.addSound("rcmod:AmmoEmpty.wav");
//		event.manager.addSound("rcmod:TaunterSound1.wav");
//		event.manager.addSound("rcmod:TaunterSound2.wav");
//		event.manager.addSound("rcmod:TaunterSound3.wav");
//		event.manager.addSound("rcmod:TaunterSound4.wav");
//		event.manager.addSound("rcmod:TaunterSound5.wav");
//		event.manager.addSound("rcmod:BombGloveExplosion1.wav");
	}
	
	
	public static void playSoundAtPlayer(EntityPlayer player, String sound, float volume, float pitch){
		World world = player.worldObj;
		world.playSoundAtEntity(player, sound, volume, pitch);
	}
	
}
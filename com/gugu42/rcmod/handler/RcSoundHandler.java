package com.gugu42.rcmod.handler;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class RcSoundHandler {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		// You add them the same way as you add blocks.
		event.manager.addSound("rcmod:MenuOpen.wav");
		event.manager.addSound("rcmod:MenuVendorQuit.wav");
		event.manager.addSound("rcmod:VendorSalesman/vendor_speech0.wav");
		event.manager.addSound("rcmod:VendorSalesman/vendor_speech1.wav");
		event.manager.addSound("rcmod:VendorSalesman/vendor_speech2.wav");
		event.manager.addSound("rcmod:VendorSalesman/vendor_speech4.wav");
		event.manager.addSound("rcmod:VendorSalesman/vendor_speech3.wav");
		event.manager.addSound("rcmod:VendorSalesman/vendor_speech5.wav");
	}
}
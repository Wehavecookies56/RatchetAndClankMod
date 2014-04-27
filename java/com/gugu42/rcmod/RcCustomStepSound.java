package com.gugu42.rcmod;

import net.minecraft.block.Block.SoundType;

public class RcCustomStepSound extends SoundType {

	private SoundType placeSound;
	private SoundType stepSound;

	public RcCustomStepSound(String par1Str, float par2, float par3,
			SoundType placeSound, SoundType stepSound) {
		super(par1Str, par2, par3);
		this.placeSound = placeSound;
		this.stepSound = stepSound;
	}

//	public float getVolume() {
//		return this.getVolume();
//	}

//	public float getPitch() {
//		return this.getPitch();
//	}

	/**
	 * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
	 */
	@Override
	public String getBreakSound() {
		return "rcmod:" + this.soundName;
	}

	/**
	 * Used when a entity walks over, or otherwise interacts with the block.
	 */
	@Override
	public String getStepResourcePath() {
		return stepSound.getStepResourcePath();
	}

	/**
	 * Used when a player places a block.
	 */
	public String getPlaceSound() {
		return placeSound.getBreakSound();
	}

}

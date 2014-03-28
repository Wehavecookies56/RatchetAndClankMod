package com.gugu42.rcmod;

import net.minecraft.block.StepSound;

public class RcCustomStepSound extends StepSound {

	private StepSound placeSound;
	private StepSound stepSound;

	public RcCustomStepSound(String par1Str, float par2, float par3,
			StepSound placeSound, StepSound stepSound) {
		super(par1Str, par2, par3);
		this.placeSound = placeSound;
		this.stepSound = stepSound;
	}

	public float getVolume() {
		return this.stepSoundVolume;
	}

	public float getPitch() {
		return this.stepSoundPitch;
	}

	/**
	 * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
	 */
	@Override
	public String getBreakSound() {
		return "rcmod:" + this.stepSoundName;
	}

	/**
	 * Used when a entity walks over, or otherwise interacts with the block.
	 */
	@Override
	public String getStepSound() {
		return stepSound.getStepSound();
	}

	/**
	 * Used when a player places a block.
	 */
	public String getPlaceSound() {
		return placeSound.getBreakSound();
	}

}

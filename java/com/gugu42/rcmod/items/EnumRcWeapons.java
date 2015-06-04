package com.gugu42.rcmod.items;

import com.gugu42.rcmod.ClientProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

import net.minecraft.item.Item;

public enum EnumRcWeapons {
	
	
	
	//PAGE 1
	BLASTER(2500, RcItems.blaster, "Blaster", 1, 1 , new String[] {"The Blaster is a great", "all-purpose weapon", "Even though its range is limited,", "its high rate of fire makes it ideal", "for taking on tough enemies at ", "close and medium ranges"}, 1),
	BOMBGLOVE(1000, RcItems.bombGlove, "BombGlove", 1, 2, new String[] {"Bomb Glove! ", "Throw bombs at your enemies!", "BOOM!", "", "", ""}, 2),
	PYROCITOR(2500, RcItems.pyrocitor, "Pyrocitor", 1, 3, new String[] {"Pyrocitor!", "This fearsome weapon", "really shines at close range.", "", "Great for taking out", "those pesky small enemies."}, 3),
	RYNO(150000, RcItems.ryno, "R.Y.N.O.", 1, 4, new String[] {"Rip ya ...", "A NEW ONE !", "The R.Y.NO. is the most powerful", "rocket launcher in the galaxy", "", "(It's in WIP)" }, 4),
	
	//PAGE 2
	WALLOPER(7500, RcItems.walloper, "Walloper", 2, 1, new String[] {"Walloper !", "Punch people hard!", "", "", "", "" }, 5),
	VISIBOMB(15000, RcItems.visibombGun, "Visibomb Gun", 2, 2, new String[] {"Visibomb!", "Steer your missiles", "with deadly accuracy", "using the on-board video relay!", "", "(It's in WIP)"}, 6),
	TAUNTER(2500, RcItems.taunter, "Taunter", 2, 3, new String[] {"Taunter!", "Annoy your enemies", "and lure them", "into traps and ambuses with ", "this obnoxious noise-maker!", "" }, 7),
	MINEGLOVE(7500, RcItems.mineGlove, "Mine Glove", 2, 4, new String[] {"Mine Glove!", "Be sneaky and set", "EXPLODING traps", "for your enemies!", "", ""}, 8),
	
	//PAGE 3
	DEVASTATOR(10000, RcItems.devastator, "Devastator", 3, 1, new String[] {"The Devastator is the perfect", "combination of effectiveness", "and economy. Less expensive than some", "of Gadgetron's other long range weapons,", " the Devastator can bring down ", "flying enemies with ease."}, 9),
	SUCK_CANNON(5000, RcItems.suckCannon, "Suck Cannon", 3, 2, new String[] {"", "", "", "", "", ""}, 10);
	
	
	public int price;
	public Item weapon;
	public String name;
	public int page;
	public int slot;
	public String[] desc;
	public int id;

	private EnumRcWeapons(int price, Item item, String name, int page, int slot, String[] desc, int id) {
		this.price = price;
		this.weapon = item;
		this.name = name;
		this.page = page;
		this.slot = slot;
		this.desc = desc;
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public Item getWeapon() {
		return weapon;
	}

	public String getName() {
		return name;
	}

	public int getPage() {
		return page;
	}

	public int getSlot() {
		return slot;
	}
	
	public String[] getDesc() {
		return desc;
	}
	
	public int getId(){
		return id;
	}

	public static EnumRcWeapons getItemForPageAndSlot(int page, int slot) {
		EnumRcWeapons[] lol = EnumRcWeapons.values();
		for (int i = 0; i < lol.length; i++) {
			if (lol[i].getPage() == page && lol[i].getSlot() == slot) {
				return lol[i];
			}
		}
		return null;
	}
	
	public static EnumRcWeapons getItemFromID(int id){
		EnumRcWeapons[] weaps = EnumRcWeapons.values();
		for(int i = 0; i < weaps.length; i++ ){
			if(weaps[i].getId() == id){
				return weaps[i];
			}
		}
		
		return null;
	}
	
	public static int getIDFromItem(Item item){
		EnumRcWeapons[] weaps = EnumRcWeapons.values();
		for(int i = 0; i < weaps.length; i++ ){
			if(weaps[i].getWeapon() == item){
				return weaps[i].getId();
			}
		}
		
		return 0;
	}
	
	public static int getPriceFromItem(Item item){
		EnumRcWeapons[] weaps = EnumRcWeapons.values();
		for(int i = 0; i < weaps.length; i++ ){
			if(weaps[i].getWeapon() == item){
				return weaps[i].getPrice();
			}
		}
		
		return 0;
	}
	
}

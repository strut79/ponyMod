package com.strutnet.pony.entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPony extends EntityHorse {
	

	private static final String[] PONY_TEXTURES = new String[] {
		"/textures/entity/horse/pinkie_pie.png", "/textures/entity/horse/pink_tail_blue_feet.png"
		,"/textures/entity/horse/apple_jack.png","/textures/entity/horse/flutter_shy.png"
		,"/textures/entity/horse/luna.png","/textures/entity/horse/rainbow_dash.png"
		,"/textures/entity/horse/twilight_sparkle.png","/textures/entity/horse/princess_celistia.png"
		,"/textures/entity/horse/rarity.png"};
	private String ponyTexture;
	
	public EntityPony(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
		int i = this.rand.nextInt(PONY_TEXTURES.length);

		if(i >=0 && i <= PONY_TEXTURES.length-1){
			ponyTexture=PONY_TEXTURES[i];
		}
//		if(!ponyTexture.contains("princess_celistia")){
//			this.setScale(this.getHorseSize());
//			this.setGrowingAge(-100);
//		}
		//setType(HorseType.DONKEY);
	}

	public float getHorseSize()
    {
        return 0.5F;
    }
	
	public String getPonyTexture() {
		return ponyTexture;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	    {
	        super.writeEntityToNBT(tagCompound);
	        tagCompound.setString("ponyTexture", this.ponyTexture);
	    }

	    @Override
	    public void readEntityFromNBT(NBTTagCompound tagCompund)
	    {
	        super.readEntityFromNBT(tagCompund);
	        this.ponyTexture = tagCompund.getString("ponyTexture");
	    }


}
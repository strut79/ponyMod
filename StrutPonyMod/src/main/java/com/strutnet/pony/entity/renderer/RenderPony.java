package com.strutnet.pony.entity.renderer;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.google.common.collect.Maps;
import com.strutnet.pony.Reference;
import com.strutnet.pony.entity.EntityPony;
import com.strutnet.pony.entity.EntityTurtle;

public class RenderPony extends RenderLiving
{
	
	private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.<String, ResourceLocation>newHashMap();

	
//	private static final ResourceLocation pinkiePieTextures = new ResourceLocation(Reference.MODID,"/textures/entity/horse/pinkie_pie.png");
//	private static final ResourceLocation pinkHorseOTextures = new ResourceLocation(Reference.MODID,"/textures/entity/horse/pink_tail_blue_feet.png");
	
	public RenderPony(RenderManager rendermanager, ModelBase model, float shadowsize)
	{
		super(rendermanager, model, shadowsize);
		
	}
	
	protected ResourceLocation getEntityTexture(EntityPony entity)
	{
//		if(entity.getHorseType() == HorseType.PINKIE_PIE) {
//			return pinkiePieTextures;
//		}else  if (entity.getHorseType() == HorseType.PINK_HORSE_O){
//			return pinkHorseOTextures;
//		}else{
//			return pinkiePieTextures;
//		}
		String s = entity.getPonyTexture();

        if (entity.getPonyTexture() == null)
        {
            return null;
        }
        else
        {
            ResourceLocation resourcelocation = (ResourceLocation)LAYERED_LOCATION_CACHE.get(s);

            if (resourcelocation == null)
            {
                resourcelocation = new ResourceLocation(Reference.MODID,s);
                //Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(entity.getVariantTexturePaths()));
                LAYERED_LOCATION_CACHE.put(s, resourcelocation);
            }

            return resourcelocation;
        }
	}
	
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityPony)entity);
	}
	
}

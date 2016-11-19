package com.strutnet.pony.entity.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.strutnet.pony.Reference;
import com.strutnet.pony.entity.EntityTurtle;

public class RenderTurtle extends RenderLiving
{
	private static final ResourceLocation turtleTextures = new ResourceLocation(Reference.MODID,"textures/entity/turtle/turtle.png");
	
	public RenderTurtle(RenderManager rendermanager, ModelBase model, float shadowsize)
	{
		super(rendermanager, model, shadowsize);
	}
	
	protected ResourceLocation getEntityTexture(EntityTurtle entity)
	{
		//System.out.println(">>>>>>>>>>>>> RenderTurtle getEntityTexture");
		return turtleTextures;
	}
	
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityTurtle)entity);
	}
	
}

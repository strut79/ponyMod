package com.strutnet.pony.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTurtle extends ModelBase 
{
	public ModelRenderer head = new ModelRenderer(this, 0, 0);
	public ModelRenderer body;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	
	public ModelTurtle()
	{
		textureWidth = 64;
		textureHeight = 64;
		
		this.head = new ModelRenderer(this, 1, 1);
		this.head.addBox(-2F, -2F, -5F, 4, 4, 5);
		this.head.setRotationPoint(0F, 19F, -8F);
		
		this.body = new ModelRenderer(this, 1, 20);
		this.body.addBox(-6F, -10F, -3F, 12, 18, 2);
		this.body.setRotationPoint(0F, 17F, 2F);		
		this.body.setTextureOffset(20, 1).addBox(-5F, -9F, -1F, 10, 16, 1);
		this.body.setTextureOffset(43, 1).addBox(-4F, -8F, 0F, 8, 14, 1);
		this.body.setTextureOffset(1, 42).addBox(-3F, -7F, 1F, 6, 12, 1);
		
		this.leg1 = new ModelRenderer(this, 1, 11);
		this.leg1.addBox(-3F, 0F, -2F, 3, 4, 3);
		this.leg1.setRotationPoint(-3F, 20F, 9F);
				
		this.leg2 = new ModelRenderer(this, 1, 11);
		this.leg2.addBox(-1F, 0F, -2F, 3, 4, 3);
		this.leg2.setRotationPoint(4F, 20F, 9F);
		
		this.leg3 = new ModelRenderer(this, 1, 11);
		this.leg3.addBox(-3F, 0F, -3F, 3, 4, 3);
		this.leg3.setRotationPoint(-3F, 20F, -5F);
		
		this.leg4 = new ModelRenderer(this, 1, 11);
		this.leg4.addBox(-1F, 0F, -3F, 3, 4, 3);
		this.leg4.setRotationPoint(4F, 20F, -5F);
	}
	
	
	 public void render(Entity entity, float time, float limbSwingDistance, float p_78088_4_, float headYRot, float headXRot, float Ytrans) 
	 {
		 this.setRotationAngles(time, limbSwingDistance, p_78088_4_, headYRot, headXRot, Ytrans, entity);
		 
		 if(this.isChild)
		 {
			 float div = 2.0F;
			 GlStateManager.pushMatrix();
			 GlStateManager.scale(1.0F / div,  1.0F / div, 1.0F / div);
			 GlStateManager.translate(0.0F, 24.0F * Ytrans, 0.0F);			 
			 this.head.render(Ytrans);
			 this.body.render(Ytrans);
			 this.leg1.render(Ytrans);
			 this.leg2.render(Ytrans);
			 this.leg3.render(Ytrans);
			 this.leg4.render(Ytrans);
			 GlStateManager.popMatrix();			 
		 }
		 else
		 {
			 this.head.render(Ytrans);
			 this.body.render(Ytrans);
			 this.leg1.render(Ytrans);
			 this.leg2.render(Ytrans);
			 this.leg3.render(Ytrans);
			 this.leg4.render(Ytrans);
		 }		 
	 }
	 
	 public void setRotationAngles(float time, float limbSwingDistance, float p_78087_3_, float headYRot, float headXRot, float p_78087_6_, Entity entity) 
	 {
		 this.head.rotateAngleX = headXRot / (180F / (float)Math.PI);
		 this.head.rotateAngleY = headYRot / (180F / (float)Math.PI);
		 
		 this.body.rotateAngleX = ((float)Math.PI / 2F);
		 
		 this.leg1.rotateAngleX = MathHelper.cos(time * 0.6662F) * 1.4F * limbSwingDistance;
		 this.leg2.rotateAngleX = MathHelper.cos(time * 0.6662F + (float)Math.PI) * 1.4F * limbSwingDistance;
		 this.leg3.rotateAngleX = MathHelper.cos(time * 0.6662F + (float)Math.PI) * 1.4F * limbSwingDistance;
		 this.leg4.rotateAngleX = MathHelper.cos(time * 0.6662F) * 1.4F * limbSwingDistance;
		 
	 }
	
	
}

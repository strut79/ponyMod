package com.strutnet.pony.entity.ai;

import java.util.Random;

import net.minecraft.entity.ai.EntityAIBase;

import com.strutnet.pony.entity.EntityTurtle;

public class TurtleAIWander extends EntityAIBase 
{
	private EntityTurtle theEntity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private boolean proceed;
	
	public TurtleAIWander(EntityTurtle entity)
	{
		this.theEntity = entity;
		this.setMutexBits(1);
	}
	
	public boolean shouldExecute()
	{
		if(!this.theEntity.onGround || this.theEntity.isInWater())
		{
			return false;
		}
		
		if(!this.proceed)
		{
			if(this.theEntity.getRNG().nextInt(15) != 0)
			{
				return false;
			}
		}
		
		Random random = this.theEntity.getRNG();
		
		double randx = this.theEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
		double randy = this.theEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 5.0F);
		double randz = this.theEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
		
		this.xPosition = randx;
		this.yPosition = randy;
		this.zPosition = randz;
		this.proceed = false;
		return true;		
	}
	
	public boolean continueExecuting()
	{
		return false;
	}
	
	public void startExecuting()
	{
		this.theEntity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1.0D);		
	}
	
	public void func_179480_f()
	{
		this.proceed = true;
	}
	
}






















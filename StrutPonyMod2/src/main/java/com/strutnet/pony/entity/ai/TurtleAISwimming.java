package com.strutnet.pony.entity.ai;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;

import com.strutnet.pony.entity.EntityTurtle;
import com.strutnet.pony.entity.EntityTurtle.TurtleMoveHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TurtleAISwimming extends EntityAIBase 
{
	private EntityTurtle theEntity;
	
	public TurtleAISwimming(EntityTurtle entity)
	{
		this.theEntity = entity;
		this.setMutexBits(1);
	}
	
	public boolean shouldExecute()
	{
		if(!this.theEntity.isInWater())
		{
			return false;
		}
		
		EntityTurtle.TurtleMoveHelper mh = (TurtleMoveHelper)this.theEntity.getMoveHelper();
		
		if(!mh.isUpdating())
		{
			if(this.theEntity.getRNG().nextInt(15) != 0)
			{
				return false;
			}
			return true;
		}
		else
		{
			double dirX = mh.getPosX() - this.theEntity.posX;
			double dirY = mh.getPosY() - this.theEntity.posY;
			double dirZ = mh.getPosZ() - this.theEntity.posZ;
			
			double destDistance = dirX * dirX + dirY * dirY + dirZ * dirZ;
			destDistance = (double)MathHelper.sqrt_double(destDistance);
			
			if(destDistance < 0.5D)
			{
				return true;
			}
			else
			{
				return false;
			}			
		}		
	}
	
	public boolean continueExecuting()
	{
		return false;
	}
	
	public void startExecuting()
	{
		Random random = this.theEntity.getRNG();
		
		double randx = this.theEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
		double randy = this.theEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 2.0F);
		double randz = this.theEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
		
		BlockPos DestBlockPos = new BlockPos(MathHelper.floor_double(randx), MathHelper.floor_double(randy), MathHelper.floor_double(randz));
		Block destBlock = this.theEntity.worldObj.getBlockState(DestBlockPos).getBlock();
		Block underDestBlock = this.theEntity.worldObj.getBlockState(DestBlockPos.offset(EnumFacing.DOWN)).getBlock();
		
		if(destBlock == Blocks.WATER || (destBlock == Blocks.AIR && underDestBlock != Blocks.WATER && underDestBlock != Blocks.AIR))
		{
			this.theEntity.getMoveHelper().setMoveTo(randx, randy, randz, 1.0D);
		}		
	}	
	
}













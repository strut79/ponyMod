package com.strutnet.pony.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.strutnet.pony.entity.ai.TurtleAISwimming;
import com.strutnet.pony.entity.ai.TurtleAIWander;
import com.strutnet.pony.items.PonyItemFactory;
 
@SideOnly(Side.CLIENT)
public class EntityTurtle extends EntityAnimal
{
    public EntityTurtle(World worldIn)
    {
        super(worldIn);
        this.setSize(1.0F, 1.0F);        
        this.tasks.addTask(0, new TurtleAISwimming(this));
        this.tasks.addTask(1, new TurtleAIWander(this));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.FISH, false));        
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));        
        
//        ((PathNavigateGround)this.getNavigator()).func_179693_d(true);
        ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
        this.moveHelper = new EntityTurtle.TurtleMoveHelper();
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>Turtle spawned");
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
    }
    
    public boolean isBreedingItem(ItemStack itemstack)
    {
    	return itemstack == null ? false :itemstack.getItem() == Items.FISH;
    }

    public boolean canBreatheUnderwater()
    {
    	return true;
    }
    
    public boolean isPushedByWater()
    {
    	return false;
    }    
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	
    	if(this.isInWater())
    	{
    		this.setAir(300);
    		this.limbSwingAmount = 0;
    		this.limbSwing = 0;
    		this.prevLimbSwingAmount = this.limbSwingAmount;
    	}
    }
    
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.rabbit.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_RABBIT_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }

    
    protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
    {
    	if(!this.isInWater())
    	{
    		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    	}
    }
    

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected Item getDropItem()
    {
       return PonyItemFactory.turtle_shell;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean recentlyhit, int modfier)
    {
        this.dropItem(PonyItemFactory.turtle_shell, 1);
    }


    public EntityTurtle createChild(EntityAgeable ageable)
    {
        return new EntityTurtle(this.worldObj);
    }

    public float getEyeHeight()
    {
        return this.height;
    }
    
    public boolean getCanSpawnHere()
    {
    	return super.getCanSpawnHere();
    }
    
    
    public void moveEntityWithHeading(float x, float z)
    {
    	if(this.isInWater())
    	{
//    		this.moveFlying(x, z, 0.1F);
    		this.moveEntity(MoverType.SELF,this.motionX, this.motionY, this.motionZ);
    		this.motionX *= 0.8D;
    		this.motionY *= 0.8D;
    		this.motionZ *= 0.8D;
    	}
    	else
    	{
    		super.moveEntityWithHeading(x, z);
    	}
    }
    
    public class TurtleMoveHelper extends EntityMoveHelper
    {
    	private EntityTurtle entity = EntityTurtle.this;
    	private int randcounter;
    	
    	public TurtleMoveHelper()
    	{
    		super(EntityTurtle.this);
    	}
    	
    	public void onUpdateMoveHelper()
    	{
    		if(entity.isInWater())
    		{
    			if(this.isUpdating())
    			{
    				if(this.randcounter-- <= 0)
    				{
    					this.randcounter += this.entity.getRNG().nextInt(5) + 10;
    					
    					double dirX = this.posX - this.entity.posX;
    					double dirY = this.posY - this.entity.posY;
    					double dirZ = this.posZ - this.entity.posZ;
    					
    					double destDistance = dirX * dirX + dirY * dirY + dirZ * dirZ;
    					
    					destDistance = (double)MathHelper.sqrt_double(destDistance);
    					
    					if(!this.checkCollision(this.posX, this.posY, this.posZ, destDistance))
    					{
    						this.entity.motionX += dirX / destDistance * 0.1D;
    						this.entity.motionY += dirY / destDistance * 0.1D;
    						this.entity.motionZ += dirZ / destDistance * 0.1D;
    					}
    					else
    					{
    						//this.update =  false;
    						double aboveBlockLocY = MathHelper.floor_double(this.posY) + MathHelper.floor_double(1);
    						Block blockAboveSelf = worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.entity.posX),MathHelper.floor_double(aboveBlockLocY), MathHelper.floor_double(this.entity.posZ))).getBlock();
    						Block destBlock = this.entity.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY),MathHelper.floor_double(this.posZ))).getBlock();
    						
    						double Ydiff = MathHelper.floor_double(this.posY) - MathHelper.floor_double(this.entity.posY);
    						
    						if (Ydiff == 1.0 && blockAboveSelf == Blocks.AIR && destBlock != Blocks.WATER && destDistance <= 3)
    						{
    							this.entity.motionX = dirX / destDistance * 0.4D;
    							this.entity.motionY = 0.5D;
    							this.entity.motionZ = dirZ / destDistance * 0.4D;
    							this.entity.getJumpHelper().setJumping();
    						}
    					}
    					this.UpdateYaw(motionX, motionZ);
    				}
    			}
    		}
    		else
    		{
    			super.onUpdateMoveHelper();
    		}
    	}
    	
    	public void UpdateYaw(double dirX, double dirZ)
    	{
    		renderYawOffset = rotationYaw = -((float)Math.atan2(dirX, dirZ)) * 180.0F / (float)Math.PI;    		
    	}
    	
    	private boolean checkCollision(double posX, double posY, double posZ, double distance)
    	{
    		double dirX = (posX - this.entity.posX) / distance;
    		double dirY = (posY - this.entity.posY) / distance;
    		double dirZ = (posZ - this.entity.posZ) / distance;
    		AxisAlignedBB collisionBox = this.entity.getEntityBoundingBox();
    		
    		for (int i = 1; (double)i < distance; ++i)
    		{
    			collisionBox = collisionBox.offset(dirX,  dirY, dirZ);
    			
    			if(!this.entity.worldObj.getCollisionBoxes(this.entity, collisionBox).isEmpty())
    			{
    				return true;
    			}
    		}
    		return false;
    	}
    	
    	public double getPosX()
    	{
    		return this.posX;
    	}
    	public double getPosY()
    	{
    		return this.posY;
    	}
    	public double getPosZ()
    	{
    		return this.posZ;
    	}

    	
    }
    
}

package com.strutnet.pony;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.strutnet.pony.entity.PonyEntityFactory;
import com.strutnet.pony.items.PonyItemFactory;
import com.strutnet.pony.proxy.CommonProxy;

@Mod(modid = Reference.MODID,name=Reference.MOD_NAME, version = Reference.VERSION)
public class StrutPonyMod
{
	
	@Instance(Reference.MODID)
	public static StrutPonyMod INSTANCE = new StrutPonyMod();
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	@EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	PonyItemFactory.init();
    	PonyItemFactory.register();
    	PonyEntityFactory.init();
    	PonyEntityFactory.register();
    	proxy.preInit(event);
    	
    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
        proxy.registerRender();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	
    }
}

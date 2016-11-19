package com.strutnet.pony.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.strutnet.pony.entity.EntityTurtle;
import com.strutnet.pony.entity.PonyEntityFactory;
import com.strutnet.pony.entity.renderer.RenderTurtle;
import com.strutnet.pony.items.PonyItemFactory;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRender(){
//		TutorialBlocks.registerRenders();
		PonyItemFactory.registerRenders();
		
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		
		PonyEntityFactory.registerRenders();
	}

}

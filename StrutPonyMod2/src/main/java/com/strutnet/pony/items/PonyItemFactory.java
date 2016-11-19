package com.strutnet.pony.items;

import com.strutnet.pony.StrutPonyMod;
import com.strutnet.pony.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PonyItemFactory {
	public static Item purple_apple;
	public static String item_name_purple_apple = "purple_apple";
	
	public static Item turtle_shell;
	public static String item_name_turtle_shell = "turtleshell";
	
	public  static void init(){
		purple_apple = new Item().setRegistryName(item_name_purple_apple).setUnlocalizedName(item_name_purple_apple);
		turtle_shell = new Item().setRegistryName(item_name_turtle_shell).setUnlocalizedName(item_name_turtle_shell);
	}
	
	public  static void register(){
		GameRegistry.register(purple_apple);
		GameRegistry.register(turtle_shell);
	}
	public static void registerRenders(){
		
		registerRender(purple_apple);
		registerRender(turtle_shell);
	}
	
	public static void registerRender(Item item){
		System.out.println(">>>>>>>>>>>>>> registryName "+item.getRegistryName());
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(),  "inventory"));
	}
}

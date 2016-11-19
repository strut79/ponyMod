package com.strutnet.pony.blocks;

import com.strutnet.pony.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PonyBlockFactory {
	public static Block test_block;
	public static String block_name = "test_block";
	
	public  static void init(){
		test_block = new TestBlock(Material.CLOTH).setUnlocalizedName(block_name).setRegistryName(block_name);
	}
	
	public  static void register(){
		GameRegistry.register(test_block);
	}
	
	public static void registerRenders(){
		registerRender(test_block);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(block.getRegistryName(),  "inventory"));
	}
}

class TestBlock extends Block {
	public TestBlock(Material materialIn) {
		super(materialIn);
	}

}
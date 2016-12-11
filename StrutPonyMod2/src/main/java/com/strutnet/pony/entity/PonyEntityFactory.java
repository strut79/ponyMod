package com.strutnet.pony.entity;

import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.strutnet.pony.Reference;
import com.strutnet.pony.StrutPonyMod;
import com.strutnet.pony.entity.renderer.RenderPony;
import com.strutnet.pony.entity.renderer.RenderTurtle;
import com.strutnet.pony.model.ModelTurtle;

public class PonyEntityFactory {
	private static int turtleEntityID = 255;
	private static int ponyID = 254;
	
	public static void init() {

	}

	public static void register() {

		System.out.println(">>>>>>>>>>>>>>>> entity register");
		EntityRegistry
				.registerModEntity(new ResourceLocation(Reference.MODID, "Turtle"),EntityTurtle.class, "Turtle", turtleEntityID, StrutPonyMod.INSTANCE, 64, 1, true);
		EntitySpawnPlacementRegistry.setPlacementType(EntityTurtle.class, EntityLiving.SpawnPlacementType.ON_GROUND);
		EntityRegistry.addSpawn(EntityTurtle.class, 8, 1, 4, EnumCreatureType.CREATURE, Biomes.BEACH, Biomes.RIVER,
				Biomes.SWAMPLAND, Biomes.FOREST);
		
		EntityRegistry
			.registerModEntity(new ResourceLocation(Reference.MODID, "Pony"),EntityPony.class, "Pony", ponyID, StrutPonyMod.INSTANCE, 64, 1, true);
		EntitySpawnPlacementRegistry.setPlacementType(EntityPony.class, EntityLiving.SpawnPlacementType.ON_GROUND);
		EntityRegistry.addSpawn(EntityPony.class, 8, 1, 4, EnumCreatureType.CREATURE, Biomes.DEFAULT);
		
		

	}

	public static void registerRenders() {
		//System.out.println(">>>>>>>>>>>>>>>> entity registerRenders");
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTurtle.class, new IRenderFactory<EntityTurtle>() {
			@Override
			public Render<? super EntityTurtle> createRenderFor(RenderManager manager) {
				//System.out.println(">>>>>>>>>>>>>>>> IRenderFactory createRenderFor");
				return new RenderTurtle(manager, new ModelTurtle(), .7f);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPony.class, new IRenderFactory<EntityPony>() {
			@Override
			public Render<? super EntityPony> createRenderFor(RenderManager manager) {
				//System.out.println(">>>>>>>>>>>>>>>> IRenderFactory createRenderFor");
				return new RenderPony(manager, new ModelHorse(), .7f);
			}
		});
	}

}

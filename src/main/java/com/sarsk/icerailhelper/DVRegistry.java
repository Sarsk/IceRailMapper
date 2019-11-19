package com.sarsk.icerailhelper;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = IceRailHelper.MODID, bus = Bus.MOD)
public class DVRegistry
{

	@SubscribeEvent
	public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event)
	{
		//DVSounds.soundRegistry = event.getRegistry();
		//DVSounds.initialization();
	}

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
	{
		//BlocksDV.setBlockRegistry(event.getRegistry());
		//BlocksDV.initialization();
	}

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event)
	{
		/*ItemsDV.setItemRegistry(event.getRegistry());
		ItemsDV.initialization();
		
		for (int i3 = 0; i3 < BlocksDV.dvBlockList.size(); ++i3)
		{
			String sequence = IceRailHelper.MODID + ":";
			register(event.getRegistry(), BlocksDV.dvBlockList.get(i3).getRegistryName().toString().replace(sequence, ""), new BlockItem(BlocksDV.dvBlockList.get(i3), (new Item.Properties().group(DVCreativeTabs.blocks))));
		}*/
	}
	
	@SubscribeEvent
	public static void onRegisterEntityTypes(Register<EntityType<?>> event)
	{
		//DVEntityTypes.init(event);
	}
	
	@SubscribeEvent
	public static void registerTileEntityTypes(Register<TileEntityType<?>> event)
	{
		//DVTileEntityTypes.init(event);
	}

	@SubscribeEvent
	public static void onRegisterBiomes(Register<Biome> event)
	{
	}

	@SubscribeEvent
	public static void onRegisterModDimensions(RegistryEvent.Register<ModDimension> event)
	{
	}

	public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(IceRailHelper.locate(name));
		registry.register(object);
	}
}
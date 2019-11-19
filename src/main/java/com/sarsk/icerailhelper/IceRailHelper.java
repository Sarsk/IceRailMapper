package com.sarsk.icerailhelper;

import com.sarsk.icerailhelper.client.IceRailHelperClient;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(IceRailHelper.MODID)
public class IceRailHelper
{
	//TODO: Thanks EMX for starter tutorial - https://emxtutorials.wordpress.com/
	
	public static final String NAME = "Ice Rail Helper";

	public static final String MODID = "icerailhelper";

	public IceRailHelper()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(IceRailHelperClient::initialization);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new DVEvents());
		//MinecraftForge.EVENT_BUS.register(new IceRailHelperClient());
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () ->
		{
			FMLJavaModLoadingContext.get().getModEventBus().addListener(IceRailHelperClient::initialization);
			MinecraftForge.EVENT_BUS.register(new IceRailHelperClient());
		});
	}
	
	private void initialization(final FMLCommonSetupEvent event)
    {
    }

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}
	
	public static String find(String name)
	{
		return MODID + ":" + name;
	}
}

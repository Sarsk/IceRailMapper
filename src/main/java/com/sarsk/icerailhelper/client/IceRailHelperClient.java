package com.sarsk.icerailhelper.client;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class IceRailHelperClient
{	public static void initialization(FMLClientSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new AlignPlayerWithIceRail());
	}
}
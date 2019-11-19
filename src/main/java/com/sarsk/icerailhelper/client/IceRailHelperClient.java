package com.sarsk.icerailhelper.client;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class IceRailHelperClient
{	public static void initialization(FMLClientSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new AlignPlayerWithIceRail());
	}

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event)
	{
		//TODO: Why are we returning if not exp? Copied from an example?
		if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;


	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(InputEvent.MouseInputEvent event)
	{
		/*
		GameSettings gs = Minecraft.getInstance().gameSettings;
		if (gs.keyBindAttack.isPressed()) // add your additional conditions here
		{
			KeyBinding.setKeyBindState(gs.keyBindAttack.getKeyCode(), false);
		}*/
	}


	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
	{
		/*
		// This event has an Entity variable, access it like this:
		event.entity;

		// do something to player every update tick:
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ItemStack heldItem = player.getHeldItem();
			if (heldItem != null && heldItem.itemID == Item.arrow.itemID) {
				player.capabilities.allowFlying = true;
			}
			else {
				player.capabilities.allowFlying = player.capabilities.isCreativeMode ? true : false;
			}
		}*/
	}

}
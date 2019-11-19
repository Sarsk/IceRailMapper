package com.sarsk.icerailhelper.client;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.event.MouseEvent;

//todo: Client side only

@OnlyIn(Dist.CLIENT)
public class AlignPlayerWithIceRail {

    final float TICK_TURN_INCREMENT = 0.5f;

    boolean isIceBlock(final BlockPos pos) {
        BlockState blockState = Minecraft.getInstance().world.getBlockState(pos);
        return (blockState.getBlock() == Blocks.ICE || blockState.getBlock() == Blocks.BLUE_ICE || blockState.getBlock() == Blocks.PACKED_ICE);
    }


    boolean isIceRoad(final BlockPos pos) {
        return false;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {
        // Get the entity
        LivingEntity entityLiving = event.getEntityLiving();
        if (event.getEntity() instanceof PlayerEntity) {
            //System.out.println("Receiving client player events! Entity " + entityLiving);
            PlayerEntity player = (PlayerEntity)event.getEntity();

        //if (Minecraft.getInstance().player.isRowingBoat()) {
            //System.out.println("Player is in a boat?" + player.isUser() +", " + player.isPassenger());
            if (player.isPassenger()) {
                // TODO: PlayerEntity is player??  ClientPlayerEntity
                //Minecraft.getInstance().player.isRowingBoat()
                Entity ride = player.getRidingEntity();
                if (ride instanceof BoatEntity) {
                    float yaw = ride.rotationYaw % 90.0f;
                    if (yaw < 0) { yaw = yaw + 90.0f;}
                    BlockPos pos = ride.getPosition();
                    //System.out.println("Player is in a boat! " + yaw + block);
                    // TODO: Is ice road (single or strip of ice)
                    if (isIceBlock(pos)) {
                        // Rotate to align on axis or diagonal
                        if (yaw < 10) {
                            ride.rotationYaw = ride.rotationYaw - TICK_TURN_INCREMENT;
                        } else if (yaw > 35 && yaw < 45) {
                            ride.rotationYaw = ride.rotationYaw + TICK_TURN_INCREMENT;
                        } else if (yaw > 45 && yaw < 55) {
                            ride.rotationYaw = ride.rotationYaw - TICK_TURN_INCREMENT;
                        } else if ( yaw > 80) {
                            ride.rotationYaw = ride.rotationYaw + TICK_TURN_INCREMENT;
                        }
                    }
                }
            }
            //System.out.println("Is the client player? " + Minecraft.getInstance().player.isUser());
            //Minecraft.getInstance().player.getRidingEntity().rotationYaw = Minecraft.getInstance().player.getRidingEntity().rotationYaw+0.1f;
        }
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

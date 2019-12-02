package com.sarsk.icerailhelper.client;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static java.lang.Math.abs;

@OnlyIn(Dist.CLIENT)
public class AlignPlayerWithIceRail {

    final float TICK_TURN_INCREMENT = 1.5f;
    final float CLOSEST_ENOUGH_THRESHOLD = 1.0f;
    final int TARGET_AXIS_DEGREES = 45;

    boolean isIceBlock(final BlockPos pos) {
        if (false) {
            return false;
        } else {
            BlockState blockState = Minecraft.getInstance().world.getBlockState(pos);
            return (blockState.getBlock() == Blocks.ICE || blockState.getBlock() == Blocks.BLUE_ICE || blockState.getBlock() == Blocks.PACKED_ICE);
        }
    }

    boolean isIceRoad(final BlockPos pos) {
        // TODO: Check block, and neighbors to infer if this is part of a road
        return isIceBlock(pos);
    }

    private boolean isCloseEnough(float yaw) {
        return (Math.abs(yaw - Utilities.findClosestValue(yaw, TARGET_AXIS_DEGREES )) < CLOSEST_ENOUGH_THRESHOLD);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {
        // Only process player entities
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)event.getEntity();
            //System.out.println("Receiving client player events! Player " + player);

            //System.out.println("Player is in a boat?" + player.isUser() +", " + player.isPassenger() + " remote? " + player.getEntityWorld().isRemote);
            if (player.isUser() && player.isPassenger() && player.getEntityWorld().isRemote) {
                Entity ride = player.getRidingEntity();

                if (abs(ride.rotationYaw - ride.prevRotationYaw) > TICK_TURN_INCREMENT) {
                    System.out.println("User is rotating on their own,  waiting for it to settle! ");
                    return;
                }

                if (ride instanceof BoatEntity) {
                    BlockPos pos = ride.getPosition().down();
                    // TODO: Is ice road (single or strip of ice)
                    if (isIceBlock(pos)) {
                        //System.out.println("Is IceBlock! ");
                        // Rotate to align on axis or diagonal
                        // Note: The player and player view also rotate with the boat.
                        //       Adjust them as well
                        final float oldBoatRotationYawDelta = ride.rotationYaw - ride.prevRotationYaw;
                        final float oldPlayerRotationYawDelta = player.rotationYaw - player.prevRotationYaw;
                        final float oldPlayerHeadRotationYawDelta = player.rotationYawHead - player.prevRotationYawHead;
                        final float newRotationYaw = Utilities.rotateTowardsAxis(ride.rotationYaw, TARGET_AXIS_DEGREES, TICK_TURN_INCREMENT);
                        final float rotationDelta = (newRotationYaw - ride.rotationYaw);
                        System.out.println("Prev Rotation: " + ride.prevRotationYaw + ", " + player.prevRotationYaw + ", " + player.prevRotationYawHead);
                        System.out.println("Curr Rotation: " + ride.rotationYaw + ", " + player.rotationYaw + ", " + player.rotationYawHead);
                        System.out.println("Rotation Deltas: " + oldBoatRotationYawDelta + ", " + oldPlayerRotationYawDelta + ", " + oldPlayerHeadRotationYawDelta
                            + " [" + rotationDelta + "] target [" + Utilities.findClosestValue(ride.rotationYaw, TARGET_AXIS_DEGREES) + "]");
                        // Eliminate oscillation by snapping to angle if within threshold value
                        if (isCloseEnough(ride.rotationYaw)) {
                            System.out.println("Locked to rotation " + ride.rotationYaw);
                            ride.rotationYaw = Utilities.findClosestValue(ride.rotationYaw, TARGET_AXIS_DEGREES);
                        /*} else if (oldBoatRotationYawDelta < TICK_TURN_INCREMENT) {
                            System.out.println("Jitter? " + ride.rotationYaw);*/
                        } else {
                            ride.rotationYaw += rotationDelta;
                            //player.rotationYaw -= rotationDelta;
                            player.rotationYawHead += rotationDelta;
                            System.out.println("User forced rotation to " + ride.rotationYaw + ", " + player.rotationYawHead);
                        }
                                            }
                }
            }
        }
    }
}

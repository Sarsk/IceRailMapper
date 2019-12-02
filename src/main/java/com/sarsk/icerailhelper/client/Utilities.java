package com.sarsk.icerailhelper.client;

public class Utilities {
    public Utilities() {

    }

    static int findClosestValue(float value, int multiple) {
        int idealValue = (int)Math.abs(value);
        int remValue = idealValue % multiple;
        // remValue will be 0 to multiple-1
        if (remValue < multiple/2)
        {
            idealValue = idealValue - remValue;
        } else {
            idealValue = idealValue + (multiple - remValue);
        }

        if (value >= 0) {
            return idealValue;
        }
        else {
            return -idealValue;
        }
    }

    static float rotateTowardsAxis(final float currentYaw, final int targetAxisDegrees, final float turnIncValue) {
        final int idealYaw = (int)currentYaw;
        final int remYaw = idealYaw % targetAxisDegrees ;
        final int threshold = targetAxisDegrees /2;

        // Rotate to closest axis
        if (remYaw > -threshold && remYaw < 0) {
            return currentYaw + turnIncValue;
        } else {
            return currentYaw - turnIncValue;
        }
    }
}

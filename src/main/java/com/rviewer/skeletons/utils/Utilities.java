package com.rviewer.skeletons.utils;

import java.util.Date;

public final class Utilities {

    private Utilities() {
        super();
    }

    public static double calculateTotalSpent(double flowVolume, Date openDate, Date closeDate) {
        double seconds = (closeDate.getTime() - openDate.getTime()) / 1000.0;
        return flowVolume * seconds;
    }
}

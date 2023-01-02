package com.rviewer.skeletons.utils;

import java.util.Date;

public final class Utilities {

    public static double calculateTotalSpent(double flowVolume, Date openDate, Date closeDate) {
        double seconds = (closeDate.getTime() - openDate.getTime()) / 1000.0;
        return flowVolume * seconds;
    }

    private Utilities() {
        super();
    }
}

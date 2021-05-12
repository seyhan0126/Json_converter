package org.example.model;

public class ReportDefinition {
    public int getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public void setTopPerformersThreshold(int topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
    }

    public boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
    }

    public int getPeriodLimit() {
        return periodLimit;
    }

    public void setPeriodLimit(int periodLimit) {
        this.periodLimit = periodLimit;
    }

    private int topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private int periodLimit;

    @Override
    public String toString() {
        return "ReportDefinition{" +
                "topPerformersThreshold=" + topPerformersThreshold +
                ", useExprienceMultiplier=" + useExprienceMultiplier +
                ", periodLimit=" + periodLimit +
                '}';
    }


    public ReportDefinition() {
    }


}

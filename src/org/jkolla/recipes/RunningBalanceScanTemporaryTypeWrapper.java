package org.jkolla.recipes;

import java.util.List;

public class RunningBalanceScanTemporaryTypeWrapper {
   public  Double runningBal;
   public   List<RunningBalanceScanTemporaryType> runningBalanceScanTemporaryTypes;

    public RunningBalanceScanTemporaryTypeWrapper(Double runningBal, List<RunningBalanceScanTemporaryType> runningBalanceScanTemporaryTypes) {
        this.runningBal = runningBal;
        this.runningBalanceScanTemporaryTypes = runningBalanceScanTemporaryTypes;
    }

    public Double getRunningBal() {
        return runningBal;
    }

    public List<RunningBalanceScanTemporaryType> getRunningBalanceScanTemporaryTypes() {
        return runningBalanceScanTemporaryTypes;
    }

    @Override
    public String toString() {
        return "RunningBalanceScanTemporaryTypeWrapper{" +
                "runningBal=" + runningBal +
                ", runningBalanceScanTemporaryTypes=" + runningBalanceScanTemporaryTypes +
                '}';
    }
}

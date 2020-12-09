package org.jkolla.components;

import java.util.List;

public class ScanTemporaryTypeWrapper  {
   public  int counter;
   public  List<ScanTemporaryType> scanTemporaryTypes;

    public ScanTemporaryTypeWrapper(int counter, List<ScanTemporaryType> scanTemporaryTypes) {
        this.counter = counter;
        this.scanTemporaryTypes = scanTemporaryTypes;
    }

    public int getCounter() {
        return counter;
    }

    public List<ScanTemporaryType> getScanTemporaryTypes() {
        return scanTemporaryTypes;
    }

    @Override
    public String toString() {
        return "ScanTemporaryTypeWrapper{" +
                "counter=" + counter +
                ", scanTemporaryTypes=" + scanTemporaryTypes +
                '}';
    }
}

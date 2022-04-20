package system.model.adt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EMeterRead extends MeterRead {

    private int register;

    public EMeterRead(ReadType readType, Date startPeriod, Date endPeriod, double openingRead, double closingRead) {
        super(readType, startPeriod, endPeriod, openingRead, closingRead);
    }

    @Override
    public double getKWH() {
        return 0;
    }
}

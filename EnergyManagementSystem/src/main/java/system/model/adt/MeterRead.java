package system.model.adt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class MeterRead {

    private ReadType readType;
    private Date startPeriod;
    private Date endPeriod;
    private double openingRead;
    private double closingRead;

    public MeterRead(ReadType readType, Date startPeriod, Date endPeriod, double openingRead, double closingRead) {
        this.readType = readType;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.openingRead = openingRead;
        this.closingRead = closingRead;
    }

    public int getPeriodDuration() {
        return (int) TimeUnit.DAYS.convert(Math.abs(endPeriod.getTime() - startPeriod.getTime()),
                TimeUnit.MILLISECONDS);
    }

    /**
     * will calculate KWH based on Meter-type
     * @return
     */
    public abstract double getKWH();

    public ReadType getReadType() {
        return readType;
    }

    public void setReadType(ReadType readType) {
        this.readType = readType;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public double getOpeningRead() {
        return openingRead;
    }

    public void setOpeningRead(double openingRead) {
        this.openingRead = openingRead;
    }

    public double getClosingRead() {
        return closingRead;
    }

    public void setClosingRead(double closingRead) {
        this.closingRead = closingRead;
    }

    @Override
    public String toString() {
        return "MeterRead{" +
                "readType=" + readType +
                ", startPeriod=" + startPeriod +
                ", endPeriod=" + endPeriod +
                ", openingRead=" + openingRead +
                ", closingRead=" + closingRead +
                '}';
    }
}

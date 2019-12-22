package com.example.medicinescheduler.Model;

public class Medicine
{
    public long MedicineID;

    private String MedicineName;
    private String MedicineQuantity;
    private String MedicineUnit;
    private String MedicineTime;
    private String MedicineMeal;
    private String MedicineInterval;
    private String MedicineStartDate;
    private String MedicineEndDate;

    public Medicine(long MedicineID, String MedicineName,String MedicineQuantity ,String MedicineUnit,String MedicineTime,String MedicineMeal, String MedicineInterval,String MedicineStartDate,String MedicineEndDate)
    {
        this.MedicineID = MedicineID;
        this.MedicineName = MedicineName;
        this.MedicineQuantity = MedicineQuantity;
        this.MedicineUnit = MedicineUnit;
        this.MedicineTime = MedicineTime;
        this.MedicineMeal = MedicineMeal;
        this.MedicineInterval = MedicineInterval;
        this.MedicineStartDate = MedicineStartDate;
        this.MedicineEndDate = MedicineEndDate;
    }

    public Medicine()
    {

    }

    public long getMedicineID()
    {
        return MedicineID;
    }

    public void setMedicineID(long MedicineID)
    {
        this.MedicineID = MedicineID;
    }

    public String getMedicineName()
    {
        return MedicineName;
    }

    public void setMedicineName(String MedicineName)
    {
        this.MedicineName = MedicineName;
    }

    public String getMedicineQuantity()
    {
        return MedicineQuantity;
    }

    public void setMedicineQuantity(String MedicineQuantity)
    {
        this.MedicineQuantity = MedicineQuantity;
    }

    public String getMedicineUnit()
    {
        return MedicineUnit;
    }

    public void setMedicineUnit(String MedicineUnit)
    {
        this.MedicineUnit = MedicineUnit;
    }

    public String getMedicineTime()
    {
        return MedicineTime;
    }

    public void setMedicineTime(String MedicineTime)
    {
        this.MedicineTime = MedicineTime;
    }

    public String getMedicineMeal()
    {
        return MedicineMeal;
    }

    public void setMedicineMeal(String MedicineMeal)
    {
        this.MedicineMeal = MedicineMeal;
    }

    public String getMedicineInterval()
    {
        return MedicineInterval;
    }

    public void setMedicineInterval(String MedicineInterval)
    {
        this.MedicineInterval = MedicineInterval;
    }

    public String getMedicineStartDate()
    {
        return MedicineStartDate;
    }

    public void setMedicineStartDate(String MedicineStartDate)
    {
        this.MedicineStartDate = MedicineStartDate;
    }

    public String getMedicineEndDate()
    {
        return MedicineEndDate;
    }

    public void setMedicineEndDate(String MedicineEndDate)
    {
        this.MedicineEndDate = MedicineEndDate;
    }

    public String toString()
    {
        return "Medicine ID: "+getMedicineID() + "\n" +
                "Medicine Name: "+getMedicineName() + "\n" +
                "Medicine Quantity: "+getMedicineQuantity() + getMedicineUnit() + "\n" +
                "Medicine Time: "+getMedicineTime() + "\n" +
                "Medicine Meal: "+getMedicineMeal() + "\n" +
                "Medicine Interval: "+getMedicineInterval() + "\n" +
                "Medicine Start Date: "+getMedicineStartDate() + "\n" +
                "Medicine End Date: "+getMedicineEndDate();
    }

}

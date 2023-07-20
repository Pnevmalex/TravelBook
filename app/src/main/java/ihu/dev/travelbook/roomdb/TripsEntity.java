package ihu.dev.travelbook.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import ihu.dev.travelbook.ui.admin.dashboard.Dashboard;

@Entity(tableName = "Trips_Table",
        foreignKeys = {
        @ForeignKey(entity = OfficesEntity.class,
                parentColumns = "Offices_id",
                childColumns = "office_id",
                onDelete = ForeignKey.CASCADE)})
public class TripsEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Trip_id")
    private int Trip_id;
    @ColumnInfo(name = "office_id")
    private int officeId;
    @ColumnInfo(name = "Town_name")
    private String Town_name;
    @ColumnInfo(name = "Country_name")
    private String Country_name;
    @ColumnInfo(name = "Duration_time")
    private int Duration;
    @ColumnInfo(name = "Trip_type")
    private String Type;
    @ColumnInfo(name = "Trip_price")
    private double Price;
    @ColumnInfo(name = "Trip_offer")
    private boolean Offer;
    @ColumnInfo(name = "Trip_latitude")
    private double Latitude;
    @ColumnInfo(name = "Trip_longitude")
    private double Longitude;
    @ColumnInfo(name = "Image_link")
    private String Link;

    public TripsEntity() {
    }

    public TripsEntity(int officeId, String town_name, String country_name, int duration, String type, double price, boolean offer, double latitude, double longitude, String link) {
        this.officeId = officeId;
        Town_name = town_name;
        Country_name = country_name;
        Duration = duration;
        Type = type;
        Price = price;
        Offer = offer;
        Latitude = latitude;
        Longitude = longitude;
        Link = link;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public boolean getOffer() {
        return Offer;
    }

    public void setOffer(boolean offer) {
        Offer = offer;
    }

    public int getTrip_id() {
        return Trip_id;
    }

    public void setTrip_id(int trip_id) {
        Trip_id = trip_id;
    }

    public String getTown_name() {
        return Town_name;
    }

    public void setTown_name(String town_name) {
        Town_name = town_name;
    }

    public String getCountry_name() {
        return Country_name;
    }

    public void setCountry_name(String country_name) {
        Country_name = country_name;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
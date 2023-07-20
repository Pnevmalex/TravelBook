package ihu.dev.travelbook.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {OfficesEntity.class, TripsEntity.class}, version = 1, exportSchema = false)
public abstract class Adatabase extends RoomDatabase {

    public abstract OfficesDao officesDao();

    public abstract TripsDao tripsDao();


}
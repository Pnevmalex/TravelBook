package ihu.dev.travelbook.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripsDao {


    @Insert()
    void addTrip(TripsEntity trips);

    @Update()
    void updateTrip(TripsEntity trips);

    @Delete()
    void deleteTrip(TripsEntity trips);


    @Query("select * from Trips_Table")
    LiveData<List<TripsEntity>> getTrips();

    @Query("select * from Trips_Table  where Trip_id=:id")
    TripsEntity getTripById(int id);

    @Query("select * from Trips_Table  where office_id=:id")
    LiveData<List<TripsEntity>> getTripByOfficeId(int id);

    @Query("select * from Trips_Table where Trip_offer order by Trip_price ASC")
    LiveData<List<TripsEntity>> getOffers();

    @Query("select * from Trips_Table where Town_name=:city")
    LiveData<List<TripsEntity>> getTripsByName(String city);

    @Query("select * from Trips_Table where Town_name=:city")
    List<TripsEntity> getTripsByNameList(String city);
}

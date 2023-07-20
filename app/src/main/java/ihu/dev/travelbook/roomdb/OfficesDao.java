package ihu.dev.travelbook.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface OfficesDao {

    @Insert()
    void addOffice(OfficesEntity office);

    @Query("SELECT * from Offices_Table where Offices_id=(:id)")
    OfficesEntity getOfficeById(int id);

    @Query("SELECT * from Offices_Table where Offices_username=(:Username) and Offices_password=(:Password)")
    OfficesEntity LoginOffice(String Username, String Password);

    @Delete()
    void  deleteOffice(OfficesEntity office);

}

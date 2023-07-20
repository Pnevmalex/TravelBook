package ihu.dev.travelbook.roomdb;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Offices_Table")
public class OfficesEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Offices_id")
    private int id;
    @ColumnInfo(name = "Offices_name")
    private String Name;
    @ColumnInfo(name = "Offices_address")
    private String Address;
    @ColumnInfo(name = "Offices_phone")
    private long Phone;
    @ColumnInfo(name = "Offices_username")
    private String Username;
    @ColumnInfo(name = "Offices_password")
    private String Password;


    public OfficesEntity() {
    }

    public OfficesEntity(String name, String address, long phone, String username, String password) {
        Name = name;
        Address = address;
        Phone = phone;
        Username = username;
        Password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public long getPhone() {
        return Phone;
    }

    public void setPhone(long phone) {
        Phone = phone;
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

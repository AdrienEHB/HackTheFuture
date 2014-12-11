package be.ehb.adrienschautteet.hackthefuture;

import java.io.Serializable;

/**
 * Created by adrienschautteet on 11/12/14.
 */
public class CityFromDb implements Serializable {

    private int id;
    private long cityId;
    private String name;
    private int zipcode;
    private String province;
    private String alertCode;
    private String kind;

    public CityFromDb(int id, long cityId, String name, int zipcode, String province, String alertCode, String kind) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.zipcode = zipcode;
        this.province = province;
        this.alertCode = alertCode;
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public long getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getProvince() {
        return province;
    }

    public String getAlertCode() {
        return alertCode;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

}

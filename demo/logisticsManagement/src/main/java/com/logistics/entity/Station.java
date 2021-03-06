package com.logistics.entity;

public class Station {
    private String name;

    private Integer value;

    private String time;

    private String starttime;

    private String endtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column station.id
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column station.station_name
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    private String stationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", stationName=" + stationName + "]";
    }

}
package com.logistics.entity;

public class Car {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.id
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_name
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    private String carName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_station
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    private Integer carStation;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.id
     *
     * @return the value of car.id
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.id
     *
     * @param id the value for car.id
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_name
     *
     * @return the value of car.car_name
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    public String getCarName() {
        return carName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_name
     *
     * @param carName the value for car.car_name
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_station
     *
     * @return the value of car.car_station
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    public Integer getCarStation() {
        return carStation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_station
     *
     * @param carStation the value for car.car_station
     *
     * @mbggenerated Wed Oct 24 17:49:34 CST 2018
     */
    public void setCarStation(Integer carStation) {
        this.carStation = carStation;
    }
}
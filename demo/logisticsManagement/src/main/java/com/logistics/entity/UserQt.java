package com.logistics.entity;

public class UserQt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_qt.id
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_qt.username
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_qt.password
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_qt.tel
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    private Integer tel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_qt.six
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    private String six;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_qt.id
     *
     * @return the value of user_qt.id
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_qt.id
     *
     * @param id the value for user_qt.id
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_qt.username
     *
     * @return the value of user_qt.username
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_qt.username
     *
     * @param username the value for user_qt.username
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_qt.password
     *
     * @return the value of user_qt.password
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_qt.password
     *
     * @param password the value for user_qt.password
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_qt.tel
     *
     * @return the value of user_qt.tel
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public Integer getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_qt.tel
     *
     * @param tel the value for user_qt.tel
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public void setTel(Integer tel) {
        this.tel = tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_qt.six
     *
     * @return the value of user_qt.six
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public String getSix() {
        return six;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_qt.six
     *
     * @param six the value for user_qt.six
     *
     * @mbggenerated Fri Oct 19 18:19:40 CST 2018
     */
    public void setSix(String six) {
        this.six = six == null ? null : six.trim();
    }
}
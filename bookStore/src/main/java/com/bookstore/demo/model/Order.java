package com.bookstore.demo.model;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table order
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Order implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.order_number
     *
     * @mbg.generated
     */
    private String orderNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.user_number
     *
     * @mbg.generated
     */
    private Integer userNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.total_price
     *
     * @mbg.generated
     */
    private Float totalPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.total_quantity
     *
     * @mbg.generated
     */
    private Integer totalQuantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.payment_status
     *
     * @mbg.generated
     */
    private Integer paymentStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.order_number
     *
     * @return the value of order.order_number
     *
     * @mbg.generated
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.order_number
     *
     * @param orderNumber the value for order.order_number
     *
     * @mbg.generated
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.user_number
     *
     * @return the value of order.user_number
     *
     * @mbg.generated
     */
    public Integer getUserNumber() {
        return userNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.user_number
     *
     * @param userNumber the value for order.user_number
     *
     * @mbg.generated
     */
    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.total_price
     *
     * @return the value of order.total_price
     *
     * @mbg.generated
     */
    public Float getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.total_price
     *
     * @param totalPrice the value for order.total_price
     *
     * @mbg.generated
     */
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.total_quantity
     *
     * @return the value of order.total_quantity
     *
     * @mbg.generated
     */
    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.total_quantity
     *
     * @param totalQuantity the value for order.total_quantity
     *
     * @mbg.generated
     */
    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.payment_status
     *
     * @return the value of order.payment_status
     *
     * @mbg.generated
     */
    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.payment_status
     *
     * @param paymentStatus the value for order.payment_status
     *
     * @mbg.generated
     */
    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
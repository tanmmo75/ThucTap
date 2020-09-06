/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Categories;
import entity.MyItem;
import entity.OrderDetails;
import entity.Orders;
import entity.Producers;
import entity.Products;
import java.util.List;


public interface OrdersDAO {

    public long Count(Integer mode);

    public List<Orders> getAllOrders(Integer currentPage, Integer recordsPerPage);

    public Orders getOrderById(Integer id);

    public Boolean insertOrder(Orders o);

    public Boolean updateOrder(Orders o);

    public Boolean deleteOrder(Integer id);

    public List<Orders> getAllStatus1(Integer currentPage, Integer recordsPerPage);

    public List<Orders> getAllStatus2(Integer currentPage, Integer recordsPerPage);

    public List<Orders> getAllStatus3(Integer currentPage, Integer recordsPerPage);

    public Double getReportOrderTotalPrices();

    public Integer getReportSumTotalDonHangChuaHoanThanh();

    public Integer getReportSumTotalDonHangDaHoanThanh();

    public Integer getReportSumTotalDonHangDangVanChuyen();

    public Integer getReportSumTotalProduct();

    public Double getReportOrderTotalSum(Integer orderID);

    public List<OrderDetails> getOrderByListId(Integer orderID);

    public List<Categories> getReportChartjs();

    public List<Producers> getReportProducersChartjs();

    public Integer getLastOrders();
}

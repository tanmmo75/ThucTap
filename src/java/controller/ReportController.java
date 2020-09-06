/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrdersDAO;
import entity.Categories;
import entity.MyItem;
import entity.MyReport;
import entity.OrderDetails;
import entity.Orders;
import entity.Producers;
import entity.Products;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LeDung
 */
@Controller
public class ReportController {

    private OrdersDAO ordersDAO;

    @Autowired
    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @RequestMapping(value = "/listReport")
    public String listReport(Model model) {
        model.addAttribute("listRSTDHCHT", ordersDAO.getReportSumTotalDonHangChuaHoanThanh());
        model.addAttribute("listRSTDGDVC", ordersDAO.getReportSumTotalDonHangDangVanChuyen());
        model.addAttribute("listRSTDHDHT", ordersDAO.getReportSumTotalDonHangDaHoanThanh());
        model.addAttribute("listRSTP", ordersDAO.getReportSumTotalProduct());
        model.addAttribute("listROTP", ordersDAO.getReportOrderTotalPrices());

        List<Categories> listRC = ordersDAO.getReportChartjs();
        List<MyItem> listItem = new ArrayList<>();
        for (Categories c : listRC) {
            int total = 0;
            for (Products p : c.getProductsCollection()) {
                if (p.getViews() > 0) {
                    total++;
                }
            }
            if (total > 0) {
                MyItem mi = new MyItem(c.getName(), total);
                listItem.add(mi);
            }
        }
        model.addAttribute("listRC", listItem);

        List<Producers> listReportProducer = ordersDAO.getReportProducersChartjs();
        List<MyReport> listMyReportProducer = new ArrayList<>();
        for (Producers c : listReportProducer) {
            int total = 0;
            for (Products p : c.getProductsCollection()) {
                if (p.getViews() > 0) {
                    total++;
                }
            }
            if (total > 0) {
                MyReport mr = new MyReport(c.getName(), total);
                listMyReportProducer.add(mr);
            }
        }
        model.addAttribute("listReportProducer", listMyReportProducer);
        
        return "listReport";
    }
}

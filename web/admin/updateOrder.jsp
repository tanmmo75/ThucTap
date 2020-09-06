<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Review Order Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Chi tiết đơn hàng </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div class="box">
                        <header>
                            <div class="icons"><i class="icon-th-large"></i></div>
                            <h5>Màn hình xem dữ liệu</h5>
                            <div class="toolbar">
                                <ul class="nav">
                                    <li>
                                        <div class="btn-group">
                                            <a class="accordion-toggle btn btn-xs minimize-box" data-toggle="collapse" href="#collapseOne">
                                                <i class="icon-chevron-up"></i>
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </header>
                        <div id="collapseOne" class="accordion-body collapse in body">
                            <spring:form action="updateOrder.htm" class="form-horizontal" commandName="o" method="post"> 
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="box">
                                            <header>
                                                <div class="icons"><i class="icon-bolt"></i></div>
                                                <h5>Thông tin</h5>
                                            </header>
                                            <div id="collapseOne" class="accordion-body collapse in body">
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Họ và tên :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${o.shipName}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Số điện thoại :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${o.shipMobile}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Địa chỉ :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${o.shipAdress}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="box">
                                            <header>
                                                <div class="icons"><i class="icon-bolt"></i></div>
                                                <h5>Thông tin</h5>
                                            </header>
                                            <div id="collapseOne" class="accordion-body collapse in body">
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Email :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${o.shipEmai}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Ngày gửi đơn :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label"><fmt:formatDate pattern="dd/MM/yyyy" value="${o.createDate}"/></label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Trạng thái đơn hàng :</label>
                                                    <div class="col-lg-8">
                                                        <c:if test="${o.status == 1}">
                                                            <label for="text1" class="control-label"><a class='btn btn-danger btn-xs'>Chưa hoàn thành đơn hàng</a></label>
                                                        </c:if>
                                                        <c:if test="${o.status == 2}">
                                                            <label for="text1" class="control-label"><a class='btn btn-warning btn-xs'>Đang trên đường đi</a></label>
                                                        </c:if>
                                                        <c:if test="${o.status == 3}">
                                                            <label for="text1" class="control-label"><a class='btn btn-success btn-xs'>Đã hoàn thành đơn hàng</a></label>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row"> 
                                    <div class="col-lg-12">
                                        <div class="box">
                                            <header>
                                                <div class="icons"><i class="icon-bolt"></i></div>
                                                <h5>Thông tin</h5>
                                            </header>
                                            <div class="panel-body">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th colspan="6" style="text-align: center;">Mã đơn hàng số ${o.orderID}</th>
                                                            </tr>
                                                            <tr>
                                                                <th style="text-align: center;">Hình ảnh</th>
                                                                <th>Tên sản phẩm</th>
                                                                <th style="text-align: center;">Giá sản phẩm</th>
                                                                <th style="text-align: center;">Giảm giá</th>
                                                                <th style="text-align: center;">Số lượng</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${list}" var="o">                                                               
                                                                    <tr>
                                                                        <td style="text-align: center; vertical-align: middle;"><img width="60" src="${o.products.image}" title=""></td>
                                                                        <td style="vertical-align: middle;">${o.products.name}</td>
                                                                        <td style="text-align: center; vertical-align: middle;"><fmt:formatNumber value = "${o.price}" /> $</td>
                                                                        <td style="text-align: center; vertical-align: middle;">${o.discount}</td>
                                                                        <td style="text-align: center; vertical-align: middle;">${o.quantity}</td>
                                                                    </tr>                                                              
                                                            </c:forEach>
                                                            <tr>
                                                                <td colspan="5" style="text-align:right"><strong>Tổng cộng = <fmt:formatNumber value = "${objOST}" /> $</strong></td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6" style="text-align:center;">
                                                                    <div style="padding-bottom: 9px;">
                                                                        <spring:radiobutton path="status" name="status" id="status" value="1"/> 
                                                                        <label for='text1' class='control-label'>
                                                                            <a class='btn btn-danger btn-xs'>Đơn hàng chưa hoàn thành</a>
                                                                        </label>&nbsp;&nbsp; 
                                                                        <spring:radiobutton path="status" name="status" id="status" value="2"/> 
                                                                        <label for='text1' class='control-label'>
                                                                            <a class='btn btn-warning btn-xs'>Xuất hàng</a>
                                                                        </label>&nbsp;&nbsp; 
                                                                        <spring:radiobutton path="status" name="status" id="status" value="3"/> 
                                                                        <label for='text1' class='control-label'>
                                                                            <a class='btn btn-success btn-xs'>Đã hoàn thành đơn hàng</a>
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" hidden=""> 
                                    <spring:input path="orderID" type="text" id="orderID" name="orderID" placeholder="" class="form-control"/><br>
                                    <spring:input path="shipName" type="text" id="shipName" name="shipName" placeholder="" class="form-control"/><br>
                                    <spring:input path="shipMobile" type="text" id="shipMobile" name="shipMobile" placeholder="" class="form-control"/><br>
                                    <spring:input path="shipAdress" type="text" id="shipAdress" name="shipAdress" placeholder="" class="form-control"/><br>
                                    <spring:input path="shipEmai" type="text" id="shipEmai" name="shipEmai" placeholder="" class="form-control"/><br>
                                    <spring:input path="createDate" type="date" id="createDate" name="createDate" placeholder="" class="form-control"/><br>
                                </div>

                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <input type="submit" class="btn btn-primary" value="Xong" />
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>

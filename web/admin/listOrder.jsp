<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Order Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Bài viết chờ duyệt </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div id="div-5" class="accordion-body collapse in body">
                        <div class="form-group row">
                            <div class="col-lg-9" style="margin-bottom: 3px;">
                                <a href="listOrdersStatus1.htm?page=1" class="btn btn-danger btn-xs">Chưa xét duyệt</a>
                                <a href="listOrdersStatus3.htm?page=1" class="btn btn-success btn-xs">Đã xét duyệt</a>
                                <a href="listO.htm?page=1" class="btn btn-primary btn-xs">Tất cả bài viết</a>
                            </div>
                            <div class="col-lg-3">
                                <div id="AlertBox" class="a hiden" style="margin-top: -4px; font-size: large; color: #468847;">
                                    ${notificationsSuccessfully}
                                </div>
                                <div id="AlertBox2" class="b hiden" style="margin-top: -4px; font-size: large; color: #b94a48;">
                                    ${notificationsError}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Màn hình hiển thị dữ liệu
                            <div style="float:right">
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width: 150px;">Tên Người viết</th>
                                            <th style="width: 150px;">Điện Thoại Liên Hệ</th>
                                            <th style="width: 150px;">Địa Chỉ</th>
                                            <th style="width: 150px;">Email Liên Hệ</th>
                                            <th style="width: 150px;">Ngày Gửi</th>
                                            <th style="width: 150px;">Trạng Thái</th>
                                            <th style="width: 150px;">Thao Tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listOrder}" var="o">
                                            <tr>
                                                <td>${o.shipName}</td>
                                                <td>${o.shipMobile}</td>
                                                <td>${o.shipAdress}</td>
                                                <td>${o.shipEmai}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${o.createDate}"/></td>
                                                <td>
                                                    <c:if test="${o.status == 1}">
                                                        <a class='btn btn-danger btn-xs'>Chưa hoàn thành đơn hàng</a>
                                                    </c:if>
                                                    <c:if test="${o.status == 3}">
                                                        <a class='btn btn-success btn-xs'>Đã hoàn thành đơn hàng</a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <a href="initUpdateOrder.htm?orderID=${o.orderID}" class="btn btn-primary"><i class="icon-eye-open icon-white"></i></a>
                                                    <a href="deleteOrder.htm?orderID=${o.orderID}&&page=${currentPage}&&status=0" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa đơn hàng này?')"><i class="icon-remove icon-white"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!--Pagination-->
                                <div style="float:right">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <c:if test="${currentPage != 1}">
                                                <li class="paginate_button previous">
                                                    <a href="listO.htm?page=${currentPage-1}">Previous</a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li class="paginate_button active">
                                                            <a>${i} <span class="sr-only">(current)</span></a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="paginate_button">
                                                            <a href="listO.htm?page=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage lt noOfPages}">
                                                <li class="paginate_button next">
                                                    <a href="listO.htm?page=${currentPage+1}">Next</a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
                                <!--End Pagination-->
                            </div>
                        </div>
                    </div>
                </div>
                <!--from payment-->
                <div class="col-lg-12">
                    <div class="modal" id="paymentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h5 class="modal-title" id="myModalLabel">Dữ liệu bài viết</h5>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal">
                                        <div class="col-md-12"> 
                                            <div class="form-group">
                                                <label for="text1" class="control-label col-lg-4">Danh Mục Menu</label>
                                                <div class="col-lg-8">
                                                    <input type="text" id="text1" placeholder="" class="form-control">
                                                </div>
                                            </div>
                                        </div>                                      
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
                                    <button type="button" class="btn btn-primary">Cập nhật</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Feekback Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Phản hồi </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div id="div-5" class="accordion-body collapse in body">
                        <div class="form-group row">
                            <div class="col-lg-9" style="margin-bottom: 3px;">
                                <a href="listFeedBacksStatus2.htm?page=1" class="btn btn-danger btn-xs">Phản hồi chưa xem</a>
                                <a href="listFeedBacksStatus1.htm?page=1" class="btn btn-success btn-xs">Phản hồi đã xem</a>
                                <a href="listFB.htm?page=1" class="btn btn-primary btn-xs">Tất cả phản hồi</a>
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
                                            <th style="width: 150px;">Họ Tên</th>
                                            <th style="width: 150px;">Điện Thoại</th>
                                            <th style="width: 150px;">Email</th>
                                            <th style="width: 150px;">Địa Chỉ</th>
                                            <th style="width: 150px;">Ngày Gửi</th>
                                            <th style="width: 150px;">Trạng Thái</th>
                                            <th style="width: 150px;">Thao Tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listFeedBack}" var="f">
                                            <tr>
                                                <td>${f.name}</td>
                                                <td>${f.phone}</td>
                                                <td>${f.email}</td>
                                                <td>${f.address}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${f.createDate}"/></td>
                                                <td>${f.status?"<a class='btn btn-danger btn-xs'>Chưa xem</a>":"<a class='btn btn-success btn-xs'>Đã xem</a>"}</td>
                                                <td>
                                                    <a href="initUpdateFeedBack.htm?feedBackID=${f.feedBackID}" class="btn btn-primary"><i class="icon-eye-open icon-white"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <c:choose>
                                    <c:when test="${status == 1}">
                                        <div style="float:right">
                                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                                <ul class="pagination">
                                                    <c:if test="${currentPage != 1}">
                                                        <li class="paginate_button previous">
                                                            <a href="listFeedBacksStatus1.htm?name=${currentName}&&page=${currentPage-1}">Previous</a>
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
                                                                    <a href="listFeedBacksStatus1.htm?name=${currentName}&&page=${i}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:if test="${currentPage lt noOfPages}">
                                                        <li class="paginate_button next">
                                                            <a href="listFeedBacksStatus1.htm?name=${currentName}&&page=${currentPage+1}">Next</a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${status == 2}">
                                        <div style="float:right">
                                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                                <ul class="pagination">
                                                    <c:if test="${currentPage != 1}">
                                                        <li class="paginate_button previous">
                                                            <a href="listFeedBacksStatus2.htm?name=${currentName}&&page=${currentPage-1}">Previous</a>
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
                                                                    <a href="listFeedBacksStatus2.htm?name=${currentName}&&page=${i}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:if test="${currentPage lt noOfPages}">
                                                        <li class="paginate_button next">
                                                            <a href="listFeedBacksStatus2.htm?name=${currentName}&&page=${currentPage+1}">Next</a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="float:right">
                                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                                <ul class="pagination">
                                                    <c:if test="${currentPage != 1}">
                                                        <li class="paginate_button previous">
                                                            <a href="listFB.htm?page=${currentPage-1}">Previous</a>
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
                                                                    <a href="listFB.htm?page=${i}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:if test="${currentPage lt noOfPages}">
                                                        <li class="paginate_button next">
                                                            <a href="listFB.htm?page=${currentPage+1}">Next</a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="modal" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h5 class="modal-title" id="myModalLabel">Xem dữ liệu</h5>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal">
                                        <div class="col-md-12"> 
                                            <div class="form-group">
                                                <label for="text1" class="control-label col-lg-3">Họ Tên</label>
                                                <div class="col-lg-9">
                                                    <input type="text" id="text1" placeholder="" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12"> 
                                            <div class="form-group">
                                                <label for="text1" class="control-label col-lg-3">Điện Thoại</label>
                                                <div class="col-lg-9">
                                                    <input type="text" id="text1" placeholder="" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="control-label col-lg-3">Email</label>
                                                <div class="col-lg-9">
                                                    <input type="text" id="text1" placeholder="" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12"> 
                                            <div class="form-group">
                                                <label for="text1" class="control-label col-lg-3">Địa Chỉ</label>
                                                <div class="col-lg-9">
                                                    <input type="text" id="text1" placeholder="" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12"> 
                                            <div class="form-group">
                                                <label for="text1" class="control-label col-lg-3">Nội Dung</label>
                                                <div class="col-lg-9">
                                                    <textarea class="form-control" rows="6"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12"> 
                                            <div class="form-group">
                                                <label for="text1" class="control-label col-lg-3">Ngày Tạo</label>
                                                <div class="col-lg-9">
                                                    <input type="text" id="text1" placeholder="" class="form-control">
                                                </div>
                                            </div>
                                        </div>                                      
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>

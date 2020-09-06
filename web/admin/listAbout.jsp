<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="About Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Giới thiệu </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div id="div-5" class="accordion-body collapse in body">
                        <div class="form-group row">
                            <div class="col-lg-4"></div>
                            <div class="col-lg-5" style="margin-bottom:-8px;">
                                <form action="initfindA.htm" class="form-horizontal" method="post">
                                    <div class="form-group input-group">
                                        <input path="name" type="text" id="name" name="name" placeholder="Tên giới thiệu" value="${currentName}" class="form-control"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="submit"><i class="icon-search"></i></button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-3">
                                <div id="AlertBox" class="a hiden" style="margin-top: 6px; font-size: large; color: #468847;">
                                    ${notificationsSuccessfully}
                                </div>
                                <div id="AlertBox2" class="b hiden" style="margin-top: 6px; font-size: large; color: #b94a48;">
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
                                <a href="initAddAbout.htm" class="btn btn-default btn-sm" style="margin-top:-5px">
                                    Thêm Mới
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width: 150px;">Lời Giới Thiệu</th>
                                            <th style="width: 150px;">Ngày Tạo</th>
                                            <th style="width: 150px;">Hình Ảnh</th>
                                            <th style="width: 150px;">Trạng Thái</th>
                                            <th style="width: 150px;">Thao Tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listAbout}" var="a">
                                            <tr>
                                                <td style="width: 150px;">${a.name}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.createDate}"/></td>
                                                <td><img src="${a.image}" width="220px;" height="175px;" /></td>
                                                <td>${a.status?"<a class='btn btn-danger btn-xs'>Không hiển thị</a>":"<a class='btn btn-success btn-xs'>Hiển thị</a>"}</td>
                                                <td>
                                                    <a href="initUpdateAbout.htm?aboutID=${a.aboutID}" class="btn btn-primary"><i class="icon-pencil icon-white"></i></a>
                                                    <a href="deleteAbout.htm?aboutID=${a.aboutID}&&find=${currentName}&&page=${currentPage}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa bản ghi này?')"><i class="icon-remove icon-white"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!--Pagination-->
                                <c:choose>
                                    <c:when test="${currentName != null}">
                                        <div style="float:right">
                                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                                <ul class="pagination">
                                                    <c:if test="${currentPage != 1}">
                                                        <li class="paginate_button previous">
                                                            <a href="findA.htm?name=${currentName}&&page=${currentPage-1}">Previous</a>
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
                                                                    <a href="findA.htm?name=${currentName}&&page=${i}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:if test="${currentPage lt noOfPages}">
                                                        <li class="paginate_button next">
                                                            <a href="findA.htm?name=${currentName}&&page=${currentPage+1}">Next</a>
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
                                                            <a href="listA.htm?page=${currentPage-1}">Previous</a>
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
                                                                    <a href="listA.htm?page=${i}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:if test="${currentPage lt noOfPages}">
                                                        <li class="paginate_button next">
                                                            <a href="listA.htm?page=${currentPage+1}">Next</a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <!--End Pagination-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>
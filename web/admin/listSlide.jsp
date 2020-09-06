<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Slide Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Trình chiếu </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div id="div-5" class="accordion-body collapse in body">
                        <div class="form-group row">
                            <div class="col-lg-4"></div>
                            <div class="col-lg-5" style="margin-bottom:-8px;">
                                <div class="form-group input-group">
                                    <input type="text" class="form-control" name="searchString" value="" placeholder="" disabled=""/>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit" disabled=""><i class="icon-search"></i></button>
                                    </span>
                                </div>
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
                                <a href="initAddSlide.htm" class="btn btn-default btn-sm" style="margin-top:-5px">
                                    Thêm Mới
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width: 150px;">Hình Ảnh</th>
                                            <th style="width: 150px;">Thứ Tự Hiển Thị</th>
                                            <th style="width: 150px;">Trạng Thái</th>
                                            <th style="width: 150px;">Thao Tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listSlide}" var="s">
                                            <tr>
                                                <td><img src="${s.image}" width="100%;" height="150px;"></td>
                                                <td>${s.displayOrder}</td>
                                                <td>${s.status?"<a class='btn btn-danger btn-xs'>Không hiển thị</a>":"<a class='btn btn-success btn-xs'>Hiển thị</a>"}</td>
                                                <td>
                                                    <a href="initUpdateSlide.htm?slideID=${s.slideID}" class="btn btn-primary"><i class="icon-pencil icon-white"></i></a>
                                                    <a href="deleteSlide.htm?slideID=${s.slideID}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa bản ghi này?')"><i class="icon-remove icon-white"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Update Category Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Loại sản phẩm </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div class="box">
                        <header>
                            <div class="icons"><i class="icon-th-large"></i></div>
                            <h5>Màn hình cập nhập dữ liệu</h5>
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
                            <spring:form action="updateCategory.htm" class="form-horizontal" commandName="cate" method="post">
                                <spring:hidden path="categoryID"/>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Tên Danh Mục</label>
                                    <div class="col-lg-10">
                                        <spring:input path="name" type="text" id="name" name="name" placeholder="" class="form-control"/>
                                        <spring:errors path="name" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Danh Mục Cha</label>
                                    <div class="col-lg-10">
                                        <spring:input path="parenId" type="text" id="parenId" name="parenId" placeholder="" class="form-control"/>
                                        <spring:errors path="parenId" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group" hidden="">
                                    <label class="control-label col-lg-2">Tài khoản đăng</label>
                                    <div class="col-lg-10">
                                        <spring:select path="userID" class="form-control" name="userID">
                                            <spring:options items="${listUser}" itemLabel="name" itemValue="userID"/>                                           
                                        </spring:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Thứ Tự Hiển Thị</label>
                                    <div class="col-lg-10">
                                        <spring:input path="displayOrder" type="number" id="displayOrder" name="displayOrder" placeholder="" class="form-control"/>
                                        <spring:errors path="displayOrder" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Ngày Tạo</label>
                                    <div class="col-lg-10">
                                        <spring:input path="createDate" type="date" readonly="true" id="today" name="createDate" placeholder="" class="form-control"/>
                                        <spring:errors path="createDate" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-2">Trạng Thái</label>
                                    <div class="col-lg-10" style="padding-top: 8px;">
                                        <spring:radiobutton path="status" value="false" checked="true"/> Kích hoạt
                                        &nbsp;&nbsp;
                                        <spring:radiobutton path="status" value="true"/> Khóa
                                        <spring:errors path="status" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <a href="listCate.htm?page=1" type="button" class="btn btn-default">Quay lại</a>
                                    <input type="submit" class="btn btn-primary" value="Cập nhật" />
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>
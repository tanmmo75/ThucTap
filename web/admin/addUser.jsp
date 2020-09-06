<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Insert User Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Tài khoản </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div class="box">
                        <header>
                            <div class="icons"><i class="icon-th-large"></i></div>
                            <h5>Màn hình nhập dữ liệu</h5>
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
                            <spring:form action="addUser.htm" class="form-horizontal" commandName="u" method="post">
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Tài khoản</label>
                                    <div class="col-lg-10">
                                        <spring:input path="username" type="text" id="username" name="username" placeholder="" class="form-control"/>
                                        <spring:errors path="username" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Mật khẩu</label>
                                    <div class="col-lg-10">
                                        <spring:input path="password" type="password" id="password" name="password" placeholder="" class="form-control"/>
                                        <spring:errors path="password" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Tên người dùng</label>
                                    <div class="col-lg-10">
                                        <spring:input path="name" type="text" id="name" name="name" placeholder="" class="form-control"/>
                                        <spring:errors path="name" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Địa chỉ</label>
                                    <div class="col-lg-10">
                                        <spring:input path="address" type="text" id="address" name="address" placeholder="" class="form-control"/>
                                        <spring:errors path="address" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Email</label>
                                    <div class="col-lg-10">
                                        <spring:input path="email" type="text" id="email" name="email" placeholder="" class="form-control"/>
                                        <spring:errors path="email" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Điện thoại</label>
                                    <div class="col-lg-10">
                                        <spring:input path="phone" type="text" id="phone" name="phone" placeholder="" class="form-control"/>
                                        <spring:errors path="phone" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Ngày Tạo</label>
                                    <div class="col-lg-10">
                                        <spring:input path="createDate" type="date" id="today" name="createDate" readonly="true" placeholder="" class="form-control"/>
                                        <spring:errors path="createDate" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Nhóm Quyền</label>
                                    <div class="col-lg-10">
                                        <spring:select path="userGroupID" class="form-control" name="userGroupID">
                                            <spring:options items="${listUserGroup}" itemLabel="name" itemValue="userGroupID"/>                                           
                                        </spring:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Trạng thái</label>
                                    <div class="col-lg-10" style="padding-top: 8px;">
                                        <spring:radiobutton path="status" value="false" checked="true"/> Kích hoạt
                                        &nbsp;&nbsp;
                                        <spring:radiobutton path="status" value="true"/> Khóa
                                    </div>
                                </div>
                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <a href="listU.htm?page=1" type="button" class="btn btn-default">Quay lại</a>
                                    <input type="submit" class="btn btn-primary" value="Lưu" />
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>
<script>
    document.querySelector("#today").valueAsDate = new Date();
</script>
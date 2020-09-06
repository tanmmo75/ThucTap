<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="register" tagdir="/WEB-INF/tags" %>
<register:layoutclientdetail title="Register Page">   
    <jsp:attribute name="content">
        <div id="sidebar" class="span3">
            <jsp:include page="mycartIndex.jsp"></jsp:include>
                <ul id="sideManu" class="nav nav-tabs nav-stacked">
                <c:forEach items="${listC}" var="listC">
                    <li>
                        <a href="productClientByCategory.htm?categoryID=${listC.categoryID}">${listC.name}</a>
                    </li>
                </c:forEach>
            </ul>
            <br/>
            <jsp:include page="topviewIndex.jsp"></jsp:include>
                <div class="thumbnail">
                    <img src="themes/images/payment_methods.png" title="Bootshop Payment Methods" alt="Payments Methods">
                    <div class="caption">
                        <h5>Phương thức thanh toán</h5>
                    </div>
                </div>
            </div>
            <div class="span9">
                <ul class="breadcrumb">
                    <li><a href="indexClient.htm">Home</a> <span class="divider">/</span></li>
                    <li class="active">Cập nhật</li>
                </ul>
                <h3> Cập nhật tài khoản ${notificationsSuccessfully} ${notificationsError}</h3>
            <div class="well">
                <spring:form action="updateIndexUser.htm" class="form-horizontal" commandName="u" method="post">
                    <h4>Thông tin cá nhân</h4>
                    <spring:hidden path="userID"/>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Tên đăng nhập <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:input path="username" style="width:90%" type="text" id="username" name="username" placeholder="Tên đăng nhập" class="form-control"/><br>
                            <spring:errors path="username" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Mật khẩu <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:input path="password" style="width:90%" type="password" id="password" name="password" placeholder="Mật khẩu" class="form-control"/><br>
                            <spring:errors path="password" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Họ tên <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:input path="name" style="width:90%" type="text" id="name" name="name" placeholder="Họ tên" class="form-control"/><br>
                            <spring:errors path="name" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Địa chỉ <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:input path="address" style="width:90%" type="text" id="address" name="address" placeholder="Địa chỉ" class="form-control"/><br>
                            <spring:errors path="address" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Email <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:input path="email" style="width:90%" type="text" id="email" name="email" placeholder="Email" class="form-control"/><br>
                            <spring:errors path="email" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Số điện thoại <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:input path="phone" style="width:90%" type="text" id="phone" name="phone" placeholder="Số điện thoại" class="form-control"/><br>
                            <spring:errors path="phone" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputFname1">Ngày tạo</label>
                        <div class="controls">
                            <spring:input path="createDate" style="width:90%" type="date" id="today" name="createDate" readonly="true" placeholder="" class="form-control"/><br>
                            <spring:errors path="createDate" cssStyle="color:red;"/>
                        </div>
                    </div>
                    <div class="control-group" hidden="">
                        <label class="control-label" for="inputFname1">Quyền <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:radiobutton path="userGroupID" value="5" checked="true"/>
                        </div>
                    </div>
                    <div class="control-group" hidden="">
                        <label class="control-label" for="inputFname1">Trạng thái <sup style="color: red;">(*)</sup></label>
                        <div class="controls">
                            <spring:radiobutton path="status" value="false" checked="true"/> Kích hoạt
                            &nbsp;&nbsp;
                            <spring:radiobutton path="status" value="true"/> Khóa
                        </div>
                    </div>
                    <div class="control-group" style="text-align: center;">
                        <div class="controls">
                            <a href="indexClient.htm" class="btn btn-danger">Quay lại</a>
                            <input class="btn btn-success" id="btn" type="submit" value="Cập nhật" />
                        </div>
                    </div>		
                </spring:form>
            </div>
        </div>
    </jsp:attribute>
</register:layoutclientdetail>
<script>
    document.querySelector("#today").valueAsDate = new Date();
</script>
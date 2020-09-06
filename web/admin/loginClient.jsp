<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="viewcart" tagdir="/WEB-INF/tags" %>
<viewcart:layoutclientcategory title="Login Page">   
    <jsp:attribute name="content">
        <div id="sidebar" class="span3">
                <ul id="sideManu" class="nav nav-tabs nav-stacked">
                <c:forEach items="${listC}" var="listC">
                    <li>
                        <a href="productClientByCategory.htm?categoryID=${listC.categoryID}">${listC.name}</a>
                    </li>
                </c:forEach>
            </ul>
            <br/>
            <jsp:include page="topviewIndex.jsp"></jsp:include>
                
            </div>
            <div class="span9">
                <ul class="breadcrumb">
                    <li><a href="indexClient.htm">Home</a> <span class="divider">/</span></li>
                    <li class="active">Đăng nhập</li>
                </ul>	
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th> Màn hình đăng nhập <span style="float: right; color: red;">${message}</span></th>
                    </tr>
                    <tr> 
                        <td>
                            <spring:form action="loginClient.htm" commandName="lg" class="form-horizontal" id="form" method="post">
                                <div class="control-group">
                                    <label class="control-label" for="inputUsername">Tài khoản</label>
                                    <div class="controls">
                                        <spring:input path="username" type="text" id="username" name="username" placeholder="Tên tài khoản" autofocus="true"/>
                                        <spring:errors path="username" cssStyle="color:red;white-space: nowrap;"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="inputPassword1">Mật khẩu</label>
                                    <div class="controls">
                                        <spring:input path="password" type="password" id="password" name="password" placeholder="Mật khẩu"/>
                                        <spring:errors path="password" cssStyle="color:red;white-space: nowrap;"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <button type="submit" class="btn">Đăng nhập</button>  
                                        <input type="button" id="btn" value="Làm mới" class="btn"/>
                                    </div>
                                </div>
                            </spring:form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </jsp:attribute>
</viewcart:layoutclientcategory>
<script>
    $(document).ready(function () {
        $("#btn").click(function () {
            $("#username").focus();
            $("#form")[0].reset();
        });
    });
</script>
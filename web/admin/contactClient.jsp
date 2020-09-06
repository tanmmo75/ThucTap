<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="contact" tagdir="/WEB-INF/tags" %>
<contact:layoutclientcategory title="Contact Page">   
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
                    <li class="active">Liên hệ</li>
                </ul>
                <h3> Liên hệ </h3>	
                <hr class="soft"/>
                <div class="tab-content">
                    <div class="tab-pane active" id="blockView">
                        <div class="col-lg-12">
                        <c:forEach items="${listContact}" var="p">
                            ${p.content}
                        </c:forEach>
                    </div>
                    <div class="col-lg-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>Phản hồi <span style="float: right; color: red;">${notificationsSuccessfully} ${notificationsError} </span></th>
                                </tr>
                                <tr> 
                                    <td>
                                        <spring:form action="addFeedBack.htm" class="form-horizontal" commandName="fb" method="post">
                                            <div class="control-group">
                                                <label class="control-label" for="inputCountry">Họ và tên <span style="color: red;">(*)</span> </label>
                                                <div class="controls">
                                                    <spring:input path="name" style="width: 90%;" type="text" id="name" name="name" placeholder="Họ và tên" class="form-control"/><br>
                                                    <spring:errors path="name" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="inputPost">Số điện thoại <span style="color: red;">(*)</span> </label>
                                                <div class="controls">
                                                    <spring:input path="phone" style="width: 90%;" type="text" id="phone" name="phone" placeholder="Số điện thoại" class="form-control"/><br>
                                                    <spring:errors path="phone" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="inputCountry">Email <span style="color: red;">(*)</span> </label>
                                                <div class="controls">
                                                    <spring:input path="email" style="width: 90%;" type="text" id="email" name="email" placeholder="Email" class="form-control"/><br>
                                                    <spring:errors path="email" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="inputPost">Địa chỉ <span style="color: red;">(*)</span> </label>
                                                <div class="controls">
                                                    <spring:input path="address" style="width: 90%;" type="text" id="address" name="address" placeholder="Địa chỉ" class="form-control"/><br>
                                                    <spring:errors path="address" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="inputPost">Nội dung <span style="color: red;">(*)</span> </label>
                                                <div class="controls">
                                                    <spring:textarea path="content" type="text" id="content" rows="6" style="width: 90%;" name="content" placeholder="Nội dung" class="form-control"></spring:textarea><br>
                                                    <spring:errors path="content" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="inputCountry">Ngày gửi phản hồi </label>
                                                <div class="controls">
                                                    <spring:input path="createDate" style="width: 90%;" type="date" id="today" readonly="true" name="createDate" placeholder="" class="form-control"/>
                                                    <spring:errors path="createDate" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group" hidden="">
                                                <label class="control-label" for="inputPost">Trạng thái </label>
                                                <div class="controls">
                                                    <spring:radiobutton path="status" value="false"/> Kích hoạt
                                                    &nbsp;&nbsp;
                                                    <spring:radiobutton path="status" value="true" checked="true"/> Khóa
                                                    <spring:errors path="status" cssStyle="color:red;"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <div class="controls">
                                                    <input type="submit" class="btn" value="Gửi phản hồi">
                                                </div>
                                            </div>
                                        </spring:form>			  
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <hr class="soft"/>
            </div>
        </div>
    </jsp:attribute>
</contact:layoutclientcategory>
<script>
    document.querySelector("#today").valueAsDate = new Date();
</script>

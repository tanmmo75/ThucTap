<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="about" tagdir="/WEB-INF/tags" %>
<about:layoutclientcategory title="About Page">   
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
                    <li class="active">Giới thiệu</li>
                </ul>
                <h3> Giới thiệu </h3>	
                <hr class="soft"/>
                <div class="tab-content">

                <c:forEach items="${listAbout}" var="p">
                    <div class="span4" style="margin-left: 0px; margin-bottom: 20px; margin-right: 35px;">
                        <img src="${p.image}" />
                    </div>
                    <div class="span4">
                        ${p.detail}
                    </div>
                </c:forEach>


                <div class="col-lg-12">
                    <iframe style="width:100%; height:300px; border: 0px" scrolling="no" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4672.17974869361!2d105.77187153682115!3d10.031773606523508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31a0883d0dac6b15%3A0xf6ae5b1bd18625!2zVHLGsOG7nW5nIMSQ4bqhaSBI4buNYyBD4bqnbiBUaMah!5e0!3m2!1svi!2s!4v1591965318296!5m2!1svi!2s"></iframe><br>
                    <small><a href="https://www.google.com/maps/place/Tr%C6%B0%E1%BB%9Dng+%C4%90%E1%BA%A1i+H%E1%BB%8Dc+C%E1%BA%A7n+Th%C6%A1/@9.8104743,105.6927189,12z/data=!4m5!3m4!1s0x31a0883d0dac6b15:0xf6ae5b1bd18625!8m2!3d10.0302039!4d105.7721088?hl=vi-VN" style="color:#0000FF;text-align:left">View Larger Map</a></small>
                </div>
                <hr class="soft"/>
            </div>
        </div>
    </jsp:attribute>
</about:layoutclientcategory>

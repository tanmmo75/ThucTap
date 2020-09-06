<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages': ['columnchart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {

        // Create the data table.    
        var data = google.visualization.arrayToDataTable([
            ['Country', 'Area(square km)'],
    <c:forEach items="${listRC}" var="rc"> ['${rc.name}',${rc.value} ], </c:forEach>
        ]);
        // Set chart options
        var options = {
            'title': 'Thống kê sản phẩm tồn kho theo danh mục',
            is3D: true,
            pieSliceText: 'label',
            tooltip: {showColorCode: true},
            'width': 500,
            'height': 300
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart'));
        chart.draw(data, options);
    }
    </script>
    <script type="text/javascript">
        //load the Google Visualization API and the chart
        google.load('visualization', '1', {'packages': ['columnchart']});

        //set callback
        google.setOnLoadCallback(createChart);

        //callback function
        function createChart() {

            //create data table object
            var dataTable = new google.visualization.DataTable();

            //define columns
            dataTable.addColumn('string', 'Quarters 2009');
            dataTable.addColumn('number', 'Sản phẩm');


            //define rows of data
            dataTable.addRows([<c:forEach items="${listReportProducer}" var="ro"> ['${ro.name}',${ro.value} ], </c:forEach>]);

            //instantiate our chart object
            var chart1 = new google.visualization.ColumnChart(document.getElementById('chart1'));

            //define options for visualization
            var options = {width: 520, height: 300, is3D: true, title: 'Thống kê tồn kho sản phẩm theo nhà sản xuất'};

            //draw our chart
            chart1.draw(dataTable, options);

        }
    </script>
    <style>
        .red-bg {
            color: #fff;
            background: #d95043;
            background-color: #d95043;
        }
        .info-box {
            min-height: 140px;
            margin-bottom: 30px;
            padding: 20px;
            color: white;
            -webkit-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.35), 0 3px 1px -1px rgba(0, 0, 0, 0.1);
            -moz-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.35), 0 3px 1px -1px rgba(0, 0, 0, 0.1);
            box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.35), 0 3px 1px -1px rgba(0, 0, 0, 0.1);
        }
        .info-box1 {
            min-height: 140px;
            margin-bottom: 30px;
            padding: 20px;
            color: white;
            -webkit-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.35), 0 3px 1px -1px rgba(0, 0, 0, 0.1);
            -moz-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.35), 0 3px 1px -1px rgba(0, 0, 0, 0.1);
            box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.35), 0 3px 1px -1px rgba(0, 0, 0, 0.1);
        }
        .info-box i {
            display: block;
            height: 100px;
            font-size: 60px;
            line-height: 100px;
            width: 100px;
            float: left;
            text-align: center;
            margin-right: 20px;
            padding-right: 20px;
            color: rgba(255, 255, 255, 0.75);
        }

        .info-box1 i {
            display: block;
            height: 100px;
            font-size: 60px;
            line-height: 100px;
            width: 100px;
            float: left;
            text-align: center;
            margin-right: 20px;
            padding-right: 20px;
            color: rgba(255, 255, 255, 0.75);
        }

        .info-box .count {
            margin-top: 20px;
            font-size: 34px;
            font-weight: 700;
        }

        .info-box1 .count {
            margin-top: 20px;
            font-size: 34px;
            font-weight: 700;
        }

        .info-box .title {
            font-size: 12px;
            text-transform: uppercase;
            font-weight: 600;
        }

        .info-box1 .title {
            font-size: 12px;
            text-transform: uppercase;
            font-weight: 600;
        }

        .blue-bg {
            color: #fff;
            background: #57889c;
            background-color: #57889c;
        }
        .brown-bg {
            color: #fff;
            background: #d1b993;
            background-color: #d1b993;
        }
        .dark-bg {
            color: #fff;
            background: #1a2732;
            background-color: #1a2732;
        }
        .green-bg {
            color: #fff;
            background: #26c281;
            background-color: #26c281;
        }
        .info-box:hover {
            color: yellow;
        }
    </style>
<mt:layout title="Report Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Thống kê </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <a href="listOrdersStatus1.htm">
                    <div class="col-lg-3">
                        <div class="info-box blue-bg">
                            <i class="icon-shopping-cart"></i>
                            <div class="count">
                                ${listRSTDHCHT}
                            </div>
                            <div class="title">Đơn chưa hoàn thành</div>

                        </div>
                    </div>
                </a>
                <a href="listOrdersStatus2.htm">
                    <div class="col-lg-3">
                        <div class="info-box brown-bg">
                            <i class="icon-ambulance"></i>
                            <div class="count">
                                ${listRSTDGDVC}
                            </div>
                            <div class="title">Đơn đang vận chuyển</div>
                        </div>
                    </div>
                </a>
                <a href="listOrdersStatus3.htm">
                    <div class="col-lg-3">
                        <div class="info-box green-bg">
                            <i class="icon-ok"></i>
                            <div class="count">
                                ${listRSTDHDHT}
                            </div>
                            <div class="title">Đơn đã hoàn thành</div>
                        </div>
                    </div>
                </a>
                <a href="listP.htm">
                    <div class="col-lg-3">
                        <div class="info-box dark-bg">
                            <i class="icon-frown"></i>
                            <div class="count">
                                ${listRSTP}
                            </div>
                            <div class="title">Sản Phẩm Tồn Kho</div>
                        </div>
                    </div>
                </a>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="info-box1 red-bg">
                        <i class="icon-usd"></i>
                        <div class="count">
                            Tổng doanh thu hiện tại : <fmt:formatNumber value = "${listROTP}" /> $
                        </div>
                        <div class="title" style="text-align: left;">Tính đến tháng / ngày / năm : <input class="red-bg" style="border: none;" type="date" id="today" readonly="true" ></div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div id="chart"></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div id="chart1"></div>
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
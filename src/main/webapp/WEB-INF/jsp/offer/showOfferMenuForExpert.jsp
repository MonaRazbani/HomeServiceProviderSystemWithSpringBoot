<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/table.css">

</head>

<body>
    <div style="position: relative;">
        <div class="bg-image">
        </div>
        <div class="box" style="position:absolute">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col col-lg-12">
                        <div class="list-group">
                            <table class="table table-striped table-light table-hover ">
                                <tr>
                                    <th>customer name </th>
                                    <th>subService</th>
                                    <th>explanation</th>
                                    <th>suggestedPrice</th>
                                    <th>address</th>
                                    <th>status</th>
                                </tr>
                                <tr>
                                    <td>
                                        ${sessionScope.offerDto.order.customer.firstName }
                                        ${sessionScope.offerDto.order.customer.lastName} </td>
                                    <td>${sessionScope.offerDto.order.subService.name}</td>
                                    <td>${sessionScope.offerDto.order.explanation}</td>
                                    <td>${sessionScope.offerDto.order.suggestedPrice}</td>
                                    <td>${sessionScope.offerDto.order.address.address}</td>
                                    <td>${sessionScope.offerDto.order.status}</td>
                                </tr>
                            </table>
                            <h4>offer info</h4>
                            <table class="table table-striped table-info table-hover">
                                <tr>
                                    <th>suggestedPrice</th>
                                    <th>startDate</th>
                                    <th>suggestedDurationOfService</th>
                                </tr>
                                <tr>
                                    <td>${sessionScope.offerDto.suggestedPrice}</td>
                                    <td>${sessionScope.offerDto.startDate}</td>
                                    <td>${sessionScope.offerDto.suggestedDurationOfService}</td>
                                    <td>${sessionScope.offerDto.status}</td>
                                </tr>
                            </table>

                            <div class="row justify-content-center">
                            <c:if test="${sessionScope.offerDto.status=='WAITING_FOR_ACCEPT'}">
                                    <a href="/offer/editOfferStartDate" type="button"
                                       class=" justify-content-center col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"
                                       id="btnPay"> edit start date </a>

                                <a href="/offer/editOfferSuggestedPrice" type="button"
                                   class=" justify-content-center col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"
                                   id="btnPay"> edit suggested price  </a>
                                <a href="/offer/editOfferSuggestedDurationOfService" type="button"
                                   class=" justify-content-center col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"
                                   id="btnPay"> edit suggested Duration Of Service  </a>

                            </c:if>
                                <c:if test="${sessionScope.offerDto.status=='ACCEPT'}">
                                    <a href="/offer/changeOrderStatus" type="button"
                                       class=" justify-content-center col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"
                                       id="btnPay"> change order status </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>

<%-- 
    Document   : purchaseHistory
    Created on : Jan 11, 2021, 8:23:42 AM
    Author     : ngvba
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HanaShop</title>
    </head>
    <body>

        <c:set var="role" value="${sessionScope.USERROLE}"/>
        <c:if test="${role == 'admin'}">
            <c:redirect url="managerPage"/>
        </c:if>

        <!-- Welcome -->
        <font color ="red">
        <c:if test="${not empty sessionScope.USERFULLNAME}">
            Welcome, ${sessionScope.USERFULLNAME}<br/>
            <form action="logout">
                <input type="submit" value="Logout" />
            </form>
        </c:if>
        <c:if test="${empty sessionScope.USERFULLNAME}">
            <c:redirect url="loginPage"/>
        </c:if>
        </font>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <h1>My Purchases</h1>

        <form action="searchBillByDate" method="POST">
            Search: <input type="text" name="txtSearchName" value="${param.txtSearchName}" /></br>
            <input type="date" name="txtSearchDate" value="${param.txtSearchDate}" /></br>
            <input type="submit" value="Search Bill" /></br>
        </form>

        <c:set var="listMapBillDetail" value="${requestScope.LISTMAPBILLDETAIL}"/>
        <c:if test="${not empty listMapBillDetail}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Code orders</th>
                        <th>Date of purchase</th>
                        <th>Product</th>
                        <th>Total money</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bill" items="${listMapBillDetail}">
                        <tr>
                            <td>${bill.key.billID}</td>
                            <td>${bill.key.dayOfPurchase}</td>
                            <td>
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>Food Name</th>
                                            <th>Amount</th>
                                            <th>Price</th>
                                            <th>Total Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="billDetail" items="${listMapBillDetail[bill.key]}">
                                            <tr>
                                                <td>${billDetail.food.foodName}</td>
                                                <td>${billDetail.amount}</td>
                                                <td>${billDetail.price}$</td>
                                                <td>${billDetail.total}$</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                            <td>${bill.key.total}$</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listMapBillDetail}">
            <h3>No result...</h3>
        </c:if>

        <a href="cartPage">Return to cart</a></br>
        <a href="shoppingPage">Go to shopping</a>

    </body>
</html>

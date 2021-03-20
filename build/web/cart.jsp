<%-- 
    Document   : cart
    Created on : Jan 9, 2021, 3:42:19 PM
    Author     : ngvba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hanashop</title>
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
            <a href="loginPage">Click here to login</a>
        </c:if>
        </font>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <h1>Cart</h1>
        <c:set var="cart" value="${sessionScope.CUSTCART}"/>
        <c:if test="${not empty cart}">
            <c:set var="foods" value="${cart.foods}"/>
            <c:if test="${not empty foods}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Food Name</th>
                            <th>Amount</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="food" items="${foods}" varStatus="counter">
                        <form action="motifyCart" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>${food.food.foodName}</td>
                                <td>
                                    <input type="number" min="1" max="999999" size="1" name="txtAmount" value="${food.amount}" required="required"/>
                                    <input type="submit" value="Update Amount" name="btAction" />
                                </td>
                                <td>${food.price}</td>
                                <td>${food.total}</td>
                                <td>
                                    <input type="submit" value="Delete Food" onclick="return confirm('Are you sure you want to delete?')" name="btAction"/>
                                    <input type="hidden" name="txtFoodID" value="${food.food.foodID}" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <tr>
                        <td colspan="3"></td>
                        <td colspan="2">
                            Total: ${cart.totalPrice}
                        </td>
                    </tr>
                </tbody>
            </table>
            <form action="checkOutPage">
                <input type="submit" value="Check out"/>
            </form> 
        </c:if>
    </c:if>

    <c:if test="${empty cart}">
        <font color="red">
        <h4>Cart is currently empty</h4>
        </font>
    </c:if>

    <a href="/L1_HanaShop_Rebuild">Click here to go to shopping</a>
    
</body>
</html>

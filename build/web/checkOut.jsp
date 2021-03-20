<%-- 
    Document   : checkOut
    Created on : Jan 10, 2021, 8:44:27 PM
    Author     : ngvba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${empty sessionScope.USERFULLNAME}">
            <c:redirect url="loginPage"/>
        </c:if>

        <!-- Welcome -->
        <font color ="red">
        <c:if test="${not empty sessionScope.USERFULLNAME}">
            Welcome, ${sessionScope.USERFULLNAME}<br/>
            <form action="logout">
                <input type="submit" value="Logout" />
            </form>
        </c:if>
        </font>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <h1>Check Out</h1>

        <form action="checkOut" method="POST">
            <h3>Shipping Info</h3>
            <c:set var="user" value="${sessionScope.USER}"/>
            <c:set var="error" value="${requestScope.ERRORS}" />
            Full name: <input type="text" name="txtFullName" value="${user.fullname}" maxlength="100"/></br>
            <c:if test="${not empty error.fullnameLengthError}">
                <font color="red">
                ${error.fullnameLengthError}<br/>
                </font>
            </c:if>

            Phone number: <input type="number" name="txtPhone" value="${user.phone}" maxlength="10"/></br>
            <c:if test="${not empty error.phoneLengthError}">
                <font color="red">
                ${error.phoneLengthError}<br/>
                </font>
            </c:if>

            Email: <input type="email" name="txtEmail" value="${user.email}" maxlength="50" /></br>
            <c:if test="${not empty error.emailLengthError}">
                <font color="red">
                ${error.emailLengthError}<br/>
                </font>
            </c:if>

            Address: <input type="text" name="txtAddress" value="${user.address}" minlength="5" maxlength="250" /></br>
            <c:if test="${not empty error.addressLengthError}">
                <font color="red">
                ${error.addressLengthError}<br/>
                </font>
            </c:if>


            <h3>Product</h3>
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
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="food" items="${foods}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${food.food.foodName}
                                    </td>
                                    <td>
                                        ${food.amount}
                                    </td>
                                    <td>
                                        ${food.price}
                                    </td>
                                    <td>
                                        ${food.total}
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
                </c:if>
            </c:if>

            <c:set var="outOfStock" value="${requestScope.OUTOFSTOCK}"/>
            <c:if test="${not empty outOfStock}">
                <font color="red">
                <c:forEach var="food" items="${outOfStock}">
                    ${food.key.foodName} is out of stock (${food.value})<br/>
                </c:forEach>
                </font>
            </c:if>

            <h3>Payment</h3>
            Payment Option: <select name="paymentOption">
                <option>Cash on delivery (COD)</option>
            </select></br>

            <h3>Confirm to buy</h3>
            <input type="submit" value="Confirm" /></br>
            <a href="cartPage">Return to cart</a>
        </form>
    </body>
</html>

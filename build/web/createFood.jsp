<%-- 
    Document   : createFood
    Created on : Jan 8, 2021, 7:58:50 PM
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
        <c:if test="${role != 'admin'}">
            <c:redirect url="loginPage"/>
        </c:if>


        <!-- Welcome -->
        <font color ="red">
        <c:if test="${not empty sessionScope.USERFULLNAME}">
            Welcome, ${sessionScope.USERFULLNAME}<br/>
            <form action="logout">
                <input type="submit" value="Logout"/>
            </form>
        </c:if>
        </font>
        <c:if test="${empty sessionScope.USERFULLNAME}">
            <c:redirect url="loginPage"/>
        </c:if>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <c:set var="err" value="${requestScope.ERRORS}"/>
        <h3>Create new food</h3>
        <c:set var="newFood" value="${requestScope.NEWFOOD}"/>
        <form action="createFood" method="POST" enctype="multipart/form-data">
            Food ID: <input type="text" name="txtFoodID" value="${newFood.foodID}" maxlength="20" /></br>
            <c:if test="${not empty err.foodIDIsExited}">
                <font color="red">
                ${err.foodIDIsExited}<br/>
                </font>
            </c:if>
                
            Food Name: <input type="text" name="txtFoodName" value="${newFood.foodName}" maxlength="30" /></br>
            <c:if test="${not empty err.foodNameLengthError}">
                <font color="red">
                ${err.foodNameLengthError}<br/>
                </font>
            </c:if>

            Image: <input type="file" name="file" value="" accept=".jpg, .png, .jpeg" /></br>
            <c:if test="${not empty err.foodImageIsNotAPicture}">
                <font color="red">
                ${err.foodImageIsNotAPicture}<br/>
                </font>
            </c:if>

            Description: <input type="text" name="txtDescription" value="${newFood.description}" maxlength="250" /></br>
            <c:if test="${not empty err.descriptionLengthError}">
                <font color="red">
                ${err.descriptionLengthError}<br/>
                </font>
            </c:if>

            Price: <input type="number" name="txtPrice" value="${newFood.price}" min="0.01" step="0.01" max="1000" /></br>
            <c:if test="${not empty err.priceLengthError}">
                <font color="red">
                ${err.priceLengthError}<br/>
                </font>
            </c:if>

            Category: <input type="text" name="txtCategory" value="${newFood.category}" maxlength="20" /></br>

            <c:if test="${not empty err.categoryLengthError}">
                <font color="red">
                ${err.categoryLengthError}<br/>
                </font>
            </c:if>
            Quantity: <input type="number" name="txtQuantity" value="${newFood.quantity}" max="9999" min ="0" /></br>
            <c:if test="${not empty err.quantityLengthError}">
                <font color="red">
                ${err.quantityLengthError}<br/>
                </font>
            </c:if>
                
            <input type="submit" value="Create Food" />
        </form>

        <a href="/L1_HanaShop_Rebuild">Go back to manager page</a>
    </body>
</html>

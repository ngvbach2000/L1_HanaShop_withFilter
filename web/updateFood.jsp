<%-- 
    Document   : updateFood
    Created on : Jan 11, 2021, 12:42:39 PM
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
        <c:if test="${role != 'admin'}">
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
        <c:if test="${empty sessionScope.USERFULLNAME}">
            <c:redirect url="loginPage"/>
        </c:if>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <h2>Update Food</h2>

        <c:set var="err" value="${requestScope.ERRORS}"/>
        <c:set var="food" value="${requestScope.UPDATEVALUES}"/>
        <c:if test="${not empty food}">
            <form action="updateFood" method="POST" enctype="multipart/form-data">
                Food Name: <input type="text" name="txtFoodName" value="${food.foodName}" maxlength="30" /></br>
                <c:if test="${not empty err.foodNameLengthError}">
                    <font color="red">
                    ${err.foodNameLengthError}<br/>
                    </font>
                </c:if>

                Image: <img src="${food.image}" width="100" height="100"/></br>
                <input type="hidden" name="txtImage" value="${food.image}" />
                <input type="file" name="file" value="" accept=".jpg, .png, .jpeg"/></br>
                Description: <input type="text" name="txtDescription" value="${food.description}" maxlength="250" /></br>
                <c:if test="${not empty err.descriptionLengthError}">
                    <font color="red">
                    ${err.descriptionLengthError}<br/>
                    </font>
                </c:if>

                Price: <input type="number" step="0.01" name="txtPrice" value="${food.price}" min="0.01" max="1000" />$</br>
                <c:if test="${not empty err.priceLengthError}">
                    <font color="red">
                    ${err.priceLengthError}<br/>
                    </font>
                </c:if>

                Category: <select name="txtCategory">
                    <c:set var="ListCategory" value="${sessionScope.LISTFULLCATEGORY}"/>
                    <c:if test="${not empty ListCategory}">
                        <c:forEach var="category" items="${ListCategory}" varStatus="counter"> 
                            <option ${category == food.category ? 'selected':''}>${category}</option>
                        </c:forEach>
                    </c:if>
                </select></br>
                Status: <select name="ddlStatus">
                    <c:if test="${food.status}">
                        <option>active</option>
                        <option>inactive</option>
                    </c:if>
                    <c:if test="${!food.status}">
                        <option>inactive</option>
                        <option>active</option>
                    </c:if>
                </select></br>
                Quantity: <input type="number" name="txtQuantity" min="0" max="9999" value="${food.quantity}" /></br>
                <c:if test="${not empty err.quantityLengthError}">
                    <font color="red">
                    ${err.quantityLengthError}<br/>
                    </font>
                </c:if>

                <input type="submit" value="Update" />
                <input type="hidden" name="txtFoodID" value="${food.foodID}" />
                <input type="hidden" name="currentPage" value="${param.currentPage}" />
                <input type="hidden" name="searchFood" value="${param.searchFood}" />
                <input type="hidden" name="searchCategory" value="${param.searchCategory}" />
                <input type="hidden" name="searchPriceFrom" value="${param.searchPriceFrom}" />
                <input type="hidden" name="searchPriceTo" value="${param.searchPriceTo}" />
            </form>

        </c:if>

    </body>
</html>

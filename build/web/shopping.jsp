<%-- 
    Document   : search
    Created on : Jan 4, 2021, 6:46:25 PM
    Author     : ngvba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <a href="loginPage">Click here to login</a>
        </c:if>
        </font>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <form action="viewPurchaseHistory">
            <input type="submit" value="View Purchase History" />
        </form>

        <a href="cartPage"><h3>Go to cart</h3></a>

        <!-- search -->
        <form action="searchFood">
            <!-- search name text field -->
            Search: <input type="text" name="txtSearchFood" value="${param.txtSearchFood}" maxlength="30"/></br>

            <!-- category search drop down list -->
            Category: <select name="DDLCategory">
                <option value="">--chose category</option>
                <c:set var="ListCategory" value="${sessionScope.LISTCATEGORY}"/>
                <c:if test="${not empty ListCategory}">
                    <c:forEach var="category" items="${ListCategory}" varStatus="counter"> 
                        <option ${category == param.DDLCategory ? 'selected':''}>${category}</option>
                    </c:forEach>
                </c:if>
            </select></br>

            <!-- search by range of price -->
            Price: <input type="number" min ="0" step="0.01" max="100" name="txtPriceFrom" value="${param.txtPriceFrom}" size="1"/>
            - <input type="number" min="0" max="100" step="0.01" name="txtPriceTo" value="${param.txtPriceTo}" size="1"/></br>
            <input type="submit" value="Search" />
        </form></br>

        <!-- show 20 food -->
        <c:set var="ListFood" value="${requestScope.LISTFOOD}"/>
        <c:if test="${not empty ListFood}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Create Date</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="food" items="${ListFood}" varStatus="counter">
                    <form action="addFoodToCart" method="POST">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${food.foodName}
                            </td>
                            <td>
                                <img src="${food.image}" width="100" height="100"/>
                            </td>
                            <td>
                                ${food.description}
                            </td>
                            <td>
                                ${food.price}$
                            </td>
                            <td>
                                ${food.category}
                            </td>
                            <td>
                                ${food.createDate}
                            </td>
                            <td>
                                <input type="submit" value="Add to cart" />
                                <input type="hidden" name="txtFoodID" value="${food.foodID}" />
                                <input type="hidden" name="lastSearchNameValue" value="${param.txtSearchFood}" />
                                <input type="hidden" name="lastSearchCategoryValue" value="${param.DDLCategory}"" />
                                <input type="hidden" name="lastSearchPriceFormValue" value="${param.txtPriceFrom}" />
                                <input type="hidden" name="lastSearchPriceToValue" value="${param.txtPriceTo}" />
                                <input type="hidden" name="currentPage" value="${requestScope.CURRENTPAGE}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- no result text -->
    <c:if test="${empty ListFood}">
        <h2>No result...</h2>
    </c:if>
    </br>

    <!-- paging food -->
    <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>
    <c:set var="noOfPage" value="${requestScope.NOOFPAGE}"/>

    <c:url var="previousPage" value="" >
        <c:param name="txtSearchFood" value="${param.txtSearchFood}"/>
        <c:param name="DDLCategory" value="${param.DDLCategory}" />
        <c:param name="txtPriceFrom" value="${param.txtPriceFrom}" />
        <c:param name="txtPriceTo" value="${param.txtPriceTo}" />
        <c:param name="page" value="${currentPage - 1}"/>
    </c:url>
    <c:if test="${currentPage != 1}">
        <a href="${previousPage}">Previous</a>
    </c:if>

    <c:url var="nextPage" value="" >
        <c:param name="txtSearchFood" value="${param.txtSearchFood}"/>
        <c:param name="DDLCategory" value="${param.DDLCategory}" />
        <c:param name="txtPriceFrom" value="${param.txtPriceFrom}" />
        <c:param name="txtPriceTo" value="${param.txtPriceTo}" />
        <c:param name="page" value="${currentPage + 1}"/>
    </c:url>
    <c:if test="${currentPage < noOfPage}">
        <a href="${nextPage}">Next</a>
    </c:if>

    <table border="0">
        <tr>
            <c:forEach begin="1" end="${noOfPage}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <c:url var="choosePage" value="" >
                            <c:param name="txtSearchFood" value="${param.txtSearchFood}"/>
                            <c:param name="DDLCategory" value="${param.DDLCategory}" />
                            <c:param name="txtPriceFrom" value="${param.txtPriceFrom}" />
                            <c:param name="txtPriceTo" value="${param.txtPriceTo}" />
                            <c:param name="page" value="${i}"/>
                        </c:url>
                        <td> <a href="${choosePage}">${i}</a> </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

</body>
</html>

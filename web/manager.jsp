<%-- 
    Document   : manager
    Created on : Jan 4, 2021, 6:56:14 PM
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
    </font>
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
                <input type="submit" value="Logout" name="btAction"/>
            </form>
        </c:if>
        </font>
        <c:if test="${empty sessionScope.USERFULLNAME}">
            <c:redirect url="loginPage"/>
        </c:if>

        <a href="/L1_HanaShop_Rebuild"><h1>HanaShop</h1></a>

        <!-- search -->
        <form action="searchFullFood">
            <!-- search name text field -->
            Search: <input type="text" name="txtSearchFood" value="${param.txtSearchFood}" maxlength="30"/></br>

            <!-- category search drop down list -->
            Category: <select name="DDLCategory">
                <option value="">--chose category</option>
                <c:set var="ListCategory" value="${sessionScope.LISTFULLCATEGORY}"/>
                <c:if test="${not empty ListCategory}">
                    <c:forEach var="category" items="${ListCategory}" varStatus="counter"> 
                        <option ${category == param.DDLCategory ? 'selected':''}>${category}</option>
                    </c:forEach>
                </c:if>
            </select></br>

            <!-- search by range of price -->
            Price: <input type="number" min ="0" step="0.01" max="100" name="txtPriceFrom" value="${param.txtPriceFrom}" size="1"/>
            - <input type="number" min="0" max="100" step="0.01" name="txtPriceTo" value="${param.txtPriceTo}" size="1"/></br>
            <input type="submit" value="Search Food" />
        </form></br>

        <a href="createFoodPage">Create new Food</a></br>

        <c:set var="ListFullFood" value="${requestScope.LISTFULLFOOD}"/>
        <c:if test="${not empty ListFullFood}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Quantity</th>
                        <th>Create Date</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                <form action="deleteFood" method="POST">
                    <c:forEach var="food" items="${ListFullFood}" varStatus="counter">
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
                            <td>${food.createDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${food.status}">
                                        active
                                    </c:when>
                                    <c:otherwise>
                                        inactive
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${food.quantity}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${food.status}">
                                        <input type="checkbox" name="ckbDeleteFood" value="${food.foodID}" />
                                    </c:when>
                                    <c:otherwise>
                                        Deleted
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>
                                <c:url var="updateValues" value="updateValues">
                                    <c:param name="foodID" value="${food.foodID}"/>
                                    <c:param name="currentPage" value="${requestScope.CURRENTPAGE}" />
                                    <c:param name="searchFood" value="${param.txtSearchFood}" />
                                    <c:param name="searchCategory" value="${param.DDLCategory}" />
                                    <c:param name="searchPriceFrom" value="${param.txtPriceFrom}" />
                                    <c:param name="searchPriceTo" value="${param.txtPriceTo}" />
                                </c:url>
                                <a href="${updateValues}">Update</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <td colspan="9"></td>
                    <td>
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete?')"/>
                        <input type="hidden" name="currentPage" value="${requestScope.CURRENTPAGE}" />
                        <input type="hidden" name="searchFood" value="${param.txtSearchFood}" />
                        <input type="hidden" name="searchCategory" value="${param.DDLCategory}" />
                        <input type="hidden" name="searchPriceFrom" value="${param.txtPriceFrom}" />
                        <input type="hidden" name="searchPriceTo" value="${param.txtPriceTo}" />
                    </td>
                    <td></td>
                </form>
            </tbody>
        </table>
    </c:if>

    <!-- no result text -->
    <c:if test="${empty ListFullFood}">
        <h2>No result...</h2>
    </c:if>

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

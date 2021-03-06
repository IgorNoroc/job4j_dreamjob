<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <style type="text/css">
        #h { border: black; color: aquamarine}
    </style>
    <title>Работа мечты</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <label>
                <div class="row">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">
                                <div class="container">
                                    <div class="topright"><c:out value="${user.name}"/> | Выйти</div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </label>
            <div class="card-header">
                Кандидаты
            </div>
            <div class="card-body">
                <table class="table" style="background-color: antiquewhite">
                    <thead>
                    <tr>
                        <th scope="col">Названия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${candidates}" var="can">
                        <tr>
                            <td>
                                <a href='<c:url value="/candidate/edit.jsp?id=${can.id}"/>'>
                                    <i class="fa fa-edit mr-3"></i>
                                </a>
                                <c:out value="${can.name}"/>
                            </td>
                            <td>
                                <label>
                                    <c:forEach items="${cities}" var="city">
                                        <c:if test="${can.cityId == city.id}">
                                        <c:out value="${city.name}"/>
                                        </c:if>
                                    </c:forEach>
                                </label>
                            </td>
                            <td>
                                <a href="<c:url value='/download?photo=${can.photo}'/>">Download</a>
                            </td>
                            <td>
                                <img src="<c:url value='/download?photo=${can.photo}'/>" width="200px" height="200px"/>
                            </td>
                            <td>
                                <a href="<c:url value='/candidate/upload_image.jsp?id=${can.id}'/>">Upload Candidate's
                                    image</a>
                            </td>
                            <td>
                                <a href="<c:url value='/deleteCandidate?id=${can.id}'/>">Delete Candidate</a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <h5 id="h"><a href="<%=request.getContextPath()%>/posts.do">Home <<<</a></h5>
    </div>
</div>
</body>
</html>

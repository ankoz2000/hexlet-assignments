<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<table>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach var = "user" items="${users}">
        <tr>
            <td>
                <c:out value = "<p href=\"/users/show?id=${user.id}\">${user.id}</p>"/>
            </td>
            <td>
                <c:out value = "${user.lastName}"/>
            </td>
            <td>
                <c:out value = "${user.firstName}"/>
            </td>
            <td>
                <c:out value = "${user.email}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<!-- END -->

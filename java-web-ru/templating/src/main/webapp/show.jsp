<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<table>
    <jsp:useBean id="user" scope="request" type="java.lang.Object"/>
    <tr>
        <td>
            <c:out value = "<p href=\"/users/delete?id=${user.id}\">${user.id}</p>"/>
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
</table>
<!-- END -->

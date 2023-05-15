<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<form action="/users/delete?id=${id}" method="post">
    <p>Удалить пользователя ${lastName} ${firstName}?</p>
    <button type="submit" class="btn btn-danger">Удалить</button>
</form>
<!-- END -->

<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
${error} <br/>
${message} <br/>
${status}
${param.error}
${param.login_error}
<c:if test= "${not empty param.error}">
  <font size= "2" color= "red"><b>Неправильный логин или пароль</b></font>
</c:if>
<div class="container">
        <form c:action="/login" method="post">
            <div><label>ddd User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
<%@ include file="common/footer.jspf"%>
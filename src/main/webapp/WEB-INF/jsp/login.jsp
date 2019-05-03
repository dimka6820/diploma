<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <aside class="col-sm-4">
        <div class="card">
            <article class="card-body">
                <h4 class="card-title text-center mb-4 mt-1">Вход</h4>
                <hr>
                <c:if test="${not empty param.error}">
                    <p class="text-danger text-center"><b>Неправильный логин или пароль</b></p>
                </c:if>
                <form c:action="/login" method="post">
                    <div class="form-group">
                        <label>Логин</label>
                        <input placeholder="Логин" class="form-control" name="username" type="text">
                    </div>

                    <div class="form-group">
                        <label>Пароль</label>
                        <input class="form-control" placeholder="******" type="password" name="password">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Войти</button>
                    </div>
                </form>
            </article>
        </div>

<%@ include file="common/footer.jspf" %>
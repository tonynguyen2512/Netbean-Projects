<div class="row">
    <div class="col-sm-1 col-lg-1"></div>
    <div class="col-sm-10 col-lg-10">
        <div class="row">
            <div class="col-lg-3">
                <a href="index.jsp" title="Goto Home page"><img src="https://4.bp.blogspot.com/-lNPjgF0o0ag/VQc9e6qNP6I/AAAAAAAAE9w/QvPfaMIzdicFof9UmCNDq7SV21-TXQE7ACPcBGAYYCw/s1600/Incredibles%2BLogo.png" height="90"/></a>
            </div>
            <div class="col-lg-7"  style="vertical-align: middle;">
                <br>
                <h2>${param.title} ::</h2>
            </div>
            <div class="col-lg-2 align-bottom align-text-bottom" style="text-align: center">
                <a href="${user!=null ? 'Logout' : 'Login'}" title="Click to ${user!=null ? 'Logout' : 'Login'}">
                    <img  src="https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100" width="20">
                </a>
                <br>
                Welcome ${sessionScope.user==null ? 'Guest !' : user.customername}
                <br>
                <c:if test="${cart != null}">
                    <a href="ViewCart" title="View Your Cart">
                        <img  src="data:image/png;base64,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" width="20">
                    </a>
                    (${cart.size})
                </c:if>
            </div>
        </div>
        <div class="col-sm-1 col-lg-1"></div>
    </div>
</div>
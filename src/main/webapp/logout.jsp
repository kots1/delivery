<%
    session.removeAttribute("role");
    session.removeAttribute("user");
    response.sendRedirect(request.getContextPath());
%>

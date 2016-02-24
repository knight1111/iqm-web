CasFailure


<%
response.setStatus(403);

//获取异常类
Throwable ex = Exceptions.getThrowable(request);

out.print(StringUtils.replace(ex.getMessage(), "msg:", ""));
%>
<%@page import="com.thomsonreuters.common.utils.Exceptions"%>
<%@page import="com.thomsonreuters.common.utils.StringUtils"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>

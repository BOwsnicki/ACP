<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<f:view>
		<h:form>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="Name"></h:outputText>
			<h:inputText value="#{loginBean.name}"></h:inputText>
			<h:outputText value="Password"></h:outputText>
			<h:inputSecret></h:inputSecret>
		</h:panelGrid>

			<h:commandButton action="login" value="login"></h:commandButton>
		</h:form></f:view></body>
</html>
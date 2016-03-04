<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="content" class="container">
	<div>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}/index/dashboard">Dashboard</a></li>
			<li class="active">Gateway</li>
		</ol>
	</div>

	<table id="query_table" class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>PermId</th>
				<th>Value</th>
				<th>EffectiveFrom</th>
				<th>EffectiveTo</th>
			</tr>
		</thead>
	</table>

	<form:form modelAttribute="contentModel" method="post">
		<form:input path="username" />
		<br />
		<form:password path="password" />
		<br />
		<form:checkbox path="testBoolean" />True
		<br />
		<!-- checkbox -->
		<form:checkbox path="testArray" value="arrayItem AAA" />arrayItem AAA
	    <form:checkbox path="testArray" value="arrayItem BBB" />arrayItem BBB
	    <form:checkbox path="testArray" value="arrayItem CCC" />arrayItem CCC
	    <form:checkbox path="testArray" value="arrayItem DDD" />arrayItem DDD
	<br />
		<form:checkboxes path="selectArray" items="${contentModel.testArray}" />
		<br />
		<form:checkboxes path="selectIds" items="${contentModel.testMap}" />
		<br />
		<!-- radiobutton -->
		<form:radiobutton path="radiobuttonId" value="0" />0
	    <form:radiobutton path="radiobuttonId" value="1" />1
	    <form:radiobutton path="radiobuttonId" value="2" />2
<br />
		<form:radiobuttons path="selectId" items="${contentModel.testMap}" />
		<br />
		<!-- select -->
		<form:select path="selectId" items="${contentModel.testMap}" />
		<br />
		<form:select path="selectId">
			<option>Please select</option>
			<form:option value="1">mapItem AAA</form:option>
			<form:option value="2">mapItem BBB</form:option>
			<form:option value="3">mapItem CCC</form:option>
		</form:select>
		<br />
		<form:select path="selectId">
			<option>Please select</option>
			<option value="1">mapItem AAA</option>
			<option value="2">mapItem BBB</option>
			<option value="3">mapItem CCC</option>
		</form:select>
		<br />
		<form:select path="selectId">
			<option />Please select
	        <form:options items="${contentModel.testMap}" />
		</form:select>
		<br />
		<form:textarea path="remark" />
		<br />
		<input type="submit" value="Submit" />
	</form:form>

</div>

<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/api/fnPagingInfo.js"></script>


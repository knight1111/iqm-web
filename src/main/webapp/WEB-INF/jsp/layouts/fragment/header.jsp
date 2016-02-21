<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="header" class="container">
	<h1 id="app" class="pull-left">Warrant</h1>
	<h1 id="branding" class="pull-right">Thomson Reuters</h1>
</div>
<div id="navbar" class="container">
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
					class="icon-bar"></span><span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="../index/dashboard">Dashboard</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Warrant<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="../warrant/gateway">Gateway</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <shiro:principal /> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<shiro:hasRole name="admin">
							<li><a href="../user/list">User Management</a></li>
						</shiro:hasRole>
						<li><a href="../user/find">User Preference</a></li>
						<li class="divider"></li>
						<li><a href="../logout">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
</div>
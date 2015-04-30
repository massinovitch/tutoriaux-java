<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />   
		<link rel="stylesheet" type="text/css" href="../css/corp.css" />
		<title>Gestion Auto-Ecole</title>
		<script type="text/javascript" src="js/main.js"></script>
	</head>
	<body>
		<!-- Page Layout -->
		<table id="RnoPage" class="RnoLayout-HP">
			<tbody>
				<tr>
					<td id="RnoPageWidthRange">
						<!-- Global Links Top -->
						<div id="RnoGlobalLinksTop" class="sc">
							<div>
								<ul>
									<li><a href="#">Contact</a></li>
									<li><a href="#">Aide</a></li>
								</ul>
							</div>
						</div>
						<!-- Page Header -->
						<div id="RnoBranding" class="sc"> 
							<a href="#"><img src="../images/logos/logotype-autoecole.jpg" id="RnoLogo" alt="Gestion Auto-Ecole" /></a>
							<h1 id="RnoApplicationName"><img src="../images/logos/application_title.gif" alt="Gestion Auto-Ecole" /></h1>
							<span><img src="../images/branding-coin.gif" alt="" height="31" width="31" /></span>
						</div>
						<!-- Body Content -->
						<div id="RnoBody">
							<tiles:insert attribute="loginForm" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta http-equiv="Content-Type"	content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />	
		<link rel="stylesheet" type="text/css" href="css/corp.css"  />
		<title>
			Gestion Auto-Ecole
		</title>
	</head>   
	<body>
		<table id="RnoPage" class="RnoLayout-1col">
			<tr>
				<td id="RnoPageWidthRange">
					<tiles:insert attribute="headerBar" />
					<tiles:insert attribute="menuBar" />
					<div id="RnoBody">
						<div id="RnoMainContent" class="sc">
							<tiles:insert attribute="workingArea" />
						</div>
					</div>
					<tiles:insert attribute="footerBar" />
				</td>
			</tr>
		</table>
	</body>
</html>
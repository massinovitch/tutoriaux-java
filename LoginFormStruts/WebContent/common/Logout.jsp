<table>
	<tr>
		<td>
		<table>
			<tr>
				<td height="50"></td>
			</tr>
			<tr>
				<td>Voulez vous vraiment vous déconnecter ?</td>
			<tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<form METHOD="POST" action="<%=request.getContextPath()%>/pages/Login.jsp">
		<table>
			<tr>
				<td height="20">
			</tr>
			<tr>
				<td>
				<ul class="RnoBtn">
					<li class="RnoNoSep"><input type="hidden"
						name="logoutExitPage" value="/pages/Login.jsp" /></li>
				</ul>
				</td>
				<td>
				<ul class="RnoBtn">
					<li class="RnoNoSep"><input type="submit" name="logout"
						value="OUI" /></li>
				</ul>
				</td>
				<td>
				<ul class="RnoBtn">
					<li class="RnoNoSep"><INPUT type="button" name="cancel"
						value="Non"
						onclick="javascript:top.location.href='<%=request.getContextPath()%>/Accueil.do'"></li>
				</ul>
				</td>
			</tr>
		</table>

		</form>
		</td>
	<tr>
</table>

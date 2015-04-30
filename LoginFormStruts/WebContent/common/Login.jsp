<!-- 2-col Layout -->
	<ul class="Rno-2col sc">
		<!-- 2-col Layout Left block -->
		<li class="colA">
			<h2>Utilisateur</h2>

			<div id="RnoHPUser">
			<form name="LoginForm" METHOD="POST" action="<%=request.getContextPath()%>/LoginPublic.do" focus="username">
				<table>
				<tbody><tr>
					<th><label for="RnoIPN"> utilisateur :</label></th>
					<td><input name="username"  type="text" /></td>
				</tr>
				<tr>
					<th><label for="RnoPass">Mot de passe :</label></th>
					<td><input name="password"  type="password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<ul class="RnoBtn">
						<li class="RnoNoSep">
						   <input type="submit" name ="action" value = "Connexion"/> 
						</li>
					</ul>
					</td>
				</tr>
				</tbody></table>
				</form>
			</div>
		</li>
		<!-- /2-col Layout Right block -->
	</ul>
<!-- /2-col Layout -->

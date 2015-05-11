<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<div id="RnoWorkspace">
	<div class="RnoSection"> 
		<h3 class="RnoSectionTitle">
			<span></span>
			<bean:message key="acceuil"/>
		</h3>
		<div class="RnoSectionContent"> 
			<ul>
				<li>
					<a href="FonctionSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="GeoGcdSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="GcdSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="MtcSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="PerimetreSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="PieceSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="3dVisualsSelection.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="RnoSection"> 
		<h3 class="RnoSectionTitle">
			<span></span>
			<bean:message key="acceuil"/>
		</h3>
		<ul>
			<logic:present role="3DM_ADM">
				<li>
					<a href="UpdatePatchworkToBase.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="FichierExtractSigne.do">
						<bean:message key="acceuil"/>
					</a>
				</li>
				<li>
					<a href="ImportGdgSigne.do"><bean:message key="acceuil"/>
					</a>
				</li>
			</logic:present>
			<li>
				<a href="Search.do">
					<bean:message key="acceuil"/>
				</a>
			</li>
		</ul>
	</div>
</div>
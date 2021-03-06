package com.raistudies.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.raistudies.forms.LoginForm;
import com.raistudies.util.AbstractBaseAction;

public class LoginAction extends AbstractBaseAction {

	@Override
	public ActionForward processAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = AbstractBaseAction.SUCCESS;  
		LoginForm loginForm = (LoginForm)form;
		if(loginForm.getUsername() == null || loginForm.getPassword() == null ||
				!loginForm.getUsername().equalsIgnoreCase("rahul") || !loginForm.getPassword().equals("abc")){
			forward = AbstractBaseAction.FAILURE;
		}
		else {
			request.getSession().setAttribute(AbstractBaseAction.SESSION, AbstractBaseAction.SESSION);	
		}

		return mapping.findForward(forward);	
	}

}

package com.raistudies.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.raistudies.util.AbstractBaseAction;

public class AccueilAction extends AbstractBaseAction {
	@Override
	public ActionForward processAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = AbstractBaseAction.SUCCESS;  
		return mapping.findForward(forward);	
	}
}

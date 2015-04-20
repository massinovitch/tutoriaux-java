package com.raistudies.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class AbstractBaseAction extends Action {
	
	/**
	 * The SUCCESS of type String represent the forward identifier when action
	 * succeed
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * The FAILURE of type String represent the forward identifier when action
	 * fail
	 */
	public static final String FAILURE = "failure";	
	
	/**
	 * the session
	 */
	public static final String SESSION = "PRESENT";	

	/**
	 * Basic base implementation for execute struts method. Check security
	 * attributes for the input request , logging and errors handling can be
	 * perform in generic way here.
	 * @param mapping : ActionMapping
	 * @param form : ActionForm
	 * @param request : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {						
		return processAction(mapping, form, request, response);
	}

	/**
	 * This method is overrided by all struts actions
	 * @param mapping : ActionMapping
	 * @param form : ActionForm
	 * @param request : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @return ActionForward
	 */
	public abstract ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
	

}

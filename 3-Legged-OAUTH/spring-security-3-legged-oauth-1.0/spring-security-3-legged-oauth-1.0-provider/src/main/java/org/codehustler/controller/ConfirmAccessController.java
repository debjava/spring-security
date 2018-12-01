package org.codehustler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class maps the /api/confirm_access URL to the confirm_access.jsp page.
 * This is where the User will authorise the Request Token granted to the Consumer.
 *
 * @author Alessandro Giannone
 * @version 1.0
 */
@Controller
public class ConfirmAccessController
{
	public ConfirmAccessController() {}


	@RequestMapping( value = "/oauth/confirm_access", method = RequestMethod.GET )
	public ModelAndView confirmAccess( HttpServletRequest request, HttpServletResponse response )
	{
		String token = request.getParameter( "oauth_token" );
		String callback = request.getParameter( "oauth_callback" );

		if( token == null )
			throw new IllegalArgumentException("No Request Token provided for authorization.");

		ModelAndView mav = new ModelAndView( "confirm_access" );
		mav.addObject( "oauth_token", token );
		mav.addObject( "oauth_callback", callback );
		return mav;
	}
}
/*******************************************************************************
 * Copyright (c) 2011.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     - initial API and implementation
 *******************************************************************************/

package org.restlet.ext.osgi;

import java.util.Dictionary;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpContext;
import org.restlet.Application;
import org.restlet.Context;

/**
 * @author bhunt
 * 
 */
public class ApplicationProvider implements IApplicationProvider
{
	public void bindRouterProvider(IRouterProvider routerProvider)
	{
		this.routerProvider = routerProvider;

		if (application != null)
			application.setInboundRoot(routerProvider.getInboundRoot(application.getContext()));
	}

	@Override
	public Application createApplication(Context context)
	{
		application = doCreateApplication(context);

		if (routerProvider != null)
			application.setInboundRoot(routerProvider.getInboundRoot(context));

		return application;
	}

	@Override
	public String getAlias()
	{
		return alias;
	}

	public Application getApplication()
	{
		return application;
	}

	@Override
	public HttpContext getContext()
	{
		return null;
	}

	@Override
	public Dictionary<String, Object> getInitParms()
	{
		return null;
	}

	public void unbindRouterProvider(IRouterProvider routerProvider)
	{
		if (this.routerProvider == routerProvider)
			this.routerProvider = null;
	}

	protected void activate(ComponentContext context)
	{
		@SuppressWarnings("unchecked")
		Dictionary<String, Object> properties = context.getProperties();
		alias = (String) properties.get("alias");
	}

	protected Application doCreateApplication(Context context)
	{
		// FIXME Workaround for a bug in Restlet 2.1M7 - the context should be passed to the Application
		// constructor.

		Application app = new Application();
		app.setContext(context);
		return app;
	}

	private String alias;
	private Application application;
	private IRouterProvider routerProvider;
}

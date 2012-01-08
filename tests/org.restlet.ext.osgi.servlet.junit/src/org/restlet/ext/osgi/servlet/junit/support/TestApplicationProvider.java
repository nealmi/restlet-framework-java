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

package org.restlet.ext.osgi.servlet.junit.support;

import java.util.Dictionary;
import java.util.Hashtable;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.ext.osgi.ApplicationProvider;

/**
 * @author bhunt
 * 
 */
public class TestApplicationProvider extends ApplicationProvider
{
	@Override
	public String getAlias()
	{
		return "/";
	}

	@Override
	protected Application doCreateApplication(Context context)
	{
		Application application = new Application();
		application.setContext(context);
		return application;
	}

	@Override
	public Dictionary<String, Object> getInitParms()
	{
		Hashtable<String, Object> initParms = new Hashtable<String, Object>();
		initParms.put("org.restlet.clients", "FILE");
		return initParms;
	}
}

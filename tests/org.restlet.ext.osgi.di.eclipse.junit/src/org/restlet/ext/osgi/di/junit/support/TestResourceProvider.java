/*******************************************************************************
 * Copyright (c) 2010 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/

package org.restlet.ext.osgi.di.junit.support;

import org.restlet.Context;
import org.restlet.ext.osgi.ResourceProvider;
import org.restlet.ext.osgi.di.eclipse.InjectedFinder;
import org.restlet.resource.Finder;

/**
 * @author bhunt
 * 
 */
public class TestResourceProvider extends ResourceProvider
{
	@Override
	public String[] getPaths()
	{
		return new String[] { "/junit/" };
	}

	@Override
	protected Finder createFinder(Context context)
	{
		return new InjectedFinder(context, TestResource.class);
	}
}

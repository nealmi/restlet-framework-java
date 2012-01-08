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

package org.restlet.ext.osgi.di.eclipse;

import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.ext.osgi.internal.di.eclipse.bundle.Activator;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;

/**
 * @author bhunt
 * 
 */
@SuppressWarnings({ "restriction" })
public class InjectedFinder extends Finder
{
	public InjectedFinder(Context context, Class<? extends ServerResource> targetClass)
	{
		super(context, targetClass);
		serviceContext = EclipseContextFactory.getServiceContext(Activator.getContext());
	}

	@Override
	public ServerResource create(Class<? extends ServerResource> clazz, Request request, Response response)
	{
		IEclipseContext childContext = serviceContext.createChild("ResourceContext");
		diLock.lock(); // The lock is required because ContextInjectionFactory.make() is not thread safe

		try
		{
			InjectedResource serverResource = (InjectedResource) ContextInjectionFactory.make(clazz, childContext);
			serverResource.setEclipseContext(childContext);
			return (ServerResource) serverResource;
		}
		finally
		{
			diLock.unlock();
		}
	}

	private IEclipseContext serviceContext;

	private static ReentrantLock diLock = new ReentrantLock();
}

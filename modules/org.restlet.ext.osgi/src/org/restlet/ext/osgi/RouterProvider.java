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

import java.util.HashSet;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * @author bhunt
 * 
 */
public class RouterProvider extends RestletProvider implements IRouterProvider
{
	public void bindDefaultResourceProvider(IResourceProvider resourceProvider)
	{
		defaultRestletProvider = resourceProvider;

		if (router != null)
			router.attachDefault(resourceProvider.getInboundRoot(router.getContext()));
	}

	public void bindDefaultRouterProvider(IRouterProvider routerProvider)
	{
		defaultRestletProvider = routerProvider;

		if (router != null)
			router.attachDefault(routerProvider.getInboundRoot(router.getContext()));
	}

	public void bindDirectoryProvider(IDirectoryProvider directoryProvider)
	{
		directoryProviders.add(directoryProvider);

		if (router != null)
			attachDirectory(directoryProvider);
	}

	public void bindResourceProvider(IResourceProvider resourceProvider)
	{
		resourceProviders.add(resourceProvider);

		if (router != null)
			attachResource(resourceProvider);
	}

	@Override
	public Restlet getInboundRoot(Context context)
	{
		if (router == null)
		{
			router = createRouter(context);

			for (IResourceProvider resourceProvider : resourceProviders)
				attachResource(resourceProvider);

			for (IDirectoryProvider directoryProvider : directoryProviders)
				attachDirectory(directoryProvider);

			if (defaultRestletProvider != null)
				router.attachDefault(defaultRestletProvider.getInboundRoot(context));
		}

		Restlet inboundRoot = super.getInboundRoot(context);
		return inboundRoot != null ? inboundRoot : router;
	}

	public void unbindDefaultResourceProvider(IResourceProvider resourceProvider)
	{
		if (defaultRestletProvider == resourceProvider)
		{
			defaultRestletProvider = null;

			if (router != null)
				router.detach(resourceProvider.getInboundRoot(router.getContext()));
		}
	}

	public void unbindDefaultRouterProvider(IRouterProvider routerProvider)
	{
		if (defaultRestletProvider == routerProvider)
		{
			defaultRestletProvider = routerProvider;

			if (router != null)
				router.detach(routerProvider.getInboundRoot(router.getContext()));
		}
	}

	public void unbindDirectoryProvider(IDirectoryProvider directoryProvider)
	{
		if (directoryProviders.remove(directoryProvider))
		{
			if (router != null)
				router.detach(directoryProvider.getInboundRoot(router.getContext()));
		}
	}

	public void unbindResourceProvider(IResourceProvider resourceProvider)
	{
		if (resourceProviders.remove(resourceProvider))
		{
			if (router != null)
				router.detach(resourceProvider.getInboundRoot(router.getContext()));
		}
	}

	protected Router createRouter(Context context)
	{
		return new Router(context);
	}

	@Override
	protected Restlet getFilteredRestlet()
	{
		return router;
	}

	private void attachDirectory(IDirectoryProvider directoryProvider)
	{
		router.attach(directoryProvider.getPath(), directoryProvider.getInboundRoot(router.getContext()));
	}

	private void attachResource(IResourceProvider resourceProvider)
	{
		for (String path : resourceProvider.getPaths())
			router.attach(path, resourceProvider.getInboundRoot(router.getContext()));
	}

	private Router router;
	private IRestletProvider defaultRestletProvider;
	private HashSet<IResourceProvider> resourceProviders = new HashSet<IResourceProvider>();
	private HashSet<IDirectoryProvider> directoryProviders = new HashSet<IDirectoryProvider>();
}

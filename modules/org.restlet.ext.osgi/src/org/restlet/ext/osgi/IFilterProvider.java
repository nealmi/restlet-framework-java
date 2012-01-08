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

package org.restlet.ext.osgi;

import org.restlet.routing.Filter;

/**
 * This is an OSGi service interface for registering Restlet filters with a router or a resource.
 * Users are expected to register an instance as an OSGi service. It is recommended that
 * you use the {@link org.restlet.ext.osgi.FilterProvider} implementation. You may
 * extend it if necessary, or as a last resort, provide your own implementation of
 * {@link IFilterProvider}.
 * 
 * @author bhunt
 */
public interface IFilterProvider extends IRestletProvider
{
	Filter getFilter();
}

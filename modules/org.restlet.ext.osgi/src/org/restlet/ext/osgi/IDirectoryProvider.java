/*******************************************************************************
 * Copyright (c) 2011 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Bryan Hunt  - initial API and implementation
 *******************************************************************************/

package org.restlet.ext.osgi;

/**
 * This is an OSGi service interface for registering Restlet directories with a router.
 * Users are expected to register an instance as an OSGi service. It is recommended that
 * you use the {@link org.restlet.ext.osgi.DirectoryProvider} implementation. You may
 * extend it if necessary, or as a last resort, provide your own implementation of
 * {@link IDirectoryProvider}.
 * 
 * @author bhunt
 */
public interface IDirectoryProvider extends IRestletProvider
{
	String getPath();
}

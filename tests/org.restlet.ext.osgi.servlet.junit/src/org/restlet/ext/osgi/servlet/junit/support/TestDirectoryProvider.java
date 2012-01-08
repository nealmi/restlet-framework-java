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

package org.restlet.ext.osgi.servlet.junit.support;

import org.restlet.Context;
import org.restlet.ext.osgi.DirectoryProvider;
import org.restlet.resource.Directory;

/**
 * @author bhunt
 * 
 */
public class TestDirectoryProvider extends DirectoryProvider
{
	public TestDirectoryProvider(String rootUri)
	{
		this.rootUri = rootUri;
	}

	@Override
	public String getPath()
	{
		return "/junit/";
	}

	@Override
	protected Directory createDirectory(Context context)
	{
		Directory directory = new Directory(context, rootUri);
		directory.setListingAllowed(true);
		return directory;
	}

	private String rootUri;
}

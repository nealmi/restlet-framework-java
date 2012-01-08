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

import java.util.Dictionary;

import org.osgi.service.component.ComponentContext;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.resource.Directory;

/**
 * @author bhunt
 * 
 */
public class DirectoryProvider extends RestletProvider implements IDirectoryProvider
{
	@Override
	public Restlet getInboundRoot(Context context)
	{
		if (directory == null)
			directory = createDirectory(context);

		Restlet inboundRoot = super.getInboundRoot(context);
		return inboundRoot != null ? inboundRoot : directory;
	}

	@Override
	public String getPath()
	{
		return path;
	}

	protected Directory createDirectory(Context context)
	{
		Directory directory = new Directory(context, rootUri);
		directory.setIndexName(indexName);
		directory.setDeeplyAccessible(deeplyAccessible);
		directory.setModifiable(modifiable);
		directory.setNegotiatingContent(negotiatingContent);
		return directory;
	}

	@Override
	protected Restlet getFilteredRestlet()
	{
		return directory;
	}

	protected void activate(ComponentContext context)
	{
		@SuppressWarnings("unchecked")
		Dictionary<String, Object> properties = context.getProperties();

		path = (String) properties.get("path");
		rootUri = (String) properties.get("rootUri");

		String indexName = (String) properties.get("indexName");

		if (indexName != null)
			this.indexName = indexName;

		Boolean deeplyAccessible = (Boolean) properties.get("deeplyAccessible");

		if (deeplyAccessible != null)
			this.deeplyAccessible = deeplyAccessible;

		Boolean modifiable = (Boolean) properties.get("modifiable");

		if (modifiable != null)
			this.modifiable = modifiable;

		Boolean negotiatingContent = (Boolean) properties.get("negotiatingContent");

		if (negotiatingContent != null)
			this.negotiatingContent = negotiatingContent;
	}

	private Directory directory;

	private String indexName = "index";
	private String path;
	private String rootUri;
	private boolean deeplyAccessible = true;
	private boolean modifiable = false;
	private boolean negotiatingContent = true;
}

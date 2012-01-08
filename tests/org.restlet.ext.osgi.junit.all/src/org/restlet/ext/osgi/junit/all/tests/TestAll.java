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

package org.restlet.ext.osgi.junit.all.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.restlet.ext.osgi.di.junit.tests.TestInjectedFinder;
import org.restlet.ext.osgi.servlet.junit.tests.TestRestletServletService;

/**
 * @author bhunt
 * 
 */

@RunWith(Suite.class)
@SuiteClasses({ TestRestletServletService.class, TestInjectedFinder.class })
public class TestAll
{}

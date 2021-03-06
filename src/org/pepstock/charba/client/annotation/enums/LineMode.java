/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.annotation.enums;

import org.pepstock.charba.client.annotation.LineAnnotation;
import org.pepstock.charba.client.commons.Key;

/**
 * Defines the orientation of {@link LineAnnotation}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum LineMode implements Key
{
	HORIZONTAL("horizontal"),
	VERTICAL("vertical");

	// name value of property
	private final String value;

	/**
	 * Creates a property value to use into native object.
	 * 
	 * @param value value of annotation type property name
	 */
	private LineMode(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}

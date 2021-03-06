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
package org.pepstock.charba.client.commons;

/**
 * This is a standard implementation of a key of property inside a Java script object.<br>
 * The standard is the key name is a String.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StandardKey implements Key {

	private final String value;

	/**
	 * Builds the object with the key value as string
	 * 
	 * @param value value of key as String
	 */
	StandardKey(String value) {
		// stores value
		this.value = value;
		// checks if argument is consistent
		Key.checkIfValid(this);
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

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
package org.pepstock.charba.client.jsinterop.enums;

import org.pepstock.charba.client.jsinterop.commons.Key;

/**
 * The font-style property specifies the font style for a text.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum FontStyle implements Key
{

	/**
	 * The browser displays a normal font style. This is default
	 */
	normal,
	/**
	 * The browser displays thick characters.
	 */
	bold,
	/**
	 * The browser displays an oblique font style
	 */
	oblique,
	/**
	 * The browser displays an italic font style
	 */
	italic;

}
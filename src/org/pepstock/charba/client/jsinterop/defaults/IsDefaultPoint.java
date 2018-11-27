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
package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.PointStyle;

public interface IsDefaultPoint extends IsDefaultArc {

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	 int getRadius();

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point. 
	 */
	 PointStyle getPointStyle();

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	 int getHitRadius();

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered. 
	 */
	 int getHoverRadius();

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	 int getHoverBorderWidth();

}
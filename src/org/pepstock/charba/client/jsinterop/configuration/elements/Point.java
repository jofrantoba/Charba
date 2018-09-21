/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License(Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing(software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND(either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.configuration.elements;

import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.options.Options;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.LineChart
 * @see org.pepstock.charba.client.BubbleChart
 */
public final class Point extends BaseElement {
	
	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public Point(Options options) {
		super(options, options.getElements().getPoint());
	}

	/**
	 * Sets the radius of the point shape. If set to 0(the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(int radius) {
		getOptions().getElements().getPoint().setRadius(radius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public int getRadius() {
		return getOptions().getElements().getPoint().getRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle pointStyle) {
		getOptions().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public PointStyle getPointStyle() {
		return getOptions().getElements().getPoint().getPointStyle();
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(int hitRadius) {
		getOptions().getElements().getPoint().setHitRadius(hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point. 
	 */
	public int getHitRadius() {
		return getOptions().getElements().getPoint().getHitRadius();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(int hoverRadius) {
		getOptions().getElements().getPoint().setHoverRadius(hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered. 
	 */
	public int getHoverRadius() {
		return getOptions().getElements().getPoint().getHoverRadius();
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param hoverBorderWidth the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int hoverBorderWidth) {
		getOptions().getElements().getPoint().setHoverBorderWidth(hoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	public int getHoverBorderWidth() {
		return getOptions().getElements().getPoint().getBorderWidth();
	}

}
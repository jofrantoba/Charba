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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.elements.Arc;
import org.pepstock.charba.client.options.elements.Line;
import org.pepstock.charba.client.options.elements.Point;
import org.pepstock.charba.client.options.elements.Rectangle;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles. When set, these options apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Elements extends ChartContainer {

	private final Point point;

	private final Line line;

	private final Arc arc;

	private final Rectangle rectangle;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		point,
		line,
		rectangle,
		arc
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Elements(AbstractChart<?, ?> chart) {
		super(chart);
		// creates the sub-options objects
		point = new Point(chart);
		line = new Line(chart);
		arc = new Arc(chart);
		rectangle = new Rectangle(chart);
		// set java script objects even if empty
		setValue(Property.point, point);
		setValue(Property.line, line);
		setValue(Property.rectangle, rectangle);
		setValue(Property.arc, arc);
	}

	/**
	 * @return the point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @return the arc
	 */
	public Arc getArc() {
		return arc;
	}

	/**
	 * @return the rectangle
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}
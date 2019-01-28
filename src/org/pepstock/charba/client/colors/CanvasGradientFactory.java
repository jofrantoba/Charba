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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.items.ChartAreaNode;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Utility class which creates a canvas gradient java script object using a Charba gradient.<br>
 * A Charba gradient describes how a GWT canvas gradient must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 * @see com.google.gwt.canvas.dom.client.CanvasGradient
 */
public final class CanvasGradientFactory {

	/**
	 * To avoid any instantiation
	 */
	private CanvasGradientFactory() {
		// do nothing
	}

	/**
	 * Creates a GWT canvas gradient java script object using a Charba gradient and a chart instance which must provide a canvas
	 * instance and its context.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @return a GWT canvas gradient
	 */
	public static CanvasGradient createGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		// checks if chart is initialized
		if (chart.isInitialized()) {
			// creates the result instance
			CanvasGradient result = null;
			// checks if the gradient must be linear oe radial
			if (GradientType.linear.equals(gradient.getType())) {
				// creates a linear
				result = createLinearGradient(chart, gradient);
			} else {
				// creates a radial
				result = createRadialGradient(chart, gradient);
			}
			// checks if result is consistent
			if (result != null) {
				// scans all colors to add to gradient
				for (GradientColor color : gradient.getColors()) {
					// adds colors using offset and color
					result.addColorStop(color.getOffset(), color.getColorAsString());
				}
			}
			// returns result
			return result;
		} else {
			// if here,
			// chart is not initialized
			// then throws an exception
			throw new IllegalArgumentException("Chart is not initialized");
		}
	}

	/**
	 * Creates a linear gradient, an image consisting of a progressive transition between two or more colors along a straight
	 * line.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @return a GWT linear canvas gradient
	 */
	private static CanvasGradient createLinearGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
		// these are the coordinates instances of gradient
		// x0 - the x coordinate of the starting point of the gradient
		// y0 - the y coordinate of the starting point of the gradient
		// x1 - the x coordinate of the ending point of the gradient
		// y1 - the y coordinate of the ending point of the gradient
		double x0 = 0;
		double y0 = 0;
		double x1 = 0;
		double y1 = 0;
		// these are the coordinates instances of scope
		// left - the x coordinate of the starting point of the scope
		// top - the y coordinate of the starting point of the scope
		// right - the x coordinate of the ending point of the scope
		// bottom - the y coordinate of the ending point of the scope
		final double top;
		final double bottom;
		final double left;
		final double right;
		// depending of scope (canvas or chart area)
		if (GradientScope.canvas.equals(gradient.getScope())) {
			// sets the coordinates of scope
			// CANVAS
			top = 0;
			left = 0;
			right = canvas.getOffsetWidth();
			bottom = canvas.getOffsetHeight();
		} else {
			// sets the coordinates of scope
			// CHART AREA
			ChartAreaNode chartArea = chart.getNode().getChartArea();
			top = chartArea.getTop();
			left = chartArea.getLeft();
			bottom = chartArea.getBottom();
			right = chartArea.getRight();
		}
		// checks the orientation requires by gradient
		// and then calculates the coordinates instances of gradient
		if (GradientOrientation.topDown.equals(gradient.getOrientation())) {
			// TOP-DOWN: top --> bottom
			x0 = left;
			y0 = top;
			x1 = left;
			y1 = bottom;
		} else if (GradientOrientation.bottomUp.equals(gradient.getOrientation())) {
			// BOTTOM-UP: bottom --> up
			x0 = left;
			y0 = bottom;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.leftRight.equals(gradient.getOrientation())) {
			// LEFT-RIGHT: left --> right
			x0 = left;
			y0 = top;
			x1 = right;
			y1 = top;
		} else if (GradientOrientation.rightLeft.equals(gradient.getOrientation())) {
			// RIGHT-LEFT: right --> left
			x0 = right;
			y0 = top;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.topRight.equals(gradient.getOrientation())) {
			// DIAGONAL TOP-RIGHT: top(left) --> bottom(right)
			x0 = left;
			y0 = top;
			x1 = right;
			y1 = bottom;
		} else if (GradientOrientation.bottomLeft.equals(gradient.getOrientation())) {
			// DIAGONAL BOTTOM-LEFT: bottom(right) --> top(left)
			x0 = right;
			y0 = bottom;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.topLeft.equals(gradient.getOrientation())) {
			// DIAGONAL TOP-LEFT: top(right) --> bottom(left)
			x0 = right;
			y0 = top;
			x1 = left;
			y1 = bottom;
		} else if (GradientOrientation.bottomRight.equals(gradient.getOrientation())) {
			// DIAGONAL BOTTOM-RIGHT: bottom(left) --> top(right)
			x0 = left;
			y0 = bottom;
			x1 = right;
			y1 = top;
		} else {
			// if here, the scope is invalid for a linear gradient
			// then exception
			throw new IllegalArgumentException("Gradient orientation is wrong [" + gradient.getOrientation() + "]");
		}
		// returns GWT canvas gradient
		// by GWT context 2d method
		return context.createLinearGradient(x0, y0, x1, y1);
	}

	/**
	 * Creates a radial gradient, an image consisting of a progressive transition between two or more colors that radiate from
	 * an origin. Its shape may be a circle or an ellipse.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @return a GWT radial canvas gradient
	 */
	private static CanvasGradient createRadialGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
		// these are the coordinates and radius instances of gradient
		// x0 - the x coordinate of the center of the start circle of the gradient
		// y0 - the y coordinate of the center of the start circle of the gradient
		// r0 - the radius of the start circle of the gradient
		// x1 - the x coordinate of the center of the end circle of the gradient
		// y1 - the y coordinate of the center of the end circle of the gradient
		// r1 - the radius of the end circle of the gradient
		double x0 = 0;
		double y0 = 0;
		double r0 = 0;
		double x1 = 0;
		double y1 = 0;
		double r1 = 0;
		// these are the coordinates of center and radius of scope
		final double centerX;
		final double centerY;
		final double radius;
		// depending of scope (canvas or chart area)
		if (GradientScope.canvas.equals(gradient.getScope())) {
			// CANVAS
			// the center of canvas has the following coordinates:
			// X - the width divided by 2
			// Y - the height divided by 2
			centerX = (canvas.getOffsetWidth() / 2);
			centerY = (canvas.getOffsetHeight() / 2);
			// radius - if max value between width and height, divided by 2
			radius = (Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2);
		} else {
			// gets chart area
			ChartAreaNode chartArea = chart.getNode().getChartArea();
			// CHART
			// the center of canvas has the following coordinates:
			// X - the difference between right and left, divided by 2 plus left
			// Y - the difference between bottom and top, divided by 2 plus top
			centerX = ((chartArea.getRight() - chartArea.getLeft()) / 2) + chartArea.getLeft();
			centerY = ((chartArea.getBottom() - chartArea.getTop()) / 2) + chartArea.getTop();
			// radius - if max value between the difference between right and left and the difference between bottom and top,
			// divided by 2
			radius = (Math.max((chartArea.getRight() - chartArea.getLeft()), (chartArea.getBottom() - chartArea.getTop())) / 2);
		}
		// checks the orientation requires by gradient
		// and then calculates the coordinates instances of gradient
		if (GradientOrientation.inOut.equals(gradient.getOrientation())) {
			// from center to border of scope (canvas or chart area) O-->
			x0 = centerX;
			y0 = centerY;
			r0 = 0;
			x1 = centerX;
			y1 = centerY;
			r1 = radius;
		} else if (GradientOrientation.outIn.equals(gradient.getOrientation())) {
			// from border of scope to center (canvas or chart area) -->O
			x0 = centerX;
			y0 = centerY;
			r0 = radius;
			x1 = centerX;
			y1 = centerY;
			r1 = 0;
		} else {
			// if here, the scope is invalid for a linear gradient
			// then exception
			throw new IllegalArgumentException("Gradient orientation is wrong [" + gradient.getOrientation() + "]");
		}
		// returns GWT canvas gradient
		// by GWT context 2d method
		return context.createRadialGradient(x0, y0, r0, x1, y1, r1);
	}

}

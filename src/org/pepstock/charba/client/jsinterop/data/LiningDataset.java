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
package org.pepstock.charba.client.jsinterop.data;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.enums.CapStyle;
import org.pepstock.charba.client.jsinterop.enums.Fill;
import org.pepstock.charba.client.jsinterop.enums.JoinStyle;
import org.pepstock.charba.client.jsinterop.enums.PointStyle;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Line and Radar charts.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
abstract class LiningDataset extends Dataset {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor,
		borderDash,
		borderDashOffset,
		borderCapStyle,
		borderJoinStyle,
		borderWidth,
		fill,
		lineTension,
		pointBackgroundColor,
		pointBorderColor,
		pointBorderWidth,
		pointRadius,
		pointStyle,
		pointHitRadius,
		pointHoverBackgroundColor,
		pointHoverBorderColor,
		pointHoverBorderWidth,
		pointHoverRadius,
		pointRotation
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the fill color under the line.
	 * 
	 * @return the fill color under the line.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, Defaults.get().getGlobal().getElements().getLine().getBackgroundColorAsString());
	}

	/**
	 * Returns the fill color under the line.
	 * 
	 * @return the fill color under the line.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the line.
	 * 
	 * @return the color of the line.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, Defaults.get().getGlobal().getElements().getLine().getBorderColorAsString());
	}

	/**
	 * Returns the color of the line.
	 * 
	 * @return the color of the line.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the line in pixels.
	 * 
	 * @param borderWidth the width of the line in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * 
	 * @return the width of the line in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, Defaults.get().getGlobal().getElements().getLine().getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.borderDash, ArrayInteger.of(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.borderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.borderCapStyle, CapStyle.class, Defaults.get().getGlobal().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter.
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.class, Defaults.get().getGlobal().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(Fill fill) {
		// checks if is no fill
		if (Fill.nofill.equals(fill)) {
			// sets the boolean value instead of string one
			setValue(Property.fill, false);
		} else {
			// sets value
			setValue(Property.fill, fill);
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public Fill getFill() {
		// gets value type
		ObjectType type = type(Property.fill);
		// if is a boolean FALSE value
		if (ObjectType.Boolean.equals(type)) {
			// returns no fill
			return getValue(Property.fill, false) ? Fill.origin : Fill.nofill;
		}
		// returns the fill object because was not stored as boolean
		return getValue(Property.fill, Fill.class, Defaults.get().getGlobal().getElements().getLine().getFill());
	}

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation
	 * is used.
	 * 
	 * @param lineTension curve tension of the line
	 */
	public void setLineTension(double lineTension) {
		setValue(Property.lineTension, lineTension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic
	 * interpolation is used.
	 * 
	 * @return curve tension of the line.
	 */
	public double getLineTension() {
		return getValue(Property.lineTension, Defaults.get().getGlobal().getElements().getLine().getTension());
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(IsColor... pointBackgroundColor) {
		setValueOrArray(Property.pointBackgroundColor, pointBackgroundColor);
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(String... pointBackgroundColor) {
		setValueOrArray(Property.pointBackgroundColor, pointBackgroundColor);
	}

	/**
	 * Returns the fill color for points.
	 * 
	 * @return list of the fill color for points.
	 */
	public List<String> getPointBackgroundColorAsString() {
		ArrayString array = getValueOrArray(Property.pointBackgroundColor, Defaults.get().getGlobal().getElements().getPoint().getBackgroundColorAsString());
		;
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the fill color for points.
	 * 
	 * @return list of the fill color for points.
	 */
	public List<IsColor> getPointBackgroundColor() {
		return ColorBuilder.parse(getPointBackgroundColorAsString());
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(IsColor... pointBorderColor) {
		setValueOrArray(Property.pointBorderColor, pointBorderColor);
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(String... pointBorderColor) {
		setValueOrArray(Property.pointBorderColor, pointBorderColor);
	}

	/**
	 * Returns the border color for points.
	 * 
	 * @return list of the border color for points.
	 */
	public List<String> getPointBorderColorAsString() {
		ArrayString array = getValueOrArray(Property.pointBorderColor, Defaults.get().getGlobal().getElements().getPoint().getBorderColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the border color for points.
	 * 
	 * @return list of the border color for points.
	 */
	public List<IsColor> getPointBorderColor() {
		return ColorBuilder.parse(getPointBorderColorAsString());
	}

	/**
	 * Sets the width of the point border in pixels.
	 * 
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	public void setPointBorderWidth(int... pointBorderWidth) {
		setValueOrArray(Property.pointBorderWidth, pointBorderWidth);
	}

	/**
	 * Returns the width of the point border in pixels.
	 * 
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.pointBorderWidth, Defaults.get().getGlobal().getElements().getPoint().getBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	public void setPointHitRadius(double... pointHitRadius) {
		setValueOrArray(Property.pointHitRadius, pointHitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
		ArrayDouble array = getValueOrArray(Property.pointHitRadius, Defaults.get().getGlobal().getElements().getPoint().getHitRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(IsColor... pointHoverBackgroundColor) {
		setValueOrArray(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
	}

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(String... pointHoverBackgroundColor) {
		setValueOrArray(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
	}

	/**
	 * Returns the point background color when hovered.
	 * 
	 * @return list of the point background color when hovered.
	 */
	public List<String> getPointHoverBackgroundColorAsString() {
		ArrayString array = getValueOrArray(Property.pointBackgroundColor, Defaults.get().getGlobal().getElements().getPoint().getBackgroundColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the point background color when hovered.
	 * 
	 * @return list of the point background color when hovered.
	 */
	public List<IsColor> getPointHoverBackgroundColor() {
		return ColorBuilder.parse(getPointHoverBackgroundColorAsString());
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(IsColor... pointHoverBorderColor) {
		setValueOrArray(Property.pointHoverBorderColor, pointHoverBorderColor);
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(String... pointHoverBorderColor) {
		setValueOrArray(Property.pointHoverBorderColor, pointHoverBorderColor);
	}

	/**
	 * Returns the point border color when hovered.
	 * 
	 * @return list of the point border color when hovered.
	 */
	public List<String> getPointHoverBorderColorAsString() {
		ArrayString array = getValueOrArray(Property.pointHoverBorderColor, Defaults.get().getGlobal().getElements().getPoint().getBorderColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the point border color when hovered.
	 * 
	 * @return list of the point border color when hovered.
	 */
	public List<IsColor> getPointHoverBorderColor() {
		return ColorBuilder.parse(getPointHoverBorderColorAsString());
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	public void setPointHoverBorderWidth(int... pointHoverBorderWidth) {
		setValueOrArray(Property.pointHoverBorderWidth, pointHoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.pointHoverBorderWidth, Defaults.get().getGlobal().getElements().getPoint().getHoverBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	public void setPointHoverRadius(double... pointHoverRadius) {
		setValueOrArray(Property.pointHoverRadius, pointHoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
		ArrayDouble array = getValueOrArray(Property.pointHoverRadius, Defaults.get().getGlobal().getElements().getPoint().getHoverRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setPointRadius(double... pointRadius) {
		setValueOrArray(Property.pointRadius, pointRadius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
		ArrayDouble array = getValueOrArray(Property.pointRadius, Defaults.get().getGlobal().getElements().getPoint().getRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		setValueOrArray(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return list of the style of the point.
	 */
	public List<PointStyle> getPointStyle() {
		ArrayString array = getValueOrArray(Property.pointStyle, Defaults.get().getGlobal().getElements().getPoint().getPointStyle());
		return ArrayListHelper.list(PointStyle.class, array);
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param pointRotation array of the rotation of the point in degrees.
	 */
	public void setPointRotation(double... pointRotation) {
		setValueOrArray(Property.pointRotation, pointRotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getPointRotation() {
		ArrayDouble array = getValueOrArray(Property.pointRotation, Defaults.get().getGlobal().getElements().getPoint().getRotation());
		return ArrayListHelper.list(array);
	}

}
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
package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayIntegerList;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.ArrayStringList;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class GridLines extends AbstractModel<Scale, IsDefaultGridLines> implements IsDefaultGridLines {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		display,
		circular,
		color,
		borderDash,
		borderDashOffset,
		lineWidth,
		drawBorder,
		drawOnChartArea,
		drawTicks,
		tickMarkLength,
		zeroLineWidth,
		zeroLineColor,
		zeroLineBorderDash,
		zeroLineBorderDashOffset,
		offsetGridLines
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	GridLines(Scale scale, Key childKey, IsDefaultGridLines defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If <code>false</code>, do not display grid lines for this axis.
	 * 
	 * @param display If <code>false</code>, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>false</code>, do not display grid lines for this axis.
	 * 
	 * @return If <code>false</code>, do not display grid lines for this axis.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * If <code>true</code>, gridlines are circular (on radar chart only).
	 * 
	 * @param circular If <code>true</code>, gridlines are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		setValue(Property.circular, circular);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, gridlines are circular (on radar chart only).
	 * 
	 * @return If <code>true</code>, gridlines are circular (on radar chart only).
	 */
	public boolean isCircular() {
		return getValue(Property.circular, getDefaultValues().isCircular());
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		setColor(ArrayString.of(color));
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setColor(ArrayString.of(color));
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	private void setColor(ArrayString color) {
		// checks if there is only 1 element
		if (color.length() == 1) {
			// if 1 element, sets the value as string
			// the same for all lines
			setValue(Property.color, color.get(0));
		} else {
			// otherwise uses an array for all lines
			setArrayValue(Property.color, color);
		}
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public String getColorAsString() {
		// checks if the stored value is a string
		if (ObjectType.String.equals(type(Property.color)) || !has(Property.color)) {
			// returns a string
			return getValue(Property.color, getDefaultValues().getColorAsString());
		} else {
			// returns the default
			return getDefaultValues().getColorAsString();
		}
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<String> getColorsAsString() {
		ArrayStringList result = null;
		// checks if the stored value is a string
		if (ObjectType.String.equals(type(Property.color)) || !has(Property.color)) {
			// creates new list
			result = new ArrayStringList();
			// adds the string value
			result.add(getValue(Property.color, getDefaultValues().getColorAsString()));
		} else if (ObjectType.Array.equals(type(Property.color))) {
			// if array
			// loads the array
			ArrayString array = getArrayValue(Property.color);
			result = ArrayListHelper.list(array);
		}
		return result;
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorsAsString());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayInteger.of(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	private void setBorderDash(ArrayInteger borderDash) {
		setArrayValue(Property.borderDash, borderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.borderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes.
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, getDefaultValues().getBorderDashOffset());
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		setLineWidth(ArrayInteger.of(lineWidth));
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	private void setLineWidth(ArrayInteger lineWidth) {
		// checks if there is only 1 element
		if (lineWidth.length() == 1) {
			// if 1 element, sets the value as string
			// the same for all lines
			setValue(Property.lineWidth, lineWidth.get(0));
		} else {
			// otherwise uses an array for all lines
			setArrayValue(Property.lineWidth, lineWidth);
		}
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines.
	 */
	public int getLineWidth() {
		// checks if the stored value is a number
		if (ObjectType.Number.equals(type(Property.lineWidth)) || !has(Property.lineWidth)) {
			// returns a number
			return getValue(Property.lineWidth, getDefaultValues().getLineWidth());
		} else {
			// returns the default
			return getDefaultValues().getLineWidth();
		}
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines.
	 */
	public List<Integer> getLinesWidth() {
		ArrayIntegerList result = null;
		// checks if the stored value is a number
		if (ObjectType.Number.equals(type(Property.lineWidth)) || !has(Property.lineWidth)) {
			// creates new list
			result = new ArrayIntegerList();
			// adds the number value
			result.add(getValue(Property.lineWidth, getDefaultValues().getLineWidth()));
		} else if (ObjectType.Array.equals(type(Property.lineWidth))) {
			// if array
			// loads the array
			ArrayInteger array = getArrayValue(Property.lineWidth);
			result = ArrayListHelper.list(array);
		}
		return result;
	}

	/***
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		setValue(Property.drawBorder, drawBorder);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	public boolean isDrawBorder() {
		return getValue(Property.drawBorder, getDefaultValues().isDrawBorder());
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are
	 *            multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		setValue(Property.drawOnChartArea, drawOnChartArea);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @return If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you
	 *         need to control which grid lines are drawn.
	 */
	public boolean isDrawOnChartArea() {
		return getValue(Property.drawOnChartArea, getDefaultValues().isDrawOnChartArea());
	}

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		setValue(Property.drawTicks, drawTicks);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	public boolean isDrawTicks() {
		return getValue(Property.drawTicks, getDefaultValues().isDrawTicks());
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		setValue(Property.tickMarkLength, tickMarkLength);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area.
	 */
	public int getTickMarkLength() {
		return getValue(Property.tickMarkLength, getDefaultValues().getTickMarkLength());
	}

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	public void setZeroLineWidth(int zeroLineWidth) {
		setValue(Property.zeroLineWidth, zeroLineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0).
	 */
	public int getZeroLineWidth() {
		return getValue(Property.zeroLineWidth, getDefaultValues().getZeroLineWidth());
	}

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	public void setZeroLineColor(IsColor zeroLineColor) {
		setZeroLineColor(zeroLineColor.toRGBA());
	}

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	public void setZeroLineColor(String zeroLineColor) {
		setValue(Property.zeroLineColor, zeroLineColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0).
	 */
	public String getZeroLineColorAsString() {
		return getValue(Property.zeroLineColor, getDefaultValues().getZeroLineColorAsString());
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0).
	 */
	public IsColor getZeroLineColor() {
		return ColorBuilder.parse(getZeroLineColorAsString());
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDash(int... zeroLineBorderDash) {
		setZeroLineBorderDash(ArrayInteger.of(zeroLineBorderDash));
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	private void setZeroLineBorderDash(ArrayInteger zeroLineBorderDash) {
		setArrayValue(Property.zeroLineBorderDash, zeroLineBorderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public List<Integer> getZeroLineBorderDash() {
		ArrayInteger array = getArrayValue(Property.zeroLineBorderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset) {
		setValue(Property.zeroLineBorderDashOffset, zeroLineBorderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0).
	 */
	public int getZeroLineBorderDashOffset() {
		return getValue(Property.zeroLineBorderDashOffset, getDefaultValues().getZeroLineBorderDashOffset());
	}

	/**
	 * If <code>true</code>, grid lines will be shifted to be between labels. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @param offsetGridLines if <code>true</code>, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		setValue(Property.offsetGridLines, offsetGridLines);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, grid lines will be shifted to be between labels. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @return if <code>true</code>, grid lines will be shifted to be between labels.
	 */
	public boolean isOffsetGridLines() {
		return getValue(Property.offsetGridLines, getDefaultValues().isOffsetGridLines());
	}

}
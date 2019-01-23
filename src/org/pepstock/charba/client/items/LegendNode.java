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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.LegendHitBoxItem.LegendHitBoxItemFactory;
import org.pepstock.charba.client.items.LegendItem.LegendItemFactory;

/**
 * Wrapper of legend node of CHART.JS.<br>
 * This is a wrapper of legend node of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendNode extends BaseBoxNodeItem {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		doughnutMode,
		legendItems,
		legendHitBoxes,
		lineWidths,
		columnWidths
	}

	// factory to create legend items for array container list
	private final LegendItemFactory legendItemFactory = new LegendItemFactory();
	// factory to create legend hit box items for array container list
	private final LegendHitBoxItemFactory legendHitBoxItemFactory = new LegendHitBoxItemFactory();

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public LegendNode(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns if it is in doughnut mode.
	 * 
	 * @return <code>true</code> it is in doughnut mode. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isDoughnutMode() {
		return getValue(Property.doughnutMode, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the list of line widths.
	 * 
	 * @return the list of line widths.
	 */
	public List<Integer> getLineWidths() {
		// gets array from native object
		ArrayInteger array = getArrayValue(Property.lineWidths);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of columns widths.
	 * 
	 * @return the list of columns widths.
	 */
	public List<Integer> getColumnWidths() {
		// gets array from native object
		ArrayInteger array = getArrayValue(Property.columnWidths);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of hit boxes of the legend.
	 * 
	 * @return the list of hit boxes of the legend.
	 */
	public List<LegendHitBoxItem> getHitBoxes() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.legendHitBoxes);
		// returns list
		return ArrayListHelper.unmodifiableList(array, legendHitBoxItemFactory);
	}

	/**
	 * Returns the list of items of the legend.
	 * 
	 * @return the list of items of the legend.
	 */
	public List<LegendItem> getItems() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.legendItems);
		// returns list
		return ArrayListHelper.unmodifiableList(array, legendItemFactory);
	}
}
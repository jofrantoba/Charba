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
package org.pepstock.charba.client.jsinterop.items;

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.jsinterop.options.Scales;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event
 * position.<br>
 * Created and passed by CHART.JS and provide dataset metadata information.<br>
 * Contains all data set items.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DatasetMetaItem extends NativeObjectContainer {
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		hidden,
		type,
		data,
		yAxisID,
		xAxisID
	}
	// instance of dataset items factory.
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();
	
	/**
	 * To avoid any user creation but provides an empty object
	 */
	DatasetMetaItem() {
		// do noting
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public DatasetMetaItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the type of dataset. 
	 * 
	 * @return the type of dataset. If not set or invalid, the default is {@link org.pepstock.charba.client.ChartType#bar}.
	 */
	public Type getType() {
		// gets string value from java script object
		String value = getValue(Property.type, ChartType.bar.name());
		// checks if consistent with out of the box chart types
		Type type = ChartType.get(value);
		// if not, creates new type being a controller.
		if (type == null) {
			// gets type from controllers
			type = Defaults.get().getControllers().getTypeByString(value);
		}
		return type == null ? ChartType.bar : type;
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#BOOLEAN}.
	 */
	public void setHidden(boolean hidden) {
		setValue(Property.hidden, hidden);
	}
	
	/**
	 * Returns the Y axis ID. 
	 * 
	 * @return the Y axis ID. Default is {@link org.pepstock.charba.client.jsinterop.options.Scales#DEFAULT_Y_AXIS_ID}.
	 */
	public String getYAxisID() {
		return getValue(Property.yAxisID, Scales.DEFAULT_Y_AXIS_ID);
	}
	
	/**
	 * Returns the X axis ID. 
	 * 
	 * @return the X axis ID. Default is {@link org.pepstock.charba.client.jsinterop.options.Scales#DEFAULT_X_AXIS_ID}.
	 */
	public String getXAxisID() {
		return getValue(Property.xAxisID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Returns a list of dataset metadata items.
	 * 
	 * @return a list of dataset metadata items.
	 */
	public List<DatasetItem> getDatasets() {
		ArrayObject array = getArrayValue(Property.data);
		return ArrayListHelper.unmodifiableList(array, datasetItemFactory);
	}
	
}
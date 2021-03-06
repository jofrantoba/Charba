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
package org.pepstock.charba.client.impl.plugins;

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.HtmlLegendTextCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.UndefinedValues;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of {@link HtmlLegend#ID} plugin.<br>
 * This is mapping the configuration both default global and per chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegendOptions extends AbstractCursorPointerOptions {

	/**
	 * Default cursor type when the cursor is over the legend, {@link Cursor#POINTER}.
	 */
	public static final Cursor DEFAULT_CURSOR_POINTER = Cursor.POINTER;
	/**
	 * Default maximum legends columns to show.
	 */
	public static final int DEFAULT_MAXIMUM_LEGEND_COLUMNS = Integer.MAX_VALUE;
	/**
	 * Default display if legend must be showed.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		MAXIMUM_LEGEND_COLUMNS("maxLegendColumns"),
		DISPLAY("display"),
		// internal key to store a unique id
		CHARBA_ID("_charbaId");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
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

	// internal count needed to remove callbacks instance from cache
	// from html legened item factory
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// defaults global options instance
	private HtmlLegendDefaultsOptions defaultsOptions;
	// legend text callback instance
	private HtmlLegendTextCallback legendTextCallback = null;

	/**
	 * Builds the object with new java script object setting the default value of plugin.<br>
	 * The global plugin options is used, if exists, as defaults values.
	 */
	public HtmlLegendOptions() {
		this(null, null);
	}

	/**
	 * Builds the object with a chart instance in order to get the right defaults.<br>
	 * If the plugin options have not been set by chart type, it will use the global.
	 * 
	 * @param chart chart type to use to get the default values by chart
	 */
	public HtmlLegendOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(HtmlLegend.ID, HtmlLegend.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds the object with the default global ones
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 */
	HtmlLegendOptions(HtmlLegendDefaultsOptions defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Builds the object with a java script object stored into options and the default global ones
	 * 
	 * @param nativeObject native object into options
	 * @param defaultsOptions default options stored into defaults global
	 */
	HtmlLegendOptions(NativeObject nativeObject, HtmlLegendDefaultsOptions defaultsOptions) {
		super(HtmlLegend.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(HtmlLegend.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
		// checks if CHARBA ID is not already stored
		if (!has(Property.CHARBA_ID)) {
			// stores the id based on a counter
			setValue(Property.CHARBA_ID, COUNTER.getAndIncrement());
		}
	}

	/**
	 * Returns the chart id in order to retrieve the legend text callback for the options.
	 * 
	 * @return identifier of the options or {@link UndefinedValues#INTEGER} if does not exist
	 */
	int getCharbaId() {
		return getValue(Property.CHARBA_ID, UndefinedValues.INTEGER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.AbstractCursorPointerOptions#getCursorPointerAsString()
	 */
	@Override
	final String getCursorPointerAsString() {
		return defaultsOptions.getCursorPointerAsString();
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultsOptions.isDisplay());
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	public HtmlLegendTextCallback getLegendTextCallback() {
		return legendTextCallback != null ? legendTextCallback : defaultsOptions.getLegendTextCallback();
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	public void setLegendTextCallback(HtmlLegendTextCallback legendTextCallback) {
		internalSetLegendTextCallback(legendTextCallback);
		// stores legend callback into factory as cache
		HtmlLegend.FACTORY.store(getCharbaId(), legendTextCallback);
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	void internalSetLegendTextCallback(HtmlLegendTextCallback legendTextCallback) {
		this.legendTextCallback = legendTextCallback;
	}

	/**
	 * Sets the maximum amount of columns of legend.
	 * 
	 * @param maxColumns the maximum amount of columns of legend
	 */
	public void setMaximumLegendColumns(int maxColumns) {
		// checks if max columns is consistent
		setValue(Property.MAXIMUM_LEGEND_COLUMNS, maxColumns < 1 ? DEFAULT_MAXIMUM_LEGEND_COLUMNS : maxColumns);
	}

	/**
	 * Returns the maximum amount of columns of legend.
	 * 
	 * @return he maximum amount of columns of legend
	 */
	public int getMaximumLegendColumns() {
		return getValue(Property.MAXIMUM_LEGEND_COLUMNS, defaultsOptions.getMaximumLegendColumns());
	}

}

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

import org.pepstock.charba.client.jsinterop.Configuration;
import org.pepstock.charba.client.jsinterop.ConfigurationElement;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.jsinterop.commons.ConfigurationLoader;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * CHART.JS entity object to configure the data options of a chart.<br>
 * It contains labels and datasets.
 *  
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
 */
public final class Data extends NativeObjectContainer<NativeData> implements ConfigurationElement{
	
	// maintains the list of datasets
	private final ArrayObjectContainerList<Dataset, NativeDataset> currentDatasets = new ArrayObjectContainerList<Dataset, NativeDataset>();
	
	/**
	 * @param nativeObject
	 */
	public Data() {
		super(new NativeData());
	}
	
	/**
	 * Sets the labels of the data 
	 * @param labels array of labels
	 */
	public void setLabels(String... labels){
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets labels
		setLabels(l);
	}

	/**
	 * Sets the labels of the data
	 * @param labels labels object to manage also multiline labels.
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public void setLabels(Labels labels){
		getNativeObject().setLabels(labels.getArray());
	}

	/**
	 * Returns the labels 
	 * @return the labels
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public Labels getLabels(){
		return Labels.load(getNativeObject().getLabels());
	}

	/**
	 * Sets the labels for X axes of the data 
	 * @param labels array of labels
	 */
	public void setXLabels(String... labels){
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets X labels
		setXLabels(l);
	}

	/**
	 * Sets the labels for X axes of the data 
	 * @param labels labels object to manage also multiline labels.
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public void setXLabels(Labels labels){
		getNativeObject().setXLabels(labels.getArray());
	}

	/**
	 * Returns the labels for X axes
	 * @return the labels for X axes
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public Labels getXLabels(){
		return Labels.load(getNativeObject().getXLabels());
	}

	/**
	 * Sets the labels for Y axes of the data
	 * @param labels array of labels
	 */
	public void setYLabels(String... labels){
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets Y labels
		setYLabels(l);
	}

	/**
	 * Sets the labels for Y axes of the data
	 * @param labels labels object to manage also multiline labels.
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public void setYLabels(Labels labels){
		getNativeObject().setYLabels(labels.getArray());
	}

	/**
	 * Returns the labels for Y axes
	 * @return the labels for Y axes
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public Labels getYLabels(){
		return Labels.load(getNativeObject().getYLabels());
	}

	/**
	 * Sets a set of datasets for chart
	 * @param datasets set of dataset
	 * @see org.pepstock.charba.client.data.Dataset
	 */
	public void setDatasets(Dataset... datasets){
		if (datasets != null) {
			this.currentDatasets.clear();
			this.currentDatasets.addAll(datasets);
			getNativeObject().setDatasets(this.currentDatasets.getArray());
		}
	}

	/**
	 * Returns the list of datasets
	 * @return the list of datasets
	 * @see org.pepstock.charba.client.data.Dataset
	 */
	public List<Dataset> getDatasets(){
		return this.currentDatasets;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.ConfigurationElement#load(org.pepstock.charba.client.jsinterop.Configuration)
	 */
	@Override
	public void load(Configuration configuration) {
		ConfigurationLoader.loadData(configuration, this);
	}
	
	
	
}
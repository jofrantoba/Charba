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
package org.pepstock.charba.client.impl.callbacks;

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendClickEventHandler;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Implementation of legend click handler which disable the capability to hide the last visible dataset.<br>
 * In this way, always a dataset is showed.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class AtLeastOneDatasetHandler implements LegendClickEventHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.LegendClickEventHandler#onClick(org.pepstock.charba.client.events.
	 * LegendClickEvent)
	 */
	@Override
	public void onClick(LegendClickEvent event) {
		// checks consistency of argument
		if (event != null && IsChart.isValid(event.getChart())) {
			// get the chart instance form event
			IsChart chart = event.getChart();
			// checks if legend is related to a dataset or index
			if (event.getItem().getDatasetIndex() != UndefinedValues.INTEGER) {
				// gets the dataset by the legend item and its relation with dataset
				DatasetMetaItem metadata = chart.getDatasetMeta(event.getItem().getDatasetIndex());
				// if the dataset is already hidden
				// checks if there is ONLY 1 visible dataset
				if (!metadata.isHidden() && !checkByDatasetIndex(chart, event.getItem().getDatasetIndex())) {
					return;
				}
				// invokes default click callbacks
				Defaults.get().invokeLegendOnClick(event);
			} else if (event.getItem().getIndex() != UndefinedValues.INTEGER) {
				// gets metadata to know if is hidden
				DatasetMetaItem metadataItem = chart.getDatasetMeta(0);
				// gets all items
				List<DatasetItem> items = metadataItem.getDatasets();
				// gets dataset item
				DatasetItem item = items.get(event.getItem().getIndex());
				if (!item.isHidden() && !checkByIndex(chart, event.getItem().getIndex())) {
					return;
				}
				// invokes default click callbacks
				Defaults.get().invokeLegendOnClick(event);
			}
		}
	}

	/**
	 * Checks if there is at least one dataset visible on top of the dataset index which must be hidden.
	 * 
	 * @param chart chart instance involved in this event.
	 * @param datasetIndex dataset index related to this event.
	 * @return <code>true</code> if there is at least one dataset visible on top of the dataset index which must be hidden.
	 */
	private boolean checkByDatasetIndex(IsChart chart, int datasetIndex) {
		// gets all datasets
		List<Dataset> dss = chart.getData().getDatasets();
		// scans them by for cycle to have the index for retrieving
		// the dataset metadata
		for (int i = 0; i < dss.size(); i++) {
			// gets metadata to know if is hidden
			DatasetMetaItem metadataItem = chart.getDatasetMeta(i);
			// if visible return can be hidden
			// ignoring the dataset index related to event
			if (i != datasetIndex && !metadataItem.isHidden()) {
				return true;
			}
		}
		// if here, no dataset visible
		return false;
	}

	/**
	 * Checks if there is at least one dataset visible on top of the index which must be hidden.
	 * 
	 * @param chart chart instance involved in this event.
	 * @param index index related to this event.
	 * @return <code>true</code> if there is at least one dataset visible on top of the index which must be hidden.
	 */
	private boolean checkByIndex(IsChart chart, int index) {
		// gets all datasets
		List<Dataset> dss = chart.getData().getDatasets();
		// scans them by for cycle to have the index for retrieving
		// the dataset metadata
		for (int i = 0; i < dss.size(); i++) {
			// gets metadata to know if is hidden
			DatasetMetaItem metadataItem = chart.getDatasetMeta(i);
			if (!metadataItem.isHidden()) {
				// gets all items
				List<DatasetItem> items = metadataItem.getDatasets();
				// scans all items
				for (DatasetItem item : items) {
					// if visible return can be hidden
					// ignoring the index related to event
					if (item.getIndex() != index && !item.isHidden()) {
						return true;
					}
				}
			}
		}
		// if here, no dataset visible
		return false;
	}
}

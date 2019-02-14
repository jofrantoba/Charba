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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.datalabels.Context;
import org.pepstock.charba.client.enums.DataType;

/**
 * Utility to calculate the percentage of the value based on the datasets of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Percentage {

	/**
	 * To avoid any instantiation
	 */
	private Percentage() {
		// do nothing
	}

	/**
	 * Computes the percentage of the value based on the data of datasets. If the dataset is composed by {@link DataPoint}, it
	 * uses the {@link DataPoint#getY()} value to compute the percentage. The stack is ignored.
	 * 
	 * @param chart chart instance
	 * @param value current value to be evaluated
	 * @param context data labels plugin context
	 * @return the percentage value, a double between 0 and 1. If the datasets does not contains any data, it will return
	 *         {@link Double#NaN}.
	 */
	public static double compute(AbstractChart<?, ?> chart, double value, Context context) {
		return compute(chart, value, context, false);
	}

	/**
	 * Computes the percentage of the value based on the data of datasets. If the dataset is composed by {@link DataPoint}, it
	 * uses the {@link DataPoint#getY()} value to compute the percentage.
	 * 
	 * @param chart chart instance
	 * @param value current value to be evaluated
	 * @param context data labels plugin context
	 * @param stacked if <code>true</code>, it calculates the total at the same data index (cross datasets), otherwise if
	 *            <code>false</code> it calculates the total at the same dataset index (single dataset)
	 * @return the percentage value, a double between 0 and 1. If the datasets does not contains any data, it will return
	 *         {@link Double#NaN}.
	 */
	public static double compute(AbstractChart<?, ?> chart, double value, Context context, boolean stacked) {
		// creates the total reference
		double total = 0D;
		// checks if stacked
		if (stacked) {
			// scans all datasets
			for (Dataset ds : chart.getData().getDatasets()) {
				// if dataset contains data points
				if (DataType.points.equals(ds.getDataType())) {
					// then dataset is data point container
					// and then cast it
					HasDataPoints hasDataPoints = (HasDataPoints) ds;
					// gets the data points at data index
					DataPoint point = hasDataPoints.getDataPoints().get(context.getIndex());
					// adds the Y value to the total
					total = total + point.getY();
				} else if (DataType.numbers.equals(ds.getDataType())) {
					// if here, the dataset has got data as doubles
					// then it get the double at data index
					double data = ds.getData().get(context.getIndex());
					// adds it to total
					total = total + data;
				}
			}
		} else {
			// if here, the argument of stack is false
			// then it calculates the values inside the dataset
			Dataset ds = chart.getData().getDatasets().get(context.getDatasetIndex());
			// if dataset contains data points
			if (DataType.points.equals(ds.getDataType())) {
				// then dataset is data points container
				// and then cast it
				HasDataPoints hasDataPoints = (HasDataPoints) ds;
				// gets the data points
				List<DataPoint> points = hasDataPoints.getDataPoints();
				// scans data points
				for (DataPoint dataPoint : points) {
					// adds the Y value to the total
					total = total + dataPoint.getY();
				}
			} else if (DataType.numbers.equals(ds.getDataType())) {
				// if here, the dataset has got data as doubles
				// then it get the doubles
				List<Double> data = ds.getData();
				// scans doubles
				for (Double dataValue : data) {
					// adds it to total
					total = total + dataValue;
				}
			}
		}
		// if total is zero
		// means that no datasets are available data
		if (total == 0) {
			// returns NaN
			return Double.NaN;
		}
		// calculates the percentage
		return value / total;
	}

}
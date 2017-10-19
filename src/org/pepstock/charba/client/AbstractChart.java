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
package org.pepstock.charba.client;

import java.util.List;

import org.pepstock.charba.client.callbacks.LegendCallback;
import org.pepstock.charba.client.commons.JsObjectArrayList;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.DatasetMetaItemArray;
import org.pepstock.charba.client.options.BaseOptions;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class of all charts.<br>
 * It contains Chart.js initialization.<br>
 * It is a RequiresResize to be able to resize the chart getting the size of the parent.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <O> Options type for the specific chart 
 * @param <D> Dataset type for the specific chart
 */
public abstract class AbstractChart<O extends BaseOptions, D extends Dataset>  extends Widget implements Chart<O, D>, RequiresResize {
	
	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// constant for WIDTH property
	private static final String WIDTH_PROPERTY = "width";
	// constant for HEIGHT property
	private static final String HEIGHT_PROPERTY = "height";
	// PCT standard for width
    private static final double DEFAULT_PCT_WIDTH = 90D; 
	// reference to Chart.js chart instance
    JavaScriptObject chart = null;

    // chart container
    final DivElement div;
    
    // canvas where Chart.js draws the chart
    final CanvasElement canvas;
   
    // CHart configuration object
    private final Configuration configuration = new Configuration();
    
    // Data element of configuration
    private final Data data = new Data();
    
    private boolean drawOnAttach = true;
    
    private boolean destroyOnDetach = true;
    
    // gets if Canvas is supported
    private final boolean isCanvasSupported = Canvas.isSupported();
 
    /**
     * Initializes HTMl elements (DIV and Canvas).<br>
     * It sets also some default behaviors (width in percentage) for resizing 
     */
	public AbstractChart(){
		// creates DIV
		div = Document.get().createDivElement();
		// sets relative position
		div.getStyle().setPosition(Position.RELATIVE);
		// sets default width values
		div.getStyle().setWidth(DEFAULT_PCT_WIDTH, Unit.PCT);
		div.getStyle().setHeight(100, Unit.PCT);
		// checks if canvas is supported
		if (isCanvasSupported){
			// creates a canvas and add to DIV
			canvas = Document.get().createCanvasElement();
			div.appendChild(canvas);
		} else {
			// creates a header element
			HeadingElement h = Document.get().createHElement(3);
			// to show the error message
			// because canvas is not supported
			h.setInnerText(CANVAS_NOT_SUPPORTED_MESSAGE);
			div.appendChild(h);
			// resets canvas
			canvas = null;
		}
		// sets DIV as element of the widget
		setElement(div);
		// injects Chart.js java script source
		Injector.ensureInjected();
		// creates handler manager
		
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#createHandlerManager()
	 */
	@Override
	protected HandlerManager createHandlerManager() {
		return new ChartHandlerManager(this);
	}

	/**
	 * @return the chart container HTML element
	 */
	public final DivElement getContainer() {
		return div;
	}

	/**
	 * @return the data configuration object
	 */
	public final Data getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#setHeight(java.lang.String)
	 */
	@Override
	public void setHeight(String height) {
		div.getStyle().setProperty(HEIGHT_PROPERTY, height);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#setWidth(java.lang.String)
	 */
	@Override
	public void setWidth(String width) {
		div.getStyle().setProperty(WIDTH_PROPERTY, width);
	}

	/**
	 * @return the drawOnAttach
	 */
	public boolean isDrawOnAttach() {
		return drawOnAttach;
	}
	
	/**
	 * @param drawOnAttach the drawOnAttach to set
	 */
	public void setDrawOnAttach(boolean drawOnAttach) {
		this.drawOnAttach = drawOnAttach;
	}
	
	/**
	 * @return the destroyOnDetach
	 */
	public boolean isDestroyOnDetach() {
		return destroyOnDetach;
	}
	
	/**
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	public void setDestroyOnDetach(boolean destroyOnDetach) {
		this.destroyOnDetach = destroyOnDetach;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		// attaches the widget
		super.onAttach();
		// if is not to be drawn on attach, doesn't draw
		if (isDrawOnAttach()){
			draw();
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onDetach()
	 */
	@Override
	protected void onDetach() {
		// detaches the widget
		super.onDetach();
		// if is not to be destroyed on detach, doesn't destroy
		if (isDestroyOnDetach()){
			destroy();
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ResizeComposite#onResize()
	 */
	@Override
	public final void onResize() {
		// FIXME resize method is missing
	}

	/**
	 * Use this to destroy any chart instances that are created. 
	 * This will clean up any references stored to the chart object within Chart.js, 
	 * along with any associated event listeners attached by Chart.js.
	 */
 	public void destroy(){
		if (chart != null){
			destroyChart();
		}
	}

 	/**
 	 * Use this to stop any current animation loop. 
 	 * This will pause the chart during any current animation frame. 
 	 * Call <code>.render()</code> to re-animate.
 	 * @see AbstractChart#render()
 	 */
	public void stop(){
		if (chart != null){
			stopChart();
		}
	}

	/**
	 * Will clear the chart canvas. 
	 * Used extensively internally between animation frames.
	 */
	public void clear(){
		if (chart != null){
			clearChart();
		}
	}
	
	/**
	 * Reset the chart to it's state before the initial animation. 
	 * A new animation can then be triggered using update.
	 */
	public void reset(){
		if (chart != null){
			resetChart();
		}
	}

	/**
	 * his returns a base 64 encoded string of the chart in it's current state.
	 * @return base 64 image or null if chart is not initialized
	 */
	public String toBase64Image(){
		if (chart != null){
			return toBase64ImageChart();
		}
		return null;
	}

	/**
	 * Returns an HTML string of a legend for that chart. 
	 * The legend is generated from the legendCallback in the options.
	 * @return the HTML legend or null if chart is not initialized
	 * @see LegendCallback
	 */
	public String generateLegend(){
		if (chart != null){
			return generateLegendChart();
		}
		return null;
	}

	/**
	 * Use this to manually resize the canvas element. 
	 * This is run each time the canvas container is resized, but can be called this method manually 
	 * if you change the size of the canvas nodes container element.
	 */
	public void resize(){
		if (chart != null){
			resizeChart();
		}
	}

	/**
	 * Triggers an update of the chart. 
	 * This can be safely called after updating the data object. 
	 * This will update all scales, legends, and then re-render the chart.
	 */
	public void update(){
    	update(null);
	}

	/**
	 * Triggers an update of the chart. 
	 * This can be safely called after updating the data object. 
	 * This will update all scales, legends, and then re-render the chart.
	 * A config object can be provided with additional configuration for the update process. 
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * @param config A config object can be provided with additional configuration for the update process
	 * @see UpdateConfiguration
	 */
	public void update(UpdateConfiguration config){
		if (chart != null){
			updateChart(config == null ? null : config.getObject());
		}
	}

	/**
	 * Triggers a redraw of all chart elements. 
	 * Note, this does not update elements for new data. Use <code>.update()</code> in that case.
	 * @see AbstractChart#update()
	 */
	public void render(){
		render(null);
	}

	/**
	 * Triggers a redraw of all chart elements. 
	 * Note, this does not update elements for new data. Use <code>.update()</code> in that case.
	 * A config object can be provided with additional configuration for the render process. 
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * @param config A config object can be provided with additional configuration for the render process
 	 * @see AbstractChart#update()
 	 * @see UpdateConfiguration
	 */
	public void render(UpdateConfiguration config){
		if (chart != null){
			renderChart(config == null ? null : config.getObject());
		}
	}
	
	public List<DatasetMetaItem> getDatasetMeta(int index){
		if (chart != null && data.getDatasets() != null  && !data.getDatasets().isEmpty() && index < data.getDatasets().size()){ 
			DatasetMetaItemArray array = getChartDatasetMeta(index);
			return array.getItems();
		}
		return new JsObjectArrayList<DatasetMetaItem>();
	}
	
	public DatasetMetaItem getElementAtEvent(ChartNativeEvent event){
		if (chart != null && event != null){
			return getChartElementAtEvent(event);
		}
		return null;
	}

	public List<DatasetMetaItem> getElementsAtEvent(ChartNativeEvent event){
		if (chart != null && event != null){
			DatasetMetaItemArray array = getChartElementsAtEvent(event);
			return array.getItems();
		}
		return new JsObjectArrayList<DatasetMetaItem>();
	}

	public void draw(){
		if (isCanvasSupported){
			BaseOptions options = getOptions();
			Data data = getData();
			configuration.setType(getType());
			configuration.setOptions(options);
			configuration.setData(data);
			drawChart(configuration.getObject());
		}
	}
	
	public String toJSONString(){
		BaseOptions options = getOptions();
		Data data = getData();
		configuration.setType(getType());
		configuration.setOptions(options);
		configuration.setData(data);
		return configuration.getObject().toJSON();
	}

	private native void drawChart(JavaScriptObject config)/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    var canvas = this.@org.pepstock.charba.client.AbstractChart::canvas;
	    if (chart != null) {
	        chart.destroy();
	    }
	    var ctx = canvas.getContext("2d");
	    chart = new $wnd.Chart(ctx, config);
	    this.@org.pepstock.charba.client.AbstractChart::chart = chart;
	}-*/;

	private native void resizeChart()/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    chart.resize();
	}-*/;

	private native void updateChart(JavaScriptObject config)/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    if (config == null){
	    	chart.update();
	    } else {
	    	chart.update(config);
	    }
	}-*/;

	private native void renderChart(JavaScriptObject config)/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    if (config == null){
	    	chart.render();
	    } else {
	    	chart.render(config);
	    }
	}-*/;

	private native void destroyChart()/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    chart.destroy();
	}-*/;

	private native void stopChart()/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    chart.stop();
	}-*/;

	private native void resetChart()/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    chart.reset();
	}-*/;

	private native void clearChart()/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    chart.clear();
	}-*/;

	private native String generateLegendChart()/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    return chart.generateLegend();
	}-*/;

    private native String toBase64ImageChart()/*-{
		var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
	    if (chart != null){
	        return chart.toBase64Image();
	    }
	    return null;
	}-*/;
    
	private native DatasetMetaItem getChartElementAtEvent(JavaScriptObject event)/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
		var items = chart.getElementAtEvent(event);
		if (items.length == 1){
			return items[0];
	    }
	    return null;
	}-*/;

	private native DatasetMetaItemArray getChartElementsAtEvent(JavaScriptObject event)/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
		var items = chart.getElementsAtEvent(event);
		var myItems = new Object();
   		myItems.items = items;
	    return myItems;
	}-*/;

	private native DatasetMetaItemArray getChartDatasetMeta(int datasetIndex)/*-{
	    var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
		var item = chart.getDatasetMeta(datasetIndex);
		console.log(item);
		var myItems = new Object();
   		myItems.items = item.data;
	    return myItems;
	}-*/;

 }
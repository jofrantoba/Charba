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
package org.pepstock.charba.client.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.xml.client.XMLParser;

/**
 * Utility to have an image to apply to canvas of chart in order to add HTML custom information on chart.<br>
 * The utility is leveraging on <code>svg+mxl</code> and <code>foreignObject</code> elements.<br>
 * The HTML content MUST be XML well-formed, when passed as string.<br>
 * This is the SVG XML tree, used:<br>
 * <br>
 * <pre>
 * &lt;svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}"&gt;
 *    &lt;foreignObject width="100%" height="100%"&gt;
 *       &lt;div xmlns="http://www.w3.org/1999/xhtml"&gt;
 *       ... HTML XML well-formed content ....
 *       &lt;/div&gt;
 *    &lt;/foreignObject&gt;
 * &lt;/svg&gt;
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationBuilder {
	
	// cache of the image created
	// K = svg string (with width and height), V = image element
	private static final Map<String, ImageElement> IMAGES = new HashMap<>();
	// template of data image URL to create the image from HTML content
	private static final String TEMPLATE_IMAGE_URL = "data:image/svg+xml;charset=utf-8,<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"{1}\" height=\"{2}\">"+ 
			"<foreignObject width=\"100%\" height=\"100%\"><div xmlns=\"http://www.w3.org/1999/xhtml\">{0}</div></foreignObject></svg>";	
	// token inside the template to use to replace the HTML XML content
	private static final String CONTENT_ARGUMENT_TOKEN = "\\{0\\}";
	// token inside the template to use to replace the width of resulted image
	private static final String WIDTH_ARGUMENT_TOKEN = "\\{1\\}";
	// token inside the template to use to replace the height of resulted image
	private static final String HEIGHT_ARGUMENT_TOKEN = "\\{2\\}";
	
	/**
	 * To avoid any instantiation
	 */
	private AnnotationBuilder() {
		// do nothing
	}

	/**
	 * Creates an image to apply to canvas with the HTML content (passed as GWT element) and width and height of the resulted image.
	 * 
	 * @param htmlXmlContent GWT element which represents the XML content to show
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return an image to apply to canvas
	 */
	public static ImageElement build(Element htmlXmlContent, double width, double height) {
		// creates a DIV wrapper, needed ONLY to get the inner HTML
		// this element don't need for further computation
		DivElement wrapper = Document.get().createDivElement();
		// wraps the XML content
     	wrapper.appendChild(htmlXmlContent);
     	// builds the image and returns it
		return buildWithValidatedContent(wrapper.getInnerHTML(), width, height);
	}

	/**
	 * Creates an image to apply to canvas with the HTML content (MUST BE XML well-formed) and width and height of the resulted image.
	 * 
	 * @param htmlXmlContent HTML content to apply on canvas, in XML well-formed
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return an image to apply to canvas
	 */
	public static ImageElement build(String htmlXmlContent, double width, double height) {
		try {
			// parse the XML document checking if correct
			XMLParser.parse(htmlXmlContent);
			// returns it
			return buildWithValidatedContent(htmlXmlContent, width, height);
		} catch (Exception e) {
			// the content is not XML well-formed
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Creates the images to be returned, managing the cache of them.
	 * 
	 * @param validatedhtmlXmlContent HTML content, already checked and well-formed
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return an image to apply to canvas
	 */
	private static ImageElement buildWithValidatedContent(String validatedhtmlXmlContent, double width, double height) {
		// copies the template string
		String result = TEMPLATE_IMAGE_URL;
		// replaces the html content into the template
		result = result.replaceAll(CONTENT_ARGUMENT_TOKEN, validatedhtmlXmlContent);
		// replaces the width into the template
		result = result.replaceAll(WIDTH_ARGUMENT_TOKEN, String.valueOf(width));
		// replaces the height into the template
		result = result.replaceAll(HEIGHT_ARGUMENT_TOKEN, String.valueOf(height));
		// the result is a key of images created
		// if already built
		if (IMAGES.containsKey(validatedhtmlXmlContent)) {
			// returns the existing one
			return IMAGES.get(validatedhtmlXmlContent);
		}
		// creates the image with URL
		Image image = new Image(validatedhtmlXmlContent);
		// transforms it into an element
		ImageElement element = ImageElement.as(image.getElement());
		// stores into cache
		IMAGES.put(validatedhtmlXmlContent, element);
		// returns it
		return element;
	}
}
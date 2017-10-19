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
package org.pepstock.charba.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event which is fired when a request to create new application has been submitted.<br>
 * When a new application is created, is mandatory to indicate the first administrator of the application.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AddHandlerEvent extends GwtEvent<AddHandlerEventHandler> {

	public static final Type<AddHandlerEventHandler> TYPE = new Type<AddHandlerEventHandler>();
	
	private final Type<? extends EventHandler> type;

	public AddHandlerEvent(Type<? extends EventHandler> type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public Type<? extends EventHandler> getType() {
		return type;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<AddHandlerEventHandler> getAssociatedType() {
		return TYPE;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(AddHandlerEventHandler handler) {
		handler.onAdd(this);
	}

}
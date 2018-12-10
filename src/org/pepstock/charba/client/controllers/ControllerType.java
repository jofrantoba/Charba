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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;

public final class ControllerType implements Type {
	
	private final String type;
	
	private final ScaleType scaleType;
	
	public ControllerType(String type) {
		this(type, ScaleType.multi);
	}

	public ControllerType(String type, ScaleType scaleType) {
		this.type = type;
		this.scaleType = scaleType;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.Key#name()
	 */
	@Override
	public String name() {
		return type;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Type#scaleType()
	 */
	@Override
	public ScaleType scaleType() {
		return scaleType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Type) {
			Type objType = (Type)obj;
			if (objType.name() != null && type != null) {
				return type.equals(objType.name());
			}
		}
		return false;
	}
	

}

/*
 * Copyright (c) 2001-2016 Convertigo SA.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 *
 * $URL$
 * $Author$
 * $Revision$
 * $Date$
 */

package com.twinsoft.convertigo.beans.mobile.components;

import java.util.Iterator;

import com.twinsoft.convertigo.beans.core.ITagsProperty;

public abstract class UIControlAttr extends UIAttribute implements ITagsProperty {
	
	private static final long serialVersionUID = -1131663200389122563L;

	public UIControlAttr() {
		super();
	}

	@Override
	public UIControlAttr clone() throws CloneNotSupportedException {
		UIControlAttr cloned = (UIControlAttr) super.clone();
		return cloned;
	}
	
	@Override
	public String getAttrValue() {
		if (isEnabled()) {
			StringBuilder sb = new StringBuilder();
			Iterator<UIComponent> it = getUIComponentList().iterator();
			while (it.hasNext()) {
				UIComponent component = (UIComponent)it.next();
				if (component instanceof UIControlAttrValue) {
					sb.append(component.computeTemplate()).append(";");
				}
			}
			return sb.toString();
		}
		return "";
	}

	@Override
	public String computeTemplate() {
		if (isEnabled()) {
			String attr = getAttrName();
			String val = getAttrValue();
			
	        if (attr.isEmpty() || val.isEmpty()) { 
	        	return "";
	        }
	        else {
	        	return (" "+attr+"=\""+val+"\"");
	        }
		}
		return "";
	}
	
}
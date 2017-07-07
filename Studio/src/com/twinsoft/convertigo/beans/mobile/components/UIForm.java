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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class UIForm extends UIElement {

	private static final long serialVersionUID = 4115423575037640826L;

	public UIForm() {
		super("form");
	}

	@Override
	public UIForm clone() throws CloneNotSupportedException {
		UIForm cloned = (UIForm) super.clone();
		return cloned;
	}

	public String getFormGroupName() {
		return "form"+ this.priority;
	}

	@Override
	protected StringBuilder initAttributes() {
		StringBuilder attributes = super.initAttributes();
		String formGroupName = getFormGroupName();
		attributes.append(" [formGroup]=").append("\"").append(formGroupName).append("\"")
					.append(" novalidate");
		return attributes;
	}	
	
	@Override
	protected String computeConstructor() {
		if (isEnabled()) {
			StringBuilder constructors = new StringBuilder();
			StringBuilder validator = new StringBuilder();
			Iterator<UIComponent> it = getUIComponentList().iterator();
			while (it.hasNext()) {
				UIComponent component = (UIComponent)it.next();
				if (component instanceof UIElement) {
					String constructor = ((UIElement)component).computeConstructor();
					constructors.append(constructors.length() > 0 && !constructor.isEmpty() ? ",":"").append(constructor);
				} else if (component instanceof UIFormCustomValidator) {
					if (validator.length() == 0) {// only one
						validator.append(((UIFormCustomValidator)component).computeConstructor());
					}
				}
			}
			
			StringBuilder cartridge = new StringBuilder();
			cartridge.append("/**").append(System.lineSeparator())
						.append("\t\t * "+ getName()).append(System.lineSeparator());
			for (String commentLine : getComment().split(System.lineSeparator())) {
				cartridge.append("\t\t *   ").append(commentLine).append(System.lineSeparator());
			}
			cartridge.append("\t\t */").append(System.lineSeparator());
			
			StringBuilder sb = new StringBuilder();
			String formGroupName = getFormGroupName();
			sb.append(cartridge);
			sb.append("\t\tthis."+ formGroupName + " = new FormGroup({");
			sb.append(constructors).append(System.lineSeparator()).append("\t\t}");
			sb.append(validator.length() > 0 ? ",":"").append(validator).append(");");
			sb.append(System.lineSeparator());
			return sb.toString();
		}
		return "";
	}
	
	@Override
	protected String computeFunction() {
		if (isEnabled()) {
			StringBuilder sb = new StringBuilder();
			Iterator<UIComponent> it = getUIComponentList().iterator();
			while (it.hasNext()) {
				UIComponent component = (UIComponent)it.next();
				if (component instanceof UIElement) {
					sb.append(((UIElement)component).computeFunction());
				} else if (component instanceof UIFormCustomValidator) {
					sb.append(((UIFormValidator)component).computeFunction());
				}
			}
			return sb.toString();
		}
		return "";
	}
	
	@Override
	public void computeScripts(JSONObject jsonScripts) {
		if (isEnabled()) {
			try {
				String imports = jsonScripts.getString("imports");
				
				String search = "import { FormGroup, FormControl, Validators}\tfrom '@angular/forms';";
				if (imports.indexOf(search) == -1) {
					imports += search + System.lineSeparator();
				}
				jsonScripts.put("imports", imports);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	
			String declaration = "public "+ getFormGroupName() + " :  FormGroup;" + System.lineSeparator();
			try {
				String declarations = jsonScripts.getString("declarations") + declaration;
				jsonScripts.put("declarations", declarations);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			String constructor = computeConstructor() + System.lineSeparator();
			try {
				String constructors = jsonScripts.getString("constructors") + constructor;
				jsonScripts.put("constructors", constructors);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			String function = computeFunction() + System.lineSeparator();
			try {
				String constructors = jsonScripts.getString("constructors") + function;
				jsonScripts.put("constructors", constructors);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			super.computeScripts(jsonScripts);
		}
	}
}
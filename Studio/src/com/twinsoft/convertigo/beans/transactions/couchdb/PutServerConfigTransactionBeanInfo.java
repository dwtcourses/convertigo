/*
 * Copyright (c) 2001-2011 Convertigo SA.
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
 * $URL: $
 * $Author: $
 * $Revision: $
 * $Date: $
 */
package com.twinsoft.convertigo.beans.transactions.couchdb;

import java.beans.PropertyDescriptor;

import com.twinsoft.convertigo.beans.core.MySimpleBeanInfo;

public class PutServerConfigTransactionBeanInfo extends MySimpleBeanInfo {

	public PutServerConfigTransactionBeanInfo() {
		try {
			beanClass = PutServerConfigTransaction.class;
			additionalBeanClass = AbstractServerTransaction.class;

			resourceBundle = getResourceBundle("res/PutServerConfigTransaction");
			
			displayName = getExternalizedString("display_name");
			shortDescription = getExternalizedString("short_description");

			iconNameC16 = "/com/twinsoft/convertigo/beans/transactions/couchdb/images/servertransaction_color_16x16.png";
			iconNameC32 = "/com/twinsoft/convertigo/beans/transactions/couchdb/images/servertransaction_color_32x32.png";
			
			properties = new PropertyDescriptor[2];
			
			properties[0] = new PropertyDescriptor("u_section", beanClass, "getU_section", "setU_section");
			properties[0].setDisplayName(getExternalizedString("property.u_section.display_name"));
			properties[0].setShortDescription(getExternalizedString("property.u_section.short_description"));
			
			properties[1] = new PropertyDescriptor("u_key", beanClass, "getU_key", "setU_key");
			properties[1].setDisplayName(getExternalizedString("property.u_key.display_name"));
			properties[1].setShortDescription(getExternalizedString("property.u_key.short_description"));

		}
		catch(Exception e) {
			com.twinsoft.convertigo.engine.Engine.logBeans.error("Exception with bean info; beanClass=" + beanClass.toString(), e);
		}
	}

}
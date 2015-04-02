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


public abstract class AbstractDatabaseTransaction extends AbstractCouchDbTransaction {

	private static final long serialVersionUID = -2445580621995795014L;
	
	public static final CouchDbParameter var_database = CouchDbParameter.Path_database;
	
	public AbstractDatabaseTransaction() {
		super();
	}
	
	private String u_db = "";

	@Override
	public AbstractDatabaseTransaction clone() throws CloneNotSupportedException {
		AbstractDatabaseTransaction clonedObject =  (AbstractDatabaseTransaction) super.clone();
		return clonedObject;
	}
	
	public String getTargetDatabase() {
		return getConnector().getTargetDatabase(this);
	}

	public String getU_db() {
		return u_db;
	}

	public void setU_db(String u_db) {
		this.u_db = u_db;
	}
}
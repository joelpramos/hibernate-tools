/*
 * Hibernate Tools, Tooling for your Hibernate Projects
 * 
 * Copyright 2017-2020 Red Hat, Inc.
 *
 * Licensed under the GNU Lesser General Public License (LGPL), 
 * version 2.1 or later (the "License").
 * You may not use this file except in compliance with the License.
 * You may read the licence in the 'lgpl.txt' file in the root folder of 
 * project or obtain a copy at
 *
 *     http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hibernate.tool.jdbc2cfg.Views;

import java.sql.SQLException;

import org.hibernate.boot.Metadata;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Table;
import org.hibernate.tool.api.metadata.MetadataDescriptorFactory;
import org.hibernate.tools.test.util.HibernateUtil;
import org.hibernate.tools.test.util.JdbcUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * @author max
 * @author koen
 */
public class TestCase {
	
	private Metadata metadata;
	
	@Before
	public void setUp() {
		JdbcUtil.createDatabase(this);
		metadata = MetadataDescriptorFactory
				.createReverseEngineeringDescriptor(null, null)
				.createMetadata();
	}
	
	@After
	public void tearDown() {
		JdbcUtil.dropDatabase(this);
	}
	
	@Test
	public void testViewAndSynonyms() throws SQLException {
		
		PersistentClass classMapping = metadata.getEntityBinding("Basicview");
		Assert.assertNotNull(classMapping);
	
		classMapping = metadata.getEntityBinding("Weirdname");
		Assert.assertTrue("If this is not-null synonyms apparently work!",classMapping==null);

		// get comments
		Table table = HibernateUtil.getTable(metadata, "BASIC");
		Assert.assertEquals("a basic comment", table.getComment());
		Assert.assertEquals("a solid key", table.getPrimaryKey().getColumn(0).getComment());
		
		table = HibernateUtil.getTable(metadata, "MULTIKEYED");
		Assert.assertNull(table.getComment());
		Assert.assertNull(table.getColumn(0).getComment());
		
	}


}

/**
* Copyright (C) 2020 - Telemedi Team
*
* This file is part of Telemedi.
*
* Telemedi is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* Telemedi is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package co.avaldes.telemedi.database

import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Infrastructure
import javax.sql.DataSource
import org.jetbrains.exposed.sql.Database

/**
 * Initializes the Exposed database connection using a [DataSource] provided by micronaut.
 *
 * @author Alejandro Valdes
 */
@Context
@Infrastructure
class DatabaseConnection(dataSource: DataSource) {
  init
  {
    Database.connect(dataSource).apply {
      useNestedTransactions = true
    }
  }
}

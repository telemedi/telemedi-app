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

package co.avaldes.telemedi.database.common

import org.jetbrains.exposed.dao.id.UUIDTable

open class TagTable(name: String = "") : UUIDTable(name) {
  val name = varchar("name", NAME_LENGTH)
  val description = varchar("description", DESCRIPTION_LENGTH)

  companion object {
    private const val NAME_LENGTH = 30
    private const val DESCRIPTION_LENGTH = 200
  }
}

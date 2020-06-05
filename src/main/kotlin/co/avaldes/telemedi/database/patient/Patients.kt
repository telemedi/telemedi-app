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

package co.avaldes.telemedi.database.patient

import co.avaldes.telemedi.common.IdentityDocumentType
import co.avaldes.telemedi.common.Sex
import co.avaldes.telemedi.database.common.enum.PGEnum
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime

object Patients : UUIDTable() {
  val name: Column<String> = varchar("name", 60)
  val identityDocument: Column<String> = varchar("identity_document", 30)
  val identityDocumentType = customEnumeration(
    "identity_type",
    "identity_document_type_enum",
    { value -> IdentityDocumentType.valueOf(value as String) },
    { PGEnum("identity_document_type_enum", it) })
  val birthDate = datetime("birth_date")
  val sex = customEnumeration(
    "sex",
    "sex_enum",
    { value -> Sex.valueOf(value as String) },
    { PGEnum("sex_enum", it) })
}

object PatientsTags : Table() {
  val patient = reference("patient",
    Patients)
  val tag = reference("tag", PatientTags)
}

object PatientsNotes : Table() {
  val patient = reference("patient",
    Patients)
  val note = reference("note", PatientNotes)
}

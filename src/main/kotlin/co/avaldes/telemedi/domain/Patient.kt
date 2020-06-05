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

package co.avaldes.telemedi.domain

import co.avaldes.telemedi.database.patient.Patients
import co.avaldes.telemedi.database.patient.PatientsNotes
import co.avaldes.telemedi.database.patient.PatientsTags
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Patient(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : UUIDEntityClass<Patient>(Patients)
  var name by Patients.name
  var identityDocument by Patients.identityDocument
  var identityDocumentType by Patients.identityDocumentType
  var birthDate by Patients.birthDate
  var sex by Patients.sex
  var tags by PatientTag via PatientsTags
  var notes by PatientNote via PatientsNotes
}

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

package co.avaldes.telemedi

import co.avaldes.telemedi.database.patient.PatientNotes
import co.avaldes.telemedi.database.patient.PatientTags
import co.avaldes.telemedi.database.patient.Patients
import io.micronaut.runtime.Micronaut
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object Application {
  @JvmStatic
  fun main(args: Array<String>) {
    Micronaut.build()
      .packages("co.avaldes.telemedi")
      .mainClass(Application.javaClass)
      .start()

    transaction {
      addLogger(StdOutSqlLogger)
      exec("""
        DO $$
        BEGIN
          IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'sex_enum') THEN
            CREATE TYPE sex_enum AS ENUM ('Female', 'Male');
          END IF;
        END
        $$;
      """.trimIndent())
      exec("""
        DO $$
        BEGIN
          IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'identity_document_type_enum') THEN
            CREATE TYPE identity_document_type_enum AS ENUM ('National', 'Other', 'Passport');
          END IF;
        END
        $$;
      """.trimIndent())
      SchemaUtils.create(PatientNotes)
      SchemaUtils.create(PatientTags)
      SchemaUtils.create(Patients)
    }
  }
}

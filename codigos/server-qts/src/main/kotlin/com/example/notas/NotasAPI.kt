import com.example.notas.CalculadoraDeNotas
import com.example.notas.notas
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing


fun Application.NotasAPI() {
    routing {
        // Cria um novo aluno
        post("/aluno/{nomeAluno}") {
            val nomeAluno = call.parameters["nomeAluno"] ?: ""
            if (notas[nomeAluno] == null) {
                val aluno = CalculadoraDeNotas(nomeAluno, emptyList())
                // Adiciona a lista vazia ao mapa, em vez de sobrescrever:
                notas[nomeAluno] = aluno.notas
                call.respondText("Aluno $nomeAluno criado com sucesso!", status = HttpStatusCode.Created)
            } else {
                call.respondText("Aluno já existe.", status = HttpStatusCode.Conflict)
            }
        }

        // Adiciona nota a um aluno existente
        post("/aluno/{nomeAluno}/nota/{nota}") {
            val nomeAluno = call.parameters["nomeAluno"] ?: ""
            val nota = call.parameters["nota"]?.toDoubleOrNull() ?: 0.0

            // Recupera a lista de notas atual do aluno:
            val notasAluno = notas[nomeAluno]
            if (notasAluno != null) {
                // Adiciona a nova nota à lista existente:
                val notasAtualizadas = notasAluno + nota
                notas[nomeAluno] = notasAtualizadas
                call.respondText("Nota adicionada com sucesso. Notas do aluno: $notasAtualizadas", status = HttpStatusCode.OK)
            } else {
                call.respondText("Aluno não encontrado.", status = HttpStatusCode.NotFound)
            }
        }

        // Consulta a média do aluno
        get("/aluno/{nomeAluno}/media") {
            val nomeAluno = call.parameters["nomeAluno"] ?: ""
            val aluno = notas[nomeAluno]
            if (aluno != null) {
                val media = CalculadoraDeNotas(nomeAluno, aluno).calcularMedia()
                call.respondText(media, status = HttpStatusCode.OK)
            } else {
                call.respondText("Aluno não encontrado.", status = HttpStatusCode.NotFound)
            }
        }

        // Consulta a maior nota do aluno
        get("/aluno/{nomeAluno}/maior") {
            val nomeAluno = call.parameters["nomeAluno"] ?: ""
            val aluno = notas[nomeAluno]
            if (aluno != null) {
                val maiorNota = CalculadoraDeNotas(nomeAluno, aluno).encontrarMaiorNota()
                call.respondText(maiorNota, status = HttpStatusCode.OK)
            } else {
                call.respondText("Aluno não encontrado.", status = HttpStatusCode.NotFound)
            }
        }

        // Consulta a menor nota do aluno
        get("/aluno/{nomeAluno}/menor") {
            val nomeAluno = call.parameters["nomeAluno"] ?: ""
            val aluno = notas[nomeAluno]
            if (aluno != null) {
                val menorNota = CalculadoraDeNotas(nomeAluno, aluno).encontrarMenorNota()
                call.respondText(menorNota, status = HttpStatusCode.OK)
            } else {
                call.respondText("Aluno não encontrado.", status = HttpStatusCode.NotFound)
            }
        }

        get("/aluno/{nomeAluno}/notas") {
            val nomeAluno = call.parameters["nomeAluno"] ?: ""
            val aluno = notas[nomeAluno]
            if (aluno != null) {
                call.respondText("Notas de $nomeAluno: $aluno", status = HttpStatusCode.OK)
            } else {
                call.respondText("Aluno não encontrado.", status = HttpStatusCode.NotFound)
            }
        }

        // Consulta as notas de todos os alunos
        get("/alunos/notas") {
            if (notas.isEmpty()) {
                call.respondText("Não há alunos cadastrados.", status = HttpStatusCode.OK)
            } else {
                val notasDeTodosAlunos = notas.mapValues {
                    "Notas de ${it.key}: ${it.value}" // Removemos o .value aqui
                }
                call.respondText(
                    notasDeTodosAlunos.toString(),
                    status = HttpStatusCode.OK
                )
            }
        }
    }
}
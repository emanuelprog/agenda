<template>
  <div>
    <h1>Agendas</h1>
    <ul>
      <li v-for="agenda in agendas" :key="agenda.id">
        {{ agenda.titulo }} - {{ agenda.descricao }}
        <button @click="selecionarAgenda(agenda)">Editar</button>
        <button @click="excluirAgenda(agenda.id)">Excluir</button>
      </li>
    </ul>

    <div>
      <h2>{{ modoEdicao ? "Editar Agenda" : "Criar Agenda" }}</h2>
      <form @submit.prevent="salvarAgenda">
        <div>
          <label for="titulo">Título:</label>
          <input v-model="agendaAtual.titulo" id="titulo" required />
        </div>
        <div>
          <label for="descricao">Descrição:</label>
          <input v-model="agendaAtual.descricao" id="descricao" required />
        </div>
        <button type="submit">
          {{ modoEdicao ? "Salvar Alterações" : "Criar Agenda" }}
        </button>
        <button type="button" @click="limparFormulario">Cancelar</button>
      </form>
    </div>
  </div>
</template>

<script>
import AgendaService from "@/services/AgendaService";

export default {
  data() {
    return {
      agendas: [],
      agendaAtual: {
        id: null,
        titulo: "",
        descricao: "",
      },
      modoEdicao: false,
    };
  },
  created() {
    this.buscarAgendas();
  },
  methods: {
    buscarAgendas() {
      AgendaService.buscarAgendas()
        .then((response) => {
          this.agendas = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    salvarAgenda() {
      if (this.modoEdicao) {
        AgendaService.editarAgenda(this.agendaAtual.id, this.agendaAtual)
          .then(() => {
            this.buscarAgendas();
            this.limparFormulario();
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        AgendaService.criarAgenda(this.agendaAtual)
          .then(() => {
            this.buscarAgendas();
            this.limparFormulario();
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
    selecionarAgenda(agenda) {
      this.agendaAtual = { ...agenda };
      this.modoEdicao = true;
    },
    excluirAgenda(id) {
      AgendaService.deletarAgenda(id)
        .then(() => {
          this.buscarAgendas();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    limparFormulario() {
      this.agendaAtual = {
        id: null,
        titulo: "",
        descricao: "",
      };
      this.modoEdicao = false;
    },
  },
};
</script>

<style></style>

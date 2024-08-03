import axios from 'axios';

const API_URL = 'http://localhost:8081/api/agenda';

class AgendaService {
    buscarAgendas() {
        return axios.get(API_URL);
    }

    criarAgenda(event) {
        return axios.post(API_URL, event);
    }

    editarAgenda(id, event) {
        return axios.put(`${API_URL}/${id}`, event);
    }

    deletarAgenda(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new AgendaService();
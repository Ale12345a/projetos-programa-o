
import React, { useState, useEffect } from 'react';

import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  FlatList,
  StyleSheet,
  SafeAreaView,
  Keyboard
} from 'react-native';

import AsyncStorage from '@react-native-async-storage/async-storage';

export default function App() {

  const [tarefas, setTarefas] = useState([]);
  const [nova, setNova] = useState('');
  const [carregado, setCarregado] = useState(false);

  // Carregar tarefas guardadas
  useEffect(() => {
    const carregar = async () => {
      try {
        const dados = await AsyncStorage.getItem('tarefas');

        if (dados !== null) {
          setTarefas(JSON.parse(dados));
        }
      } catch (erro) {
        console.log('Erro ao carregar tarefas:', erro);
      } finally {
        setCarregado(true);
      }
    };

    carregar();
  }, []);

  // Guardar tarefas sempre que são alteradas
  useEffect(() => {
    if (!carregado) return;

    const guardar = async () => {
      try {
        await AsyncStorage.setItem(
          'tarefas',
          JSON.stringify(tarefas)
        );
      } catch (erro) {
        console.log('Erro ao guardar tarefas:', erro);
      }
    };

    guardar();
  }, [tarefas, carregado]);

  // Adicionar tarefa
  const adicionarTarefa = () => {
    if (nova.trim() === '') return;

    const tarefa = {
      id: Date.now().toString(),
      titulo: nova.trim(),
      concluida: false
    };

    setTarefas(atual => [...atual, tarefa]);

    setNova('');
    Keyboard.dismiss();
  };

  // Marcar tarefa como concluída
  const concluirTarefa = (id) => {
    setTarefas(atual =>
      atual.map(tarefa =>
        tarefa.id === id
          ? { ...tarefa, concluida: !tarefa.concluida }
          : tarefa
      )
    );
  };

  // Eliminar tarefa
  const eliminarTarefa = (id) => {
    setTarefas(atual =>
      atual.filter(tarefa => tarefa.id !== id)
    );
  };

  const concluidas = tarefas.filter(
    tarefa => tarefa.concluida
  ).length;

  // Apresentação de cada tarefa
  const renderTarefa = ({ item }) => (
    <View style={styles.tarefa}>

      <TouchableOpacity
        style={styles.tarefaConteudo}
        onPress={() => concluirTarefa(item.id)}
      >
        <View style={[
          styles.checkbox,
          item.concluida && styles.checkboxAtivo
        ]}>
          {item.concluida && (
            <Text style={styles.check}>✓</Text>
          )}
        </View>

        <Text style={[
          styles.tituloTarefa,
          item.concluida && styles.textoConcluido
        ]}>
          {item.titulo}
        </Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => eliminarTarefa(item.id)}
      >
        <Text style={styles.eliminar}>✕</Text>
      </TouchableOpacity>

    </View>
  );

  return (
    <SafeAreaView style={styles.container}>

      <View style={styles.conteudo}>

        <Text style={styles.subtitulo}>
          ORGANIZA A TUA VIDA
        </Text>

        <Text style={styles.titulo}>
          Minhas Tarefas
        </Text>

        <Text style={styles.estatisticas}>
          {tarefas.length - concluidas} pendentes · {concluidas} concluídas
        </Text>

        {/* Campo para adicionar tarefas */}
        <View style={styles.adicionarContainer}>

          <TextInput
            style={styles.input}
            placeholder="Adicionar uma tarefa..."
            placeholderTextColor="#999"
            value={nova}
            onChangeText={setNova}
            onSubmitEditing={adicionarTarefa}
            returnKeyType="done"
          />

          <TouchableOpacity
            style={styles.botaoAdicionar}
            onPress={adicionarTarefa}
          >
            <Text style={styles.botaoTexto}>+</Text>
          </TouchableOpacity>

        </View>

        {/* Lista de tarefas */}
        <FlatList
          data={tarefas}
          keyExtractor={item => item.id}
          renderItem={renderTarefa}
          contentContainerStyle={styles.lista}
          ListEmptyComponent={
            <Text style={styles.vazio}>
              Ainda não tens tarefas. Adiciona a primeira!
            </Text>
          }
        />

      </View>

    </SafeAreaView>
  );
}

const styles = StyleSheet.create({

  container: {
    flex: 1,
    backgroundColor: '#F4F6F8'
  },

  conteudo: {
    flex: 1,
    padding: 25,
    paddingTop: 45
  },

  subtitulo: {
    fontSize: 12,
    fontWeight: 'bold',
    color: '#648B72',
    letterSpacing: 2
  },

  titulo: {
    fontSize: 30,
    fontWeight: 'bold',
    color: '#202A25',
    marginTop: 10
  },

  estatisticas: {
    fontSize: 14,
    color: '#777',
    marginTop: 8,
    marginBottom: 30
  },

  adicionarContainer: {
    flexDirection: 'row',
    marginBottom: 25,
    gap: 10
  },

  input: {
    flex: 1,
    backgroundColor: '#FFFFFF',
    padding: 15,
    borderRadius: 12,
    fontSize: 15
  },

  botaoAdicionar: {
    width: 52,
    backgroundColor: '#548565',
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center'
  },

  botaoTexto: {
    fontSize: 28,
    color: '#FFFFFF'
  },

  lista: {
    paddingBottom: 30
  },

  tarefa: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: '#FFFFFF',
    padding: 17,
    borderRadius: 12,
    marginBottom: 12
  },

  tarefaConteudo: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center'
  },

  checkbox: {
    width: 23,
    height: 23,
    borderWidth: 2,
    borderColor: '#548565',
    borderRadius: 7,
    marginRight: 13,
    alignItems: 'center',
    justifyContent: 'center'
  },

  checkboxAtivo: {
    backgroundColor: '#548565'
  },

  check: {
    color: '#FFFFFF',
    fontWeight: 'bold'
  },

  tituloTarefa: {
    fontSize: 15,
    color: '#303A34',
    flexShrink: 1
  },

  textoConcluido: {
    textDecorationLine: 'line-through',
    color: '#999'
  },

  eliminar: {
    color: '#D65A5A',
    fontSize: 19,
    paddingLeft: 15
  },

  vazio: {
    textAlign: 'center',
    color: '#999',
    marginTop: 40,
    fontSize: 14
  }

});


import threading
import time
import random


# Função que simula o download de um ficheiro
def download(ficheiro):

    tempo = random.randint(1, 3)

    print(f"A iniciar {ficheiro} (vai demorar {tempo}s)...")

    # Simular o tempo de download
    time.sleep(tempo)

    print(f"{ficheiro} concluído!")


# Lista de ficheiros
ficheiros = [f"ficheiro_{i}" for i in range(1, 6)]

# Lista para guardar as threads
threads = []

# Registar o início
inicio = time.time()

print("A iniciar downloads...\n")

# Criar e iniciar as threads
for ficheiro in ficheiros:

    t = threading.Thread(
        target=download,
        args=(ficheiro,)
    )

    t.start()

    threads.append(t)


# Esperar que todas as threads terminem
for t in threads:
    t.join()


# Calcular tempo total
fim = time.time()

print("\nTodos os downloads terminaram!")

print(f"Tempo total: {fim - inicio:.2f} segundos")

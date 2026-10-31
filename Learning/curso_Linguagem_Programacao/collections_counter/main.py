
from collections import Counter
import timeit


# Versão manual
def contar_ocorrencias(lista):
    resultado = {}

    for item in lista:
        if item in resultado:
            resultado[item] += 1
        else:
            resultado[item] = 1

    return resultado


# Versão otimizada com Counter
def contar_ocorrencias_counter(lista):
    return Counter(lista)


# Criar lista de teste
lista = [1] * 10000 + [2] * 10000 + [3] * 10000


# Executar versão manual
ocorrencias_manual = contar_ocorrencias(lista)

# Executar versão Counter
ocorrencias_counter = contar_ocorrencias_counter(lista)


# Apresentar resultados
print("=== RESULTADOS ===")

print("\nVersão manual:")
print(ocorrencias_manual)

print("\nVersão Counter:")
print(ocorrencias_counter)


# Comparação de desempenho
tempo_manual = timeit.timeit(
    lambda: contar_ocorrencias(lista),
    number=100
)

tempo_counter = timeit.timeit(
    lambda: contar_ocorrencias_counter(lista),
    number=100
)


print("\n=== DESEMPENHO ===")

print(f"Tempo manual: {tempo_manual:.6f} segundos")
print(f"Tempo Counter: {tempo_counter:.6f} segundos")

if tempo_counter < tempo_manual:
    melhoria = tempo_manual / tempo_counter

    print(f"\nCounter foi {melhoria:.2f}x mais rápido!")
else:
    print("\nA versão manual foi mais rápida neste teste.")

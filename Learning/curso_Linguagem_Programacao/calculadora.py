
print("=== CALCULADORA SIMPLES ===")

# Pedir os valores ao utilizador
num1 = float(input("Introduza o primeiro número: "))
num2 = float(input("Introduza o segundo número: "))

# Apresentar as operações disponíveis
print("\nEscolha uma operação:")
print("1 - Adição (+)")
print("2 - Subtração (-)")
print("3 - Multiplicação (*)")
print("4 - Divisão (/)")

operacao = input("Opção: ")

# Executar a operação escolhida
if operacao == "1":
    resultado = num1 + num2
    print("Resultado:", resultado)

elif operacao == "2":
    resultado = num1 - num2
    print("Resultado:", resultado)

elif operacao == "3":
    resultado = num1 * num2
    print("Resultado:", resultado)

elif operacao == "4":
    if num2 != 0:
        resultado = num1 / num2
        print("Resultado:", resultado)
    else:
        print("Erro: não é possível dividir por zero.")

else:
    print("Opção inválida.")

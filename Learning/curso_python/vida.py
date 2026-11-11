# Número de Vida

date = input("Introduza a sua data de nascimento: ")

while len(date) > 1:
    total = 0

    for digit in date:
        total += int(digit)

    date = str(total)

print(date)
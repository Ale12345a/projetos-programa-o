# Sudoku

sudoku = []

# Ler as 9 linhas e verificar se os dados são válidos
for i in range(9):
    row = input("Introduza uma linha: ")

    if len(row) != 9 or not row.isdigit() or set(row) != set("123456789"):
        print("No")
        break

    sudoku.append(row)

else:
    valid = True

    # Verificar as colunas
    for col in range(9):
        column = [sudoku[row][col] for row in range(9)]

        if set(column) != set("123456789"):
            valid = False
            break

    # Verificar os subquadrados 3x3
    if valid:
        for row in range(0, 9, 3):
            for col in range(0, 9, 3):
                square = []

                for i in range(3):
                    for j in range(3):
                        square.append(sudoku[row + i][col + j])

                if set(square) != set("123456789"):
                    valid = False
                    break

            if not valid:
                break

    if valid:
        print("Yes")
    else:
        print("No")
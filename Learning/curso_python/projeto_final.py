from random import randrange


def display_board(board):
    # Desenha o tabuleiro
    print("+-------+-------+-------+")

    for row in board:
        print("|       |       |       |")
        print("|", end="")
        for cell in row:
            print("   " + cell + "   |", end="")
        print()
        print("|       |       |       |")
        print("+-------+-------+-------+")


def enter_move(board):
    # Pede ao utilizador uma jogada válida
    while True:
        try:
            move = int(input("Digite seu movimento: "))
        except ValueError:
            print("Introduza um número inteiro!")
            continue

        if move < 1 or move > 9:
            print("Escolha um número entre 1 e 9!")
            continue

        row = (move - 1) // 3
        column = (move - 1) % 3

        if board[row][column] == "X" or board[row][column] == "O":
            print("Essa casa já está ocupada!")
        else:
            board[row][column] = "O"
            break


def make_list_of_free_fields(board):
    # Cria uma lista com as coordenadas das casas livres
    free_fields = []

    for row in range(3):
        for column in range(3):
            if board[row][column] != "X" and board[row][column] != "O":
                free_fields.append((row, column))

    return free_fields


def victory_for(board, sign):
    # Verifica as linhas
    for row in range(3):
        if board[row][0] == sign and board[row][1] == sign and board[row][2] == sign:
            return True

    # Verifica as colunas
    for column in range(3):
        if board[0][column] == sign and board[1][column] == sign and board[2][column] == sign:
            return True

    # Verifica a diagonal principal
    if board[0][0] == sign and board[1][1] == sign and board[2][2] == sign:
        return True

    # Verifica a diagonal secundária
    if board[0][2] == sign and board[1][1] == sign and board[2][0] == sign:
        return True

    return False


def draw_move(board):
    # Escolhe aleatoriamente uma casa livre
    free_fields = make_list_of_free_fields(board)

    if free_fields:
        index = randrange(len(free_fields))
        row, column = free_fields[index]
        board[row][column] = "X"


# Programa principal

board = [
    ["1", "2", "3"],
    ["4", "5", "6"],
    ["7", "8", "9"]
]

# O computador começa sempre no centro
board[1][1] = "X"

display_board(board)

while True:

    # Jogada do utilizador
    enter_move(board)
    display_board(board)

    if victory_for(board, "O"):
        print("Você ganhou!")
        break

    # Verifica se ainda existem casas livres
    if not make_list_of_free_fields(board):
        print("Empate!")
        break

    # Jogada do computador
    draw_move(board)
    display_board(board)

    if victory_for(board, "X"):
        print("O computador ganhou!")
        break

    if not make_list_of_free_fields(board):
        print("Empate!")
        break


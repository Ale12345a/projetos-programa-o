# Encontre uma palavra!

word = input("Introduza a palavra: ")
text = input("Introduza a combinação de caracteres: ")

word = word.lower()
text = text.lower()

position = 0

for char in word:
    position = text.find(char, position)

    if position == -1:
        print("No")
        break

    position += 1
else:
    print("Yes")

# Palíndromos

text = input("Introduza um texto: ")

text = text.replace(" ", "").lower()

if text and text == text[::-1]:
    print("É palíndromo")
else:
    print("Não é palíndromo")

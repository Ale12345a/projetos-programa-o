
# Anagramas

text1 = input("Introduza o primeiro texto: ")
text2 = input("Introduza o segundo texto: ")

text1 = text1.replace(" ", "").lower()
text2 = text2.replace(" ", "").lower()

if text1 and text2 and sorted(text1) == sorted(text2):
    print("Anagramas")
else:
    print("Não são anagramas")

# Insira o texto que deseja criptografar.
text = input("Enter message: ")

# Insira um valor válido de shift (repetir até o êxito).
shift = 0

while shift == 0:
    try:    
        shift = int(input("Enter the cipher shift value (1..25): "))
        if shift not in range(1,26):
        	raise ValueError
    except ValueError:
        shift = 0
    if shift == 0:
        print("Incorrect shift value!")

cipher = ''

for char in text:
    # É uma letra?
    if char.isalpha():
        # Shift its code.
        code = ord(char) + shift
        # Localizar o código da primeira letra (maiúscula ou minúscula)
        if char.isupper():
            first = ord('A')
        else:
            first = ord('a')
        # Fazer correção.
        code -= first
        code %= 26
        # Anexar o caractere codificado na mensagem.
        cipher += chr(first + code)
    else:
        # Anexar o caractere original na mensagem.
        cipher += char

print(cipher)
    
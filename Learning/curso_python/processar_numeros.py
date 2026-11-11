
# Processador de números.

line = input("Enter a line of numbers - separate them with spaces: ")
strings = line.split()
total = 0

if not strings:
    print("No numbers provided.")
else:
    try:
        for substr in strings:
            total += float(substr)
        print("The total is:", total)
    except ValueError:
        print(substr, "is not a number.")

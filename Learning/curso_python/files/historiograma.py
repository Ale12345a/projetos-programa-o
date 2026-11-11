filename = input("Enter the name of the file: ")

try:
    with open(filename, 'r') as file:
        text = file.read()

    frequency = {}

    for char in text.lower():
        if 'a' <= char <= 'z':
            if char in frequency:
                frequency[char] += 1
            else:
                frequency[char] = 1

    for char in sorted(frequency):
        print(char, "->", frequency[char])

except IOError as e:
    print("I/O error occurred:", e)
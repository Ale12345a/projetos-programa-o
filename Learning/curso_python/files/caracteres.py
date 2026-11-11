filename = input("Enter the name of the file: ")

try:
    with open(filename, 'r') as file:
        text = file.read().lower()

    frequency = {}

    for char in text:
        if 'a' <= char <= 'z':
            if char in frequency:
                frequency[char] += 1
            else:
                frequency[char] = 1

    sorted_frequency = sorted(
        frequency.items(),
        key=lambda item: item[1],
        reverse=True
    )

    with open(filename + '.hist', 'w') as file:
        for char, count in sorted_frequency:
            file.write(char + ' -> ' + str(count) + '\n')

except IOError as e:
    print("I/O error occurred:", e)
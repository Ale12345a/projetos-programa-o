filename = input("Enter the name of the file: ")

students = {}

try:
    with open(filename, 'r') as file:
        for line in file:
            first_name, last_name, points = line.split()

            full_name = first_name + ' ' + last_name
            points = float(points)

            if full_name in students:
                students[full_name] += points
            else:
                students[full_name] = points

    for name in sorted(students):
        print(name, students[name])

except IOError as e:
    print("I/O error occurred:", e)
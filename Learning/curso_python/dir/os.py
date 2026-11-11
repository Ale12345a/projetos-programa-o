import os

def find(path, dir):
    for entry in os.listdir(path):
        new_path = os.path.join(path, entry)

        if os.path.isdir(new_path):
            if entry == dir:
                print(os.path.abspath(new_path))

            find(new_path, dir)


find("./tree", "python")